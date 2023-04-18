package cz.czechitas.simplesthttp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.springframework.web.client.*;
import net.miginfocom.swing.*;

public class HlavniOkno extends JFrame {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    JButton btnZjistiCas;
    JLabel labPresnyCas;
    JTextField editPresnyCas;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    JPanel contentPane;
    MigLayout migLayoutManager;

    public HlavniOkno() {
        initComponents();
    }

    private void priStiskuBtnZjistiCas(ActionEvent e) {
        RestTemplate httpKlient = new RestTemplate();
        String presnyCas = httpKlient.getForObject(
                "http://localhost:51423/simplest-http/presny-cas",
                String.class);
        editPresnyCas.setText(presnyCas);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        btnZjistiCas = new JButton();
        labPresnyCas = new JLabel();
        editPresnyCas = new JTextField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("35-Presny_cas+Java");
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets rel,hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[grow,fill]",
            // rows
            "[]" +
            "[fill]" +
            "[]"));
        this.contentPane = (JPanel) this.getContentPane();
        this.contentPane.setBackground(this.getBackground());
        LayoutManager layout = this.contentPane.getLayout();
        if (layout instanceof MigLayout) {
            this.migLayoutManager = (MigLayout) layout;
        }

        //---- btnZjistiCas ----
        btnZjistiCas.setText("Obnovit \u010das");
        btnZjistiCas.setFont(btnZjistiCas.getFont().deriveFont(btnZjistiCas.getFont().getSize() + 5f));
        btnZjistiCas.addActionListener(e -> priStiskuBtnZjistiCas(e));
        contentPane.add(btnZjistiCas, "cell 1 0");

        //---- labPresnyCas ----
        labPresnyCas.setText("Aktu\u00e1ln\u00ed \u010das je:");
        labPresnyCas.setFont(labPresnyCas.getFont().deriveFont(labPresnyCas.getFont().getSize() + 5f));
        contentPane.add(labPresnyCas, "cell 0 1");

        //---- editPresnyCas ----
        editPresnyCas.setColumns(20);
        editPresnyCas.setFont(editPresnyCas.getFont().deriveFont(editPresnyCas.getFont().getSize() + 5f));
        contentPane.add(editPresnyCas, "cell 1 1 2 1");
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}
