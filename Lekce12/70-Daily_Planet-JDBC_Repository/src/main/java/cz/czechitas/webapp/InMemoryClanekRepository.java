package cz.czechitas.webapp;

import java.time.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

//@Repository
public class InMemoryClanekRepository implements ClanekRepository {

    private Long idSequence = 3000L;
    private List<Clanek> seznamClanku = new ArrayList<>();

    public InMemoryClanekRepository() {
        save(new Clanek("Boss podsvětí dostal 20 let", "Clark Kent", LocalDate.of(2018, 1, 31)));
        save(new Clanek("Lékaři doporučují opatrnost", "Lois Lane", LocalDate.of(2018, 2, 28)));
        save(new Clanek("Bezkontaktní karty lákají zloděje", "Perry White", LocalDate.of(2017, 12, 24)));
        save(new Clanek("Ministryně navštívila problematické předměstí", "Jimmy Olsen", LocalDate.of(2016, 7, 31)));
        save(new Clanek("Soutěž o lístky na fotbal", "Cat Grant", LocalDate.of(2016, 8, 1)));
        save(new Clanek("Vrah prodavačky je ve vazbě", "Ron Troupe", LocalDate.of(2017, 10, 28)));
    }

    @Override
    public synchronized List<Clanek> findAll() {
        List<Clanek> clanky = new ArrayList<>(seznamClanku.size());
        for (Clanek jedenClanek : seznamClanku) {
            clanky.add(clone(jedenClanek));
        }
        return clanky;
    }

    @Override
    public synchronized Clanek findById(Long id) {
        int index = najdiIndexZaznamu(id);
        if (index == -1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return clone(seznamClanku.get(index));
    }

    @Override
    public synchronized void save(Clanek zaznamKUlozeni) {
        if (zaznamKUlozeni.getId() != null) {
            int index = najdiIndexZaznamu(zaznamKUlozeni.getId());
            if (index != -1) {
                updatuj(zaznamKUlozeni, index);
                return;
            }
        }
        pridej(zaznamKUlozeni);
    }

    @Override
    public synchronized void deleteById(Long id) {
        int index = najdiIndexZaznamu(id);
        if (index == -1) return;
        seznamClanku.remove(index);
    }

    //-------------------------------------------------------------------------

    private void updatuj(Clanek zaznamKUlozeni, int index) {
        Clanek clanek = clone(zaznamKUlozeni);
        seznamClanku.set(index, clanek);
    }

    private void pridej(Clanek zaznamKPridani) {
        Clanek clanek = clone(zaznamKPridani);
        clanek.setId(idSequence);
        idSequence = idSequence + 1L;
        seznamClanku.add(clanek);
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

    private Clanek clone(Clanek puvodni) {
        return new Clanek(puvodni.getId(), puvodni.getNazev(), puvodni.getAutor(), puvodni.getDatum());
    }

}
