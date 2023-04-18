package cz.czechitas.webapp;

import java.sql.*;
import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private CustomerRepository customerRepository;

    public HlavniController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping("/")
    public ModelAndView zobrazIndex() throws SQLException {
        List<Customer> zakaznici = customerRepository.findAll();

        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("index");
        drzakNaDataAJmenoSablony.addObject("seznamZakazniku", zakaznici);
        return drzakNaDataAJmenoSablony;
    }

}
