/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.test;

import edu.baskel.services.BanCRUD;
import edu.baskel.services.ReclamationCRUD;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Skander
 */
public class Test_Admin         // TODO code application logic here
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ReclamationCRUD m = new ReclamationCRUD();
            BanCRUD b= new BanCRUD();
            //m.Banner_user();
            //b.bannereExiste(6);
            b.Reactive_ban();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}