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

    @RequestMapping(value = "/api/stul", method = RequestMethod.GET)
    public @ResponseBody HerniPlocha zobrazStul(@RequestParam("id") Long id) {
        HerniPlocha herniPlocha = pexesoService.najdiHerniPlochu(id);
        return herniPlocha;
    }

    @RequestMapping(value = "/api/stul", method = RequestMethod.POST)
    public @ResponseBody HerniPlocha zpracujTah(@RequestParam("id") Long idHerniPlochy,
                                                @RequestBody Tah vybranaKarta) {
        int poziceKarty = vybranaKarta.getPoziceKarty();
        pexesoService.provedTah(idHerniPlochy, poziceKarty);
        return zobrazStul(idHerniPlochy);
    }


    @ExceptionHandler(NeexistujiciHraException.class)
    public ResponseEntity<?> zacniNovouHruPokudIdNeexistuje() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
