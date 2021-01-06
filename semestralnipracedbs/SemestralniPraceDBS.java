package semestralnipracedbs;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;


/**
 *
 * @author User
 */
public class SemestralniPraceDBS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String databaseURL = "jdbc:sqlserver://" + "localhost" + ";database=DBS2020_KristynaKohoutova" + ";user=student;password=student;";
            Connection myConn = DriverManager.getConnection(databaseURL);
            Statement myStmt = myConn.createStatement();
            ResultSet myRs = myStmt.executeQuery("select * from Pacient");
            while (myRs.next()) {
                    String rodne_cislo = myRs.getString("rodne_cislo");
                    String jmeno = myRs.getString("jmeno");
                    System.out.format("rodne cislo: %-10s jmeno: %-10s%n",rodne_cislo, jmeno);
            }
            DBSMethods.getAllData("Rehab_sestra", myConn);
        } catch (SQLException ex) {
            Logger.getLogger(SemestralniPraceDBS.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
