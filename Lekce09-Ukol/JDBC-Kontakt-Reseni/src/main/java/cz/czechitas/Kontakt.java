package cz.czechitas;

public class Kontakt {

    private Long id;
    private String jmeno;
    private String telefonniCislo;
    private String email;

    @Override
    public String toString() {
        return "Kontakt{" +
                "id=" + id +
                ", jmeno='" + jmeno + '\'' +
                ", telefonniCislo='" + telefonniCislo + '\'' +
                ", email='" + email + '\'' +
                '}';
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
