package cz.czechitas.webapp;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("index");
        drzakNaDataAJmenoSablony.addObject("formular", new HusyAKraliciForm());
        return drzakNaDataAJmenoSablony;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView zpracujIndex(HusyAKraliciForm vyplnenyFormular) {
        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("vysledek");
        int pocetNohou = vyplnenyFormular.getPocetHus() * 2 + vyplnenyFormular.getPocetKraliku() * 4;
        int pocetHlav = vyplnenyFormular.getPocetHus() + vyplnenyFormular.getPocetKraliku();
        drzakNaDataAJmenoSablony.addObject("pocetNohou", pocetNohou);
        drzakNaDataAJmenoSablony.addObject("pocetHlav", pocetHlav);
        return drzakNaDataAJmenoSablony;
    }
}
