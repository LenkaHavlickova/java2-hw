package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaDataAJmenoStranky = new ModelAndView("index");

        List<Zbozi> nakupniSeznam = new ArrayList<>();
        nakupniSeznam.add(new Zbozi("Milka", 2));
        nakupniSeznam.add(new Zbozi("Ritter Sport", 5));
        nakupniSeznam.add(new Zbozi("Studentská pečeť", 3));
        nakupniSeznam.add(new Zbozi("Raciolky", 0));
        drzakNaDataAJmenoStranky.addObject(
                "seznamVyrobku", nakupniSeznam);

        return drzakNaDataAJmenoStranky;
    }

}
