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

        List<String> seznamJidel = new ArrayList<>();
        seznamJidel.add("Mango");
        seznamJidel.add("Banány");
        seznamJidel.add("Meloun");
        seznamJidel.add("Kiwi");
        seznamJidel.add("Pomelo");
        seznamJidel.add("Třešně");
        drzakNaDataAJmenoStranky.addObject(
                "seznamOvoce", seznamJidel);

        return drzakNaDataAJmenoStranky;
    }

}
