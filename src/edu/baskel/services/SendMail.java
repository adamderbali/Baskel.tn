/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;



/**
 *
 * @author sabri
 */
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendMail {
    
    public static void envoiMail(String recp) throws Exception{
        System.out.println("Preparation mail");
        
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
      String monCompte = "njiwa.nousou@gmail.com";
      String password = "NesNaj2019";
      
      Session session = Session.getDefaultInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(monCompte, password);
            }
  
      });     
      
      Message message = prepareMessage(session,monCompte,recp);
      Transport.send(message);
        System.out.println("Mail envoyé");
 }      
      

    private static Message prepareMessage(Session session,String monCompte,String recp) throws MessagingException {
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(monCompte));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recp) );
            message.setSubject("Conframtion participation");
            message.setSubject("Conframtion participation");
            message.setText("votre participation est confirmé");
            return message;
        } catch (AddressException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    }
    

