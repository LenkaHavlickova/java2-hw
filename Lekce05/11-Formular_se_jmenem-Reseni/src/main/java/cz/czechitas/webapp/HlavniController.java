package cz.czechitas.webapp;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView zobrazFormularSeJmenem() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView zpracujeFormularSeJmenem(JmenoForm zadaneJmeno) {
        String vysledneJmeno = zadaneJmeno.getKrestniJmeno()
                + " " + zadaneJmeno.getPrijmeni();

        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("vysledek");
        drzakNaDataAJmenoSablony.addObject("celeJmeno", vysledneJmeno);
        return drzakNaDataAJmenoSablony;
    }
}
