package cz.czechitas.webapp.persistence;

import cz.czechitas.webapp.entity.*;

public interface PexesoRepository {

    HerniPlocha findOne(Long id);

    HerniPlocha save(HerniPlocha plocha);
}
