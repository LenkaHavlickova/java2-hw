package cz.czechitas.java.database;

import java.sql.*;
import java.util.*;
import org.mariadb.jdbc.*;
import org.springframework.jdbc.core.*;

public class SpousteciTrida {

    public static void main(String[] args) throws SQLException {
        MariaDbDataSource konfiguraceDatabaze = new MariaDbDataSource();
        konfiguraceDatabaze.setUserName("student");
        konfiguraceDatabaze.setPassword("password");
        konfiguraceDatabaze.setUrl("jdbc:mysql://localhost:3306/VideoBoss");

        JdbcTemplate odesilacDotazu = new JdbcTemplate(konfiguraceDatabaze);
        RowMapper<Customer> prevodnik = BeanPropertyRowMapper.newInstance(Customer.class);

        System.out.println("Zakaznik 10:");
        Customer zakaznik = odesilacDotazu.queryForObject(
                "select ID, Firstname, Lastname, Address, Deleted, Version" +
                        " from Customers where ID=?",
                prevodnik,
                10L);
        System.out.println(zakaznik);

        System.out.println("Vsichni zakaznici:");
        List<Customer> zakaznici = odesilacDotazu.query(
                "select ID, Firstname, Lastname, Address, Deleted, Version" +
                        " from Customers",
                prevodnik);
        for (Customer jedenZakaznik : zakaznici) {
            System.out.println(jedenZakaznik);
        }
    }

}
