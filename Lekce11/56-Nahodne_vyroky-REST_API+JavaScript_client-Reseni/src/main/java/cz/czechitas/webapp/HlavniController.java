package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class HlavniController {

    private static final List<String> VYROKY = Arrays.asList(
            "Když jsem přijížděl domů, vjel jsem omylem do jiného dvora a naboural do stromu, protože ho doma na tomto místě nemám.",
            "To druhé auto do mne nabouralo bez jakéhokoliv předchozího varování.",
            "Myslel jsem, že mám stažené okénko. Zjistil jsem ale, že je zavřené, když jsem vystrčil ruku ven.",
            "Srazil jsem se se stojícím nákladním automobilem, když přijížděl z opačného směru.",
            "Ten dědula, kterého jsem porazil, by se na druhou stranu silnice stejně nedostal.",
            "Chodec do mne narazil a pak mi skočil pod auto.",
            "Ten chodec váhal, kterým směrem se má vydat, tak jsem ho přejel."
    );

    @RequestMapping(value = "/nahodny", method = RequestMethod.GET)
    public @ResponseBody String zobrazVyrok() {
        int cisloVyroku = (int)(Math.random() * VYROKY.size());
        String jedenVyrok = VYROKY.get(cisloVyroku);
        return jedenVyrok;
    }

}
