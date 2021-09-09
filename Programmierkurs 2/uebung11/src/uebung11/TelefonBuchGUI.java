// Dennis Agostinho da Silva
// 17.11.2019

package uebung11;

import javax.swing.*;
import java.awt.*;

public class TelefonBuchGUI extends JFrame {

    private TelefonBuch telBuch;

    public TelefonBuchGUI() {
        // TelefonBuch anlegen:
        telBuch = new TelefonBuch();


        // Menuleiste einbauen:
        // ...
        TelefonBuchMenuBar datei = new TelefonBuchMenuBar(telBuch);
        this.setJMenuBar(datei);

        // mainPanel mit Umrandung versehen und das
        // Einfuegen- und  SuchenLoeschenPanel einbauen:
        TelefonBuchEinfuegenPanel einfuegenPanel = new TelefonBuchEinfuegenPanel(telBuch);
        TelefonBuchSuchenLoeschenPanel suchenLoeschenPanel = new TelefonBuchSuchenLoeschenPanel(telBuch);


        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(einfuegenPanel);
        mainPanel.add(suchenLoeschenPanel);
        // ...
        this.setContentPane(mainPanel);

        // Sonstige Eigenschaften des Hauptfenster setzen:
        this.setResizable(true);
        this.setTitle("Telefonbuch");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TelefonBuchGUI();
    }
}
