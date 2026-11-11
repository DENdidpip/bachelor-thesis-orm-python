package vsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/edudb", "edu", "edu4edu");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM kniha");
        List<Kniha> zoznam = new ArrayList<>();
        while (rs.next()) {
            Kniha k = new Kniha();
            k.nazov = rs.getString(1);
            k.cena = rs.getInt(2);
            zoznam.add(k);
        }
        for (Kniha k : zoznam) {
            System.out.println("nazov=" + k.nazov + "\tcena=" + k.cena);
        }
        con.close();
    }
}
