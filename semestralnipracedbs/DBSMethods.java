/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracedbs;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class DBSMethods {
    public static void getAllData(String tableName, Connection myConn) throws SQLServerException, SQLException {      
        
        String sql = "SELECT * FROM " + tableName;
        PreparedStatement preparedStatement = myConn.prepareStatement(sql);
        //preparedStatement.setInt(1, 2);
        
        ResultSet rs1 = preparedStatement.executeQuery();
        
        switch(tableName){
            case "Rehab_sestra":
                while (rs1.next()) {
                int id_sestry = rs1.getInt("id_sestry");
                String jmeno = rs1.getString("jmeno");
                String prijmeni = rs1.getString("prijmeni");
                System.out.format("%n %4d %s %s %n",id_sestry, jmeno, prijmeni);
            }
            break;
            case "Rehabilitace":
                while(rs1.next()){
                int id_rehabilitace = rs1.getInt("id_rehabilitace");
                String nazev = rs1.getString("nazev");
                int frekvence_cetnost = rs1.getInt("frekvence_cetnost");
                String rodne_cislo_pacienta = rs1.getString("rodne_cislo_pacienta");
                int id_sestry = rs1.getInt("id_sestry");
                System.out.format("%n %4d %s %4d %s %15d %n", id_rehabilitace, nazev, frekvence_cetnost, rodne_cislo_pacienta, id_sestry);
                }
            break;
            case "Pacient":
                while(rs1.next()){
                String rodne_cislo = rs1.getString("rodne_cislo");
                String jmeno = rs1.getString("jmeno");
                String prijmeni = rs1.getString("prijmeni");
                int id_lekare = rs1.getInt("id_lekare");
                int id_pokoje = rs1.getInt("id_pokoje");
                System.out.format("%n %s %s %s  id lekare: %d  id pokoje: %d %n", rodne_cislo, jmeno, prijmeni, id_lekare, id_pokoje);
                }
            break;
            case "Obvodni_lekar":
                while(rs1.next()){
                int id_lekare = rs1.getInt("id_lekare");
                String jmeno = rs1.getString("jmeno");
                String prijmeni = rs1.getString("prijmeni");
                String adresa_ordinace = rs1.getString("adresa_ordinace");
                System.out.format("%n %4d %-14s %-11s %-100s %n", id_lekare, prijmeni, jmeno, adresa_ordinace);
                }
            break;
            case "Pokoj":
                while(rs1.next()){
                int id_pokoje = rs1.getInt("id_pokoje");
                int poschodi = rs1.getInt("poschodi");
                int kapacitu = rs1.getInt("kapacitu");
                int id_nemocnice = rs1.getInt("id_nemocnice");
                System.out.format("%n id pokoje: %3d   poschodi: %3d   kapacita: %3d   id nemocnice: %3d %n", id_pokoje, poschodi, kapacitu, id_nemocnice);
                }
            break;
            case "Nemocnice_LDN":
                while(rs1.next()){
                int id_nemocnice = rs1.getInt("id_nemocnice");
                String nazev = rs1.getString("nazev");
                String adresa = rs1.getString("adresa");
                String primar = rs1.getString("primar");
                System.out.format("%n %4d %-25s %s %s %n", id_nemocnice, nazev, adresa, primar);
                }
            break;
        
        }

    }
    public static ArrayList<String> getIDOptions(String tableName, Connection myConn) throws SQLException{
        ArrayList<String> list = new ArrayList<>();
        switch(tableName){
            case "Rehab_sestra":
                String sql = "SELECT * FROM " + tableName;
                PreparedStatement preparedStatement = myConn.prepareStatement(sql);
                ResultSet rs1 = preparedStatement.executeQuery();
                
                while (rs1.next()) {
                list.add(String.valueOf(rs1.getInt("id_sestry")));
                }
            break;
            case "Rehabilitace":
                String sqlRehab = "SELECT * FROM " + tableName;
                PreparedStatement preparedStatementRehab = myConn.prepareStatement(sqlRehab);
                ResultSet rs1Rehab = preparedStatementRehab.executeQuery();
                
                while (rs1Rehab.next()) {               
                list.add(String.valueOf(rs1Rehab.getInt("id_rehabilitace")));
                }
            break;
            case "Pacient":
                String sqlPacient = "SELECT * FROM " + tableName;
                PreparedStatement preparedStatementPacient = myConn.prepareStatement(sqlPacient);
                ResultSet rs1Pacient = preparedStatementPacient.executeQuery();
                
                while(rs1Pacient.next()){
                list.add(String.valueOf(rs1Pacient.getString("rodne_cislo")));
                }
            break;
            case "Obvodni_lekar":
                String sqlLekar = "SELECT * FROM " + tableName;
                PreparedStatement preparedStatementLekar = myConn.prepareStatement(sqlLekar);
                ResultSet rs1Lekar = preparedStatementLekar.executeQuery();
                
                while(rs1Lekar.next()){
                list.add(String.valueOf(rs1Lekar.getInt("id_lekare")));
                }
            break;
            case "Pokoj":
                String sqlPokoj = "SELECT * FROM " + tableName;
                PreparedStatement preparedStatementPokoj = myConn.prepareStatement(sqlPokoj);
                ResultSet rs1Pokoj = preparedStatementPokoj.executeQuery();
                
                while(rs1Pokoj.next()){
                list.add(String.valueOf(rs1Pokoj.getInt("id_pokoje")));
                }
            break;
            case "Nemocnice_LDN":
                String sqlNemocnice = "SELECT * FROM " + tableName;
                PreparedStatement preparedStatementNemocnice = myConn.prepareStatement(sqlNemocnice);
                ResultSet rs1Nemocnice = preparedStatementNemocnice.executeQuery();
                
                while(rs1Nemocnice.next()){
                list.add(String.valueOf(rs1Nemocnice.getInt("id_nemocnice")));
                }
            break;
        
        }
        return list;
    }
    public static void getDataID(String tableName, String rowID, Connection myConn) throws SQLServerException, SQLException {                    
        switch(tableName){
            case "Rehab_sestra":
                String sql = "SELECT * FROM " + tableName + " WHERE id_sestry = ?";
                PreparedStatement preparedStatement = myConn.prepareStatement(sql);
                preparedStatement.setInt(1, parseInt(rowID));
                ResultSet rs1 = preparedStatement.executeQuery();
                
                while (rs1.next()) {
                int id_sestry = rs1.getInt("id_sestry");
                String jmeno = rs1.getString("jmeno");
                String prijmeni = rs1.getString("prijmeni");
                System.out.format("%n %4d %s %s %n",id_sestry, jmeno, prijmeni);
            }
            break;
            case "Rehabilitace":
                String sqlRehab = "SELECT * FROM " + tableName + " WHERE id_rehabilitace = ?";
                PreparedStatement preparedStatementRehab = myConn.prepareStatement(sqlRehab);
                preparedStatementRehab.setInt(1, parseInt(rowID));
                ResultSet rs1Rehab = preparedStatementRehab.executeQuery();
                
                while (rs1Rehab.next()) {               
                int id_rehabilitace = rs1Rehab.getInt("id_rehabilitace");
                String nazev = rs1Rehab.getString("nazev");
                int frekvence_cetnost = rs1Rehab.getInt("frekvence_cetnost");
                String rodne_cislo_pacienta = rs1Rehab.getString("rodne_cislo_pacienta");
                int id_sestryRehab = rs1Rehab.getInt("id_sestry");
                System.out.format("%n %4d %s %4d %s %15d %n", id_rehabilitace, nazev, frekvence_cetnost, rodne_cislo_pacienta, id_sestryRehab);
                }
            break;
            case "Pacient":
                String sqlPacient = "SELECT * FROM " + tableName + " WHERE rodne_cislo like ?";
                PreparedStatement preparedStatementPacient = myConn.prepareStatement(sqlPacient);
                preparedStatementPacient.setString(1, rowID);
                ResultSet rs1Pacient = preparedStatementPacient.executeQuery();
                
                while(rs1Pacient.next()){
                String rodne_cislo = rs1Pacient.getString("rodne_cislo");
                String jmeno = rs1Pacient.getString("jmeno");
                String prijmeni = rs1Pacient.getString("prijmeni");
                int id_lekare = rs1Pacient.getInt("id_lekare");
                int id_pokoje = rs1Pacient.getInt("id_pokoje");
                System.out.format("%n %s %s %s  id lekare: %d  id pokoje: %d %n", rodne_cislo, jmeno, prijmeni, id_lekare, id_pokoje);
                }
            break;
            case "Obvodni_lekar":
                String sqlLekar = "SELECT * FROM " + tableName + " WHERE id_lekare = ?";
                PreparedStatement preparedStatementLekar = myConn.prepareStatement(sqlLekar);
                preparedStatementLekar.setInt(1, parseInt(rowID));
                ResultSet rs1Lekar = preparedStatementLekar.executeQuery();
                
                while(rs1Lekar.next()){
                int id_lekare = rs1Lekar.getInt("id_lekare");
                String jmeno = rs1Lekar.getString("jmeno");
                String prijmeni = rs1Lekar.getString("prijmeni");
                String adresa_ordinace = rs1Lekar.getString("adresa_ordinace");
                System.out.format("%n %4d %-14s %-11s %-100s %n", id_lekare, prijmeni, jmeno, adresa_ordinace);
                }
            break;
            case "Pokoj":
                String sqlPokoj = "SELECT * FROM " + tableName + " WHERE id_pokoje = ?";
                PreparedStatement preparedStatementPokoj = myConn.prepareStatement(sqlPokoj);
                preparedStatementPokoj.setInt(1, parseInt(rowID));
                ResultSet rs1Pokoj = preparedStatementPokoj.executeQuery();
                
                while(rs1Pokoj.next()){
                int id_pokoje = rs1Pokoj.getInt("id_pokoje");
                int poschodi = rs1Pokoj.getInt("poschodi");
                int kapacitu = rs1Pokoj.getInt("kapacitu");
                int id_nemocnice = rs1Pokoj.getInt("id_nemocnice");
                System.out.format("%n id pokoje: %3d   poschodi: %3d   kapacita: %3d   id nemocnice: %3d %n", id_pokoje, poschodi, kapacitu, id_nemocnice);
                }
            break;
            case "Nemocnice_LDN":
                String sqlNemocnice = "SELECT * FROM " + tableName + " WHERE id_nemocnice = ?";
                PreparedStatement preparedStatementNemocnice = myConn.prepareStatement(sqlNemocnice);
                preparedStatementNemocnice.setInt(1, parseInt(rowID));
                ResultSet rs1Nemocnice = preparedStatementNemocnice.executeQuery();
                
                while(rs1Nemocnice.next()){
                int id_nemocnice = rs1Nemocnice.getInt("id_nemocnice");
                String nazev = rs1Nemocnice.getString("nazev");
                String adresa = rs1Nemocnice.getString("adresa");
                String primar = rs1Nemocnice.getString("primar");
                System.out.format("%n %4d %-25s %s %s %n", id_nemocnice, nazev, adresa, primar);
                }
            break;
        
        }
            
       
    }
    
    public static void addToTable(String tableName, ArrayList<String> list, Connection myConn) throws SQLException {
        PreparedStatement preparedStatement;
        String sql;
        switch (tableName) {
            case "Rehab_sestra":
                sql = String.format("INSERT INTO %s (id_sestry, jmeno, prijmeni) VALUES ('%d', '%s', '%s')", tableName, parseInt(list.get(0)), list.get(1), list.get(2));
                preparedStatement = myConn.prepareStatement(sql);
                preparedStatement.executeUpdate();
                break;
            case "Rehabilitace":
                sql = String.format("INSERT INTO %s (id_rehabilitace, nazev, frekvence_cetnost, rodne_cislo_pacienta, id_sestry) VALUES ('%d', '%s', '%d', '%s', '%d')", tableName, parseInt(list.get(0)), list.get(1), parseInt(list.get(2)), list.get(3), parseInt(list.get(4)));
                preparedStatement= myConn.prepareStatement(sql);
                preparedStatement.executeUpdate();
                break;
            case "Obvodni_lekar":
                sql = String.format("INSERT INTO %s (id_lekare, jmeno, prijmeni, adresa_ordinace) VALUES ('%d', '%s', '%s', '%s')", tableName, parseInt(list.get(0)), list.get(1), list.get(2), list.get(3));
                preparedStatement = myConn.prepareStatement(sql);
                preparedStatement.executeUpdate();
                break;
            case "Pacient":
                sql = String.format("INSERT INTO %s (rodne_cislo, jmeno, prijmeni, id_lekare, id_pokoje) VALUES ('%s', '%s', '%s', '%d', '%d')", tableName, list.get(0), list.get(1), list.get(2), parseInt(list.get(3)), parseInt(list.get(4)));
                preparedStatement = myConn.prepareStatement(sql);
                preparedStatement.executeUpdate();
                break;
            case "Pokoj":
                sql = String.format("INSERT INTO %s (id_pokoje, poschodi, kapacita, id_nemocnice) VALUES ('%d', '%d', '%d', '%d')", tableName, parseInt(list.get(0)), parseInt(list.get(1)), parseInt(list.get(2)), parseInt(list.get(3)));
                preparedStatement = myConn.prepareStatement(sql);
                preparedStatement.executeUpdate();
                break;
            case "Nemocnice_LDN":
                sql = String.format("INSERT INTO %s (id_nemocnice, nazev, adresa, primar) VALUES ('%d', '%s', '%s', '%s')", tableName, parseInt(list.get(0)), list.get(1), list.get(2), list.get(3));
                preparedStatement = myConn.prepareStatement(sql);
                preparedStatement.executeUpdate();
                break;

        }
    }
    
    public static void deleteFromTable(String tableName, String rowID, Connection myConn) throws SQLException{
        //DELETE FROM table_name WHERE condition;
        String sql = "";
        PreparedStatement preparedStatement;
        switch(tableName){
        case "Rehab_sestra":
            sql = String.format("DELETE FROM %s WHERE id_sestry = '%d'", tableName, parseInt(rowID));
            break;
         
        case "Rehabilitace":
            sql = String.format("DELETE FROM %s WHERE id_rehabilitace = '%d'", tableName, parseInt(rowID));
            break;
        
        case "Obvodni_lekar":
            sql = String.format("DELETE FROM %s WHERE id_lekare = '%d'", tableName, parseInt(rowID));
            break;
        
        case "Pacient":
            sql = String.format("DELETE FROM %s WHERE rodne_cislo = '%s'", tableName, rowID);
            break;
        case "Pokoj":
            sql = String.format("DELETE FROM %s WHERE id_pokoje = '%d'", tableName, parseInt(rowID));
            break;
        case "Nemocnice_LDN":
            sql = String.format("DELETE FROM %s WHERE id_nemocnice = '%d'", tableName, parseInt(rowID));
            break;       
        }
        preparedStatement = myConn.prepareStatement(sql);
        preparedStatement.executeUpdate();
    
    }
    
//    public static void selectInSelect(Connection myConn){
//        String sql= "SELECT jmeno, (SELECT ) FROM  WHERE ";
//                PreparedStatement preparedStatementRehab = myConn.prepareStatement(sql);
//                ResultSet rs1Rehab = preparedStatementRehab.executeQuery();
//    }

}
