/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracedbs;
//import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
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
    public static void getAllData(Table t, Connection myConn) throws SQLServerException, SQLException {      
        
        String sql = "SELECT * FROM " + t.name;
        PreparedStatement preparedStatement = myConn.prepareStatement(sql);        
        ResultSet rs1 = preparedStatement.executeQuery();
        while (rs1.next()) {
            for (int i = 0; i < t.dataRequest.length; i++) {
                if(t.dataRequest[i].type instanceof Integer){

                    System.out.format("%17s: %10d ",t.dataRequest[i].text,rs1.getInt(t.dataRequest[i].text));
                }
                else if(t.dataRequest[i].type instanceof Table)
                {
                    Table requestTable = (Table)t.dataRequest[i].type;
                    if(requestTable.dataRequest[i].type instanceof Integer){
                        System.out.format("%17s: %10d ",t.dataRequest[i].text,rs1.getInt(t.dataRequest[i].text));
                    }
                    else{
                        System.out.format("%17s: %25s ",t.dataRequest[i].text,rs1.getString(t.dataRequest[i].text));
                    }
                }
                else{
                    System.out.format("%17s: %25s ",t.dataRequest[i].text,rs1.getString(t.dataRequest[i].text));
                }
            }
        System.out.println("");
        }
    }
    public static ArrayList<String> getIDOptions(Table t, Connection myConn) throws SQLException{
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT * FROM " + t.name;
        PreparedStatement preparedStatement = myConn.prepareStatement(sql);
        ResultSet rs1 = preparedStatement.executeQuery();
        while (rs1.next()) {
            
                if(t.dataRequest[0].type instanceof Integer)
                   list.add(String.valueOf(rs1.getInt(t.dataRequest[0].text)));
                else if(t.dataRequest[0].type instanceof Table)
                {
                    Table requestTable = (Table)t.dataRequest[0].type;
                    if(requestTable.dataRequest[0].type instanceof Integer)
                        list.add(String.valueOf(rs1.getInt(t.dataRequest[0].text)));
                    else
                        list.add(rs1.getString(t.dataRequest[0].text));
                }
                else
                    list.add(rs1.getString(t.dataRequest[0].text));
            
        }
        return list;
    }
    public static String getDataID(Table t, String rowID, Connection myConn) throws SQLException {                    
        String sql = "SELECT * FROM " + t.name + " WHERE "+t.dataRequest[0].text+" = ?";
        PreparedStatement preparedStatement = myConn.prepareStatement(sql);
        if(t.dataRequest[0].type instanceof Integer)
            preparedStatement.setInt(1, parseInt(rowID));
        else
             preparedStatement.setString(1, rowID);
        ResultSet rs1 = preparedStatement.executeQuery();
        StringBuilder sb = new StringBuilder();
        
        while (rs1.next()) {
            for (int i = 0; i < t.dataRequest.length; i++) {
                if(t.dataRequest[i].type instanceof Integer)
                   sb.append(rs1.getInt(t.dataRequest[i].text)+" ");
                else if(t.dataRequest[i].type instanceof Table)
                {
                    Table requestTable = (Table)t.dataRequest[i].type;
                    if(requestTable.dataRequest[i].type instanceof Integer)
                        sb.append(rs1.getInt(t.dataRequest[i].text)+" ");
                    else
                        sb.append(rs1.getString(t.dataRequest[i].text)+" ");
                }
                else
                    sb.append(rs1.getString(t.dataRequest[i].text)+" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public static void addToTable(Table t, ArrayList<String> list, Connection myConn) throws SQLException {
       PreparedStatement preparedStatement;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO "+t.name+" (");
        
	for (int i = 0; i < t.dataRequest.length; i++) 
        {
            if(i < t.dataRequest.length - 1){
                sb.append(t.dataRequest[i].text+", ");
            }else{
                sb.append(t.dataRequest[i].text+" ");
            }
            
        }
        sb.append(") VALUES (");
        for (int i = 0; i < list.size(); i++) {
            if(i < list.size() - 1){
                sb.append("'"+list.get(i)+"'"+", ");
            }else{
                sb.append("'"+list.get(i)+"'"+")");
            }
        }
        String sql = sb.toString();
        preparedStatement = myConn.prepareStatement(sql);
        preparedStatement.executeUpdate();     
    }
    
    public static void deleteFromTable(Table t, String rowID, Connection myConn) throws SQLException{
        String sql;
        PreparedStatement preparedStatement;
        
        sql = "DELETE FROM "+ t.name +" WHERE "+t.dataRequest[0].text+" = "+rowID;
        preparedStatement = myConn.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }

    public static void modifyTable(Table t, Connection myConn, String rowID, String columnName, String newValue) throws SQLException {
        String sql = "UPDATE " + t.name + " SET " + columnName + " = " + newValue + " WHERE " + t.dataRequest[0].text + " = " + rowID;
        PreparedStatement preparedStatement = myConn.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }

    public static void mySQLCommandA(Connection myConn) throws SQLException {
        String sql = "SELECT R.Name, (\n"
                + "		SELECT R1.Frequency\n"
                + "		FROM Rehabilitation R1\n"
                + "		WHERE R.Rehabilitation_ID = R1.Rehabilitation_ID \n"
                + "		) AS maxFrequency\n"
                + "FROM Rehabilitation R\n"
                + "WHERE R.Frequency = (SELECT Max(Frequency) FROM Rehabilitation R2)";
        PreparedStatement preparedStatement = myConn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        
        System.out.println("\n This query returns the rehabilitation with highest frequency \n");
        while (rs.next()) {
            String Name = rs.getString("Name");
            int maxFrequency = rs.getInt("maxFrequency");
            System.out.format("   %-25s   %4d  %n%n", Name, maxFrequency);
        }
    }
    
    public static void mySQLCommandB(Connection myConn) throws SQLException {
        String sql = "SELECT Hospital_name, number_of_rooms FROM Hospital H LEFT OUTER JOIN ( SELECT Hospital_ID, COUNT(Hospital_ID) AS number_of_rooms FROM Room GROUP BY Hospital_ID) AS X ON X.Hospital_ID = H.Hospital_ID";
        PreparedStatement preparedStatement = myConn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        
        System.out.println("\n This query returns the number of rooms of each hospital \n");
        while (rs.next()) {
            String Hospital_name = rs.getString("Hospital_name");
            int number_of_rooms = rs.getInt("number_of_rooms");
            System.out.format("   %-25s   %4d  %n%n", Hospital_name, number_of_rooms);
        }
    }
    
    public static void mySQLCommandC(Connection myConn) throws SQLException {
        String sql = "SELECT P.First_name FROM Patient P WHERE Room_ID IN (SELECT Room.Room_ID FROM Room WHERE Room.[Floor] = 2)";
        PreparedStatement preparedStatement = myConn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        
        System.out.println("\n This query returns all patients on second floor \n");
        while (rs.next()) {
            String patient_name = rs.getString("First_name");
            System.out.format("   %s %n%n", patient_name);
        }
    }
    public static void mySQLCommandD(Connection myConn) throws SQLException {
        String sql = "SELECT Room_ID FROM Patient GROUP BY Room_ID HAVING COUNT(*) >= 2";
        PreparedStatement preparedStatement = myConn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        System.out.println("\n This query returns rooms id where live two or more patients \n");
        while (rs.next()) {
            int Room_ID = rs.getInt("Room_ID");
            System.out.format("   %d %n%n", Room_ID);
        }
    }


    public static void mySQLCommandE(Connection myConn) throws SQLException {
        String sql = "SELECT Patient.First_name, Patient.Second_name, Patient.Personal_identification_number\n"
                + "FROM Patient\n"
                + "EXCEPT \n"
                + "SELECT Patient.First_name, Patient.Second_name, Patient.Personal_identification_number\n"
                + "FROM Patient, Rehabilitation\n"
                + "WHERE Patient.Personal_identification_number = Rehabilitation.Personal_identification_number";
        PreparedStatement preparedStatement = myConn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        
        System.out.println("\n This query returns patients with no rehabilitation\n");
        while (rs.next()) {
            String First_name = rs.getString("First_name");
            String Second_name = rs.getString("Second_name");
            String PIN = rs.getString("Personal_identification_number");
            System.out.format("   %-25s %-25s %-25s %n%n", First_name, Second_name, PIN);
        }
    }
    
    public static void mySQLCommandF(Connection myConn) throws SQLException {
        String sql = "SELECT Patient.First_name, Patient.Second_name, Rehabilitation.Name\n"
                + "FROM Patient LEFT JOIN Rehabilitation ON Rehabilitation.Personal_identification_number = Patient.Personal_identification_number";
        PreparedStatement preparedStatement = myConn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        
        System.out.println("\n This query returns patients and their rehabilitations, if they do not have any, it returns null \n");
        while (rs.next()) {
            String First_name = rs.getString("First_name");
            String Second_name = rs.getString("Second_name");
            String Name = rs.getString("Name");
            System.out.format("   %-25s %-25s %-25s %n%n", First_name, Second_name, Name);
        }
    }

    
}
