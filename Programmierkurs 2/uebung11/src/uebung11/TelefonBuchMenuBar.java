// Dennis Agostinho da Silva
// 17.11.2019

package uebung11;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;


public class TelefonBuchMenuBar
        extends JMenuBar implements ActionListener {

    private TelefonBuch telBuch;
    private JMenuBar menuBar;
    private JMenuItem lesen,speichern,beenden;
    private String[] options = {"Nein", "Ja"};

    public TelefonBuchMenuBar(TelefonBuch tb) {
        telBuch = tb;

        JMenu menu1 = new JMenu("Datei");
        menu1.setMnemonic(KeyEvent.VK_D);
        lesen = new JMenuItem("TelefonBuch lesen ...", KeyEvent.VK_L);
        lesen.addActionListener(this);
        menu1.add(lesen);
        speichern = new JMenuItem("TelefonBuch speichern ...", KeyEvent.VK_S);
        speichern.addActionListener(this);
        menu1.add(speichern);
        beenden = new JMenuItem("TelefonBuch beenden", KeyEvent.VK_B);
        beenden.addActionListener(this);
        menu1.add(beenden);
        menuBar = new JMenuBar();
        menuBar.add(menu1);
        this.add(menuBar);

    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == lesen) {
            System.out.println("Lesen");
            String userDir = System.getProperty("user.home");
            JFileChooser fc = new JFileChooser(userDir +"/Desktop");
            int i = fc.showOpenDialog(null);
            if(i == JFileChooser.APPROVE_OPTION) {
                telBuch.read(fc.getSelectedFile());
            }
        } else if (source == speichern) {
            System.out.println("Speichern");
            String userDir = System.getProperty("user.home");
            JFileChooser fc = new JFileChooser(userDir +"/Desktop");
            int i = fc.showOpenDialog(null);
            if(i == JFileChooser.APPROVE_OPTION) {
                telBuch.save(fc.getSelectedFile());
            }
        } else if (source == beenden) {
            System.out.println("Beenden");
            int ende = JOptionPane.showConfirmDialog(this,
                    "Programm beenden?",
                    "Beenden",JOptionPane.YES_NO_OPTION);
            if (ende == JOptionPane.YES_OPTION) {
                System.out.println("Beenden");
                System.exit(0);
            } else if (ende == JOptionPane.NO_OPTION){
                System.out.println("Nicht Beenden");
            }
        }
    }
}

