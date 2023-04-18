"use strict";

var elementVyrok = document.querySelector("#vyrok");
elementVyrok.innerText = "Testing if it works";

function nactiVyrok() {
    var httpKlient = new XMLHttpRequest();
    httpKlient.onload = hotovo;
    httpKlient.onerror = chyba;
    httpKlient.open("GET", "nahodny", true);
    httpKlient.send();
}

function hotovo() {
    elementVyrok.innerText = this.response;
}

function chyba(err) {
    alert("Nastala chyba při komunikaci se serverem: " + err);
}



