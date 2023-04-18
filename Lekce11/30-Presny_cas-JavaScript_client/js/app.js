"use strict";

var elemCasovePole = document.querySelector("#casovePole");
// elemCasovePole.innerText = "23:00:00, 10. 5. 2018";

obnovCas();

function obnovCas() {
    console.log("Starting HTTP request");
    var httpKlient = new XMLHttpRequest();
    httpKlient.onload = hotovo;
    httpKlient.onerror = chyba;
    httpKlient.open("GET", "http://localhost:8080/simplest-http/presny-cas", true);
    httpKlient.send();
}

function hotovo() {
    // console.log(this.response);
    // console.log(typeof this.response);
    elemCasovePole.innerText = this.response;
}

function chyba(err) {
    console.log(err);
}


var casovac = setInterval(obnovCas, 5000);

