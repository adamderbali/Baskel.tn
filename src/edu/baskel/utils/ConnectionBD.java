/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class ConnectionBD {
    Connection cnx = null;
     
    public String url ="jdbc:mysql://localhost:3306/baskel_tn";
    public String login ="root";
    public String pwd ="";
    public static ConnectionBD instance;

    private ConnectionBD() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("COnnected");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
 public Connection getCnx(){
     return cnx;
 }   
 public static ConnectionBD getInstance(){
     if(instance==null)
         instance = new ConnectionBD();
     return instance;
 }
}



