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
        Long pocetZakazniku = odesilacDotazu.queryForObject(
                "select count(*) from Customers", Long.class);
        System.out.println("V databazi je " + pocetZakazniku + " zakazniku");
    }

}
