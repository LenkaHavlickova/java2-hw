package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private static final List<Hrac> HRACI = Collections.unmodifiableList(Arrays.asList(
            new Hrac("Jack", "Sparrow", 20, 100),
            new Hrac("Hector", "Barbossa", 80, 100),
            new Hrac("Horatio", "Hornblower", 950, 1000),
            new Hrac("Captain", "Iglo", 50, 500),
            new Hrac("Captain", "Nemo", 75, 400),
            new Hrac("Popeye", "The Sailor", 150, 8000)
    ));

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping("/stul.html")
    public ModelAndView zobrazStul() {
        ModelAndView drzakNaData = new ModelAndView("stul");

        List<String> karty = new ArrayList<>();
        for (int i=1; i<=8; i++) {
            karty.add("karta" + i);
            karty.add("karta" + i);
        }
        Collections.shuffle(karty);
        drzakNaData.addObject("karty", karty);

        return drzakNaData;
    }

    @RequestMapping("/hraci.html")
    public ModelAndView zobrazHrace() {
        ModelAndView drzakNaData = new ModelAndView("hraci");
        drzakNaData.addObject("prebornici", HRACI);
        return drzakNaData;
    }

}
