
package edu.baskel.services;

import edu.baskel.entities.Membre;
import edu.baskel.entities.Reparateur;
import edu.baskel.utils.ConnectionBD;
import edu.baskel.utils.SessionInfo;
import static edu.baskel.utils.SessionInfo.iduser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author dell
 */
public class MembreCRUD {

    /* public static String nomm;
    public static String prenomm;
    public static String telm;
    public static String emailm;
    public static String adressem;
    public static String mpassem;
    public static String datem;
     */
    Connection cnx;
    Stage owner = new Stage();
    Membre membreLogged;
    private int val;

    public MembreCRUD() {
        cnx = ConnectionBD.getInstance().getCnx();
    }
//ajouter membre

    public Membre ajouterMembres2(Membre m) {
        try {
            String requete = "INSERT INTO membre (nom_u, prenom_u, adresse_u,email_u,sexe_u,date_u, mot_passe_u,num_tel_u,image_u)"
                    + "VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, m.getNom_u());
            pst.setString(2, m.getPrenom_u());
            pst.setString(3, m.getAdresse_u());
            pst.setString(4, m.getEmail_u());
            pst.setString(5, m.getSexe_u());
            pst.setDate(6, m.getDate_u());
            pst.setString(7, m.getMot_passe_u());
            pst.setString(8, m.getNum_tel_u());
            pst.setString(9, m.getImage_u());
            //pst.setString(10, 0); lezem nbadalha int

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return m;
    }
// update info d un membre

    public void updateMembre(Membre m) {
        try {
            String requete = "UPDATE membre SET nom_u=?, prenom_u=?, adresse_u=?,email_u=?, sexe_u=?, date_u=?, mot_passe_u=?, num_tel_u=? WHERE id_u=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(9, m.getId_u());
            pst.setString(1, m.getNom_u());
            pst.setString(2, m.getPrenom_u());
            pst.setString(3, m.getAdresse_u());
            pst.setString(4, m.getEmail_u());
            pst.setString(5, m.getSexe_u());
            pst.setDate(6, m.getDate_u());
            pst.setString(7, m.getMot_passe_u());
            pst.setString(8, m.getNum_tel_u());
            //pst.setString(9, m.getImage_u());

            pst.executeUpdate();
            System.out.println("personne modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

//affichage de liste de tt les membres
    public List<Membre> getlistMembre() {
        List<Membre> listeMembre = new ArrayList<>(); //lezemha hné bech ywalli ychouf return

        try {
            String requete = "SELECT* FROM membre";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Membre m = new Membre();
                m.setId_u(rs.getInt(1));
                m.setNom_u(rs.getString(2));
                m.setPrenom_u(rs.getString(3));
                m.setAdresse_u(rs.getString(4));
                m.setEmail_u(rs.getString(5));
                m.setSexe_u(rs.getString(6));
                m.setDate_u(rs.getDate(7));
                m.setMot_passe_u(rs.getString(8));
                m.setNum_tel_u(rs.getString(9));
                m.setImage_u(rs.getString(10));
                m.setType_u(rs.getString(11));
                listeMembre.add(m);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeMembre;

    }
    
//verification ban
    public int ValidationBan(String emailu) {
        try {
            String req = "SELECT validation_u FROM membre where email_u = ?";
            PreparedStatement per = cnx.prepareStatement(req);
            per.setString(1, emailu);
            ResultSet rs = per.executeQuery();
            if(!rs.next()){
                System.out.println("mich mawjoud");
            }else{
                val = rs.getInt("validation_u");
               
        }} catch (SQLException ex) {
            ex.printStackTrace();
        }
        return val;
    }
    
// verification email et mot de passe au login
    public Membre Verification(String email, String motdepasse) {
        try {
            String sq1 = "SELECT * FROM membre where email_u=? AND mot_passe_u=?";
            PreparedStatement prep = cnx.prepareStatement(sq1);
            prep.setString(1, email);
            prep.setString(2, motdepasse);
            ResultSet res = prep.executeQuery();

            if (!res.next()) {
                return null;
                //return false;
            } else {

                System.out.println("authentification ok!");
                iduser = res.getInt("id_u");
                String nomm = res.getString("nom_u");
                String prenomm = res.getString("prenom_u");
                String telm = res.getString("num_tel_u");
                String emailm = res.getString("email_u");
                String adressem = res.getString("adresse_u");
                String mpassem = res.getString("mot_passe_u");
                Date datem = res.getDate("date_u");
                String imagem = res.getString("image_u");
                membreLogged = new Membre(iduser, nomm, prenomm, adressem, emailm, telm, datem, motdepasse, telm, imagem);

                SessionInfo.setLoggedM(membreLogged);

                System.out.println(membreLogged);

                System.out.println(iduser);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return membreLogged;
        //return true;

    }
    
// verifaction s il ya deja un compte avec cette adresse mail a l inscription
    public boolean VerificationExistence(Membre m) {
        try {
            String sq1 = "SELECT * FROM membre where email_u=?";
            PreparedStatement prep = cnx.prepareStatement(sq1);
            prep.setString(1, m.getEmail_u());
            ResultSet res = prep.executeQuery();
            if (res.next()) {

                return false;
            }
        } catch (Exception ex) {

        }
        return true;
    }
    
// ajouter user a l inscription
    public Membre AddUser(Membre user) {

        //if (user instanceof Membre) {
        this.ajouterMembres2(user);

        //}
        return user;
    }

    public Reparateur adduser2(Reparateur user) {

        // if (user instanceof Reparateur) {
        ReparateurCRUD rc = new ReparateurCRUD();
        rc.ajouterReparateur((Reparateur) user);
        //}

        return user;
    }
    
// afficher les info d  un seul membre
    public void AfficherMembre(Membre m) {
        String requet = "SELECT * FROM membre WHERE id_u=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requet);
            pst.setInt(1, m.getId_u());
            ResultSet rs = pst.executeQuery();
            System.out.println("donné afficher");
            if (rs.next()) {
                m.setNom_u(rs.getString("nom_u"));
                m.setPrenom_u(rs.getString("prenom_u"));
                m.setEmail_u(rs.getString("email_u"));
                m.setAdresse_u(rs.getString("adresse_u"));
                m.setNum_tel_u(rs.getString("num_tel_u"));
                m.setMot_passe_u(rs.getString("mot_passe_u"));
                m.setDate_u(rs.getDate("date_u"));

            } else {
                System.out.println("erreur d affcihage");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
// partie a voir


    /*
            public void AfficherMembre(int id) {
            String requet = "SELECT * FROM membre WHERE id_u=?";
            try {
            PreparedStatement pst = cnx.prepareStatement(requet);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            System.out.println("donné afficher");
            if (rs.next()) {
            nomm = rs.getString("nom_u");
            prenomm = rs.getString("prenom_u");
            emailm = rs.getString("email_u");
            adressem = rs.getString("adresse_u");
            telm = rs.getString("num_tel_u");
            mpassem = rs.getString("mot_passe_u");
            datem = rs.getDate("date_u").toString();
            System.out.println(datem);
            
            } else {
            System.out.println("erreur d affcihage");
            }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
            
            }
     */
 /*
    public void AfficherMembre(int id) {
        String requet = "SELECT * FROM membre WHERE id_u=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requet);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            System.out.println("donné afficher");
            if (rs.next()) {
                nomm = rs.getString("nom_u");
                prenomm = rs.getString("prenom_u");
                emailm = rs.getString("email_u");
                adressem = rs.getString("adresse_u");
                telm = rs.getString("num_tel_u");
                mpassem = rs.getString("mot_passe_u");
                datem = rs.getDate("date_u").toString();
                System.out.println(datem);

            } else {
                System.out.println("erreur d affcihage");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
     */
    // veriffication par mot de passe avant la modif des info
    public boolean VerificationChgInfo(String mot_pas) {
        try {
            String sq1 = "SELECT * FROM membre where mot_passe_u=?";
            PreparedStatement pst = cnx.prepareStatement(sq1);
            pst.setString(1, mot_pas);
            ResultSet res = pst.executeQuery();

            if (!res.next()) {
                /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setContentText("Mot de passe incorrectS");
                alert.show();*/
                return false;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return true;

    }
// update du mot de passe (forgot password)

    public void changerMP(String eml, String passm) {

        try {
            String updatQuery = "UPDATE membre SET mot_passe_u=? WHERE email_u =?";
            PreparedStatement pst = cnx.prepareStatement(updatQuery);
            pst.setString(1, passm);
            pst.setString(2, eml);
            pst.executeUpdate();
            System.out.println("reset done");
        } catch (Exception ex) {

        }
    }
// suprression d un compte par son utilisateur

    public void supprimerMembre(int id) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("supression compte");
        alert1.setHeaderText("Information");
        alert1.setContentText("etes vous sur de vouloir supprimer votre compte");
        Optional<ButtonType> action = alert1.showAndWait();
        if (action.get() == ButtonType.OK) {
            try {
                String requete = "DELETE FROM membre WHERE id_u=?";
                PreparedStatement pst = cnx.prepareStatement(requete);
                pst.setInt(1, id);
                pst.executeUpdate();
                pst.close();
                System.out.println("membre supprimé");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public void ajouterban(int id){
        String query = null;
        try {

            query = "UPDATE membre SET nbr_ban_u=nbr_ban_u+1 WHERE id_u=?;";
            PreparedStatement pstmt = cnx.prepareStatement(query);

            pstmt.setInt(1,id);

            pstmt.executeUpdate();

            System.err.println("Insert: OK!");

        } catch (SQLException e) {
            System.out.println("Insert reclamtion : Query error ->" + e.getMessage());
        }

    }

}
