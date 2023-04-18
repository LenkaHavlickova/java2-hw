package cz.czechitas.webapp;

import java.sql.*;
import javax.sql.*;
import org.mariadb.jdbc.*;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.*;
import cz.czechitas.webapp.entity.*;

@Configuration
public class ApplicationConfig {

    @Bean
    public JdbcTemplate odesilacDotazu(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public RowMapper<HerniPlocha> prevodnikPlochy() {
        return BeanPropertyRowMapper.newInstance(HerniPlocha.class);
    }

    @Bean
    public RowMapper<Karta> prevodnikKarty() {
        return BeanPropertyRowMapper.newInstance(Karta.class);
    }

    @Bean
    public DataSource zdrojDat() {
        try {
            MariaDbDataSource dataSource = new MariaDbDataSource();
            dataSource.setUserName("student");
            dataSource.setPassword("password");
            dataSource.setUrl("jdbc:mysql://localhost:3306/Pexeso");
            return dataSource;
        } catch (SQLException e) {
            throw new RuntimeException("Nepodarilo se pripojit do databaze: " + e.getMessage(), e);
        }
    }

}
