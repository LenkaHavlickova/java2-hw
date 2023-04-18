package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private static final List<Clovek> SEZNAM_LIDI = Collections.unmodifiableList(Arrays.asList(
            new Clovek("Albert", "Einstein"),
            new Clovek("Enrico", "Fermi"),
            new Clovek("Richard", "Feynman")
    ));

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping("/zemekoule/usa/pratele.html")
    public ModelAndView zobrazPratele() {
        ModelAndView data = new ModelAndView("lide/kamaradi");
        data.addObject("lide", SEZNAM_LIDI);
        return data;
    }

}
