package cz.czechitas.webapp;

import java.time.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaDataAJmenoStranky = new ModelAndView("index");
        LocalTime cas = LocalTime.now();
        LocalDate datum = LocalDate.now();

        drzakNaDataAJmenoStranky.addObject("aktualniCas", cas);
        drzakNaDataAJmenoStranky.addObject("dnesniDatum", datum);

        return drzakNaDataAJmenoStranky;
    }

}
