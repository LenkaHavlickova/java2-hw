"use strict";

var idHry;

priStartu();

//-----------------------------------------------------------------------------

var StavKarty = Object.freeze({
    "LICEM_NAHORU": "LICEM_NAHORU",
    "ODEBRANA": "ODEBRANA",
    "RUBEM_NAHORU": "RUBEM_NAHORU"
});

var StavHry = Object.freeze({
    "HRAC1_VYBER_PRVNI_KARTY": "HRAC1_VYBER_PRVNI_KARTY",
    "HRAC1_VYBER_DRUHE_KARTY": "HRAC1_VYBER_DRUHE_KARTY",
    "HRAC1_ZOBRAZENI_VYHODNOCENI": "HRAC1_ZOBRAZENI_VYHODNOCENI",
    "KONEC": "KONEC"
});

function priStartu() {
    zjistiIdHryZAdresyStranky();
    udelejKartyKlikatelne();
    obnovStul();
}

function zjistiIdHryZAdresyStranky() {
    var regex = /id=(\d+)/;
    var match = regex.exec(window.location);
    idHry = match[1];
}

function udelejKartyKlikatelne() {
    var seznamElemKaret = document.querySelectorAll(".pexeso > img");
    for (var i = 0; i < seznamElemKaret.length; i++) {
        var elemKarta = seznamElemKaret[i];
        elemKarta.addEventListener("click", priKliknutiNaKartu);
    }
}

//-----------------------------------------------------------------------------

function obnovStul() {
    odesliHttpRequest("GET", zpracujVyslednouHerniPlochu);
}

function zpracujVyslednouHerniPlochu() {
    if (this.status >= 400) {
        zpracujChybuKomunikace(this.status, this.statusText);
        return;
    }
    var herniPlocha = JSON.parse(this.response);
    prekresliStul(herniPlocha);
}

function prekresliStul(herniPlocha) {
    var seznamElemKaret = document.querySelectorAll(".pexeso > img");
    for (var i = 0; i < seznamElemKaret.length; i++) {
        var elemKarta = seznamElemKaret[i];
        var karticka = herniPlocha.karticky[i];

        if (karticka.stav == StavKarty.LICEM_NAHORU) {
            elemKarta.src = "images/karta" + karticka.cisloObrazku + ".jpg";
            elemKarta.classList.remove("bez-karty");
        } else if (karticka.stav == StavKarty.RUBEM_NAHORU) {
            elemKarta.src = "images/rub.png";
            elemKarta.classList.remove("bez-karty");
        } else {
            elemKarta.src = "images/empty.png";
            elemKarta.classList.add("bez-karty");
        }
    }

    if (herniPlocha.stav == StavHry.KONEC) {
        zobrazHlasku("konec-hry");
    }
}

//-----------------------------------------------------------------------------

function priKliknutiNaKartu(evt) {
    var element = evt.target;
    var seznamElemKaret = document.querySelectorAll(".pexeso > img");
    var poziceKarty = Array.prototype.indexOf.call(seznamElemKaret, element);
    odesliTahNaServer(poziceKarty);
}

function odesliTahNaServer(poziceOtoceneKarty) {
    var zprava = { poziceKarty: poziceOtoceneKarty };
    odesliHttpRequest("POST", zpracujVyslednouHerniPlochu, JSON.stringify(zprava));
}

//-----------------------------------------------------------------------------

function odesliHttpRequest(httpMetoda, obsluhaUspesneKomunikace, teloPozadavku) {
    var httpKlient = new XMLHttpRequest();
    httpKlient.onload = obsluhaUspesneKomunikace;
    httpKlient.onerror = zpracujChybuKomunikace;
    httpKlient.open(httpMetoda, "api/stul?id=" + idHry, true);
    httpKlient.setRequestHeader("Content-Type", "application/json");
    httpKlient.setRequestHeader("Accept", "application/json");
    httpKlient.send(teloPozadavku);
}

function zpracujChybuKomunikace(statusCode, statusText) {
    if (typeof statusCode === "number") {
        if (statusCode === 404) {
            zobrazHlasku("hra-nenalezena");
        } else {
            zobrazHlasku("obecna-chyba", "Chyba od serveru " + statusCode + ": " + statusText);
        }
    } else {
        zobrazHlasku("obecna-chyba", "Chyba komunikace se serverem");
    }
}

function zobrazHlasku(idDivuHlasky, textHlasky) {
    var seznamElemHlasky = document.querySelectorAll(".zprava > div");
    for (var i=0; i<seznamElemHlasky.length; i++) {
        var elemHlaska = seznamElemHlasky[i];
        if (elemHlaska.id === idDivuHlasky) {
            elemHlaska.classList.remove("neviditelne");
        } else {
            elemHlaska.classList.add("neviditelne");
        }
    }

    if (typeof(textHlasky) !== "undefined") {
        var elemTextHlasky = document.querySelector("#text-hlasky");
        elemTextHlasky.innerHTML = textHlasky;
    }
}
