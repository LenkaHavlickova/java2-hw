package cz.czechitas.webapp;

public class Clovek {

    private String krestniJmeno;
    private String prijmeni;

    public Clovek(String krestniJmeno, String prijmeni) {
        this.krestniJmeno = krestniJmeno;
        this.prijmeni = prijmeni;
    }

    public String getKrestniJmeno() {
        return krestniJmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }
}
