/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.utils.ConnectionBD;
import java.sql.Connection;

/**
 *
 * @author ASUS
 */
public class PieceCRUD {
    
    Connection cnx;

    public PieceCRUD() {
        cnx = ConnectionBD.getInstance().getCnx();
    }
    
}
