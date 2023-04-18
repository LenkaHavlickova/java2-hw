package cz.czechitas.webapp;

import java.sql.*;
import java.util.*;
import org.mariadb.jdbc.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.lookup.*;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.*;

@Repository
public class JdbcKontaktRepository implements KontaktRepository {

    private JdbcTemplate odesilacDotazu;
    private RowMapper<Kontakt> prevodnik;

	public JdbcKontaktRepository() {
        try {
            MariaDbDataSource konfiguraceDatabaze = new MariaDbDataSource();
            konfiguraceDatabaze.setUserName("student");
            konfiguraceDatabaze.setPassword("password");
            konfiguraceDatabaze.setUrl("jdbc:mysql://localhost:3306/SeznamKontaktu");

            odesilacDotazu = new JdbcTemplate(konfiguraceDatabaze);
            prevodnik = BeanPropertyRowMapper.newInstance(Kontakt.class);
        } catch (SQLException e) {
            throw new DataSourceLookupFailureException("Nepodarilo se vytvorit DataSource", e);
        }
	}

    @Override
    public List<Kontakt> findAll() {
        List<Kontakt> seznamKontaktu = odesilacDotazu.query(
                "select ID, Jmeno, TelefonniCislo, Email" +
                        " from Kontakt",
                prevodnik);
        return seznamKontaktu;
    }

    @Override
    public Kontakt findById(Long id) {
        Kontakt kontakt = odesilacDotazu.queryForObject(
                "select ID, Jmeno, TelefonniCislo, Email" +
                        " from Kontakt where ID=?",
                prevodnik,
                id);
        return kontakt;
    }

    @Override
    public void save(Kontakt zaznamKUlozeni) {
        if (zaznamKUlozeni.getId() == null) {
            pridej(zaznamKUlozeni);
        } else {
            updatuj(zaznamKUlozeni);
        }
    }

    @Override
    public void deleteById(Long id) {
        odesilacDotazu.update(
                "DELETE FROM Kontakt WHERE id = ?",
                id);
    }

    //-------------------------------------------------------------------------

    private void updatuj(Kontakt zaznamKUlozeni) {
        odesilacDotazu.update(
                "UPDATE Kontakt SET Jmeno = ?, TelefonniCislo = ?, Email = ? WHERE id = ?",
                zaznamKUlozeni.getJmeno(),
                zaznamKUlozeni.getTelefonniCislo(),
                zaznamKUlozeni.getEmail(),
                zaznamKUlozeni.getId());
    }

    private void pridej(Kontakt zaznamKPridani) {
        GeneratedKeyHolder drzakNaVygenerovanyKlic = new GeneratedKeyHolder();
        String sql = "INSERT INTO Kontakt (Jmeno, TelefonniCislo, Email) " +
                "VALUES (?, ?, ?)";
        odesilacDotazu.update((Connection con) -> {
                    PreparedStatement prikaz = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    prikaz.setString(1, zaznamKPridani.getJmeno());
                    prikaz.setString(2, zaznamKPridani.getTelefonniCislo());
                    prikaz.setString(3, zaznamKPridani.getEmail());
                    return prikaz;
                },
                drzakNaVygenerovanyKlic);
        zaznamKPridani.setId(drzakNaVygenerovanyKlic.getKey().longValue());

    }
}
