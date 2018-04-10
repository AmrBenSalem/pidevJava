/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.util.*;
import static javafx.application.Application.launch;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
/**
 *
 * @author douha
 */
public class SendEmail {
    
    
    public static void envoyer(String emetteur,String recpteur,String subject,String desc){
        // Recipient's email ID needs to be mentioned.
    

      // Sender's email ID needs to be mentioned
     

      // Assuming you are sending email from localhost
      String host = "localhost";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);
//properties.setProperty("mail.smtp.port", "587");
      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(emetteur));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(recpteur));

         // Set Subject: header field
         message.setSubject(subject);

         // Now set the actual message
         message.setText(desc);

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
    
    
   
   
}


