package cz.czechitas.dailyplanet;

import java.time.*;

public class Clanek {

    private Long id;
    private String nazev;
    private String autor;
    private LocalDate datum;

    public Clanek() {
        this.datum = LocalDate.now();
    }

    public Clanek(String nazev, String autor, LocalDate datum) {
        this.nazev = nazev;
        this.autor = autor;
        this.datum = datum;
    }

    public Clanek(Long id, String nazev, String autor, LocalDate datum) {
        this.id = id;
        this.nazev = nazev;
        this.autor = autor;
        this.datum = datum;
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

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate newValue) {
        datum = newValue;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String newValue) {
        autor = newValue;
    }

    @Override
    public String toString() {
        return "Clanek{" +
                "id=" + id +
                ", nazev='" + nazev + '\'' +
                ", autor='" + autor + '\'' +
                ", datum=" + datum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clanek)) return false;

        Clanek clanek = (Clanek) o;

        return id != null ? id.equals(clanek.id) : clanek.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
