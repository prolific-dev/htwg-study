import java.io.*;
import java.sql.*;

public class Aufgabe6Teil2 {

    public static void main(String[] args) {
        String name = "dbsys08";
        String passwd = "dbsys08Passwort";
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        String kundeEmail = "petermaier@gmx.de";
        String fwname = "Chateux de mer";
        String anreise = "2020-04-25";
        String abreise = "2020-04-30";

/*        try {
            System.out.println("Kundenemail: ");
            kundeEmail = in.readLine();
            System.out.println("Ferienwohnung: ");
            fwname = in.readLine();
            System.out.println("Anreise(YYYY-MM-DD): ");
            anreise = in.readLine();
            System.out.println("Abreise(YYYY-MM-DD): ");
            abreise = in.readLine();
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Eingabe: " + e);
            System.exit(-1);
        }*/


        System.out.println();

        if (kundeEmail == null || fwname == null || anreise == null || abreise == null) {
            System.out.println("Nicht ausreichende Eingabe!");
            System.exit(-1);
        }

        try {
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
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    System.exit(-1);
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.exit(-1);
        }
    }

}