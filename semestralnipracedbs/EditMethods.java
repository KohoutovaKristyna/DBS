/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracedbs;

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
    
  static void setConnection() {
        try {
            String databaseURL = "jdbc:sqlserver://" + "localhost" + ";database=DBS2020_KristynaKohoutova" + ";user=student;password=student;";
            myConn = DriverManager.getConnection(databaseURL);
            Statement myStmt = myConn.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(SemestralniPraceDBS.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
 static void setTables() {
//        //primární klíč vždy na první pozici
//        tables.add(new Main.Table("Nemocnice_LDN", new Main.DataRequest[]{new Main.DataRequest("Hospital ID", 5), new Main.DataRequest("Hospital name", ""), new Main.DataRequest("Hospital address", ""), new Main.DataRequest("Senior doctor", "")}));
//        tables.add(new Main.Table("Pokoj", new Main.DataRequest[]{new Main.DataRequest("Room ID", 0), new Main.DataRequest("Floor", 0), new Main.DataRequest("Capacity", 0), new Main.DataRequest("Hospital ID", tables.get(0))}));
//        tables.add(new Main.Table("Obvodni_lekar", new Main.DataRequest[]{new Main.DataRequest("Doctor's ID", 5), new Main.DataRequest("First name", ""), new Main.DataRequest("Second name", ""), new Main.DataRequest("Address", "")}));
//        tables.add(new Main.Table("Pacient", new Main.DataRequest[]{new Main.DataRequest("Personal identifcation number", ""), new Main.DataRequest("First name", ""), new Main.DataRequest("Second name", ""), new Main.DataRequest("Doctor's ID", tables.get(2)), new Main.DataRequest("Room ID", tables.get(1))}));
//        tables.add(new Main.Table("Rehab_sestra", new Main.DataRequest[]{new Main.DataRequest("Nurse ID", 5), new Main.DataRequest("First name", ""), new Main.DataRequest("Second name", "")}));
//        tables.add(new Main.Table("Rehabilitace", new Main.DataRequest[]{new Main.DataRequest("Rehabilitation ID", 5), new Main.DataRequest("Name", ""), new Main.DataRequest("Times a week", 0), new Main.DataRequest("Patient's PIN", tables.get(3)), new Main.DataRequest("Nurse's ID", tables.get(4))}));
//      
    
        //primární klíč vždy na první pozici
        tables.add(new Main.Table("Nemocnice_LDN",new Main.DataRequest[]{new Main.DataRequest("Hospital ID",5), new Main.DataRequest("Hospital name","50"), new Main.DataRequest("Hospital address","100"), new Main.DataRequest("Senior doctor","40")}));
        tables.add(new Main.Table("Pokoj",new Main.DataRequest[]{new Main.DataRequest("Room ID",0), new Main.DataRequest("Floor",0), new Main.DataRequest("Capacity",0), new Main.DataRequest("Hospital ID",tables.get(0))}));
        tables.add(new Main.Table("Obvodni_lekar",new Main.DataRequest[]{new Main.DataRequest("Doctor's ID",5), new Main.DataRequest("First name","30"), new Main.DataRequest("Second name","30"), new Main.DataRequest("Address","50")}));
        tables.add(new Main.Table("Pacient",new Main.DataRequest[]{new Main.DataRequest("Personal identifcation number","13"), new Main.DataRequest("First name","30"), new Main.DataRequest("Second name","30"), new Main.DataRequest("Doctor's ID",tables.get(2)), new Main.DataRequest("Room ID",tables.get(1))}));
        tables.add(new Main.Table("Rehab_sestra",new Main.DataRequest[]{new Main.DataRequest("Nurse ID",5), new Main.DataRequest("First name","30"), new Main.DataRequest("Second name","30")}));
        tables.add(new Main.Table("Rehabilitace",new Main.DataRequest[]{new Main.DataRequest("Rehabilitation ID",5),new Main.DataRequest("Name","50"), new Main.DataRequest("Times a week",0),new Main.DataRequest("Patient's PIN",tables.get(3)),new Main.DataRequest("Nurse's ID",tables.get(4))}));
    
 
 }

    static void modifyData(Main.Modification m) {
        do {
            System.out.println("Choose a table:\n x - exit to main menu\n h - hospital\n ro - room\n p - patient \n d - district doctor \n re - rehabilitation \n n - nurse for rehabilitation");
            command = sc.next();
            switch (command) {
                case "h":
                    for (int i = 0; i < tables.size(); i++) {
                        if ("Nemocnice_LDN".equals(tables.get(i).name)) {
                            modifyTable(tables.get(i), m);
                        }
                    }
                    break;
                case "ro":
                    for (int i = 0; i < tables.size(); i++) {
                        if ("Pokoj".equals(tables.get(i).name)) {
                            modifyTable(tables.get(i), m);
                        }
                    }
                    break;
                case "p":
                    for (int i = 0; i < tables.size(); i++) {
                        if ("Pacient".equals(tables.get(i).name)) {
                            modifyTable(tables.get(i), m);
                        }
                    }
                    break;
                case "d":
                    for (int i = 0; i < tables.size(); i++) {
                        if ("Obvodni_lekar".equals(tables.get(i).name)) {
                            modifyTable(tables.get(i), m);
                        }
                    }
                    break;
                case "re":
                    for (int i = 0; i < tables.size(); i++) {
                        if ("Rehabilitace".equals(tables.get(i).name)) {
                            modifyTable(tables.get(i), m);
                        }
                    }
                    break;
                case "n":
                    for (int i = 0; i < tables.size(); i++) {
                        if ("Rehab_sestra".equals(tables.get(i).name)) {
                            modifyTable(tables.get(i), m);
                        }
                    }
                    break;
                case "x":
                    break;
                default:
                    System.out.println("Please select a valid command");
                    break;
            }
        } while (!"x".equals(command));
    }

    static String getOptions(Main.Table inputTable) throws SQLException {
        //Tady ulož všechny primární klíče z dané tabulky. Pokud inputTable.name == Hospital, tak vypíšeš všechny ID nemocnice.
        ArrayList<String> options = DBSMethods.getIDOptions(inputTable.name, myConn);
        //chooseTable.Add(data z databáze);
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

    static String choosingFromList(Main.DataRequest[] options) {
        do {
            command = sc.next();
            boolean doublebreak = false;
            for (int j = 0; j < options.length; j++) {
                if (options[j].text.equals(command)) {
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

    
    static String getInput(Main.Table t, int i) throws SQLException
    {
        while(true){
        if(t.dataRequest[i].type instanceof Main.Table)
        {        
            Main.Table inputTable = (Main.Table)t.dataRequest[i].type;
            return getOptions(inputTable);
            //ten option ti vyhodí vybraný foreign key. Pokud je option null, tak to znamená, že žándý neexistuje
        }
        else
        {
            command = sc.next();
            if(t.dataRequest[i].type instanceof Integer)
            {
                while(true)
                {   
                    try
                    {
                        Integer.parseInt(command);
                        break;
                    }
                    catch(Exception e)
                    {
                        System.out.println("You haven't inserted valid value. Try again");
                        command = sc.next();
                    }
                }
            }
            if(t.dataRequest[i].type instanceof String)
            {
                

                while(command.length()>=Integer.parseInt(t.dataRequest[i].type.toString()))
                {
                    System.out.println("Your input is too long. Write maximum "+Integer.parseInt(t.dataRequest[i].type.toString())+" letters.");
                    command = sc.next();
                }

            }
            if(i!=0)
                break;
            else
            {
                boolean doublebreak = true;
                ArrayList<String> list = getIDOptions(t.name, myConn);
                for (int j = 0; j < list.size(); j++) {
                    if(command.equals(list.get(j)))
                    {
                        doublebreak = false;
                        System.out.println("Choose a unique value, as this is a primary key.");
                        break;
                    }
                }
                if(doublebreak)
                    break;
            }
        
            }
        }
        return command;
    }
    static void modifyTable(Main.Table t, Main.Modification m) {
        try {
            if (m == Main.Modification.add) {
                System.out.println("You are creating new " + t.name + "!\n Please insert following data:");
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < t.dataRequest.length; i++) {
                    System.out.println(t.dataRequest[i].text);
                    list.add(getInput(t, i));
                    
                    //tady to nechávám na tobě - uživatel ti zadá všechny data, ty jen budeš potřebovat to nějak hodit do té databáze.
                }
                DBSMethods.addToTable(t.name, list, myConn);
                System.out.println("Succesfully deleted");
            }
            if (m == Main.Modification.delete) {
                System.out.println("You are deleting " + t.name + "!\n Please choose what you want to delete:");

                String option = getOptions(t);
                try {
                    deleteFromTable(t.name, option, myConn);
                    System.out.println("Succesfully deleted");
                } catch (Exception e) {
                    System.out.println("Value is constraint with another table!!!");
                }
                //smaž vybranou možnost
            }
            if (m == Main.Modification.change) {
                System.out.println("You are modifying " + t.name + "!\n Please choose what you want to modify:");

                String option = getOptions(t);

                //vybereš danou možnost
                System.out.println("Which parameter you would like to modify?");
                for (int i = 0; i < t.dataRequest.length; i++) {
                    System.out.println(t.dataRequest[i].text);
                }
                String parameter = choosingFromList(t.dataRequest);

                //měníme tadyten parametr
                System.out.println("Now add new value");

                //a nahraj novou hodnotu
            }
            if (m == Main.Modification.show) {
                DBSMethods.getAllData(t.name, myConn);
            }
            if (m == Main.Modification.showID){
                String output = getOptions(t);
                if(output!=null)
                    DBSMethods.getDataID(t.name, output, myConn);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static void advancedCommands() {
        do {
            System.out.println("Choose a command:\n x - exit to main menu\n a - SELECT v SELECT\n b - SELECT ve FROM \n c - SELECT ve WHERE \n d - GROUP BY \n e - množinová operace\n f - LEFT JOIN ");
            command = sc.next();
            switch (command) {
                case "a":
                    //něco
                    break;
                case "b":
                    //něco
                    break;
                case "c":
                    //něco
                    break;
                case "d":
                    //něco
                    break;
                case "e":
                    //něco
                    break;
                case "f":
                    //něco
                    break;
                case "x":
                    break;
                default:
                    System.out.println("Please select a valid command");
                    break;
            }
        } while (!"x".equals(command));
    }

}

