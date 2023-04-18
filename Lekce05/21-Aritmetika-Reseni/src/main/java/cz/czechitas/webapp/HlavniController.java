package cz.czechitas.webapp;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView zobrazFormularSCisly() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView zpracujeFormularSCisly(PoctyForm zadanaCisla) {
        double cislo1 = zadanaCisla.getCislo1();
        double cislo2 = zadanaCisla.getCislo2();

        double soucet = cislo1 + cislo2;
        double rozdil = cislo1 - cislo2;
        double soucin = cislo1 * cislo2;
        double podil = cislo1 / cislo2;
        double mocnina = Math.pow(cislo1, cislo2);
        double odmocnina = Math.pow(cislo1, 1.0/cislo2);

        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("vysledek");
        drzakNaDataAJmenoSablony.addObject("plus", soucet);
        drzakNaDataAJmenoSablony.addObject("minus", rozdil);
        drzakNaDataAJmenoSablony.addObject("krat", soucin);
        drzakNaDataAJmenoSablony.addObject("deleno", podil);
        drzakNaDataAJmenoSablony.addObject("mocnina", mocnina);
        drzakNaDataAJmenoSablony.addObject("odmocnina", odmocnina);
        drzakNaDataAJmenoSablony.addObject("cislo1", cislo1);
        drzakNaDataAJmenoSablony.addObject("cislo2", cislo2);
        return drzakNaDataAJmenoSablony;
    }
}
