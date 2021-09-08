import java.io.*;
import java.sql.*;

public class Aufgabe6Teil1 {

    public static void main(String[] args) {
        String name = "dbsys08";
        String passwd = "dbsys08Passwort";
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        String land = "Spanien";
        String anreise = "2020-04-28";
        String abreise = "2020-05-05";
        String ausstattung = "Sauna";

        if (ausstattung.equals("-"))
            ausstattung = null;

        /*
        try {
            System.out.println("Land: ");
            land = in.readLine();
            System.out.println("Anreise(YYYY-MM-DD): ");
            anreise = in.readLine();
            System.out.println("Abreise(YYYY-MM-DD): ");
            abreise = in.readLine();
            System.out.println("Ausstattung(Optional, \"ohne\" für ohne Austattung: ");
            ausstattung = in.readLine();
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Eingabe: " + e);
            System.exit(-1);
        }
         */

        System.out.println();

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

                while (rset.next()) {
                    System.out.println(rset.getString("fwname") + " " + rset.getDouble("avg"));
                }


                mySelectQuery = "DROP VIEW FerienwohnungInSpanien";

                stmt.executeQuery(mySelectQuery);

                mySelectQuery = "DROP VIEW GebuchtInSpanien";

                stmt.executeQuery(mySelectQuery);

                stmt.close();
                conn.commit();
                conn.close();
            } else {

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

                while (rset.next()) {
                    System.out.println(rset.getString("fwname") + " " + rset.getDouble("avg"));
                }


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
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.exit(-1);
        }
    }
}

