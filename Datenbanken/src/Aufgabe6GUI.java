import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Aufgabe6GUI extends JFrame implements ActionListener {
    String name = "dbsys08";
    String passwd = "dbsys08Passwort";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rset = null;

    String land = null;
    String anreise = null;
    String abreise = null;
    String ausstattung = null;

    String kundeEmail = "petermaier@gmx.de";
    String fwname = null;

    // String[] laender = getCountry();
    // String[] austattungen = {"-", "Sauna"};

    JPanel panel = new JPanel();
    JPanel panel_1 = new JPanel();
    JPanel contentPane;

    JTextField tfAnreise = new JTextField("YYYY-MM-DD");
    JTextField tfAbreise = new JTextField("YYYY-MM-DD");
    JLabel lblLand = new JLabel("Land: ");
    JLabel lblAusstattung = new JLabel("Ausstattung:");
    JLabel lblAnreise = new JLabel("Anreise:");
    JLabel lblAbreise = new JLabel("Abreise:");
    JComboBox cbLand = new JComboBox(getCountry());
    JComboBox cbAus = new JComboBox(getAusstattung());
    JButton btnSuchen = new JButton("Suchen");
    JButton btnBuchen = new JButton("Buchen");

    JScrollPane scrollPane = new JScrollPane();
    JList<String> list = new JList();


    public static void main(String[] args) {
        Aufgabe6GUI frame = new Aufgabe6GUI();
        frame.setVisible(true);
    }

    public Object[] getAusstattung() {
        DefaultListModel<String> result = new DefaultListModel<>();
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); // Treiber laden
            String url = "jdbc:oracle:thin:@oracle12c.in.htwg-konstanz.de:1521:ora12c"; // String für DB-Connection
            conn = DriverManager.getConnection(url, name, passwd); // Verbindung erstellen

            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); // Transaction Isolation-Level setzen
            conn.setAutoCommit(false);

            stmt = conn.createStatement(); // Statement-Objekt erzeugen

            String mySelectQuery = "SELECT * FROM ausstattung";

            rset = stmt.executeQuery(mySelectQuery);

            while (rset.next()) {
                System.out.println(rset.getString("ausstattungsname"));
                result.addElement(rset.getString("ausstattungsname"));
            }


        } catch (SQLException se) {
            System.out.println();
            System.out.println("SQL Exception occured while establishing connection to DBS: " + se.getMessage());
            System.out.println("- SQL state  : " + se.getSQLState());
            System.out.println("- Message    : " + se.getMessage());
            System.out.println("- Vendor code: " + se.getErrorCode());
            System.out.println();
            System.out.println("EXITING WITH FAILURE ... !!!");
            System.out.println();
            try {
                conn.rollback();
            } catch (SQLException se1) {
                se1.printStackTrace();
            }
            System.exit(-1);
        }

        return result.toArray();
    }

    public Object[] getCountry() {
        DefaultListModel<String> result = new DefaultListModel<>();
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); // Treiber laden
            String url = "jdbc:oracle:thin:@oracle12c.in.htwg-konstanz.de:1521:ora12c"; // String für DB-Connection
            conn = DriverManager.getConnection(url, name, passwd); // Verbindung erstellen

            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); // Transaction Isolation-Level setzen
            conn.setAutoCommit(false);

            stmt = conn.createStatement(); // Statement-Objekt erzeugen

            String mySelectQuery = "SELECT * FROM land";

            rset = stmt.executeQuery(mySelectQuery);

            while (rset.next()) {
                System.out.println(rset.getString("landname"));
                result.addElement(rset.getString("landname"));
            }


        } catch (SQLException se) {
            System.out.println();
            System.out.println("SQL Exception occured while establishing connection to DBS: " + se.getMessage());
            System.out.println("- SQL state  : " + se.getSQLState());
            System.out.println("- Message    : " + se.getMessage());
            System.out.println("- Vendor code: " + se.getErrorCode());
            System.out.println();
            System.out.println("EXITING WITH FAILURE ... !!!");
            System.out.println();
            try {
                conn.rollback();
            } catch (SQLException se1) {
                se1.printStackTrace();
            }
            System.exit(-1);
        }

        return result.toArray();

    }


    public Aufgabe6GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 630, 455);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        panel.setBounds(5, 5, 606, 199);
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        contentPane.add(panel);
        panel.setLayout(null);

        tfAnreise.setBounds(135, 109, 190, 35);
        panel.add(tfAnreise);
        tfAnreise.setColumns(10);

        tfAbreise.setBounds(135, 154, 190, 35);
        panel.add(tfAbreise);
        tfAbreise.setColumns(10);

        cbLand.setBounds(135, 25, 190, 32);
        panel.add(cbLand);

        lblLand.setBounds(10, 25, 115, 32);
        panel.add(lblLand);

        cbAus.setBounds(135, 67, 190, 32);
        panel.add(cbAus);

        lblAusstattung.setBounds(10, 67, 115, 32);
        panel.add(lblAusstattung);

        lblAnreise.setBounds(10, 109, 115, 32);
        panel.add(lblAnreise);

        lblAbreise.setBounds(10, 157, 115, 32);
        panel.add(lblAbreise);

        btnSuchen.setBounds(417, 67 + 20, 85, 21);
        panel.add(btnSuchen);

        btnBuchen.setBounds(417, 116 + 20, 85, 21);
        panel.add(btnBuchen);

        panel_1.setBounds(5, 214, 601, 199);
        panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        contentPane.add(panel_1);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

        panel_1.add(scrollPane);
        scrollPane.setViewportView(list);

        btnSuchen.addActionListener(this);
        btnBuchen.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //----------------------------------------------------------------------------------------------------------
        if (e.getSource() == btnSuchen) {
            if (tfAnreise.getText().equals("YYYY-MM-DD") || tfAbreise.getText().equals("YYYY-MM-DD")) {
                tfAnreise.setText("Bitte korrektes Datum eingeben.");
                tfAnreise.setBackground(Color.RED);
                tfAnreise.setBackground(Color.RED);
            } else {
                tfAnreise.setBackground(Color.WHITE);
                tfAbreise.setBackground(Color.WHITE);
                System.out.println("Suchen pressed");

                land = cbLand.getSelectedItem().toString();
                anreise = tfAnreise.getText();
                abreise = tfAbreise.getText();
                ausstattung = cbAus.getSelectedItem().toString();

                if (ausstattung.equals("-"))
                    ausstattung = null;

                try {
                    if (ausstattung != null) {
                        DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); // Treiber laden
                        String url = "jdbc:oracle:thin:@oracle12c.in.htwg-konstanz.de:1521:ora12c"; // String für DB-Connection
                        conn = DriverManager.getConnection(url, name, passwd); // Verbindung erstellen

                        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); // Transaction Isolation-Level setzen
                        conn.setAutoCommit(false);

                        stmt = conn.createStatement(); // Statement-Objekt erzeugen

                        String mySelectQuery = "CREATE VIEW FerienwohnungInSpanien AS "
                                + "SELECT fwname "
                                + "FROM ferienwohnung NATURAL JOIN istausgestattet NATURAL JOIN land "
                                + "WHERE ausstattungsname = '" + ausstattung + "' "
                                + "AND landname = '" + land + "'";

                        stmt.executeQuery(mySelectQuery);

                        mySelectQuery = "CREATE VIEW GebuchtInSpanien AS "
                                + "SELECT fwname, abreise, anreise "
                                + "FROM buchung NATURAL JOIN FerienwohnungInSpanien "
                                + "WHERE (anreise BETWEEN TO_DATE('" + anreise + "','yyyy-mm-dd') "
                                + "AND TO_DATE('" + abreise + "','yyyy-mm-dd')) "
                                + "OR (anreise < TO_DATE('" + anreise + "','yyyy-mm-dd') "
                                + "AND abreise > TO_DATE('" + abreise + "','yyyy-mm-dd')) "
                                + "OR (abreise BETWEEN TO_DATE('" + anreise + "','yyyy-mm-dd') "
                                + "AND TO_DATE('" + abreise + "','yyyy-mm-dd'))";

                        stmt.executeQuery(mySelectQuery);


                        mySelectQuery = "SELECT ferienwohnung.fwname, AVG(nvl(anzahlsterne,0)) avg "
                                + "FROM ferienwohnung LEFT OUTER JOIN buchung ON ferienwohnung.fwname = buchung.fwname "
                                + "WHERE ferienwohnung.fwname NOT IN (SELECT fwname FROM GebuchtInSpanien) "
                                + "AND ferienwohnung.fwname IN (SELECT fwname FROM FerienwohnungInSpanien) "
                                + "GROUP BY ferienwohnung.fwname "
                                + "ORDER BY AVG(nvl(anzahlsterne,0)) DESC";

                        rset = stmt.executeQuery(mySelectQuery);

                        DefaultListModel<String> result = new DefaultListModel<>();

                        while (rset.next()) {
                            System.out.println(rset.getString("fwname") + ": " + rset.getDouble("avg"));
                            result.addElement(rset.getString("fwname") + ": " + rset.getDouble("avg"));
                        }

                        list = new JList<>(result);
                        scrollPane.setViewportView(list);


                        mySelectQuery = "DROP VIEW FerienwohnungInSpanien";

                        stmt.executeQuery(mySelectQuery);

                        mySelectQuery = "DROP VIEW GebuchtInSpanien";

                        stmt.executeQuery(mySelectQuery);

                        stmt.close();
                        conn.commit();
                        conn.close();
                    } else {
//---------------------------------------------------------------------------------------------------------------------------
                        DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); // Treiber laden
                        String url = "jdbc:oracle:thin:@oracle12c.in.htwg-konstanz.de:1521:ora12c"; // String für DB-Connection
                        conn = DriverManager.getConnection(url, name, passwd); // Verbindung erstellen

                        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); // Transaction Isolation-Level setzen
                        conn.setAutoCommit(false);

                        stmt = conn.createStatement(); // Statement-Objekt erzeugen

                        String mySelectQuery = "CREATE VIEW FerienwohnungInSpanien AS "
                                + "SELECT fwname "
                                + "FROM ferienwohnung NATURAL JOIN land "
                                + "WHERE landname = '" + land + "'";

                        stmt.executeQuery(mySelectQuery);

                        mySelectQuery = "CREATE VIEW GebuchtInSpanien AS "
                                + "SELECT fwname, abreise, anreise "
                                + "FROM buchung NATURAL JOIN FerienwohnungInSpanien "
                                + "WHERE (anreise BETWEEN TO_DATE('" + anreise + "','yyyy-mm-dd') "
                                + "AND TO_DATE('" + anreise + "','yyyy-mm-dd')) "
                                + "OR (anreise < TO_DATE('" + anreise + "','yyyy-mm-dd') "
                                + "AND abreise > TO_DATE('" + abreise + "','yyyy-mm-dd')) "
                                + "OR (abreise BETWEEN TO_DATE('" + anreise + "','yyyy-mm-dd') "
                                + "AND TO_DATE('" + abreise + "','yyyy-mm-dd'))";

                        stmt.executeQuery(mySelectQuery);


                        mySelectQuery = "SELECT ferienwohnung.fwname, AVG(nvl(anzahlsterne,0)) avg "
                                + "FROM ferienwohnung LEFT OUTER JOIN buchung ON ferienwohnung.fwname = buchung.fwname "
                                + "WHERE ferienwohnung.fwname NOT IN (SELECT fwname FROM GebuchtInSpanien) "
                                + "AND ferienwohnung.fwname IN (SELECT fwname FROM FerienwohnungInSpanien) "
                                + "GROUP BY ferienwohnung.fwname "
                                + "ORDER BY AVG(nvl(anzahlsterne,0)) DESC";

                        rset = stmt.executeQuery(mySelectQuery);

                        DefaultListModel<String> result = new DefaultListModel<>();

                        while (rset.next()) {
                            System.out.println(rset.getString("fwname") + ": " + rset.getDouble("avg"));
                            result.addElement(rset.getString("fwname") + ": " + rset.getDouble("avg"));
                        }

                        list = new JList<>(result);
                        scrollPane.setViewportView(list);

                        mySelectQuery = "DROP VIEW FerienwohnungInSpanien";

                        stmt.executeQuery(mySelectQuery);

                        mySelectQuery = "DROP VIEW GebuchtInSpanien";

                        stmt.executeQuery(mySelectQuery);

                        stmt.close();
                        conn.commit();
                        conn.close();
                    }

                } catch (SQLException se) {
                    System.out.println();
                    System.out.println("SQL Exception occured while establishing connection to DBS: " + se.getMessage());
                    System.out.println("- SQL state  : " + se.getSQLState());
                    System.out.println("- Message    : " + se.getMessage());
                    System.out.println("- Vendor code: " + se.getErrorCode());
                    System.out.println();
                    System.out.println("EXITING WITH FAILURE ... !!!");
                    System.out.println();
                    try {
                        conn.rollback();
                    } catch (SQLException se1) {
                        se1.printStackTrace();
                    }
                    System.exit(-1);
                }
            }
        }
//-------------------------------------------------------------------------------------------------------------------
        if(e.getSource() == btnBuchen) {
            try {
                fwname = list.getSelectedValue().toString().split(":")[0];

                if (kundeEmail == null || fwname == null || anreise == null || abreise == null) {
                    System.out.println("Nicht ausreichende Eingabe!");
                    System.exit(-1);
                }

                DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); // Treiber laden
                String url = "jdbc:oracle:thin:@oracle12c.in.htwg-konstanz.de:1521:ora12c"; // String für DB-Connection
                conn = DriverManager.getConnection(url, name, passwd); // Verbindung erstellen

                conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED); // Transaction Isolation-Level setzen
                conn.setAutoCommit(false);

                stmt = conn.createStatement(); // Statement-Objekt erzeugen

                String mySelectQuery = "CREATE VIEW Buchungen AS "
                        + "SELECT fwname, abreise, anreise "
                        + "FROM buchung "
                        + "WHERE (anreise BETWEEN TO_DATE('" + anreise + "','yyyy-mm-dd') "
                        + "AND TO_DATE('" + anreise + "','yyyy-mm-dd')) "
                        + "OR (anreise < TO_DATE('" + anreise + "','yyyy-mm-dd') "
                        + "AND abreise > TO_DATE('" + abreise + "','yyyy-mm-dd')) "
                        + "OR (abreise BETWEEN TO_DATE('" + anreise + "','yyyy-mm-dd') "
                        + "AND TO_DATE('" + abreise + "','yyyy-mm-dd'))";

                stmt.executeQuery(mySelectQuery);

                mySelectQuery = "SELECT * FROM Buchungen";

                rset = stmt.executeQuery(mySelectQuery);

                while (rset.next()) {
                    System.out.println(rset.getString("fwname"));
                    if (rset.getString("fwname").equals(fwname)) {
                        System.out.println("Bereits gebucht.");
                        mySelectQuery = "DROP VIEW Buchungen";
                        stmt.executeQuery(mySelectQuery);

                        try {
                            conn.rollback();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        return;
                    }
                }
                int createID1 = 0;
                int createID2 = 0;
                String createID;

                mySelectQuery = "SELECT COUNT(*) AS AnzahlBuchungen FROM Buchung";
                rset = stmt.executeQuery(mySelectQuery);

                while (rset.next()) {
                    createID1 = rset.getInt("AnzahlBuchungen");
                }

                mySelectQuery = "SELECT COUNT(*) AS AnzahlStornoBuchungen FROM StornierteBuchung";
                rset = stmt.executeQuery(mySelectQuery);

                while (rset.next()) {
                    createID2 = rset.getInt("AnzahlStornoBuchungen");
                }

                createID = Integer.toString((createID1 + createID2) + 1);

                mySelectQuery = "INSERT INTO Buchung(buchungsnummer, buchungsdatum, anreise, abreise, fwname, email, "
                        + "rechnungsnummer, rechnungsbetrag, bewertungsdatum, anzahlsterne) "
                        + "VALUES('" + createID + "', CURRENT_DATE, "
                        + "TO_DATE('" + anreise + "', 'yyyy-mm-dd'), TO_DATE('" + abreise + "', 'yyyy-mm-dd'), "
                        + "'" + fwname + "', '" + kundeEmail + "', '555', 1000, TO_DATE('2021-09-18', 'yyyy-mm-dd'), 3)";

                stmt.executeQuery(mySelectQuery);

                mySelectQuery = "SELECT * FROM Buchung B WHERE b.buchungsnummer = " + createID;

                rset = stmt.executeQuery(mySelectQuery);

                System.out.println("Buchung wurde getätigt: ");

                while (rset.next()) {
                    System.out.println(rset.getInt("buchungsnummer") + " "
                            + rset.getDate("buchungsdatum") + " "
                            + rset.getDate("anreise") + " "
                            + rset.getDate("abreise") + " "
                            + rset.getString("fwname") + " "
                            + rset.getString("email") + " "
                            + rset.getInt("rechnungsnummer") + " "
                            + rset.getInt("rechnungsbetrag") + " "
                            + rset.getDate("bewertungsdatum") + " "
                            + rset.getInt("anzahlsterne"));

                }

                mySelectQuery = "DROP VIEW Buchungen";

                stmt.executeQuery(mySelectQuery);

                stmt.close();
                conn.commit();
                conn.close();

                btnBuchen.setBackground(Color.GREEN);
            } catch (SQLException se) {
                System.out.println();
                System.out.println("SQL Exception occured while establishing connection to DBS: " + se.getMessage());
                System.out.println("- SQL state  : " + se.getSQLState());
                System.out.println("- Message    : " + se.getMessage());
                System.out.println("- Vendor code: " + se.getErrorCode());
                System.out.println();
                System.out.println("EXITING WITH FAILURE ... !!!");
                System.out.println();
                try {
                    conn.rollback();
                } catch (SQLException se1) {
                    se1.printStackTrace();
                }
                System.exit(-1);
            }
        }
    }
}
