package cz.czechitas;

public class Clanek {
    private Long id;
    private String nazev;
    private String autor;
    private String datum;

    @Override
    public String toString() {
        return "Clanek{" +
                "id=" + id +
                ", nazev='" + nazev + '\'' +
                ", autor='" + autor + '\'' +
                ", datum='" + datum + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long newValue) {
        id = newValue;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String newValue) {
        nazev = newValue;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String newValue) {
        autor = newValue;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String newValue) {
        datum = newValue;
    }
}
