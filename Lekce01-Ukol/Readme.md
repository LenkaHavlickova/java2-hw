Úkol 00 - Registrace IntelliJ IDEA
----------------------------------

Jednotlivým skutečným domácím úkolům předchází ještě jeden poněkud netradiční (ale neméně důležitý): zaregistrovat si
samotné vývojové prostředí, které budeme po celý kurz používat. Zadání tohoto úkolu naleznete [v samostatném
dokumentu](Ukol_00.md).

Úkol 01 - Experimentování se statickým webem a Tomcatem
-------------------------------------------------------

Cílem domácího úkolu je vytvořit jednoduché statické stránky a nasadit je na Tomcat.



### Krátký popis

#### Váš osobní webík

Úkolem bude připravit si svůj osobní web (například na úrovni jednodenního workshopu Tvořím web HTML/CSS
[https://www.czechitas.cz/cs/co-delame/tvorim-web](https://www.czechitas.cz/cs/co-delame/tvorim-web)). Webové stránky
(index.html, styly.css, ...) nejprve zkuste napsat v nově založeném projektu v IntelliJ IDEA od začátku.
Druhý krok bude použít existující webové stránky a převést je do javové webové aplikace se zabudovaným webovým serverem.
Třetí krok bude tuto webovou aplikaci nasadit na samostatně běžící webový server Tomcat.



### Detailní popis

#### Část 1 - Váš osobní webík

Abyste měli s čím experimentovat, budete potřebovat nějaký svůj web. Může to klidně být i již existující web nebo
statické webové stránky odkudkoliv. Pokud se necítíte na tvorbu vlastního webu, můžete si klidně půjčit nějaký
existující web. Například <http://krystufek.rolecek.cz/>.

Pro psaní webu existuje na internetu spousta návodů.
Můžete zkusit třeba online kurz od Luďka Rolečka
<https://www.czechitas.cz/cs/blog/zaciname-s-it/online-kurz-uvod-do-html-css-1-lekce>.

Dále můžete zkusit kurz Codecademy o základech HTML+CSS "Make a Website"
<https://www.codecademy.com/learn/make-a-website>.
Je třeba vytvořit si na Codecademy účet (což doporučuju, mají spoustu bezva kurzů na úplné základy).
Poslední ze čtyř lekcí - Bootstrap - můžete úplně s klidným svědomím vynechat.

Web byste sice mohli tvořit v libovolném textovém editoru, ale je třeba se naučit pracovat s IntelliJ IDEA.
V IntelliJ IDEA založte nový projekt -> `Static web`.
V průvodci vyplňte (pokud jste na Windows, použijte zpětná lomítka, JAVA_TRAINING je C:\Java-Training, pokud jste na macOS, použijte dopředná lomítka a JAVA_TRAINING je /Users/VASE_UZIVATELSKE_JMENO/Java-Training):
`Project name: JednoduchyWeb`
`Project location: JAVA-TRAINING/Projects/Java-2/Lekce01-Ukol/10-JednoduchyWeb`
`Module name: web-module`
`Content root: JAVA-TRAINING/Projects/Java-2/Lekce01-Ukol/10-JednoduchyWeb`
`Module file location: JAVA-TRAINING/Projects/Java-2/Lekce01-Ukol/10-JednoduchyWeb/.idea`

Projekt je vlastně jen složka s konfiguračními soubory v podsložce `.idea`.
Připravte do projektu celý web (začněte souborem `index.html`).

Projekt může vypadat takto:

![Screenshot](img/ukol01-static-web-project.png)

Stránky můžete během editování zobrazit přímo v prohlížeči zadáním adresy:

Na Windows: **file:///C:/Java-Training/Projects/Java-2/Lekce01-Ukol/10-JednoduchyWeb/index.html**

Na macOS: **file:///Users/VASE_UZIVATELSKE_JMENO/Java-Training/Projects/Java-2/Lekce01-Ukol/10-JednoduchyWeb/index.html**

Smyslem této části je použít IntelliJ IDEA jako prostý textový editor, bez jakékoliv vazby na Javu.
IntelliJ IDEA - Ultimate Edition je výborný editor i jen na prosté HTML + CSS + JavaScript.



#### Část 2 - Osobní webík v javovém projektu v IntelliJ IDEA

Druhá část je převod osobního webíku do předpřipraveného javového projektu ve Spring Bootu.
Javový projekt vytvoříte zkopírováním původního projektu z Lekce01 `40-JavaSimpleWeb`.
Celou složku zkopírujte do `JAVA-TRAINING/Projects/Java-2/Lekce01-Ukol`.

1. Složku přejmenujte na `JednoduchyWebNadJavou`
2. Otevřete projekt v IntelliJ IDEA a změňte následující údaje v projektových konfiguračních souborech:
3. PROJEKT/pom.xml -> `<artifactId>ukol01</artifactId>`
4. PROJEKT/pom.xml -> `<name>ukol01</name>`
5. PROJEKT/pom.xml -> `<finalName>ukol01</finalName>`
6. PROJEKT/src/main/resources/application.properties -> `server.servlet.context-path=/ukol01`
7. PROJEKT/src/main/resources/application.properties -> `server.port=0`
8. Schvalte bublinu **Maven projects need to be imported** volbou **Import changes**.

Web z minulé části (obsah projektové složky vyjma podsložky `.idea`) překopírujte do tohoto projektu do složky
`PROJEKT/src/main/resources/static`. Projekt by měl být spustitelný zelenou šipkou vpravo nahoře.
Po spuštění se ve spodním rámu (v textové konzoli) IntelliJ IDEA vypíše mimo jiné
adresa do prohlížeče. Něco jako `http://localhost:51552/ukol01`.
Projekt ještě polaďte a vyzkoušejte, že všechno funguje jak má, odkazy směřují na platné stránky
a obrázky a kaskádové styly jsou správně zobrazeny.
Vyzkoušejte také, že se jakékoliv změny ve zdrojových souborech okamžitě projevují
ve webovém prohlížeči po obnovení stránky stiskem F5 nebo Ctrl+F5.

Ukázka možného řešení webu (všimněte si souborů ve složce PROJEKT/src/main/resources/static)

![Screenshot](img/ukol01-static-web-project-reseni.png)

Tento projekt budete odevzdávat do Odevzdávárny jako domácí úkol.



#### Část 3 - Nasazení na Tomcat

Po odladění svého webu v javové webové aplikaci (uvnitř IntelliJ IDEA)
jej nasaďte do plnotučného webového serveru Tomcat, stejně, jak jsme to dělali v hodině.
Až budete mít přístup na webový server na internetu v cloudu, bude se na něj nasazovat stejně.

Postupujte tak, že nejprve zastavíte případný běžící projekt v IntelliJ IDEA,
vytvoříte distribuční archív v pravém menu **Maven projects**
-> Lifecycle -> `clean` a potom **Maven projects** -> Lifecycle -> `package`.
Vznikne tak soubor PROJEKT/target/ukol01.war.
Ten překopírujte do složky TOMCAT/webapps.
Tomcat nastartujete spuštěním TOMCAT/bin/startup.bat.



#### Pár tipů:

* **Pozor! Mezi zdrojovým projektem (složkou) a výsledným webovým archívem .war je velký rozdíl. Do Tomcatu se nasazuje
  výsledný archív .war, do odevzdávárny na Google Drivu se nahrává zazipovaná složka celého projektu.**
* **Do Tomcatu se NIKDY nekopíruje rozbalená složka webu, pouze archív .war. Tomcat si tento archív sám rozbalí.**
* **Pokud se chcete zbavit nasazené webové aplikace z Tomcatu, smažte pouze archív .war ve složce
  TOMCAT/webapps. Nemažte rozbalenou složku webu. Tomcat sám pozná, že jste odebrali zdrojový archív .war a rozbalenou
  složku smaže sám. To slouží zároveň jako potvrzení, že byla webová aplikace úspěšně sesazena.**
* **Pouze pokud by Tomcat zrovna neběžel, můžete smazat nejen archív .war, ale i rozbalenou složku z TOMCAT/webapps.**
* **Pro psaní doporučuji používat javový projekt se zabudovaným malým webovým serverem, který spustíte klasicky pomocí zelené
  šipky. Okno zabudovaného webového serveru vám vypíše adresu pro prohlížeč, na kterou kliknutím stránku v prohlížeči otevřete.
  Nyní již stačí pouze editovat zdrojové soubory (index.html, ...), pokaždé zmáčknout Ctrl+F9 a obnovit stránku v prohlížeči (F5).**



### Odevzdání domácího úkolu

Nejprve appku/appky zbavte přeložených spustitelných souborů.
Zařídíte to tak, že v IntelliJ IDEA vpravo zvolíte
Maven Projects -> Lifecycle -> Clean.
Úspěch se projeví tak, že v projektové složce zmizí
podsložka `target`.
Následně složku s projektem
zabalte pomocí 7-Zipu pod jménem `Ukol-CISLO-Vase_Jmeno.7z`.
(Případně lze použít prostý zip, například na Macu).
Takto vytvořený archív nahrajte na Google Drive do Odevzdávárny.

Vytvořte snímek obrazovky spuštěného programu (webové stránky) a vložte jej
do galerie `Ukol CISLO` ve skupině našeho kurzu na Facebooku.

Pokud byste chtěli odevzdat revizi úkolu (např. po opravě),
zabalte ji a nahrajte ji na stejný Google Drive znovu,
jen tentokrát se jménem `Ukol-CISLO-Vase_Jmeno-verze2.7z`.

Termín odevzdání je dva dny před další lekcí, nejpozději 23:59.
Tedy pokud je další lekce ve čtvrtek, termín je úterý 23:59.
Pokud úkol nebo revizi odevzdáte později,
prosím pošlete svému opravujícímu kouči/lektorovi email nebo zprávu přes FB.
