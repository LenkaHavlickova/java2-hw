package cz.czechitas.webapp;

import java.util.*;

public interface KontaktRepository {

    List<Kontakt> findAll();

    Kontakt findById(Long id);

    void save(Kontakt zaznamKUlozeni);

    void deleteById(Long id);
}
