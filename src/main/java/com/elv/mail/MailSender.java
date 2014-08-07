package com.elv.mail;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

public class MailSender {

    public static void Send(MailMessage message) throws AddressException, MessagingException {

        Properties props = System.getProperties();
        props.put("mail.smtp.host", message.getSmtp());
        props.put("mail.smtp.port", message.getPort());
        props.put("mail.smtp.auth", message.isAuth() + "");

        MailAuthenticator auth = null;
        if (message.isAuth())
            auth = new MailAuthenticator(message.getUsername(), message.getPassword());

        Session session = Session.getInstance(props, auth);

        MimeMessage mimeMessage = new MimeMessage(session);

        if (message.getContentInputStream() != null)
            mimeMessage = new MimeMessage(session, message.getContentInputStream());

        if (message.getFrom() != null)
            mimeMessage.setFrom(new InternetAddress(message.getFrom()));

        if (message.getTo() != null) {
            String toString = message.getTo().toString();
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toString.substring(1, toString.length() - 1), false));
        }

        if (message.getCc() != null) {
            String ccString = message.getCc().toString();
            mimeMessage.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccString.substring(1, ccString.length() - 1), false));
        }

        if (message.getBcc() != null) {
            String bccString = message.getBcc().toString();
            mimeMessage.setRecipients(Message.RecipientType.CC, InternetAddress.parse(bccString.substring(1, bccString.length() - 1), false));
        }

        if (message.getSubject() != null)
            mimeMessage.setSubject(message.getSubject());

        if (message.getMimeMultipart() != null)
            mimeMessage.setContent(message.getMimeMultipart());

        if (message.getBody() != null)
            mimeMessage.setText(message.getBody(), "utf-8");

        mimeMessage.setSentDate(new Date());

        mimeMessage.addHeader("X-Mailer", "MailBox");

        mimeMessage.saveChanges();

        Transport.send(mimeMessage);
    }

    public static MimeBodyPart createAttachment(String fileName) throws Exception {
        MimeBodyPart attachmentPart = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(fileName);
        attachmentPart.setDataHandler(new DataHandler(fds));
        attachmentPart.setFileName(fds.getName());
        return attachmentPart;
    }

    public static void main(String[] args) throws Exception {

        MailMessage message = new MailMessage();
        message.setAuth(true);
        message.setSmtp("smtp.yandex.com");
        message.setPort("25");
        message.setFrom("=?GB2312?B?xr2wssK9vfDL+Q==?= <lufax.c@yandex.com>");
        message.setUsername("lufax.c");
        message.setPassword("ttp12345");
        message.getTo().add("lufaxa@163.com");
        message.setSubject("=?GB2312?B?zqqyxri71PbWtaO6xPq1xMXz09HR+8frxPrAtKGwwr298Mv5obHSu8bwxdzTrkNQ?= =?GB2312?B?SQ==?=" + "a12");

        InputStream is = new FileInputStream("a.eml");
        message.setContentInputStream(is);

        MailSender.Send(message);
    }

}
