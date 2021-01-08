/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracedbs;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static semestralnipracedbs.DBSMethods.*;
import static semestralnipracedbs.Main.command;
import static semestralnipracedbs.Main.myConn;
import static semestralnipracedbs.Main.sc;
import static semestralnipracedbs.Main.tables;

/**
 *
 * @author User
 */
public class EditMethods {

    private static int index;

    static void setConnection() {
        try {
            String databaseURL = "jdbc:sqlserver://" + "localhost" + ";database=DBS2020_KristynaKohoutova" + ";user=student;password=student;";
            myConn = DriverManager.getConnection(databaseURL);
            Statement myStmt = myConn.createStatement();

        } catch (SQLException ex) {
            System.out.println("Connecting database failed");
        }
    }

    static void setTables() {
        tables.add(new Table("Hospital", new Table.DataRequest[]{new Table.DataRequest("Hospital_ID", 5), new Table.DataRequest("Hospital_name", "50"), new Table.DataRequest("Senior_doctor", "40"),new Table.DataRequest("Hospital_address", "100")}));
        tables.add(new Table("Room", new Table.DataRequest[]{new Table.DataRequest("Room_ID", 0), new Table.DataRequest("Floor", 0), new Table.DataRequest("Capacity", 0), new Table.DataRequest("Hospital_ID", tables.get(0))}));
        tables.add(new Table("Doctor", new Table.DataRequest[]{new Table.DataRequest("Doctor_ID", 5), new Table.DataRequest("First_name", "30"), new Table.DataRequest("Second_name", "30"), new Table.DataRequest("Address", "50")}));
        tables.add(new Table("Patient", new Table.DataRequest[]{new Table.DataRequest("Personal_identification_number", "13"), new Table.DataRequest("First_name", "30"), new Table.DataRequest("Second name", "30"), new Table.DataRequest("Doctor_ID", tables.get(2)), new Table.DataRequest("Room_ID", tables.get(1))}));
        tables.add(new Table("Nurse", new Table.DataRequest[]{new Table.DataRequest("Nurse_ID", 5), new Table.DataRequest("First_name", "30"), new Table.DataRequest("Second_name", "30")}));
        tables.add(new Table("Rehabilitation", new Table.DataRequest[]{new Table.DataRequest("Rehabilitation_ID", 5), new Table.DataRequest("Name", "50"), new Table.DataRequest("Frequency", 0), new Table.DataRequest("Personal_identification_number", tables.get(3)), new Table.DataRequest("Nurse_ID", tables.get(4))}));
    }

    static String getOptions(Table inputTable) throws SQLException {
        ArrayList<String> options = DBSMethods.getIDOptions(inputTable, myConn);
        if (options.isEmpty()) {
            System.out.println("So far there is no " + inputTable.name + " you could choose from. First, create at least one " + inputTable.name + ".");
            return null;
        }
        System.out.println("Choose one of the following " + inputTable.name + "s. ");

        for (int j = 0; j < options.size(); j++) {
            System.out.println(options.get(j));
        }

        return choosingFromList(options);
    }

    static String choosingFromList(ArrayList<String> options) {
        do {
            command = sc.next();
            boolean doublebreak = false;
            for (int j = 0; j < options.size(); j++) {
                if (options.get(j).equals(command)) {
                    doublebreak = true;
                    index = j;
                    break;
                }
            }
            if (doublebreak) {
                break;
            } else {
                System.out.println("Choose one of the listed options.");
            }
        } while (true);
        return command;
    }

    static String choosingFromList(Table.DataRequest[] options) {
        do {
            command = sc.next();
            boolean doublebreak = false;
            for (int j = 0; j < options.length; j++) {
                if (options[j].text.equals(command)) {
                    index = j;
                    doublebreak = true;
                    break;
                }
            }
            if (doublebreak) {
                break;
            } else {
                System.out.println("Choose one of the listed options.");
            }
        } while (true);
        return command;
    }

    static String getInput(Table t, int i) throws SQLException {
        while (true) {
            if (t.dataRequest[i].type instanceof Table) {
                Table inputTable = (Table) t.dataRequest[i].type;
                return getOptions(inputTable);
            } else {
                command = sc.next();
                if (t.dataRequest[i].type instanceof Integer) {
                    while (true) {
                        try {
                            Integer.parseInt(command);
                            break;
                        } catch (Exception e) {
                            System.out.println("You haven't inserted valid value. Try again");
                            command = sc.next();
                        }
                    }
                }
                if (t.dataRequest[i].type instanceof String) {
                    while (command.length() >= Integer.parseInt(t.dataRequest[i].type.toString())) {
                        System.out.println("Your input is too long. Write maximum " + Integer.parseInt(t.dataRequest[i].type.toString()) + " letters.");
                        command = sc.next();
                    }

                }
                if (i != 0) {
                    break;
                } else {
                    boolean doublebreak = true;
                    ArrayList<String> list = getIDOptions(t, myConn);
                    for (int j = 0; j < list.size(); j++) {
                        if (command.equals(list.get(j))) {
                            doublebreak = false;
                            System.out.println("Choose a unique value, as this is a primary key.");
                            break;
                        }
                    }
                    if (doublebreak) {
                        break;
                    }
                }
            }
        }
        return command;
    }

    static void modifyTable(Table t, Main.Modification m) {
        if (m == Main.Modification.add) {
            System.out.println("You are creating new " + t.name + "!\n Please insert following data:");
            ArrayList<String> list = new ArrayList<>();
            try {
                for (int i = 0; i < t.dataRequest.length; i++) 
                {
                    System.out.println(t.dataRequest[i].text);
                    list.add(getInput(t, i));
                }

                DBSMethods.addToTable(t, list, myConn);
                System.out.println("Succesfully added");
            } catch (Exception e) {
                System.out.println("Adding to table failed");
            }
        }
        if (m == Main.Modification.delete) {
            System.out.println("You are deleting " + t.name + "!\n Please choose what you want to delete:");
            try {
                String option = getOptions(t);
                deleteFromTable(t, option, myConn);
                System.out.println("Succesfully deleted");
            } catch (Exception e) {
                System.out.println("Value is constraint with another table!!!");
            }
        }

        if (m == Main.Modification.change) {
            System.out.println("You are modifying " + t.name + "!\n Please choose what you want to modify:");

            try {
                String option = getOptions(t);
                System.out.println("Which parameter you would like to modify?");
                for (int i = 0; i < t.dataRequest.length; i++) {
                    System.out.println(t.dataRequest[i].text);
                }
                String parameter = choosingFromList(t.dataRequest);
                System.out.println("Now add new value");

                DBSMethods.modifyTable(t, myConn, option, parameter, getInput(t, index));
                System.out.println("Modification succeeded");
            } catch (Exception e) {
                System.out.println("The value cannot be modified because it is constraint with other table");
            }
        }
        try {
            if (m == Main.Modification.show) {

                DBSMethods.getAllData(t, myConn);
            }
            if (m == Main.Modification.showID) {
                String output = getOptions(t);
                if (output != null) {
                    System.out.println(DBSMethods.getDataID(t, output, myConn));
                }
            }
        } catch (Exception e) {
            System.out.println("Reading from database failed");
        }
    }
}

    
