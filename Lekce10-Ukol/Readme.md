Lekce 10 - Úkol 08 - Pexeso na webu
===================================

V úkolu 03 jste zobrazovali herní plochu pexesa.
Připravil jsem kompletně funkční verzi pexesa pro jednoho hráče na webu.
Vložte do ní svoje zobrazení herní plochy z úkolu 03.

Stav herní plochy je uložen jen v paměti.
Cílem je předělat pexeso tak, aby se stav herní plochy ukládal v databázi
a aplikace tak byla imunní vůči restartu.

Výchozí materiály najdete zde: [Java-Training--Projects--Java-2--Lekce10-Ukol.zip](../../data/2020-jaro/java2/Java-Training--Projects--Java-2--Lekce10-Ukol.zip)


### 1. Rozběhnutí a prozkoumání architektury

Otevřete si **10-Pexeso-Demo**.
Nejprve si aplikaci spusťte a všimněte si, která komponenta je zodpovědná za jakou funkcionalitu.
V této části nemáte za úkol nic doprogramovavat, jen si aplikaci projděte.
Je zde vidět, jak může být organizována reálná aplikace.

![Obrazek architektury](img/pexeso-architektura.png)

Především si vsimete komponenty `PexesoService`, do které je přesunuta celá logika pexesa.
`PexesoService` má zhruba tyto tři služby (metody):

~~~~
vytvorNovouHerniPlochu()       -> vraci objekt HerniPlocha
najdiHerniPlochu(Long id)      -> vraci objekt HerniPlocha
provedTah(Long idHerniPlochy, int cisloKartyNaKterouSeKliknulo)
~~~~

První dvě metody jsou jednoduché.
`vytvorNovouHerniPlochu()` založí novou herní plochu se všemi kartičkami
a uloží ji do `PexesoRepository`.
Nové herní ploše se přiřadí `ID`,
podle kterého bude metoda `najdiHerniPlochu(Long id)`
schopná kdykoliv tuto herní plochu znovu vyzvednout.

Třetí metoda, to je ta masitá část (veganky prominou :-).
`provedTah(...)` se volá při kliknutí na kartičku pexesa.
Hra pexesa se může nacházet v jednom z těchto stavů:
- `HRAC1_VYBER_PRVNI_KARTY`
- `HRAC1_VYBER_DRUHE_KARTY`
- `HRAC1_ZOBRAZENI_VYHODNOCENI`
- `KONEC`

Pokud byste chtěli implementovat hru 2 hráčů, stačilo by přidat pár stavů a evidovat, který hráč je který. Pro zjednodušení to ale **nebudeme** uvažovat.

![Stavy pexesa](img/pexeso-stavy.png)

Podle stavu, ve kterém se hra nachází, se interpretuje, co má provést kliknutí na kartu.
- `HRAC1_VYBER_PRVNI_KARTY` ... vybraná karta se zobrazí lícem nahoru (pokud byla rubem nahoru; jinak se tah ignoruje)
- `HRAC1_VYBER_DRUHE_KARTY` ... vybraná karta se zobrazí lícem nahoru (pokud byla rubem nahoru; jinak se tah ignoruje)
- `HRAC1_ZOBRAZENI_VYHODNOCENI` ... vyhodnotí se, které karty jsou lícem nahoru (musejí být právě 2) a pokud mají stejné číslo obrázku, nastaví se jako odebrané. Dále se hra přepne zase do prvního stavu `HRAC1_VYBER_PRVNI_KARTY` nebo do stavu `KONEC`.
- `KONEC` ... znamená, že už nic dalšího hra dělat nebude.

Dále si všimněte datového modelu, tedy tříd `HerniPlocha` a `Karta`.
Všimněte si, že třída HerniPlocha má ID, které se používá na webu. Program tak umožňuje evidovat více her souběžně (což je na webu běžné).

Aktuálně je aplikace stavová, tzn. v Javě uchováván stav her. Pokud byste ji restartovali, všechny rozběhnuté
hry se ztratí. Uchovávání stavu je záměrně separováno do jediné třídy - `PexesoRepository`.

Webová interakce je taky zajímavá. Všimněte si, jak spolu interaguje `stul.html` a `HlavniController`. Především
každý požadavek **GET** na `stul.html` zobrazí aktuální stav herní plochy. Zároveň každý požadavek **POST** na
`stul.html` provede tah (vstupní parametr <code>vybranaKarta*XX*> určuje, se kterou kartou byl proveden tah).



### 2. Adaptování vlastní herní plochy z úkolu 03

V úkolu 03 jste naprogramovali generování herní plochy pomocí Thymeleafu.
Adaptujte ho do funkční aplikace `20-Pexeso-Zadani`.

Všimněte si především, co očekává HlavniController a podle toho připravte fungující šablonu (a CSS).



### 3. Převod na databázovou persistenci

Stav hry je separován do komponenty `PexesoRepository`.

Aktuální verze ukládá data v paměti, takže se při restartu aplikace stav souběžných her ztratí.

~~~~java
public class PexesoRepository {

    private Map<Long, HerniPlocha> seznamHernichPloch;

}
~~~~

Úkolem je dopsat druhou verzi repository,
která bude ukládat stav her do databáze
a program bude možno libovolně restartovat bez ztráty stavu her.
Tím pádem se stane samotná javová část bezstavovou
(ZEN všech webových aplikací).

Postup:
1. Přejmenování `PexesoRepository` na `InMemoryPexesoRepository`.
2. Vytvoření `interface PexesoRepository` a nastavení `InMemoryPexesoRepository` jako potomka interfacu `PexesoRepository`.
3. Vytvoření nového potomka `PexesoRepository` jménem `JdbcTemplatePexesoRepository`.



#### Detailní popis 3:

Bude třeba založit databázi na vašem lokálním počítači.
Prosím, použijte všichni stejnou databázovou strukturu, aby si lektoři a koučové nemuseli zakládat spousty nových databází pro spuštění.
Jednotlivé instance herních plánů se identifikují pomocí ID,
víc webových aplikací navzájem "nepolezou do zelí" a můžou spolu koexistovat v 1 databází.

~~~~sql
CREATE DATABASE Pexeso
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_czech_ci;

USE Pexeso;

CREATE TABLE HerniPlochy (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Stav VARCHAR(250),
    CasPoslednihoTahu TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE Karty (
    ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    HerniPlochaID INT NOT NULL,
    CisloKarty INT DEFAULT 0 NOT NULL,
    Stav VARCHAR(250) ,
    PoradiKarty INT NOT NULL DEFAULT 0,
    CONSTRAINT HerniPlocha_FK FOREIGN KEY (HerniPlochaID) REFERENCES HerniPlochy (ID)
);
~~~~

Zde jsou metody pro přístup do databáze.

Založení nové `HerniPlochy` v databázi:

~~~~java
private HerniPlocha pridejHerniPlochu(HerniPlocha plocha) {
    GeneratedKeyHolder drzakNaVygenerovanyKlic = new GeneratedKeyHolder();
    String sql = "INSERT INTO herniplochy (Stav, CasPoslednihoTahu) " +
            "VALUES (?, ?)";
    odesilacDotazu.update((Connection con) -> {
                PreparedStatement prikaz = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                prikaz.setString(1, plocha.getStav().name());
                prikaz.setObject(2, Instant.now());
                return prikaz;
            },
            drzakNaVygenerovanyKlic);
    plocha.setId(drzakNaVygenerovanyKlic.getKey().longValue());

    List<Karta> karticky = plocha.getKarticky();
    for (int i = 0; i < karticky.size(); i++) {
        Karta karticka = karticky.get(i);
        pridejKarticku(karticka, plocha.getId(), i);
    }
    return plocha;
}

private void pridejKarticku(Karta karticka, Long plochaId, int poradiKarty) {
    GeneratedKeyHolder drzakNaVygenerovanyKlic = new GeneratedKeyHolder();
    String sql = "INSERT INTO karty (CisloKarty, Stav, HerniPlochaID, PoradiKarty) " +
            "VALUES (?, ?, ?, ?)";
    odesilacDotazu.update((Connection con) -> {
                PreparedStatement prikaz = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                prikaz.setInt(1, karticka.getCisloKarty());
                prikaz.setString(2, karticka.getStav().name());
                prikaz.setLong(3, plochaId);
                prikaz.setInt(4, poradiKarty);
                return prikaz;
            },
            drzakNaVygenerovanyKlic);
    karticka.setId(drzakNaVygenerovanyKlic.getKey().longValue());
}
~~~~


Nalezení `HerniPlochy` z databáze podle ID:

~~~~java
@Override
public HerniPlocha findById(Long id) {
    try {
        HerniPlocha herniPlocha = odesilacDotazu.queryForObject(
              "SELECT ID, Stav FROM HerniPlochy WHERE ID = ?",
              prevodnikPlochy,
              id);
        List<Karta> karticky = odesilacDotazu.query(
              "SELECT ID, CisloKarty, Stav FROM Karty WHERE HerniPlochaID = ?",
              prevodnikKarty,
              id);
        herniPlocha.setKarticky(karticky);
        return herniPlocha;
    } catch (EmptyResultDataAccessException e) {
        throw new NeexistujiciHraException();
    }
}
~~~~


Update (uložení) stavu `HerniPlochy` do databáze, včetně stavu kartiček:

~~~~
private HerniPlocha updatuj(HerniPlocha plocha) {
    odesilacDotazu.update(
            "UPDATE herniplochy SET Stav = ?, CasPoslednihoTahu = ? WHERE ID = ?",
            plocha.getStav().name(),
            Instant.now(),
            plocha.getId());

    List<Karta> karticky = plocha.getKarticky();
    for (int i = 0; i < karticky.size(); i++) {
        Karta karticka = karticky.get(i);
        odesilacDotazu.update(
                "UPDATE karty SET Stav = ?, PoradiKarty = ? WHERE ID = ?",
                karticka.getStav().name(),
                i,
                karticka.getId());
    }

    return plocha;
}
~~~~

Operace *update* a *insert* by bylo dobré provádět v transakci, ale to teď řešit nebudeme. Pokud by to někoho zajímalo, zeptejte se na hodině.



### Odevzdání domácího úkolu

Nejprve appku/appky zbavte přeložených spustitelných souborů. Zařídíte to tak,
že zastavíte IntelliJ IDEA a smažete podsložku `target` v projektu.
Nesmíte mít IntelliJ IDEA zapnutou, protože projekt má nastaven
automatický překlad a hned by se tam zase vytvořila.
Následně složku s projektem zabalte pomocí 7-Zipu pod jménem `Ukol-CISLO-Vase_Jmeno.7z`.
(Případně lze použít prostý zip, například na Macu).
Takto vytvořený archív nahrajte na Google Drive do Odevzdávárny.

Pokud byste chtěli odevzdat revizi úkolu (např. po opravě),
zabalte ji a nahrajte ji na stejný Google Drive znovu,
jen tentokrát se jménem `Ukol-CISLO-Vase_Jmeno-verze2.7z`.

Termín odevzdání je dva dny před další lekcí, nejpozději 23:59.
Tedy pokud je další lekce ve čtvrtek, termín je úterý 23:59.
Pokud úkol nebo revizi odevzdáte později,
prosím pošlete svému opravujícímu kouči/lektorovi email nebo zprávu přes FB.
