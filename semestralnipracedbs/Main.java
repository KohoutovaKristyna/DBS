
package semestralnipracedbs;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Scanner;
import static semestralnipracedbs.EditMethods.modifyTable;



public class Main {
    enum Modification {
        add, change, delete, show, showID
    };
    static Scanner sc;
    static String command;
    static ArrayList<Table> tables = new ArrayList<Table>();

    static Connection myConn;
    static Statement myStmt;

    public static void main(String[] args){
        EditMethods.setConnection();
        EditMethods.setTables();
        sc = new Scanner(System.in);
        boolean cont = true;
        do {
            System.out.println("Choose a command:\n x - exit\n a - add a data into table\n d - delete data from table\n c - change data in a table\n s - show advanced SQL commands implemented by us programmers\n g - get all data\n i - get data by ID ;) ");
            command = sc.next();
            switch (command) {
                case "a":
                    modifyData(Modification.add);
                    break;
                case "d":
                    modifyData(Modification.delete);
                    break;
                case "c":
                    modifyData(Modification.change);
                    break;
                case "s":
                    advancedCommands();
                    break;
                case "g":
                    modifyData(Modification.show);
                    break;
                case "i": 
                    modifyData(Modification.showID);
                    break;
                case "x":
                    cont = false;
                    break;
                default:
                    System.out.println("Please select a valid command");
                    break;
            }
        } while (cont);
    }
    static void advancedCommands(){
        do {
            System.out.println("Choose a command:\n x - exit to main menu\n a - SELECT v SELECT\n b - SELECT ve FROM \n c - SELECT ve WHERE \n d - GROUP BY \n e - množinová operace\n f - LEFT JOIN ");
            command = sc.next();
            try{
            switch (command) {
                case "a":
                    DBSMethods.mySQLCommandA(myConn);
                    break;
                case "b":
                    DBSMethods.mySQLCommandB(myConn);
                    break;
                case "c":
                    DBSMethods.mySQLCommandC(myConn);
                    break;
                case "d":
                    DBSMethods.mySQLCommandD(myConn);
                    break;
                case "e":
                    DBSMethods.mySQLCommandE(myConn);
                    break;
                case "f":
                    DBSMethods.mySQLCommandF(myConn);
                    break;
                case "x":
                    break;
                default:
                    System.out.println("Please select a valid command");
                    break;
            }
            }catch(Exception e){
                System.out.println("Advanced command failed");
            }
        } while (!"x".equals(command));
    }
    static void modifyData(Main.Modification m) {
        boolean breakout;
        do {
            breakout = true;
            System.out.println("Choose a table:\n x - exit to main menu\n h - hospital\n ro - room\n p - patient \n d - district doctor \n re - rehabilitation \n n - nurse for rehabilitation");
            command = sc.next();
            switch (command) {
                case "h":
                    for (int i = 0; i < tables.size(); i++) {
                        if ("Hospital".equals(tables.get(i).name)) {
                            modifyTable(tables.get(i), m);
                        }
                    }
                    break;
                case "ro":
                    for (int i = 0; i < tables.size(); i++) {
                        if ("Room".equals(tables.get(i).name)) {
                            modifyTable(tables.get(i), m);
                        }
                    }
                    break;
                case "p":
                    for (int i = 0; i < tables.size(); i++) {
                        if ("Patient".equals(tables.get(i).name)) {
                            modifyTable(tables.get(i), m);
                        }
                    }
                    break;
                case "d":
                    for (int i = 0; i < tables.size(); i++) {
                        if ("Doctor".equals(tables.get(i).name)) {
                            modifyTable(tables.get(i), m);
                        }
                    }
                    break;
                case "re":
                    for (int i = 0; i < tables.size(); i++) {
                        if ("Rehabilitation".equals(tables.get(i).name)) {
                            modifyTable(tables.get(i), m);
                        }
                    }
                    break;
                case "n":
                    for (int i = 0; i < tables.size(); i++) {
                        if ("Nurse".equals(tables.get(i).name)) {
                            modifyTable(tables.get(i), m);
                        }
                    }
                    break;
                case "x":
                    break;
                default:
                    System.out.println("Please select a valid command");
                    breakout = false;
                    break;
            }
            
        } while (!breakout);
    }
}