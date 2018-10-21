import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Main {

    public void sendMessage(String from, String to, String sub, String msg, String pass){
        Properties props = System.getProperties();

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required", "true");
        boolean sessionDebug = false;
//        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        Session mailSession = Session.getDefaultInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication(){
            return  new PasswordAuthentication(from,pass);
        }});
        mailSession.setDebug(sessionDebug);
                try {
                    Message message = new MimeMessage(mailSession);
                    message.setFrom(new InternetAddress(from));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    message.setSubject(sub);
                    message.setText(msg);
                    Transport transport = mailSession.getTransport("smtp");
                    transport.connect("smtp.gmail.com", from, pass);
                    transport.send(message);
                    transport.close();

                    System.out.println("Ok");

                }catch (MessagingException e){

                    throw new RuntimeException(e);

                }

    }

    public static void main(String[] args) {

        Main x = new Main();
        x.sendMessage("sender's mail id", "recipent's mail id", "Hello", "Testing>>>>>>>>>>>>>>>>>>>", "password");
    }
}
