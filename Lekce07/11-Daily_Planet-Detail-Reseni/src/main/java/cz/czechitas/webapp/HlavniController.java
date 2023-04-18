package cz.czechitas.webapp;

import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private Long idSequence = 3000L;
    private List<Clanek> seznamClanku = new CopyOnWriteArrayList<>(Arrays.asList(
            new Clanek(idSequence++, "Boss podsvětí dostal 20 let", "Clark Kent", LocalDate.of(2018, 1, 31)),
            new Clanek(idSequence++, "Lékaři doporučují opatrnost", "Lois Lane", LocalDate.of(2018, 2, 28)),
            new Clanek(idSequence++, "Bezkontaktní karty lákají zloděje", "Perry White", LocalDate.of(2017, 12, 24)),
            new Clanek(idSequence++, "Ministryně navštívila problematické předměstí", "Jimmy Olsen", LocalDate.of(2016, 7, 31)),
            new Clanek(idSequence++, "Soutěž o lístky na fotbal", "Cat Grant", LocalDate.of(2016, 8, 1)),
            new Clanek(idSequence++, "Vrah prodavačky je ve vazbě", "Ron Troupe", LocalDate.of(2017, 10, 28))
    ));

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        return new ModelAndView("redirect:/clanky/");
    }

    @RequestMapping("/clanky/")
    public ModelAndView zobrazSeznam() {
        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("clanky/index");
        drzakNaDataAJmenoSablony.addObject("seznam", seznamClanku);
        return drzakNaDataAJmenoSablony;
    }

    @RequestMapping(value = "/clanky/{cislo}.html", method = RequestMethod.GET)
    public ModelAndView zobrazDetail(@PathVariable("cislo") Long id) {
        Clanek nalezenyClanek = findById(id);
        DetailForm detailForm = new DetailForm();
        detailForm.setNazev(nalezenyClanek.getNazev());
        detailForm.setAutor(nalezenyClanek.getAutor());
        detailForm.setDatum(nalezenyClanek.getDatum());

        ModelAndView drzakNaDataAJmenoSablony = new ModelAndView("clanky/detail");
        drzakNaDataAJmenoSablony.addObject("clanek", detailForm);
        return drzakNaDataAJmenoSablony;
    }

    private synchronized Clanek findById(Long id) {
        int index = najdiIndexZaznamu(id);
        if (index == -1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return seznamClanku.get(index);
    }

    private int najdiIndexZaznamu(Long id) {
        for (int i = 0; i < seznamClanku.size(); i++) {
            Clanek clanek = seznamClanku.get(i);
            if (clanek.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

}
