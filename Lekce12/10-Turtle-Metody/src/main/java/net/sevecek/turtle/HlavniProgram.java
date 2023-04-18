package net.sevecek.turtle;

import java.awt.*;
import java.util.concurrent.*;
import net.sevecek.turtle.engine.*;

public class HlavniProgram {

    private static Color ZELENA = new Color(16, 128, 29);
    private static Color CERVENA = new Color(205, 2, 15);
    private static Color ORANZOVA = new Color(255, 167, 72);
    private static Color BEZOVA = new Color(214, 208, 137);
    private static Color TMAVE_MODRA = new Color(103, 156, 251);
    private static Color SVETLE_MODRA = new Color(206, 232, 249);
    private static Color TYRKIS = new Color(29, 191, 105);
    private static Color SEDA = new Color(163, 190, 197);
    private static Color CERNA = new Color(54, 12, 12);
    private static double VELIKOST_KOMPOZICE = 80.0;

    private Turtle leonardo;
    private Turtle raphael;
    private Turtle michelangelo;
    private Turtle donatello;

    public void main(String[] args) {
        leonardo = new Turtle();
        michelangelo = new Turtle();
        raphael = new Turtle();
        donatello = new Turtle();
        ExecutorService paralelniSpoustec;
        paralelniSpoustec = Executors.newCachedThreadPool();
        paralelniSpoustec.execute(this::kresliLeonardem);
        paralelniSpoustec.execute(this::kresliMichelangelem);
        paralelniSpoustec.execute(this::kresliRaphaelem);
        paralelniSpoustec.execute(this::kresliDonatellem);
        paralelniSpoustec.shutdown();
    }

    public void kresliLeonardem() {
        leonardo.setLocation(450.0, 450.0);
        leonardo.penUp();
        leonardo.turnRight(90.0);
        leonardo.move(VELIKOST_KOMPOZICE * 4.0);
        leonardo.turnRight(90.0);
        leonardo.move(VELIKOST_KOMPOZICE * 0.5);
        leonardo.turnRight(180.0);
        kresliVagon(leonardo, VELIKOST_KOMPOZICE, TYRKIS);
        leonardo.move(VELIKOST_KOMPOZICE * 4.125);
        leonardo.turnLeft(90.0);
        leonardo.move(VELIKOST_KOMPOZICE * 1.26);
        leonardo.turnLeft(180.0);
    }

    public void kresliMichelangelem() {
        michelangelo.setLocation(450.0, 450.0);
        michelangelo.penUp();
        michelangelo.turnLeft(90.0);
        michelangelo.move(VELIKOST_KOMPOZICE * 2.0);
        michelangelo.turnRight(90.0);
        michelangelo.move(VELIKOST_KOMPOZICE * 2.0);
        kresliSnehulaka(michelangelo, VELIKOST_KOMPOZICE * 0.8, ZELENA);
    }

    public void kresliRaphaelem() {
        raphael.setLocation(400.0, 250.0);
        kresliZmrzlinu(raphael, VELIKOST_KOMPOZICE * 0.8, CERVENA);
        raphael.turnRight(180.0);
        raphael.move(VELIKOST_KOMPOZICE * 3.5);
        raphael.turnRight(90.0);
        raphael.move(VELIKOST_KOMPOZICE * 0.15);
        raphael.turnRight(90.0);
    }

    public void kresliDonatellem() {
        donatello.setLocation(450.0, 450.0);
        kresliVlacek(donatello, VELIKOST_KOMPOZICE, TYRKIS);
        kresliMrak(donatello, VELIKOST_KOMPOZICE);
        donatello.turnRight(80.0);
        donatello.move(VELIKOST_KOMPOZICE * 5.16);
        donatello.turnRight(90.0);
        donatello.move(VELIKOST_KOMPOZICE * 9.05);
        donatello.turnRight(90.0);

    }

    public void kresliZmrzlinu(Turtle zelva, double velikost, Color barva) {
        kresliKolecko(zelva, velikost * 0.5, barva);
        zelva.penUp();
        zelva.move(velikost / 100.0 * 8.0);
        zelva.turnRight(90.0);
        zelva.move(velikost / 100.0 * 25.0);
        zelva.turnRight(90.0);
        kresliTrojuhelnik(zelva, velikost, 30.0, BEZOVA);
        zelva.move(velikost * 0.8);
        zelva.turnRight(180.0);
    }

    public void kresliMrak(Turtle zelva, double velikost) {
        for (int i = 0; i < 10; i++) {
            zelva.penDown();
            kresliTrojuhelnik(zelva, velikost * 0.2 * (i * 0.5), 45.0, SEDA);
            zelva.turnRight(10.0);
            zelva.penUp();
            zelva.move(velikost * 0.2 * i);
        }
    }

    public void kresliVagon(Turtle zelva, double velikost, Color barva) {
        kresliObdelnik(zelva, velikost * 3.0, velikost * 0.4, barva);
        zelva.turnLeft(270.0);
        for (int i = 0; i < 3; i++) {
            zelva.move(velikost * 0.8);
            zelva.turnRight(90.0);
            kresliKolecko(zelva, velikost / 1.8, SEDA);
            zelva.turnLeft(90.0);
        }
        zelva.penUp();
        zelva.turnRight(90.0);
        zelva.move(velikost * 0.35);
        zelva.turnRight(90.0);
        zelva.penDown();
        zelva.move(velikost * 1.6);
        zelva.penUp();
        zelva.turnRight(180.0);
        zelva.move(velikost * 2.5);
        zelva.turnLeft(90.0);
        zelva.move(velikost);
        zelva.turnLeft(90.0);

        kreslijahody(zelva, velikost);

        zelva.move(velikost * 0.5);
        zelva.turnLeft(90.0);
        zelva.move(velikost * 0.5);
        zelva.turnRight(90.0);
        zelva.setPenColor(SEDA);
        zelva.penDown();
        zelva.move(velikost * 0.2);
        zelva.penUp();

    }

    public void kreslijahody(Turtle zelva, double velikost) {
        for (double i = 0.0; i < 4.0; i++) {
            zelva.move(velikost);
            zelva.turnRight(90.0);
            kresliTrojuhelnik(zelva, velikost * 0.9, 45.0, CERVENA);
            zelva.penUp();
            for (double b = 1.0; b < 4.0; b++) {
                zelva.move(velikost * 0.2);
                zelva.turnRight(90.0);

                for (double a = 0.0; a < 4.0 - b; a++) {
                    zelva.move(velikost * 0.16);
                    zelva.setPenColor(CERNA);
                    zelva.penDown();
                    zelva.move(1.0);
                    zelva.penUp();
                }
                zelva.turnRight(180.0);
                zelva.move(velikost * 0.22 / (b / 2.0));
                zelva.turnRight(90.0);
            }

            zelva.turnRight(180.0);
            zelva.move(velikost * 0.65);
            zelva.turnLeft(120.0);
            zelva.move(velikost * 0.1);
            for (double c = 0.0; c < 3.0; c++) {
                zelva.turnRight(60.0);
                zelva.penDown();
                zelva.setPenColor(ZELENA);
                zelva.move(velikost * 0.3);
                zelva.penUp();
                zelva.turnRight(180.0);
                zelva.move(velikost * 0.3);
                zelva.turnRight(180.0);

            }
            zelva.turnRight(30.0);
        }
    }

    public void kresliSnehulaka(Turtle zelva, double velikost, Color barva) {
        kresliObdelnik(zelva, velikost / 1.5, velikost / 3.0, barva);
        zelva.move(velikost / 3.5);
        zelva.turnLeft(90.0);
        zelva.penDown();
        kresliPulkolecko(zelva, velikost, barva);
        zelva.penUp();
        zelva.move(velikost / 1.5);
        zelva.penDown();
        kresliPulkolecko(zelva, velikost, barva);
        zelva.penUp();
        zelva.move(velikost / 3.0);
        zelva.turnLeft(90.0);
        zelva.move(velikost / 3.5 + 1.0);

        kresliKolecko(zelva, velikost / 1.5, SVETLE_MODRA);
        zelva.penUp();
        zelva.move((velikost * 0.75) / 3.0);
        zelva.turnRight(90.0);

        for (int i = 0; i < 2; i++) {
            zelva.move((velikost / 8.5) * (1.0 + i));
            zelva.penDown();
            zelva.setPenColor(CERNA);
            zelva.move(1.0);
            zelva.penUp();
            zelva.turnRight(180.0);
        }
        zelva.move((velikost * 0.15));
        zelva.turnLeft(90.0);
        zelva.move((velikost * 0.1));
        zelva.turnLeft(90.0);
        kresliTrojuhelnik(zelva, velikost * 0.4, 20.0, ORANZOVA);
        zelva.penUp();
        zelva.move((velikost * 0.2));
        zelva.turnRight(90.0);
        zelva.move((velikost * 0.08));
        zelva.penUp();
        for (int i = 0; i < 5; i++) {
            for (int a = 0; a < 30; a++) {
                zelva.turnRight(1.0);
                zelva.move(velikost / 350.0);
            }

            zelva.penDown();
            zelva.setPenColor(CERNA);
            zelva.move(1.0 / 100.0 * velikost);
            zelva.penUp();
        }
        zelva.turnRight(120.0);
        zelva.penUp();
        zelva.move((velikost * 0.15));
        zelva.turnRight(90.0);
        zelva.move(velikost * 0.25);

        for (double i = 0.1; i < 1.4; i++) {
            kresliKolecko(zelva, (velikost) * (0.8 + i), SVETLE_MODRA);
            zelva.penUp();
            zelva.move(((velikost) * (1.0 + i)) / 4.0);
            for (double d = 0.0; d < 3.0; d++) {
                zelva.penDown();
                zelva.setPenColor(CERNA);
                zelva.move(1.0);
                zelva.penUp();
                zelva.move(((velikost * 0.85) * (1.0 + i)) / 4.0);
            }
        }

        zelva.turnRight(180.0);
        zelva.move(velikost * 2.5);
        zelva.turnRight(90.0);
        zelva.move(velikost * 0.5);
        kresliKolecko(zelva, velikost / 2.5, SVETLE_MODRA);
        zelva.penUp();
        zelva.turnLeft(180.0);
        zelva.move(velikost);
        kresliKolecko(zelva, velikost / 2.5, SVETLE_MODRA);

        zelva.turnLeft(90.0);
        zelva.move((velikost * 1.11) * (3.0));
        zelva.turnLeft(90.0);
        zelva.move((velikost * 1.12) * 2.0);
        zelva.turnLeft(90.0);
    }

    public void kresliVlacek(Turtle zelva, double velikost, Color barva) {
        zelva.turnRight(135.0);
        kresliTrojuhelnik(zelva, velikost, 90.0, SEDA);
        zelva.turnRight(45.0);
        zelva.move(velikost / 2.0);
        zelva.turnRight(180.0);
        kresliObdelnik(zelva, velikost * 2.5, velikost * 1.2, barva);
        zelva.turnLeft(270.0);
        for (int i = 1; i < 3; i++) {
            zelva.move(velikost * (0.5 * i));
            zelva.turnRight(90.0);
            kresliKolecko(zelva, velikost / 1.8, SEDA);
            zelva.turnLeft(90.0);
        }
        zelva.move(velikost);
        zelva.turnLeft(90.0);
        kresliObdelnik(zelva, velikost * 1.2, velikost * 2.5, barva);
        zelva.turnRight(90.0);
        kresliKolecko(zelva, velikost * 1.1, SEDA);
        zelva.penUp();

        zelva.turnRight(160.0);
        zelva.move(velikost);
        zelva.turnRight(20.0);
        zelva.setPenColor(SEDA);
        zelva.penDown();
        zelva.move(velikost);
        zelva.penUp();
        zelva.turnRight(180.0);
        zelva.move(velikost);
        zelva.turnLeft(20.0);
        zelva.move(velikost);
        zelva.turnLeft(70.0);

        //okno

        zelva.penUp();
        zelva.move(velikost * 1.5);
        zelva.turnRight(90.0);
        zelva.move(velikost / 3.0);
        zelva.turnLeft(90.0);
        kresliObdelnik(zelva, velikost / 2.0, (velikost / 1.5), TMAVE_MODRA);
        zelva.penUp();
        zelva.turnLeft(90.0);

        //střecha
        zelva.move(velikost / 3.0);
        zelva.turnRight(90.0);
        zelva.move(velikost);
        zelva.turnRight(90.0);
        zelva.setPenColor(SEDA);
        zelva.penDown();
        zelva.move(velikost * 1.2);
        zelva.turnLeft(120.0);
        for (int i = 0; i < 13; i++) {
            zelva.move(velikost / 8.5);
            zelva.turnLeft(10.0);
        }
        zelva.penUp();
        zelva.turnLeft(20.0);
        zelva.move(velikost * 1.3);
        zelva.turnRight(90.0);

        //komín
        zelva.move(velikost * 1.8);
        zelva.turnRight(90.0);
        kresliObdelnik(zelva, velikost / 3.0, velikost / 2.0, barva);
        zelva.move(velikost / 2.0);

    }

    public void kresliTrojuhelnik(Turtle zelva, double delkaRamen, double uhelRamen, Color barva) {
        zelva.setPenColor(barva);
        zelva.penDown();

        zelva.turnRight((uhelRamen / 2.0));
        zelva.move(delkaRamen);
        zelva.turnRight(90.0 + (90.0 - uhelRamen));
        zelva.move(delkaRamen);
        zelva.turnRight(90.0 + (uhelRamen / 2.0));
        zelva.move(Math.abs((delkaRamen * Math.sin((uhelRamen * Math.PI / 180.0) / 2.0)) * 2.0));

        zelva.turnRight(90.0);
        zelva.penUp();
    }

    public void kresliCtverec(Turtle zelva, double delkaStrany, Color barva) {
        zelva.turnRight(90.0);
        zelva.setPenColor(barva);
        zelva.penDown();
        for (int i = 0; i < 4; i++) {
            zelva.move(delkaStrany);
            zelva.turnLeft(90.0);
        }
        zelva.turnLeft(90.0);
        zelva.penUp();
    }

    public void kresliObdelnik(Turtle zelva, double delkaStrany1, double delkaStrany2, Color barva) {
        zelva.turnRight(90.0);
        zelva.setPenColor(barva);
        zelva.penDown();
        for (int i = 0; i < 2; i++) {
            zelva.move(delkaStrany1);
            zelva.turnLeft(90.0);
            zelva.move(delkaStrany2);
            zelva.turnLeft(90.0);
        }
        zelva.turnLeft(90.0);
        zelva.penUp();
    }

    public void kresliPulkolecko(Turtle zelva, double velikost, Color barva) {
        zelva.setPenColor(barva);
        for (int i = 0; i < 18; i++) {
            zelva.move((velikost / 45.0));
            zelva.turnLeft(10.0);
        }
    }

    public void kresliKolecko(Turtle zelva, double velikost, Color barva) {
        zelva.turnRight(90.0);
        zelva.setPenColor(barva);
        zelva.penDown();
        for (int i = 0; i < 36; i++) {
            zelva.move(velikost / 10.0);
            zelva.turnLeft(10.0);
        }
        zelva.turnLeft(90.0);
        zelva.penUp();
    }

}
