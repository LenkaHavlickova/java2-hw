package cz.czechitas.webapp.persistence;

import java.sql.*;
import java.time.*;
import java.util.*;
import javax.sql.*;
import org.springframework.dao.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.*;
import cz.czechitas.webapp.entity.*;

@Component
public class JdbcTemplatePexesoRepository implements PexesoRepository {

    private JdbcTemplate odesilacDotazu;
    private RowMapper<HerniPlocha> prevodnikPlochy;
    private RowMapper<Karta> prevodnikKarty;

    public JdbcTemplatePexesoRepository(JdbcTemplate odesilacDotazu, RowMapper<HerniPlocha> prevodnikPlochy, RowMapper<Karta> prevodnikKarty) {
        this.odesilacDotazu = odesilacDotazu;
        this.prevodnikPlochy = prevodnikPlochy;
        this.prevodnikKarty = prevodnikKarty;
    }

    @Override
    public HerniPlocha findById(Long id) {
        try {
            HerniPlocha herniPlocha = odesilacDotazu.queryForObject(
                    "SELECT ID, Stav FROM HerniPlochy WHERE ID = ?",
                    prevodnikPlochy,
                    id);
            List<Karta> karticky = odesilacDotazu.query(
                    "SELECT ID, CisloKarty, Stav FROM Karty WHERE HerniPlochaID = ?",
                    prevodnikKarty,
                    id);
            herniPlocha.setKarticky(karticky);
            return herniPlocha;
        } catch (EmptyResultDataAccessException e) {
            throw new NeexistujiciHraException();
        }
    }

    @Override
    public HerniPlocha save(HerniPlocha plocha) {
        if (plocha.getId() == null) {
            return pridejHerniPlochu(plocha);
        } else {
            return updatuj(plocha);
        }
    }

    private HerniPlocha updatuj(HerniPlocha plocha) {
        odesilacDotazu.update(
                "UPDATE herniplochy SET Stav = ?, CasPoslednihoTahu = ? WHERE ID = ?",
                plocha.getStav().name(),
                Instant.now(),
                plocha.getId());

        List<Karta> karticky = plocha.getKarticky();
        for (int i = 0; i < karticky.size(); i++) {
            Karta karticka = karticky.get(i);
            odesilacDotazu.update(
                    "UPDATE karty SET Stav = ?, PoradiKarty = ? WHERE ID = ?",
                    karticka.getStav().name(),
                    i,
                    karticka.getId());
        }

        return plocha;
    }

    private HerniPlocha pridejHerniPlochu(HerniPlocha plocha) {
        GeneratedKeyHolder drzakNaVygenerovanyKlic = new GeneratedKeyHolder();
        String sql = "INSERT INTO herniplochy (Stav, CasPoslednihoTahu) " +
                "VALUES (?, ?)";
        odesilacDotazu.update((Connection con) -> {
                    PreparedStatement prikaz = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    prikaz.setString(1, plocha.getStav().name());
                    prikaz.setObject(2, Instant.now());
                    return prikaz;
                },
                drzakNaVygenerovanyKlic);
        plocha.setId(drzakNaVygenerovanyKlic.getKey().longValue());

        List<Karta> karticky = plocha.getKarticky();
        for (int i = 0; i < karticky.size(); i++) {
            Karta karticka = karticky.get(i);
            pridejKarticku(karticka, plocha.getId(), i);
        }
        return plocha;
    }

    private void pridejKarticku(Karta karticka, Long plochaId, int poradiKarty) {
        GeneratedKeyHolder drzakNaVygenerovanyKlic = new GeneratedKeyHolder();
        String sql = "INSERT INTO karty (CisloKarty, Stav, HerniPlochaID, PoradiKarty) " +
                "VALUES (?, ?, ?, ?)";
        odesilacDotazu.update((Connection con) -> {
                    PreparedStatement prikaz = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    prikaz.setInt(1, karticka.getCisloKarty());
                    prikaz.setString(2, karticka.getStav().name());
                    prikaz.setLong(3, plochaId);
                    prikaz.setInt(4, poradiKarty);
                    return prikaz;
                },
                drzakNaVygenerovanyKlic);
        karticka.setId(drzakNaVygenerovanyKlic.getKey().longValue());
    }
}
