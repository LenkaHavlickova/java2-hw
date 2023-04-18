package cz.czechitas.webapp;

import java.util.*;
import java.util.stream.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

@Repository
public class KontaktRepository {

    private Long idSequence = 1000L;
    private List<Kontakt> seznamKontaktu = new ArrayList<>();

    public KontaktRepository() {
        save(new Kontakt("Thomas Alva Edison", "+1-123-555-666", "thomas@edison.com"));
        save(new Kontakt("Albert Einstein", "+41 953 203 569", "albert.einstein@cern.ch"));
        save(new Kontakt("Kamil Ševeček", "+420 604 111 222", "kamil.sevecek@czechitas.cz"));
    }

    public synchronized List<Kontakt> findAll() {
        List<Kontakt> kopieSeznamu = new ArrayList<>(seznamKontaktu.size());
        for (Kontakt kontakt : seznamKontaktu) {
            kopieSeznamu.add(clone(kontakt));
        }
        return kopieSeznamu;
    }

    public synchronized Kontakt findById(Long id) {
        int index = najdiIndexZaznamu(id);
        if (index == -1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return clone(seznamKontaktu.get(index));
    }

    public synchronized void save(Kontakt zaznamKUlozeni) {
        if (zaznamKUlozeni.getId() != null) {
            int index = najdiIndexZaznamu(zaznamKUlozeni.getId());
            if (index != -1) {
                seznamKontaktu.set(index, clone(zaznamKUlozeni));
                return;
            }
        }
        zaznamKUlozeni.setId(idSequence++);
        seznamKontaktu.add(clone(zaznamKUlozeni));
    }

    public synchronized void deleteById(Long id) {
        int index = najdiIndexZaznamu(id);
        if (index == -1) return;
        seznamKontaktu.remove(index);
    }

    private int najdiIndexZaznamu(Long id) {
        for (int i = 0; i < seznamKontaktu.size(); i++) {
            Kontakt kontakt = seznamKontaktu.get(i);
            if (kontakt.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private Kontakt clone(Kontakt puvodniKontakt) {
        return new Kontakt(
                puvodniKontakt.getId(),
                puvodniKontakt.getJmeno(),
                puvodniKontakt.getTelefonniCislo(),
                puvodniKontakt.getEmail());
    }
}
