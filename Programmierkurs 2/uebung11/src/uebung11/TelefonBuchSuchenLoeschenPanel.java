// Dennis Agostinho da Silva
// 17.11.2019

package uebung11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.List;

public class TelefonBuchSuchenLoeschenPanel
        extends JPanel implements ActionListener {

    private TelefonBuch telBuch;
    private JTextField tfSuchLöschName;
    private JTextField tfSuchLöschZusatz;
    private JButton buttonAnwenden;
    private JComboBox feldSuchenLöschen;
    private JTextArea textArea;
    private int anwendung;

    public TelefonBuchSuchenLoeschenPanel(TelefonBuch tb) {
        telBuch = tb;

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 1));
        panel1.add(new JLabel("Name"));
        panel1.add(new JLabel("Zusatz"));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 1));
        tfSuchLöschName = new JTextField("", 20);
        panel2.add(tfSuchLöschName);
        tfSuchLöschZusatz = new JTextField("", 20);
        panel2.add(tfSuchLöschZusatz);



        JPanel panel4 = new JPanel();
        Border border = BorderFactory.createTitledBorder("Suchen/Löschen");
        panel4.setBorder(border);
        panel4.add(panel1);
        panel4.add(panel2);
        String[] suchStrings = {"Exakte Suche", "Prefix Suche", "Löschen"};
        feldSuchenLöschen = new JComboBox(suchStrings);
        panel4.add(feldSuchenLöschen);
        feldSuchenLöschen.addActionListener(this);
        buttonAnwenden = new JButton("Anwenden");
        panel4.add(buttonAnwenden);
        buttonAnwenden.addActionListener(this);

        JPanel panel3 = new JPanel();
        Border border1 = BorderFactory.createTitledBorder("Ausgabe");
        panel3.setBorder(border1);
        textArea = new JTextArea(20,50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 25, 255, 63);
        scrollPane.setViewportView(textArea);
        panel3.add(scrollPane);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(panel4);
        this.add(panel3);

    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == buttonAnwenden) {
            System.out.println("Anwenden");
            anwenden();
        } else if (source == feldSuchenLöschen) {
            switch (feldSuchenLöschen.getSelectedIndex()) {
                case 0:
                    System.out.println("Exakte Suche");
                    anwendung = 0;
                    break;
                case 1:
                    System.out.println("Prefix Suche");
                    anwendung = 1;
                    break;
                case 2:
                    System.out.println("Löschen");
                    anwendung = 2;
                    break;
                default:
                    System.out.println("Fehler");
            }
        }
    }
    private void anwenden() {
        switch (anwendung) {
            case 0:
                if (telBuch.exactSearch(tfSuchLöschName.getText(),
                        tfSuchLöschZusatz.getText()) == null) {
                    JOptionPane.showMessageDialog(this,
                            "Eintrag nicht gefunden");
                    break;
                }
                textArea.setText(telBuch.exactSearch(tfSuchLöschName.getText(),
                        tfSuchLöschZusatz.getText()));
                System.out.println(telBuch.exactSearch(tfSuchLöschName.getText(),
                        tfSuchLöschZusatz.getText()));
                System.out.println("Exakt");
                break;
            case 1:
            	if (telBuch.prefixSearch((tfSuchLöschName.getText())) == null) {
                    JOptionPane.showMessageDialog(this,
                            "Eintrag nicht gefunden");
                    break;
                }
                textArea.setText("");
                for (String x : telBuch.prefixSearch((tfSuchLöschName.getText()))) {

                    textArea.append(x + "\n");
                    System.out.println(x);
                }
                System.out.println("Prefix");
                break;
            case 2:
                if (!telBuch.remove(tfSuchLöschName.getText(),tfSuchLöschZusatz.getText())) {
                    JOptionPane.showMessageDialog(this,
                            "Eintrag nicht gefunden");
                    break;
                }
                JOptionPane.showMessageDialog(this,
                        "Löschen erfolgreich!");
                System.out.println("Löschen");
                break;
            default:
                System.out.println("Fehler");
        }
    }

}
