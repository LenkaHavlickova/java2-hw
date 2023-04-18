package cz.czechitas.webapp;

public class Kontakt {

    private Long id;
    private String jmeno;
    private String telefonniCislo;
    private String email;

    public Kontakt() {
    }

    public Kontakt(Long id, String jmeno, String telefonniCislo, String email) {
        this.id = id;
        this.jmeno = jmeno;
        this.telefonniCislo = telefonniCislo;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long newValue) {
        id = newValue;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String newValue) {
        jmeno = newValue;
    }

    public String getTelefonniCislo() {
        return telefonniCislo;
    }

    public void setTelefonniCislo(String newValue) {
        telefonniCislo = newValue;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newValue) {
        email = newValue;
    }
}
