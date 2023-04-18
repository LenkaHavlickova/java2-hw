package cz.czechitas;

import java.sql.*;
import java.util.*;
import org.mariadb.jdbc.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.*;

public class SpousteciTrida {

    public static void main(String[] args) throws SQLException {
        MariaDbDataSource nastaveniDatabaze = new MariaDbDataSource();
        nastaveniDatabaze.setUserName("student");
        nastaveniDatabaze.setPassword("password");
        nastaveniDatabaze.setUrl("jdbc:mysql://localhost:3306/dailyplanet");

        JdbcTemplate odesilacDotazu = new JdbcTemplate(nastaveniDatabaze);

        RowMapper<Clanek> prevodnik = BeanPropertyRowMapper.newInstance(Clanek.class);

        //vypis jeden clanek
        Long idKontaktu = 3L;
        Clanek jedenClanek = odesilacDotazu.queryForObject("SELECT * FROM clanky WHERE id=?", prevodnik, idKontaktu);
        System.out.println("Výpis článku: " + jedenClanek);

        //vypis vsechny clanky
        List<Clanek> seznamClanku = odesilacDotazu.query("SELECT * FROM clanky", prevodnik);
        System.out.println("Všechny články:");
        for (Clanek clanek : seznamClanku) {
            System.out.println("Články: " + clanek);
        }

        //insert dalsi clanek
        GeneratedKeyHolder drzakNaVygenerovanyKlic = new GeneratedKeyHolder();
        String sql = "INSERT INTO clanky (Nazev, Autor, Datum) " +
                "VALUES (?,?,?)";
        odesilacDotazu.update((Connection con) -> {
                    PreparedStatement prikaz = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    prikaz.setString(1, "Novinky z Brna a okolí");
                    prikaz.setString(2, "Alfons Weiss");
                    prikaz.setString(3, "2019-03-03");
                    return prikaz;
                },
                drzakNaVygenerovanyKlic);
        jedenClanek.setId(drzakNaVygenerovanyKlic.getKey().longValue());
    }

}