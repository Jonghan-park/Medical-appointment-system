<<<<<<< HEAD:src/java/service/GmailService.java
package service;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.*;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class GmailService {

    public static void sendMail(String to, String subject, String template, HashMap<String, String> tags) throws Exception {
        // {{firstname}} -> Anne
        // {{date}} -> Oct. 28
        String body = "";

        try {
            // read whole template into a single variable (body)
            BufferedReader br = new BufferedReader(new FileReader(new File(template)));
            String line = br.readLine();

            while (line != null) {
                body += line;
                line = br.readLine();
            }

            // replace all {{variable}} with the actual values
            for (String key : tags.keySet()) {
                body = body.replace("{{" + key + "}}", tags.get(key));
            }

        } catch (Exception e) {
            Logger.getLogger(GmailService.class.getName()).log(Level.SEVERE, null, e);
        }

        sendMail(to, subject, body, true);
    }

    public static void sendMail(String to, String subject, String body, boolean bodyIsHTML)
            throws MessagingException, NamingException {
        Context env = (Context) new InitialContext().lookup("java:comp/env");
        String username = (String) env.lookup("webmail-username");
        String password = (String) env.lookup("webmail-password");
        
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", host);
        props.put("mail.smtps.port", 465);
//        props.put("mail.smtps.ssl.enable", "true");
//        props.put("mail.smtps.ssl.trust", host);

        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        // create a message
        Message message = new MimeMessage(session);
        message.setSubject(subject);

        if (bodyIsHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }

        // address the message
        Address fromAddress = new InternetAddress(username);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        // send the message
        Transport transport = session.getTransport();
        transport.connect(username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
=======
package service;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.*;

/**
 *
 * @author Kevin, Samia, Fied, Yisong, Jihoon, Jonghan, Elly
 */
public class JavaMailService {

    public static void sendMail(String to, String subject, String template,
            HashMap<String, String> tags) throws Exception {
        String body = "";
        
        try(BufferedReader br = new BufferedReader(new FileReader(new File(template)))) {
            String line = br.readLine();
            
            while(line != null) {
                body += line;
                line = br.readLine();
            }

            for(String key : tags.keySet()) {
                body = body.replace("{{" + key + "}}", tags.get(key));
            }
        } catch(Exception e) {
            Logger.getLogger(JavaMailService.class.getName()).log(Level.SEVERE, null, e);
        }

        sendMail(to, subject, body, true);
    }

    public static void sendMail(String to, String subject, String body, boolean bodyIsHTML)
            throws MessagingException, NamingException {
        Context env = (Context) new InitialContext().lookup("java:comp/env");
        String username = (String) env.lookup("webmail-username");
        String password = (String) env.lookup("webmail-password");
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com"); 
        props.put("mail.smtp.port", 587); 
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
            }
	});
        
        Message message = new MimeMessage(session);
        message.setSubject(subject);
                
        if(bodyIsHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }

        Address fromAddress = new InternetAddress(username);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);
        
        Transport.send(message);     
    }
}
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6:src/java/service/JavaMailService.java
