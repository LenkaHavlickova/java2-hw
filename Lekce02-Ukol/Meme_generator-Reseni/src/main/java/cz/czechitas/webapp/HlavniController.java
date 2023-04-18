package cz.czechitas.webapp;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private static final List<String> VTIPNE_VYROKY = Arrays.asList(
            "Když vidím na stromě vyřezaná jména milenců, tak mi to nepřijde vůbec  vtipné... Zarážející je, kolik lidí si na rande bere nůž...",
            "Včera jsem potkal dívku, přeskočila jiskra a hned mi ležela u nohou, miluji svůj nový paralizér...",
            "Pro hlupáka neexistuje problém, který by se nedal vytvořit.",
            "Všechno se dá vysvětlit, bohužel ne všem.",
            "Romantické filmy ničí vztah, protože budí v ženách pocit, že něco podobného je v životě potká. Podobně klamáni jsou muži pornem.",
            "K navození vhodné atmosféry pro početí prvního potomka může pomoci víno a porno. U druhého dítěte postačí piškotky a DVD s Krtečkem."
    );

    private static final List<String> OMLUVNE_VYROKY = Arrays.asList(
            "Můj pes slavil dnes narozeniny, takže jsem musel do IKEA pro čepičky a konfety.",
            "Nemohla jsem se rozhodnout, v jakých šatech se vám budu víc líbit.",
            "Řidič autobusu usnul na zastávce a trvalo hodinu, než jsme ho vzbudili ťukáním na čelní sklo.",
            "Zachraňoval jsem koťátko uvíznuté v koruně stromu. Noo chuďátko malý.",
            "Moje postel byla dnes víc smutná než obvykle, tak jsem ji musel utěšit. Mám dobré srdce.",
            "Ráno byly tak namrzlé chodníky, že mi přišla škoda neskočit si pro brusle.",
            "Byla jsem tak nervózní z našeho rande, že mi nešlo namalovat obočí stejně.",
            "Moje sousedka vařila svíčkovou a já měl zrovna v lednici brusinky. Znamení.",
            "Po cestě do práce jsem četla plakát „Dělejte to, co chcete“, a tak jsem šla nakupovat.",
            "V televizi říkali, že je teďka hodně ta ptačí chřipka, proto jsem musela utěšovat svýho papouška.",
            "S mojí kamarádkou se rozešel partner a někdo mu to auto podpálit musel.",
            "Nemohl jsem vyjet s autem. Ta sněhová kalamita v Praze je vždycky o krk.",
            "Zasekla se mi tkanička na eskalátorech.",
            "Pomáhal jsem hledat ztracené dítě, které neslyšelo na jméno Kolja.",
            "Přítel mě ráno nechtěl pustit. Byla jsem doslova jak přivázaná k posteli.",
            "Praskla nám v baráku voda a uplavaly mi klíče.",
            "Na dálnici D1 směr Brno byla zácpa, a tak jsem se doma smála lidem, kteří jezdí do Brna.",
            "Dítě spolklo kostičku LEGO, takže jsem nemohl dostavit svůj raketoplán.",
            "V horoskopu jsem se dočetla, že se ráno pohádám se šéfem, tak jsem přišla raději po obědě.",
            "Mám doma na zdi sluneční hodiny a zrovna to ráno bylo zataženo."
    );

    private Random generatorNahodnychCisel;

    public HlavniController() {
        this.generatorNahodnychCisel = new Random();
    }

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping("/meme.html")
    public ModelAndView zobrazMeme() {
        ModelAndView drzakNaData = new ModelAndView();
        int nahodnyIndex = generatorNahodnychCisel.nextInt(VTIPNE_VYROKY.size());
        String nahodnyVyrok = VTIPNE_VYROKY.get(nahodnyIndex);
        drzakNaData.addObject("vyrok", nahodnyVyrok);
        String nahodnyObrazek = "obr" + (this.generatorNahodnychCisel.nextInt(6) + 1) + ".jpg";
        drzakNaData.addObject("obrazek", nahodnyObrazek);
        return drzakNaData;
    }

    @RequestMapping("/pro-lepsi-naladu.html")
    public ModelAndView zobrazProLepsiNaladu() {
        ModelAndView drzakNaData = new ModelAndView();

        List<String> zvoleneObrazky = new ArrayList<>();
        while (zvoleneObrazky.size() < 2) {
            int nahodnyIndex = generatorNahodnychCisel.nextInt(16) + 1;
            String nahodnyObrazek = nahodnyIndex + ".jpg";
            if (!zvoleneObrazky.contains(nahodnyObrazek)) {
                zvoleneObrazky.add(nahodnyObrazek);
            }
        }
        drzakNaData.addObject("obrazek1", zvoleneObrazky.get(0));
        drzakNaData.addObject("obrazek2", zvoleneObrazky.get(1));

        List<String> zvoleneVyroky = new ArrayList<>();
        while (zvoleneVyroky.size() < 3) {
            int nahodnyIndex = generatorNahodnychCisel.nextInt(OMLUVNE_VYROKY.size());
            String nahodnyVyrok = OMLUVNE_VYROKY.get(nahodnyIndex);
            if (!zvoleneVyroky.contains(nahodnyVyrok)) {
                zvoleneVyroky.add(nahodnyVyrok);
            }
        }
        drzakNaData.addObject("vyrok1", zvoleneVyroky.get(0));
        drzakNaData.addObject("vyrok2", zvoleneVyroky.get(1));
        drzakNaData.addObject("vyrok3", zvoleneVyroky.get(2));

        return drzakNaData;
    }
}
