package cz.czechitas.webapp;

public class PoctyForm {

    private double cislo1;
    private double cislo2;

    public PoctyForm() {
    }

    public PoctyForm(double cislo1, double cislo2) {
        this.cislo1 = cislo1;
        this.cislo2 = cislo2;
    }

    public double getCislo1() {
        return cislo1;
    }

    public void setCislo1(double newValue) {
        cislo1 = newValue;
    }

    public double getCislo2() {
        return cislo2;
    }

    public void setCislo2(double newValue) {
        cislo2 = newValue;
    }
}
