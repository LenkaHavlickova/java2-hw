package cz.czechitas.webapp;

import java.time.*;
import java.time.format.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class HlavniController {

    @RequestMapping(value = "/")
    public String zobrazIndex() {
        return "redirect:/pokus";
    }

    @RequestMapping(value = "/pokus", method = RequestMethod.GET)
    public @ResponseBody String zobrazPokus() {
        return "Ahoj. Toto je prosty text. Zadne HTML";
    }

    // Naprogramujte metodu namapovanou na tuto virtualni adresu
    // @RequestMapping(value = "/presny-cas", method = RequestMethod.GET)

}
