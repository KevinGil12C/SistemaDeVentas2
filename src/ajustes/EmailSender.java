package ajustes;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class EmailSender {

    public void recuperaClave(String nombre, String correo, String user, String clave) {
        // Información de la cuenta de Gmail
        String username = "ropaleouniformesescolares@gmail.com";
        String password = "xqitxtkqfutumorl";

        // Configuración de las propiedades
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Creación de la sesión
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Creación del mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));
            message.setSubject("Recuperación de Contraseña - Ropa Leo Uniformes Escolares");

            // Contenido del mensaje en formato HTML
            String mensajeHtml = "<html>\n"
                    + "\n"
                    + "    <head>\n"
                    + "        <style>\n"
                    + "            h1{\n"
                    + "                color: #008080;\n"
                    + "                text-align: center;\n"
                    + "            }\n"
                    + "            h3{\n"
                    + "                font-family: Arial, Helvetica, sans-serif;\n"
                    + "                text-align: justify;\n"
                    + "                color: #008080;\n"
                    + "            }\n"
                    + "            h4{\n"
                    + "                font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;\n"
                    + "                text-align: center;\n"
                    + "                color: black;\n"
                    + "                font-size: 20px;\n"
                    + "            }\n"
                    + "            p{\n"
                    + "                font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;\n"
                    + "                font-size: 16px;\n"
                    + "                color: black;\n"
                    + "                text-align: justify;\n"
                    + "            }\n"
                    + "            .center{\n"
                    + "                display: grid;\n"
                    + "                justify-content: center;\n"
                    + "                align-items: center;\n"
                    + "                height: 150px; /* Ajusta la altura según tus necesidades */\n"
                    + "            }\n"
                    + "        </style>\n"
                    + "    </head>\n"
                    + "\n"
                    + "    <body>\n"
                    + "        <h1>¡Estimado " + nombre + "!</h1>\n"
                    + "        <p>Hemos recibido una solicitud de recuperación de contraseña para su cuenta en el sistema de Ropa Leo Uniformes Escolares. A continuación, le proporcionamos los detalles de inicio de sesión:</p>\n"
                    + "        <h3>Nombre de usuario: " + user + "</h3>\n"
                    + "        <h3>Contraseña: " + clave + "</h3>\n"
                    + "        <p>Le recomendamos cambiar su contraseña después de iniciar sesión por motivos de seguridad.</p>\n"
                    + "        <p>Si no ha solicitado esta recuperación de contraseña, le recomendamos que se comunique con nuestro equipo de soporte de inmediato.</p>\n"
                    + "        <h4><br>Gracias por utilizar nuestros servicios.</h4>\n"
                    + "        <h4><br>Atentamente <br>El equipo de Ropa Leo Uniformes Escolares</h4>\n"
                    + "    </body>\n"
                    + "\n"
                    + "</html>";

            message.setContent(mensajeHtml, "text/html");

            // Envío del mensaje
            Transport.send(message);

            //System.out.println("Correo enviado exitosamente.");
        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }

    }

    public String enviarCorreoConArchivoAdjunto(String destinatarioCorreo, String nombreArchivoAdjunto) {
        // Información de la cuenta de Gmail
        String mensaje="";
        String username = "ropaleouniformesescolares@gmail.com";
        String password = "xqitxtkqfutumorl";
        
        String asuntoCorreo = nombreArchivoAdjunto+" PDF de venta";
        nombreArchivoAdjunto+=".pdf";
        // Configuración de las propiedades
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Creación de la sesión
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Creación del mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatarioCorreo));
            message.setSubject(asuntoCorreo);

            // Crear el contenido del mensaje en formato HTML
            String mensajeHtml = "<html>\n"
                    + "\n"
                    + "<head>\n"
                    + "    <style>\n"
                    + "        body {\n"
                    + "            font-family: Arial, Helvetica, sans-serif;\n"
                    + "            color: #444;\n"
                    + "            background-color: #f2f2f2;\n"
                    + "            padding: 20px;\n"
                    + "            line-height: 1.6;\n"
                    + "        }\n"
                    + "\n"
                    + "        h1 {\n"
                    + "            color: #008080;\n"
                    + "            text-align: center;\n"
                    + "            margin-bottom: 20px;\n"
                    + "        }\n"
                    + "\n"
                    + "        h3 {\n"
                    + "            font-size: 16px;\n"
                    + "            text-align: justify;\n"
                    + "            margin-bottom: 20px;\n"
                    + "        }\n"
                    + "\n"
                    + "        h4 {\n"
                    + "            font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;\n"
                    + "            text-align: center;\n"
                    + "            color: black;\n"
                    + "            font-size: 18px;\n"
                    + "            margin-top: 20px;\n"
                    + "        }\n"
                    + "\n"
                    + "        a.button {\n"
                    + "            display: inline-block;\n"
                    + "            background-color: #008080;\n"
                    + "            color: white;\n"
                    + "            padding: 10px 20px;\n"
                    + "            text-align: center;\n"
                    + "            text-decoration: none;\n"
                    + "            border-radius: 5px;\n"
                    + "            margin-top: 20px;\n"
                    + "        }\n"
                    + "\n"
                    + "        a.button:hover {\n"
                    + "            background-color: #006666;\n"
                    + "        }\n"
                    + "    </style>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "    <h1>¡Estimado/a!</h1>\n"
                    + "    <h3>Le informamos que hemos enviado el reporte PDF que solicitó. Por favor, revise su correo electrónico para encontrar el archivo adjunto.</h3>\n"
                    + "    <h3>Si tiene alguna pregunta o necesita ayuda adicional, no dude en contactarnos. Estamos para servirle.</h3>\n"
                    + "    <h3>¡Gracias por utilizar nuestros servicios y esperamos seguir atendiéndole en el futuro!</h3>\n"
                    + "    <h4>Atentamente<br>El equipo de Ropa Leo Uniformes Escolares</h4>\n"
                    + "</body>\n"
                    + "</html>";

            // Asignar el contenido HTML al mensaje
            message.setContent(mensajeHtml, "text/html");

            // Adjuntar el archivo PDF al mensaje
            Multipart multipart = new MimeMultipart();

            // Parte del contenido HTML (texto)
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(mensajeHtml, "text/html");
            multipart.addBodyPart(messageBodyPart);

            // Parte del archivo adjunto (PDF)
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            String file = "C://reportes//" + nombreArchivoAdjunto;
            DataSource source = new FileDataSource(file);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(nombreArchivoAdjunto);
            multipart.addBodyPart(attachmentBodyPart);

            // Asignar el contenido y los archivos adjuntos al mensaje
            message.setContent(multipart);

            // Envío del mensaje
            Transport.send(message);
            mensaje = "CORREO ENVIADO EXITOSAMENTE.";
        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
        return  mensaje;
    }

//    public static void main(String[] args) {
//        enviarCorreoConArchivoAdjunto("kebo.jcg77@gmail.com", "Reporte 1");
//    }
}
