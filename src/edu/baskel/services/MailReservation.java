/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.services;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Hela
 */
public class MailReservation {
    public static void envoyerMail(String recpt,String subject) throws Exception{
        System.out.println("******mail*****");
        
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
      String monCompte = "baskeltn123@gmail.com";
      String password = "baskel12345";
      
      Session session = Session.getDefaultInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(monCompte, password);
            }
  
      });     
      
      Message message = prepareMessage(session,monCompte,recpt,subject);
      Transport.send(message);
        System.out.println("Mail envoyé");
 }    
    private static Message prepareMessage(Session session,String monCompte,String recpt,String subject) throws MessagingException {
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(monCompte));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recpt) );
            message.setSubject("Réservation du vélo");
            
            message.setText(subject);
            System.out.println(subject);
            return message;
        } catch (AddressException ex) {
           ex.printStackTrace();
        }
        return null;
    }
}
