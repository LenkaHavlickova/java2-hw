package cz.czechitas.webapp;

public class Hrac {

    private String jmeno;
    private String prijmeni;
    private int pocetVyhranychTurnaju;
    private int pocetTurnajuCelkem;

    public Hrac() {
    }

    public Hrac(String jmeno, String prijmeni, int pocetVyhranychTurnaju, int pocetTurnajuCelkem) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.pocetVyhranychTurnaju = pocetVyhranychTurnaju;
        this.pocetTurnajuCelkem = pocetTurnajuCelkem;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String newValue) {
        jmeno = newValue;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String newValue) {
        prijmeni = newValue;
    }

    public int getPocetVyhranychTurnaju() {
        return pocetVyhranychTurnaju;
    }

    public void setPocetVyhranychTurnaju(int newValue) {
        pocetVyhranychTurnaju = newValue;
    }

    public int getPocetTurnajuCelkem() {
        return pocetTurnajuCelkem;
    }

    public void setPocetTurnajuCelkem(int newValue) {
        pocetTurnajuCelkem = newValue;
    }

    public double getUspesnost() {
        return 100.0 * pocetVyhranychTurnaju / pocetTurnajuCelkem;
    }
}
