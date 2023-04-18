package cz.czechitas.webapp;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.regex.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

// @Repository
public class FileClanekRepository implements ClanekRepository {

    public static final Path CESTA_K_SOUBORU = Paths.get("data.csv");
    public static final Pattern REGEX_RADKU = Pattern.compile("([0-9]+)[,;]\"(.*?)\"[,;]\"(.*?)\"[,;]([0-9]{4}-[0-9]{1,2}-[0-9]{1,2})");

    private Long idSequence = 3000L;

    @Override
    public synchronized List<Clanek> findAll() {
        return nactiDataZeSouboru();
    }

    @Override
    public synchronized Clanek findById(Long id) {
        List<Clanek> seznamClanku = findAll();
        int index = najdiIndexZaznamu(seznamClanku, id);
        if (index == -1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return clone(seznamClanku.get(index));
    }

    @Override
    public synchronized void save(Clanek zaznamKUlozeni) {
        List<Clanek> seznamClanku = findAll();
        if (zaznamKUlozeni.getId() != null) {
            int index = najdiIndexZaznamu(seznamClanku, zaznamKUlozeni.getId());
            if (index != -1) {
                updatuj(seznamClanku, zaznamKUlozeni, index);
                return;
            }
        }
        pridej(seznamClanku, zaznamKUlozeni);
    }

    @Override
    public synchronized void deleteById(Long id) {
        List<Clanek> seznamClanku = findAll();
        int index = najdiIndexZaznamu(seznamClanku, id);
        if (index == -1) return;
        seznamClanku.remove(index);
        ulozDataDoSouboru(seznamClanku);
    }

    //-------------------------------------------------------------------------

    private void updatuj(List<Clanek> seznamClanku, Clanek zaznamKUlozeni, int index) {
        Clanek clanek = clone(zaznamKUlozeni);
        seznamClanku.set(index, clanek);
        ulozDataDoSouboru(seznamClanku);
    }

    private void pridej(List<Clanek> seznamClanku, Clanek zaznamKPridani) {
        Clanek clanek = clone(zaznamKPridani);
        clanek.setId(idSequence);
        idSequence = idSequence + 1L;
        seznamClanku.add(clanek);
        ulozDataDoSouboru(seznamClanku);
    }

    private int najdiIndexZaznamu(List<Clanek> seznamClanku, Long id) {
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

    private List<Clanek> nactiDataZeSouboru() {
        try {
            List<String> radky = Files.readAllLines(CESTA_K_SOUBORU, StandardCharsets.UTF_8);

            List<Clanek> clanky = new ArrayList<>(radky.size());
            for (String radek : radky) {
                Matcher regularniAutomat = REGEX_RADKU.matcher(radek);
                if (!regularniAutomat.find()) continue;

                Clanek jedenClanek = new Clanek(
                        Long.parseLong(regularniAutomat.group(1)),
                        regularniAutomat.group(2),
                        regularniAutomat.group(3),
                        LocalDate.parse(regularniAutomat.group(4), DateTimeFormatter.ISO_DATE));
                clanky.add(jedenClanek);
            }
            return clanky;
        } catch (IOException e) {
            throw new RuntimeException("Nepodařilo se načíst data ze souboru " + CESTA_K_SOUBORU + ": " + e.getMessage(), e);
        }
    }

    private void ulozDataDoSouboru(List<Clanek> clanky) {
        try {
            List<String> radky = new ArrayList<>(clanky.size());
            for (Clanek jedenClanek : clanky) {
                String radek = jedenClanek.getId() + ",\""
                        + jedenClanek.getNazev() + "\",\""
                        + jedenClanek.getAutor() + "\","
                        + jedenClanek.getDatum().format(DateTimeFormatter.ISO_DATE);
                radky.add(radek);
            }
            Files.write(CESTA_K_SOUBORU, radky, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Nepodařilo se uložit data do souboru " + CESTA_K_SOUBORU + ": " + e.getMessage(), e);
        }
    }

}
