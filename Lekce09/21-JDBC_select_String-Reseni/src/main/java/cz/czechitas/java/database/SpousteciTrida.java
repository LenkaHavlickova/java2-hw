package cz.czechitas.java.database;

import java.sql.*;
import org.mariadb.jdbc.*;
import org.springframework.jdbc.core.*;

public class SpousteciTrida {

    public static void main(String[] args) throws SQLException {
        MariaDbDataSource konfiguraceDatabaze = new MariaDbDataSource();
        konfiguraceDatabaze.setUserName("student");
        konfiguraceDatabaze.setPassword("password");
        konfiguraceDatabaze.setUrl("jdbc:mysql://localhost:3306/VideoBoss");

        JdbcTemplate odesilacDotazu = new JdbcTemplate(konfiguraceDatabaze);
        String jmenoJednohoZakaznika = odesilacDotazu.queryForObject(
                "select concat(FirstName, ' ', LastName) from Customers where ID = 22", String.class);
        System.out.println("V databazi je zakaznik cislo 22 se jmenem " + jmenoJednohoZakaznika);
    }

}
