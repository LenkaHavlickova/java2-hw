package cz.czechitas.webapp;

import java.time.*;
import org.springframework.format.annotation.*;

public class DetailForm {

    private String nazev;

    private String autor;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate datum;

    
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

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate newValue) {
        datum = newValue;
    }

}
