package cz.czechitas.webapp;

import java.sql.*;
import java.util.*;
import org.mariadb.jdbc.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    @RequestMapping("/")
    public ModelAndView zobrazIndex() throws SQLException {
        MariaDbDataSource konfiguraceDatabaze = new MariaDbDataSource();
        konfiguraceDatabaze.setUserName("student");
        konfiguraceDatabaze.setPassword("password");
        konfiguraceDatabaze.setUrl("jdbc:mysql://localhost:3306/VideoBoss");

        JdbcTemplate odesilacDotazu = new JdbcTemplate(konfiguraceDatabaze);
        RowMapper<Customer> prevodnik = BeanPropertyRowMapper.newInstance(Customer.class);
//        Customer zakaznik = odesilacDotazu.queryForObject(
//                "select ID, Firstname, Lastname, Address, Deleted, Version" +
//                        " from Customers where ID=?",
//                prevodnik,
//                10L);
        List<Customer> zakaznici = odesilacDotazu.query(
                "select ID, Firstname, Lastname, Address, Deleted, Version" +
                        " from Customers",
                prevodnik);

        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("index");
        drzakNaDataAJmenoSablony.addObject("seznamZakazniku", zakaznici);
        return drzakNaDataAJmenoSablony;
    }

}
