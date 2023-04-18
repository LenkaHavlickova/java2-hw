package cz.czechitas.webapp;

import java.util.*;

public interface ClanekRepository {

    List<Clanek> findAll();

    Clanek findById(Long id);

    void save(Clanek zaznamKUlozeni);

    void deleteById(Long id);
}
