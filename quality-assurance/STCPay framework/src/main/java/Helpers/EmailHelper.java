package Helpers;

import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterSuite;

public class EmailHelper {

    String sender;
    String password;
    String smtpHost;
    String smtpPort;
    String smtpAuth;
    List<String> recipients;

    public EmailHelper() throws ParseException {
        JSONParser parser = new JSONParser();
        Object data = ReadWriteHelper.getJsonInfo(
                "C:\\Users\\amuhaidat.c\\Desktop\\GitHub\\Stcpay\\quality-assurance\\STCPay framework\\src\\main\\java\\Configs\\testConfig.json"
        );
        JSONObject testConfig = (JSONObject) parser.parse(String.valueOf(data));
        JSONObject emailConfig = (JSONObject) testConfig.get("emailConfig");
        sender = (String) emailConfig.get("sender");
        password = (String) emailConfig.get("password");
        smtpHost = (String) emailConfig.get("smtp_host");
        smtpPort = (String) emailConfig.get("smtp_port");
        smtpAuth = (String) emailConfig.get("smtp_auth");
        recipients = (List<String>) emailConfig.get("recipients");
    }

    public void sendEmail(String attachmentPath, String platform) {
        String to = "";
        for (int i = 0; i < recipients.size(); i++) {
            to = to + "," + recipients.get(i);
        }
        System.out.println(to);
        final String username = sender;
        final String password = this.password;
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", smtpAuth);
        prop.put("mail.smtp.host", smtpHost);
        prop.put("mail.smtp.port", smtpPort);
//        prop.put("mail.smtp.socketFactory.port", smtpPort);
        prop.put("mail.smtp.starttls.enable", "true");
//        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(
                prop,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                }
        );
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject("Automation Test Result");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(
                    "Hi, \n\nThis is an auto generated email to notify you with the automation test result " +
                            "for " +
                            platform +
                            ", kindly find the attached report. \n\nThank you,\nTest Automation Team"
            );
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String filename = attachmentPath;
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws ParseException {
        EmailHelper mail = new EmailHelper();
        mail.sendEmail(
                "C:/Users/amuhaidat.c/Desktop/GitHub/Stcpay/quality-assurance/STCPay framework/src/main/resources/Reports/WebReports/",
                "Web Application Test"
        );
    }
}
