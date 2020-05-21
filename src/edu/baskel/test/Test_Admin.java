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
        
            ReclamationCRUD m = new ReclamationCRUD();
            BanCRUD b= new BanCRUD();
            //m.Banner_user();
            //b.bannereExiste(6);
            //b.Reactive_ban();
            //System.out.println(b.getlist_ban());
        //System.out.println(m.displayall_Rec());
        //m.update_admin();
        b.Ban_Suppression();
        
    }
    
}
