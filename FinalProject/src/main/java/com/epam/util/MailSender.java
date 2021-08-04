package com.epam.util;

import com.epam.entity.Property;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    private static MailSender instance = null;
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(MailSender.class);

    public static MailSender getInstance(){
        if(instance == null){
            instance = new MailSender();
        }
        return instance;
    }

    public void sendMail(String email, String letter){
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        String to = email;
        String from = Property.getInstance().getMAIL();
        String pass = Property.getInstance().getMAIL_PASS();

        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");

        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        try {
            Session session = Session.getDefaultInstance(props,
                    new Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(from, pass);
                        }});
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Verifying mail");
            message.setText(letter);

            Transport tr = session.getTransport();
            tr.connect(null, pass);
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
        } catch (MessagingException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private MailSender(){}
}
