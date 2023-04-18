package cz.czechitas.webapp;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaDataAJmenoStranky = new ModelAndView("index");
        drzakNaDataAJmenoStranky.addObject("mojeJmeno", "Jack Sparrow");
        drzakNaDataAJmenoStranky.addObject("mujVek", 255);

        int nahodneCislo = (int)(Math.random() * 100.0);
        drzakNaDataAJmenoStranky.addObject("nahodnaHodnota", nahodneCislo);

        int hozenoNaKostce = (int)(Math.random() * 6.0 + 1.0);
        drzakNaDataAJmenoStranky.addObject("cisloKostky", hozenoNaKostce);
        drzakNaDataAJmenoStranky.addObject("odkazNaObrazekKostky", "images/kostka"+hozenoNaKostce+".png");

        return drzakNaDataAJmenoStranky;
    }

}
