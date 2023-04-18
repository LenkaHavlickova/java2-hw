package net.sevecek.videoboss.repository;

import java.util.List;
import net.sevecek.videoboss.entity.*;

public interface CustomerRepository {

    List<Customer> findAll();

    Customer findById(Long id);

    Customer save(Customer customer);

    void delete(Customer customer);
}
