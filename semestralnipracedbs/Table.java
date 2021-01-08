/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralnipracedbs;

/**
 *
 * @author linvo
 */
public class Table {
    public static class DataRequest {   
        String text;
        Object type;
        public DataRequest(String text, Object type) {
            this.text = text;
            this.type = type;
        }
    }
    
    String name;
    DataRequest[] dataRequest;

    public Table(String name, DataRequest[] dataRequest) {
        this.name = name;
        this.dataRequest = dataRequest;
    }
}

