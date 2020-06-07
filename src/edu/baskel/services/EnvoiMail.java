/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import edu.baskel.utils.InputValidation;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author dell
 */
public class EnvoiMail {
    int ran;
    
    
    //envoyer un mail avec le code de reinitialisation mot de passe
    public void envoyerMail1(String too) {
        try {
            Random rann = new Random();
            ran = rann.nextInt(999999);
            System.out.println("Préparation de l envoie du mail");
            String host = "smtp.gmail.com";
            String user = "baskeltn395@gmail.com";
            String pass = "baskel.tn";
            String to = too;
            String subject = "reseting password";
            String message = "Votre code de reinitalisation est " + " " + ran;
            boolean sessionDebug = false;
            Properties pros = System.getProperties();
            pros.put("mail.smtp.starttls.enable", "true");
            pros.put("mail.smtp.host", "host");
            pros.put("mail.smtp.port", "587");
            pros.put("mail.smtp.auth", "true");
            pros.put("mail.smtp.starttls.required", "true");
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(pros, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(user));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setText(message);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Email envoyé");
            InputValidation.notificationsucces("succés", "le code a été envoyer verifier votre boite mail");

        } catch (Exception ex) {

        }
    }
     public void envoyerMailHistorique(String too, String msgs) {
        try {
            
            System.out.println("Préparation de l envoie du mail");
            String host = "smtp.gmail.com";
            String user = "baskeltn395@gmail.com";
            String pass = "baskel.tn";
            String to = too;
            String subject = "Connexion";
            String message = msgs ;
            boolean sessionDebug = false;
            Properties pros = System.getProperties();
            pros.put("mail.smtp.starttls.enable", "true");
            pros.put("mail.smtp.host", "host");
            pros.put("mail.smtp.port", "587");
            pros.put("mail.smtp.auth", "true");
            pros.put("mail.smtp.starttls.required", "true");
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(pros, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(user));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setText(message);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Email envoyé");

        } catch (Exception ex) {

        }
    }
    //verifier le code envoyé avant dla reinitialisation de MP
    public boolean verifierCode1(String code) throws IOException {

        if (Integer.valueOf(code) == ran) {
            return true;
        } else {
            return false;
        }

    }
    
    //contacter admin
    public void envoyerMailAdmin( String sub ,String msge) {
        try {
            
            System.out.println("Préparation de l envoie du mail");
            String host = "smtp.gmail.com";
            String user = "baskelcontact@gmail.com";
            String pass = "baskel.tn2020";
            String to = "baskeltn395@gmail.com";
            String subject = sub;
            String message = msge ;
            boolean sessionDebug = false;
            Properties pros = System.getProperties();
            pros.put("mail.smtp.starttls.enable", "true");
            pros.put("mail.smtp.host", "host");
            pros.put("mail.smtp.port", "587");
            pros.put("mail.smtp.auth", "true");
            pros.put("mail.smtp.starttls.required", "true");
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(pros, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(user));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setText(message);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Email envoyé");
            InputValidation.notificationsucces("succés", "le mail a été envoyer avec succés");

        } catch (Exception ex) {

        }
    }
     public void MailReclamationAdmin(String reclameur , String obj) {
        try {
            
            System.out.println("Préparation de l envoie du mail");
            String host = "smtp.gmail.com";
            String user = "baskelcontact@gmail.com";
            String pass = "baskel.tn2020";
            String to = "sopra.ps4@gmail.com";
            String subject = ("Nouvelle Reclamation Ajouter à la boit de réception ");
            String message = ("Vous avez reçu nouvelle Reclamation sur notre Boîte réception créée par le membre "+" "+reclameur+" \n \n l'objet de Reclamation est : "+obj+" ") ;
            boolean sessionDebug = false;
            Properties pros = System.getProperties();
            pros.put("mail.smtp.starttls.enable", "true");
            pros.put("mail.smtp.host", "host");
            pros.put("mail.smtp.port", "587");
            pros.put("mail.smtp.auth", "true");
            pros.put("mail.smtp.starttls.required", "true");
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(pros, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(user));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setText(message);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            

        } catch (Exception ex) {

        }
    }
    
}
