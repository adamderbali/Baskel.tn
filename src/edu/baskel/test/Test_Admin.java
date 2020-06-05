/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.test;

import edu.baskel.entities.Avis_admin;
import edu.baskel.services.AvisAdminCRUD;
import edu.baskel.services.BanCRUD;
import edu.baskel.services.EvenementCRUD;
import edu.baskel.services.ReclamationCRUD;
import edu.baskel.services.StatCRUD;
import java.sql.SQLException;
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
    public static void main(String[] args) throws SQLException {
            //Avis_admin a = new Avis_admin(4.00,"skander", 15);
            Avis_admin b = new Avis_admin(3.00, "skander", 15);

            AvisAdminCRUD ev= new AvisAdminCRUD();
            ev.update_avis(b);
            //ev.insertion_avis(a);
            /*StatCRUD sc = new StatCRUD();
            BanCRUD b= new BanCRUD();
            sc.Reclamation_admin_nbr();
            System.out.println("\t");
            sc.Reclamation_user_nbr();
            System.out.println("\t");
            sc.affichage_user_nbr();
            System.out.println("\t");*/
            
            //try {
                //m.Banner_user();
                //b.bannereExiste(6);
                //b.Reactive_ban();
                //System.out.println(b.getlist_ban());
                //System.out.println(m.displayall_Rec());
                //m.update_admin();
                //b.Ban_Suppression();
                /*if(sc.verfi_satat("Reclamation_admin", 8)==true)
                {
                System.out.println("Yes");
                }
                else{
                System.out.println("Nooo");
                }
                } catch (SQLException ex) {
                ex.printStackTrace();        }*/
                /*sc.Stat_methode("Reclamation_user", 12);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }*/
            }
        
    }

    

