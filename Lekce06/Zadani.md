Úkol - Pøihlašovací dialog
--------------------------

Vytvoøte webovou aplikaci, podle dodaných podkladù webových stránek
[podklady](podklady).

V podkladech jsou pouze statické webové stránky, jak byste je mohli dostat od tvùrce webu. Vaším úkolem je tento
statický web pøedìlat do naší šablony Spring Bootu a rozbìhnout základní funkcionalitu.

### Popis stránek

* **index.html**. Úvodní stránka. Za normálních okolností by z ní šlo na **hlavni.html**. Nicménì jsme se rozhodli, že
  pro pøístup na **hlavni.html** je nutné se nejprve pøihlásit. Musí se tedy nejprve projít pøes stránku
  **prihlaseni.html**.
* **prihlaseni.html**. Pøihlašovací formuláø. Po odeslání formuláøe se na serveru zkontroluje zadaný email a
  heslo. Staèí, když bude aplikace akceptovat napevno email **pokus@pokus.com** a heslo **password**. Nezapomeòte, že
  **String**y se porovnávají pomocí **equals()**, nikoliv **==**. Tedy napøíklad
  **vyplnenyFormular.getPrihlasovaciJmeno().equals("pokus@pokus.com")**. V pøípadì platného pøihlášení se prohlížeè
  pøesmìruje na **hlavni.html**.  V pøípadì neplatného pøihlášení se znovu zobrazí pùvodní stránka **prihlaseni.html** a
  chybová hláška. Je velmi vhodné, aby zùstal vyplnìný email z minulého pokusu.
* **hlavni.html**. Stránka se seznamem. Buï použijte tu, která je ve statickém web designu, nebo jako bonus použijte
  aplikaci z hodiny na evidenci telefonního seznamu.
* **registrace.html**, **zapomenute-heslo.html**. Pøi pøihlašování je typicky možné se i alternativnì zaregistrovat a
  nebo si vyžádat email se zapomenutým heslem. Rozchoïte tyto formuláøe. Staèí, když budou fungovat "na oko", ale ve
  skuteènosti žádný email posílat nebudou (zapomenute-heslo.html), ani neakceptují registraci nového uživatele
  (registrace.html) a ve skuteènosti zùstane jediný možný email a heslo stále jen **pokus@pokus.com** a
  **password**". Nepovinnì bych ale velmi doporuèoval zkusit si evidenci uživatelù naprogramovat.

*Poznámka:* Takto naprogramované pøihlašování samozøejmì funguje jen pøi pøístupu z **index.html**. Pokud by uživatel
pøistoupil pøímo k **hlavni.html**, nemusel by se pøihlašovat. Tento problém neøešte a tvaøme se, že neexistuje. Øešením
je tzv. **HttpSession** a vložení pøihlášeného uživatele do této **HttpSession**. Stránka **prihlaseni.html** by se
potom zobrazovala automaticky, pokud by nebyl v **HttpSession** pøihlášený uživatel.

Jednak by to ale bylo pracné, ale zároveò je mnohem lepší použít existující autentizaèní framework, napøíklad Spring
Security. Jeho složitost je ale daleko za rozsahem jednoho domácího úkolu.

Ukázkový web mùžete vidìt na [https://margot.tomcat.cloud/ukol06/](https://margot.tomcat.cloud/ukol06/).

Stránky musejí z prohlížeèe fungovat jak pøi souborovém pøístupu, tak pøi pøístupu pøes webový server (se Spring
Bootem).
