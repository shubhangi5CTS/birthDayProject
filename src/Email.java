import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class Email {

    //use javamailAPI and JAF

    static String to="";
    static String name="";

    public Email(String name, String to)
    {
        this.name=name;
        this.to=to;

        System.out.println(" name:"  + name + " Email ID:"  + to);

    }

    public void email()
    {
         //String to= "ssmithari5@gmail.com";

        String from="ssmithari5@gmail.com";
        final String username="ssmithari5";
        final String pass="Scogni@235";

        String host="smtp.gmail.com";

        Properties prop= new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port",587);

        Session ses=Session.getInstance(prop,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, pass);
            }
        });


        try
        {
            //create a default Mimemessage object
            Message msg=new MimeMessage(ses);

            //set from
            msg.setFrom(new InternetAddress(from));

            //set to
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            //set subject
            msg.setSubject("Wish You Happy BirthDay!!!)");

            //this msg has 2 part,the body and embebded image
            MimeMultipart multipart=new MimeMultipart("Realted");


            //first part:html
            BodyPart msgBody=new MimeBodyPart();
            String htmltext="<h1>Dear &nbsp;"+ name + ",</h1><img src=\"cid:image\">";
            msgBody.setContent(htmltext,"text/html");

            //add it
            multipart.addBodyPart(msgBody);

            //second part:image part
            msgBody=new MimeBodyPart();

            DataSource fds=new FileDataSource(System.getProperty("user.dir")+"\\src\\Images\\IMG_20160530_104559.jpg");
            msgBody.setDataHandler(new DataHandler(fds));
            msgBody.setHeader("Content-ID", "<image>");

            //add image to multipart
            multipart.addBodyPart(msgBody);

            msg.setContent(multipart);

            Transport.send(msg);

            System.out.println("Sent message successfully");



        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }


    }




}
