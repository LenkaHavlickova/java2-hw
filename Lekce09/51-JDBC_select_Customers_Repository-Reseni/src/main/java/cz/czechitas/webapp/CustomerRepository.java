package cz.czechitas.webapp;

import java.sql.*;
import java.util.*;
import org.mariadb.jdbc.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.lookup.*;
import org.springframework.stereotype.*;

@Repository
public class CustomerRepository {

    private JdbcTemplate odesilacDotazu;
    private RowMapper<Customer> prevodnik;

    public CustomerRepository() {
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


    public List<Customer> findAll() {
        List<Customer> zakaznici = odesilacDotazu.query(
                "select ID, Firstname, Lastname, Address, Deleted, Version" +
                        " from Customers",
                prevodnik);
        return zakaznici;
    }

    public Customer findById(Long id) {
        Customer zakaznik = odesilacDotazu.queryForObject(
                "select ID, Firstname, Lastname, Address, Deleted, Version" +
                        " from Customers where ID=?",
                prevodnik,
                id);
        return zakaznik;
    }

    public Customer save(Customer zaznamKUlozeni) {
        // TODO
        throw new UnsupportedOperationException("Zatim nenaprogramovano");
    }

    public void deleteById(Long id) {
        // TODO
        throw new UnsupportedOperationException("Zatim nenaprogramovano");
    }
}