Pexeso s REST API
-----------------

Ukolem je předělat pexeso tak, aby negenerovalo webové stránky z šablon ThymeLeafu,
ale aby vystavovalo REST API a to bylo konzumováno statický web s JavaScriptem.



### 1. Rozběhnutí a prozkoumání architektury

Nejprve prozkoumejte nasazenou aplikaci pexesa na
<https://margot.tomcat.cloud/ukol11/>

Obzlvast se podívejte, jak tam funguje JavaScript
a jaké dělá požadavky pomocí `XMLHttpRequest`.
Zkuste si vyvolat ekvivalentní požadavek samy.
Buď pomocí prohlížeče nebo
pomocí online nástroje *hurl.it*.



### 2. Předělání komponenty HlavniController

Původní podoba `HlavniController` byla asi takováto:

~~~~java
@Controller
public class HlavniController {

    @RequestMapping(value = "/")
    public String zobrazIndex();

    @RequestMapping(value = "/stul.html", method = RequestMethod.GET)
    public ModelAndView zobrazStul(@RequestParam("id") Long id);

    @RequestMapping(value = "/stul.html", method = RequestMethod.POST)
    public String zpracujTah(@RequestParam("id") Long idHerniPlochy,
                             @RequestParam Map<String, String> allParams);

}
~~~~


Nová podoba by mohla být takováto:

~~~~java
@Controller
public class HlavniController {

    @RequestMapping(value = "/")
    public String zobrazIndex();

    @RequestMapping(value = "/api/stul", method = RequestMethod.GET)
    public @ResponseBody HerniPlocha zobrazStul(@RequestParam("id") Long id);

    @RequestMapping(value = "/api/stul", method = RequestMethod.POST)
    public @ResponseBody HerniPlocha zpracujTah(@RequestParam("id") Long idHerniPlochy,
                                                @RequestBody Tah vybranaKarta);
}
~~~~


Poznámka: Moje metoda `zpracujTah(...)` přijímá `Tah`,
což je objekt nesoucí pozici vybrané karty:

~~~~java
public class Tah {

    private int poziceKarty;

    public int getPoziceKarty() {
        return poziceKarty;
    }

    public void setPoziceKarty(int newValue) {
        poziceKarty = newValue;
    }
}
~~~~


Naprogramujte tedy metody v `HlavniController` tak, aby JavaScript fungoval.

PexesoService ani zbytek aplikace se nemusí měnit. Měníme jen webovou vrstvu.



### 3. Provedení stejné změny ve vaší verzi pexesa

Předělejte webovou stránku na použití JavaScriptu/AJAXu.