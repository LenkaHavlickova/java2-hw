package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private KontaktRepository repository;

    public HlavniController(KontaktRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/")
    public ModelAndView zobrazSeznam() {
        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("index");
        List<Kontakt> kontakty = repository.findAll();
        drzakNaDataAJmenoSablony.addObject("seznamKontaktu", kontakty);
        return drzakNaDataAJmenoSablony;
    }

    @RequestMapping(value = "/{idKontaktu:[0-9]+}.html", method = RequestMethod.GET)
    public ModelAndView zobrazDetail(@PathVariable Long idKontaktu) {
        Kontakt nalezeny = repository.findById(idKontaktu);
        if (nalezeny == null) {
            return new ModelAndView("redirect:/");
        }
        DetailForm formularovaData = new DetailForm();
        formularovaData.setJmeno(nalezeny.getJmeno());
        formularovaData.setTelefonniCislo(nalezeny.getTelefonniCislo());
        formularovaData.setEmail(nalezeny.getEmail());

        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("detail");
        drzakNaDataAJmenoSablony.addObject("kontakt", formularovaData);
        return drzakNaDataAJmenoSablony;
    }

    @RequestMapping(value = "/{idKontaktu:[0-9]+}.html", method = RequestMethod.POST)
    public ModelAndView zpracujDetail(@PathVariable Long idKontaktu, DetailForm formular) {
        Kontakt kontakt = new Kontakt(
                idKontaktu,
                formular.getJmeno(),
                formular.getTelefonniCislo(),
                formular.getEmail());
        repository.save(kontakt);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/{idKontaktu:[0-9]+}", params = "_method=DELETE")
    public ModelAndView zpracujSmazani(@PathVariable Long idKontaktu) {
        repository.deleteById(idKontaktu);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/new.html", method = RequestMethod.GET)
    public ModelAndView zobrazNovyDetail() {
        ModelAndView drzakNaDataAJmenoStranky = new ModelAndView("detail");
        DetailForm formularovaData = new DetailForm();
        drzakNaDataAJmenoStranky.addObject("kontakt", formularovaData);
        return drzakNaDataAJmenoStranky;
    }

    @RequestMapping(value = "/new.html", method = RequestMethod.POST)
    public ModelAndView zpracujNovyDetail(DetailForm formular) {
        Kontakt kontakt = new Kontakt(
                formular.getJmeno(),
                formular.getTelefonniCislo(),
                formular.getEmail());
        repository.save(kontakt);
        return new ModelAndView("redirect:/");
    }
}
