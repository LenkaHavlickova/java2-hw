package cz.czechitas.webapp;

import java.time.*;
import java.time.format.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class HlavniController {

    @RequestMapping(value = "/")
    public String zobrazIndex() {
        return "redirect:/presny-cas";
    }

    @RequestMapping(value = "/presny-cas", method = RequestMethod.GET)
    public @ResponseBody String zobrazPresnyCas() {
        return "18:30:59, 10. 5. 2018";
//        LocalDateTime aktualniCas = LocalDateTime.now();
//        DateTimeFormatter prevodnik = DateTimeFormatter.ofPattern("HH:mm:ss, d. M. yyyy");
//        String text = prevodnik.format(aktualniCas);
//        return text;
    }

}
