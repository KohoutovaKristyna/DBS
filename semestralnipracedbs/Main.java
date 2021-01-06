/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracedbs;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    static class DataRequest {   

        String text;
        Object type;

        public DataRequest(String text, Object type) {
            this.text = text;
            this.type = type;
        }
    }

    static class Table {

        String name;
        DataRequest[] dataRequest;

        public Table(String name, DataRequest[] dataRequest) {
            this.name = name;
            this.dataRequest = dataRequest;
        }
    }

    enum Modification {
        add, change, delete, show, showID
    };
    static Scanner sc;
    static String command;
    static ArrayList<Table> tables = new ArrayList<Table>();

    static Connection myConn;
    static Statement myStmt;

    public static void main(String[] args) {
        EditMethods.setConnection();
        EditMethods.setTables();
        sc = new Scanner(System.in);
        boolean cont = true;
        do {
            System.out.println("Choose a command:\n x - exit\n a - add a data into table\n d - delete data from table\n c - change data in a table\n s - show advanced SQL commands implemented by us programmers\n g - get all data\n i - get data by ID ;) ");
            command = sc.next();
            switch (command) {
                case "a":
                    EditMethods.modifyData(Modification.add);
                    break;
                case "d":
                    EditMethods.modifyData(Modification.delete);
                    break;
                case "c":
                    EditMethods.modifyData(Modification.change);
                    break;
                case "s":
                    EditMethods.advancedCommands();
                    break;
                case "g":
                    EditMethods.modifyData(Modification.show);
                    break;
                case "i": 
                    EditMethods.modifyData(Modification.showID);
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
}
