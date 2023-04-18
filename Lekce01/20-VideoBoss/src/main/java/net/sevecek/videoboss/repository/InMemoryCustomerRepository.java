package net.sevecek.videoboss.repository;

import java.util.*;
import javax.annotation.*;
import org.springframework.stereotype.*;
import net.sevecek.videoboss.entity.*;

@Repository("customerRepository")
public class InMemoryCustomerRepository implements CustomerRepository {

    private long idSequence = 100L;
    private Map<Long, Customer> customers = new TreeMap<>();

    @PostConstruct
    public void initialize() {
        save(new Customer("Charles", "Dickens", "London"));
        save(new Customer("Mark", "Twain", "Missouri"));
        save(new Customer("Victor", "Hugo", "Paris"));
        save(new Customer("Jára", "Cimrman", "Liptákov"));
        save(new Customer("Bertold", "Brecht", "Berlin"));
        save(new Customer("Umberto", "Eco", "Piedmont"));
        save(new Customer("Franz", "Kafka", "Praha"));
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> all = new ArrayList<>(customers.size());
        for (Customer customer : customers.values()) {
            if (!customer.isDeleted()) {
                all.add(customer);
            }
        }
        return all;
    }

    @Override
    public Customer findById(Long id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new NullPointerException("No Customer " + id);
        }
        return customer;
    }

    @Override
    public Customer save(Customer zaznamKUlozeni) {
        if (zaznamKUlozeni.getId() != null) {
            return update(zaznamKUlozeni);
        } else {
            return insert(zaznamKUlozeni);
        }
    }

    private Customer insert(Customer customer) {
        long id = generateNextIdFromSequence();
        customer.setId(id);
        customers.put(id, customer);
        return customer;
    }

    private Customer update(Customer newCustomer) {
        Customer originalCustomer = customers.get(newCustomer.getId());
        if (originalCustomer.getVersion() != newCustomer.getVersion()) {
            throw new RuntimeException("Optimistic lock collision: Customer [ID=" + newCustomer.getId() + "]. Customer in database was concurrently changed by another user");
        }
        originalCustomer.setFirstName(newCustomer.getFirstName());
        originalCustomer.setLastName(newCustomer.getLastName());
        originalCustomer.setAddress(newCustomer.getAddress());
        originalCustomer.setDeleted(newCustomer.isDeleted());
        originalCustomer.setVersion(originalCustomer.getVersion() + 1);
        return originalCustomer;
    }

    @Override
    public void delete(Customer customer) {
        Customer originalCustomer = customers.get(customer.getId());
        if (originalCustomer.getVersion() != customer.getVersion()) {
            throw new RuntimeException("Optimistic lock collision: Customer [ID=" + customer.getId() + "]. Customer in database was concurrently changed by another user");
        }
        originalCustomer.setVersion(originalCustomer.getVersion() + 1);
        originalCustomer.setDeleted(true);
    }

    private long generateNextIdFromSequence() {
        return ++idSequence;
    }
}
