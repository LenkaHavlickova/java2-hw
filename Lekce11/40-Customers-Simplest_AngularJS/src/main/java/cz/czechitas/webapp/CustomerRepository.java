package cz.czechitas.webapp;

import java.sql.*;
import java.util.*;
import org.mariadb.jdbc.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.lookup.*;
import org.springframework.jdbc.support.*;

public class CustomerRepository {

    private MariaDbDataSource konfiguraceDatabaze;
    private JdbcTemplate odesilacDotazu;
    private RowMapper<Customer> prevodnik;

    public CustomerRepository() {
        try {
            konfiguraceDatabaze = new MariaDbDataSource();
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
                "SELECT ID, Firstname, Lastname, Address, Deleted, Version" +
                        " FROM Customers WHERE Deleted = FALSE",
                prevodnik);
        return zakaznici;
    }

    public Customer findOne(Long id) {
        Customer zakaznik = odesilacDotazu.queryForObject(
                "SELECT ID, Firstname, Lastname, Address, Deleted, Version" +
                        " FROM Customers WHERE ID=?",
                prevodnik,
                id);
        return zakaznik;
    }

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

    public void delete(Long id, int version) {
        odesilacDotazu.update(
                "UPDATE customers SET Deleted = TRUE, Version = Version + 1 WHERE id = ? AND Version = ?",
                id,
                version);
    }

    private Customer clone(Customer zakaznik) {
        Customer copieZakaznika = new Customer();
        copieZakaznika.setId(zakaznik.getId());
        copieZakaznika.setFirstName(zakaznik.getFirstName());
        copieZakaznika.setLastName(zakaznik.getLastName());
        copieZakaznika.setAddress(zakaznik.getAddress());
        copieZakaznika.setDeleted(zakaznik.isDeleted());
        copieZakaznika.setVersion(zakaznik.getVersion());
        return copieZakaznika;
    }
}