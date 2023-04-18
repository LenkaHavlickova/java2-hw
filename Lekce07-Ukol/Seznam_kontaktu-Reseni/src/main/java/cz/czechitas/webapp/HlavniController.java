package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private List<Kontakt> seznamKontaktu;
    private Long nejvyssiId = 200L;

    public HlavniController() {
        seznamKontaktu = new ArrayList<>();
        seznamKontaktu.add(new Kontakt(nejvyssiId++, "Thomas Alva Edison", "+1-123-555-666", "thomas@edison.com"));
        seznamKontaktu.add(new Kontakt(nejvyssiId++, "Albert Einstein", "+41 953 203 569", "albert.einstein@cern.ch"));
        seznamKontaktu.add(new Kontakt(nejvyssiId++, "Kamil Ševeček", "+420 604 111 222", "kamil.sevecek@czechitas.cz"));
    }

    @RequestMapping("/")
    public ModelAndView zobrazSeznam() {
        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("index");
        drzakNaDataAJmenoSablony.addObject("seznamKontaktu", seznamKontaktu);
        return drzakNaDataAJmenoSablony;
    }

    @RequestMapping(value = "/{idKontaktu:[0-9]+}.html", method = RequestMethod.GET)
    public ModelAndView zobrazDetail(@PathVariable Long idKontaktu) {
        Kontakt nalezeny = findById(idKontaktu);
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
        Kontakt kontakt = findById(idKontaktu);
        if (kontakt != null) {
            kontakt.setJmeno(formular.getJmeno());
            kontakt.setTelefonniCislo(formular.getTelefonniCislo());
            kontakt.setEmail(formular.getEmail());
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/{idKontaktu:[0-9]+}", params = "_method=DELETE")
    public ModelAndView zpracujSmazani(@PathVariable Long idKontaktu) {
        Kontakt kontakt = findById(idKontaktu);
        if (kontakt != null) {
            seznamKontaktu.remove(kontakt);
        }
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
        nejvyssiId = nejvyssiId + 1L;
        Kontakt kontakt = new Kontakt();
        kontakt.setId(nejvyssiId);
        kontakt.setJmeno(formular.getJmeno());
        kontakt.setTelefonniCislo(formular.getTelefonniCislo());
        kontakt.setEmail(formular.getEmail());
        seznamKontaktu.add(kontakt);
        return new ModelAndView("redirect:/");
    }

    private synchronized Kontakt findById(Long id) {
        for (Kontakt kontakt : seznamKontaktu) {
            if (kontakt.getId() != null && kontakt.getId().equals(id)) {
                return kontakt;
            }
        }
        return null;
    }

}
