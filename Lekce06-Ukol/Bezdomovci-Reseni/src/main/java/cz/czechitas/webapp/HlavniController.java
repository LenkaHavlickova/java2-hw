package cz.czechitas.webapp;

import java.io.*;
import java.util.*;
import org.springframework.core.io.*;
import org.springframework.core.io.support.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private List<String> souborySObliceji;
    private List<Boolean> spravneOdpovedi;

    public HlavniController() throws IOException {
        ResourcePatternResolver prohledavacSlozek = new PathMatchingResourcePatternResolver();
        List<Resource> resources = Arrays.asList(prohledavacSlozek.getResources("classpath:/static/images/obliceje/*"));
        souborySObliceji = new ArrayList<>(resources.size());
        for (Resource prvek : resources) {
            souborySObliceji.add(prvek.getFilename());
        }

        spravneOdpovedi = new ArrayList<>();
        for (String oblicej : souborySObliceji) {
            if (oblicej.contains("jpeg")) {
                spravneOdpovedi.add(false);
            } else {
                spravneOdpovedi.add(true);
            }
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaData = new ModelAndView("index");
        drzakNaData.addObject("obliceje", souborySObliceji);
        return drzakNaData;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView zpracujIndex(BezdomovciForm dataFormulare) {
        int score = 0;
        for (int i = 0; i < dataFormulare.getOdpovedi().size(); i++) {
            boolean odpoved = dataFormulare.getOdpovedi().get(i);
            boolean spravnaOdpoved = spravneOdpovedi.get(i);
            if (spravnaOdpoved == odpoved) {
                score++;
            }
        }
        ModelAndView data = new ModelAndView("vysledky");
        data.addObject("score", score);
        return data;
    }

}
