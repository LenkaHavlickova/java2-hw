package cz.czechitas.webapp;

public class JmenoForm {

    private String krestniJmeno;
    private String prijmeni;

    public JmenoForm() {
    }

    public JmenoForm(String krestniJmeno, String prijmeni) {
        this.krestniJmeno = krestniJmeno;
        this.prijmeni = prijmeni;
    }

    public String getKrestniJmeno() {
        return krestniJmeno;
    }

    public void setKrestniJmeno(String newValue) {
        krestniJmeno = newValue;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String newValue) {
        prijmeni = newValue;
    }
}
