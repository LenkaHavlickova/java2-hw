package cz.czechitas.dailyplanet;

import java.sql.*;
import java.util.*;
import org.mariadb.jdbc.*;
import org.springframework.jdbc.core.*;

public class SpousteciTrida {

    public static void main(String[] args) throws SQLException {
        MariaDbDataSource konfiguraceDatabaze = new MariaDbDataSource();
        konfiguraceDatabaze.setUserName("student");
        konfiguraceDatabaze.setPassword("password");
        konfiguraceDatabaze.setUrl("jdbc:mysql://localhost:3306/DailyPlanet");

        JdbcTemplate odesilacDotazu = new JdbcTemplate(konfiguraceDatabaze);
        RowMapper<Clanek> prevodnik = BeanPropertyRowMapper.newInstance(Clanek.class);

        System.out.println("Clanek 1:");
        Clanek clanek = odesilacDotazu.queryForObject(
                "SELECT ID, Nazev, Autor, Datum FROM Clanky" +
                        " where ID=?",
                prevodnik,
                1L);
        System.out.println(clanek);

        System.out.println("Vsechny clanky:");
        List<Clanek> clanky = odesilacDotazu.query(
                "SELECT ID, Nazev, Autor, Datum FROM Clanky",
                prevodnik);
        for (Clanek jedenClanek : clanky) {
            System.out.println(jedenClanek);
        }
    }

}
