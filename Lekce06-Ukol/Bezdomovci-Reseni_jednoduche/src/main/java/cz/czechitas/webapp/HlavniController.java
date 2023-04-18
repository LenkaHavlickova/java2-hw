package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaData = new ModelAndView("index");
        List<String> obliceje = new ArrayList<>();
        obliceje.add("man1.jpg");
        obliceje.add("man2.jpg");
        obliceje.add("man3.jpg");
        obliceje.add("man4.jpg");
        obliceje.add("man5.jpg");
        obliceje.add("man6.jpg");
        obliceje.add("man7.jpg");
        obliceje.add("man8.jpg");
        drzakNaData.addObject("obliceje", obliceje);
        return drzakNaData;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView zpracujIndex(BezdomovciForm odpovedi) {
        int score = 0;
        if (!odpovedi.getOdpoved1()) {
            score++;
        }
        if (odpovedi.getOdpoved2()) {
            score++;
        }
        if (!odpovedi.getOdpoved3()) {
            score++;
        }
        if (odpovedi.getOdpoved4()) {
            score++;
        }
        if (odpovedi.getOdpoved5()) {
            score++;
        }
        if (!odpovedi.getOdpoved6()) {
            score++;
        }
        if (!odpovedi.getOdpoved7()) {
            score++;
        }
        if (odpovedi.getOdpoved8()) {
            score++;
        }
        ModelAndView data = new ModelAndView("vysledky");
        data.addObject("score", score);
        return data;
    }

}
