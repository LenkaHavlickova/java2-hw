package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private ClanekRepository clanekRepository;

    public HlavniController(ClanekRepository clanekRepository) {
        this.clanekRepository = clanekRepository;
    }

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        return new ModelAndView("redirect:/clanky/");
    }

    @RequestMapping("/clanky/")
    public ModelAndView zobrazSeznam() {
        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("clanky/index");
        List<Clanek> seznamClanku = clanekRepository.findAll();
        drzakNaDataAJmenoSablony.addObject("seznam", seznamClanku);
        return drzakNaDataAJmenoSablony;
    }

    @RequestMapping(value = "/clanky/{cislo}.html", method = RequestMethod.GET)
    public ModelAndView zobrazDetail(@PathVariable("cislo") Long id) {
        Clanek nalezenyClanek = clanekRepository.findById(id);
        DetailForm detailForm = new DetailForm();
        detailForm.setNazev(nalezenyClanek.getNazev());
        detailForm.setAutor(nalezenyClanek.getAutor());
        detailForm.setDatum(nalezenyClanek.getDatum());

        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("clanky/detail");
        drzakNaDataAJmenoSablony.addObject("clanek", detailForm);
        return drzakNaDataAJmenoSablony;
    }

    @RequestMapping(value = "/clanky/{cislo}.html", method = RequestMethod.POST)
    public ModelAndView zpracujDetail(@PathVariable("cislo") Long id,
                                      DetailForm vyplnenyFormular) {
        Clanek zmenenyClanek = new Clanek(
                id,
                vyplnenyFormular.getNazev(),
                vyplnenyFormular.getAutor(),
                vyplnenyFormular.getDatum());
        clanekRepository.save(zmenenyClanek);
        return new ModelAndView("redirect:/clanky/");
    }

    @RequestMapping(value = "/clanky/{cislo}.html",
            method = RequestMethod.POST,
            params = "_method=DELETE")
    public ModelAndView odstranClanek(@PathVariable("cislo") Long id) {
        clanekRepository.deleteById(id);
        return new ModelAndView("redirect:/clanky/");
    }

    @RequestMapping(value = "/clanky/new.html", method = RequestMethod.GET)
    public ModelAndView zobrazNovy() {
        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("clanky/detail");
        drzakNaDataAJmenoSablony.addObject("clanek", new DetailForm());
        return drzakNaDataAJmenoSablony;
    }

    @RequestMapping(value = "/clanky/new.html", method = RequestMethod.POST)
    public ModelAndView zpracujNovy(DetailForm vyplnenyFormular) {
        Clanek novyClanek = new Clanek(
                vyplnenyFormular.getNazev(),
                vyplnenyFormular.getAutor(),
                vyplnenyFormular.getDatum());
        clanekRepository.save(novyClanek);
        return new ModelAndView("redirect:/clanky/");
    }

}
