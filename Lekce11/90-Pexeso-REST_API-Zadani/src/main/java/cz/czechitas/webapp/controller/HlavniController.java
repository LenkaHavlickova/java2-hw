package cz.czechitas.webapp.controller;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import cz.czechitas.webapp.entity.*;
import cz.czechitas.webapp.logika.*;
import cz.czechitas.webapp.persistence.*;

@Controller
public class HlavniController {

    private PexesoService pexesoService;

    public HlavniController(PexesoService pexesoService) {
        this.pexesoService = pexesoService;
    }

    @RequestMapping(value = "/")
    public String zobrazIndex() {
        HerniPlocha plocha = pexesoService.vytvorNovouHerniPlochu();
        return "redirect:/stul.html?id=" + plocha.getId();
    }

    // TODO

}
