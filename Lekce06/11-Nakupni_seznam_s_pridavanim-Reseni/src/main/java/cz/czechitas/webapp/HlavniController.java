package cz.czechitas.webapp;

import java.util.*;
import java.util.concurrent.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private List<Zbozi> seznamVeci;

    public HlavniController() {
        seznamVeci = new CopyOnWriteArrayList<>();
        seznamVeci.add(new Zbozi("Banány", 4));
        seznamVeci.add(new Zbozi("Meloun", 1));
        seznamVeci.add(new Zbozi("Třešně", 20));
        seznamVeci.add(new Zbozi("Mango", 1));
    }

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaDataAJmenoStranky = new ModelAndView("index");
        drzakNaDataAJmenoStranky.addObject("seznamVyrobku", seznamVeci);

        return drzakNaDataAJmenoStranky;
    }

    @RequestMapping(value = "/novy.html", method = RequestMethod.GET)
    public ModelAndView zobrazNovy() {
        ModelAndView drzakNaDataAJmenoStranky;
        drzakNaDataAJmenoStranky = new ModelAndView("novy");
        return drzakNaDataAJmenoStranky;
    }

    @RequestMapping(value = "/novy.html", method = RequestMethod.POST)
    public ModelAndView zpracujNovy(Zbozi vyrobek) {
        ModelAndView drzakNaDataAJmenoStranky;
        seznamVeci.add(vyrobek);
        drzakNaDataAJmenoStranky = new ModelAndView("redirect:/");
        return drzakNaDataAJmenoStranky;
    }

}
