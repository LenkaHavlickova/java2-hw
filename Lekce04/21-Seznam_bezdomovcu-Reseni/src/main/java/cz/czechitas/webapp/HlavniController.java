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

    public HlavniController() throws IOException {
        ResourcePatternResolver prohledavacSlozek = new PathMatchingResourcePatternResolver();
        List<Resource> resources = Arrays.asList(prohledavacSlozek.getResources("classpath:/static/images/obliceje/*"));
        souborySObliceji = new ArrayList<>(resources.size());
        for (Resource prvek : resources) {
            souborySObliceji.add(prvek.getFilename());
        }
    }

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaDataAJmenoStranky = new ModelAndView("index");
        drzakNaDataAJmenoStranky.addObject("obliceje", souborySObliceji);
        return drzakNaDataAJmenoStranky;
    }

}
