package cz.czechitas.seznamy;

import java.util.*;
import java.util.concurrent.*;

//cz.czechitas.java.SpousteciTrida
public class SpousteciTrida {

    public static void main(String[] args) {
        List<String> jmena;
        jmena = new ArrayList<String>();
//        jmena = new CopyOnWriteArrayList<String>();
//        jmena = new LinkedList<String>();

        String nejlepsiJmeno = "Clark";

//        jmena = Arrays.asList("Karel", "Brunhilda", "Vladimíra", "Alexandra", "Brunhilda");

        Scanner nacitacZKlavesnice = new Scanner(System.in);
        while (true) {
            String radek = nacitacZKlavesnice.nextLine();
            if (radek.equals("")) {
                break;
            }
            jmena.add(radek);
        }

//        jmena.add("Kamil");
//        jmena.add("Zuzanka");
//        jmena.add("Adélka");
//        jmena.add("Hildegarda");
//        jmena.add(nejlepsiJmeno);
//        jmena.remove(0);
        Collections.shuffle(jmena);
        Comparator<String> porovnavac = new DelkovyComparator();
        Collections.sort(jmena, porovnavac);

        String druheJmeno = jmena.get(1);
        System.out.println(druheJmeno);
        System.out.println(jmena.get(2));
        System.out.println(jmena.size());
        System.out.println(jmena);

        for (int i = 0; i < jmena.size(); i++) {
            String jedenPrvek = jmena.get(i);
            System.out.println(jedenPrvek);
        }

        for (String jednoJmeno : jmena) {
            System.out.println(jednoJmeno);
        }

        System.out.println(jmena.indexOf("Brunhilda"));
        System.out.println(jmena.indexOf("Brunhilda"));

    }

}
