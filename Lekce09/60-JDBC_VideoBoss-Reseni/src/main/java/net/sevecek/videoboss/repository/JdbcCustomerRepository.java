package net.sevecek.videoboss.repository;

import java.sql.*;
import java.util.*;
import org.mariadb.jdbc.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.lookup.*;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.*;
import net.sevecek.videoboss.entity.*;

@Repository("customerRepository")
public class JdbcCustomerRepository implements CustomerRepository {

    private JdbcTemplate odesilacDotazu;
    private RowMapper<Customer> prevodnik;

    public JdbcCustomerRepository() {
        try {
            MariaDbDataSource konfiguraceDatabaze = new MariaDbDataSource();
            konfiguraceDatabaze.setUserName("student");
            konfiguraceDatabaze.setPassword("password");
            konfiguraceDatabaze.setUrl("jdbc:mysql://localhost:3306/VideoBoss");

            odesilacDotazu = new JdbcTemplate(konfiguraceDatabaze);
            prevodnik = BeanPropertyRowMapper.newInstance(Customer.class);
        } catch (SQLException e) {
            throw new DataSourceLookupFailureException("Nepodarilo se vytvorit DataSource", e);
        }
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> zakaznici = odesilacDotazu.query(
                "SELECT ID, Firstname, Lastname, Address, Deleted, Version" +
                        " FROM Customers WHERE Deleted = FALSE",
                prevodnik);
        return zakaznici;
    }

    @Override
    public Customer findById(Long id) {
        Customer zakaznik = odesilacDotazu.queryForObject(
                "SELECT ID, Firstname, Lastname, Address, Deleted, Version" +
                        " FROM Customers WHERE ID=?",
                prevodnik,
                id);
        return zakaznik;
    }

    @Override
    public Customer save(Customer zaznamKUlozeni) {
        if (zaznamKUlozeni.getId() != null) {
            return update(zaznamKUlozeni);
        } else {
            return insert(zaznamKUlozeni);
        }
    }

    private Customer insert(Customer zaznamKPridani) {
        Customer zakaznik = clone(zaznamKPridani);
        GeneratedKeyHolder drzakNaVygenerovanyKlic = new GeneratedKeyHolder();
        String sql = "INSERT INTO customers (Firstname, Lastname, Address, Version) " +
                "VALUES (?, ?, ?, ?)";
        odesilacDotazu.update((Connection con) -> {
                    PreparedStatement prikaz = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    prikaz.setString(1, zakaznik.getFirstName());
                    prikaz.setString(2, zakaznik.getLastName());
                    prikaz.setString(3, zakaznik.getAddress());
                    prikaz.setInt(4, zakaznik.getVersion());
                    return prikaz;
                },
                drzakNaVygenerovanyKlic);
        zakaznik.setId(drzakNaVygenerovanyKlic.getKey().longValue());
        return zakaznik;
    }

    private Customer update(Customer zaznamKUlozeni) {
        zaznamKUlozeni = clone(zaznamKUlozeni);
        odesilacDotazu.update(
                "UPDATE customers SET Firstname = ?, Lastname = ?, Address = ?, Version = Version + 1 WHERE id = ? AND Version = ?",
                zaznamKUlozeni.getFirstName(),
                zaznamKUlozeni.getLastName(),
                zaznamKUlozeni.getAddress(),
                zaznamKUlozeni.getId(),
                zaznamKUlozeni.getVersion());
        zaznamKUlozeni.setVersion(zaznamKUlozeni.getVersion() + 1);
        return zaznamKUlozeni;
    }

    @Override
    public void delete(Customer customer) {
        odesilacDotazu.update(
                "UPDATE customers SET Deleted = TRUE, Version = Version + 1 WHERE id = ? AND Version = ?",
                customer.getId(),
                customer.getVersion());
    }

    private Customer clone(Customer zakaznik) {
        Customer copieZakaznika = new Customer(
                zakaznik.getId(),
                zakaznik.getFirstName(),
                zakaznik.getLastName(),
                zakaznik.getAddress(),
                zakaznik.isDeleted(),
                zakaznik.getVersion());
        return copieZakaznika;
    }
}