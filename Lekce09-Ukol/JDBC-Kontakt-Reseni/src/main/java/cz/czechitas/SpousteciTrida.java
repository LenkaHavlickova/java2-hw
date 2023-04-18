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
        nastaveniDatabaze.setUrl("jdbc:mysql://localhost:3306/seznamkontaktu");

        JdbcTemplate odesilacDotazu = new JdbcTemplate(nastaveniDatabaze);

        RowMapper<Kontakt> prevodnik = BeanPropertyRowMapper.newInstance(Kontakt.class);

        //vypis jeden kontakt
        Long idKontaktu = 3L;
        Kontakt kontakt = odesilacDotazu.queryForObject("SELECT * FROM kontakt WHERE id=?", prevodnik, idKontaktu);
        System.out.println("Výpis kontaktu: " + kontakt);

        //vypis vsechny kontakty
        List<Kontakt> kontakty = odesilacDotazu.query("SELECT * FROM kontakt", prevodnik);
        System.out.println("Všechny kontakty:");
        for (Kontakt jedenKontakt : kontakty) {
            System.out.println(jedenKontakt);
        }

        //insert dalsi kontakt
        GeneratedKeyHolder drzakNaVygenerovanyKlic1 = new GeneratedKeyHolder();
        String sql = "INSERT INTO kontakt (Jmeno, TelefonniCislo, Email) " + "VALUES (?,?,?)";
        odesilacDotazu.update((Connection con) -> {
                    PreparedStatement prikaz = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    prikaz.setString(1, "Pepa");
                    prikaz.setString(2, "777777777");
                    prikaz.setString(3, "pepa@pepa.com");
                    return prikaz;
                },
                drzakNaVygenerovanyKlic1);
        kontakt.setId(drzakNaVygenerovanyKlic1.getKey().longValue());

/*        //pokus o update kontaktu
        Long idKontaktuUpdate = 7L;
        GeneratedKeyHolder drzakNaVygenerovanyKlic2 = new GeneratedKeyHolder();
        String sql2 = "UPDATE kontakt SET (Jmeno, TelefonniCislo, Email)" + "VALUES (?,?,?) WHERE id=?";
        odesilacDotazu.update((Connection con) -> {
                    PreparedStatement prikaz2 = con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                    prikaz2.setString(1, "Marta");
                    prikaz2.setString(2, "543 232 323");
                    prikaz2.setString(3, "marta@marta.com");
                    return prikaz2;
                },
                drzakNaVygenerovanyKlic2);
        kontakt.setId(drzakNaVygenerovanyKlic2.getKey().longValue());
        */
    }

}
