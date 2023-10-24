/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author Kirov Mabasso
 */
public class SendHtmlEmail {
    
    private static final String fromEmail = "kirovalexandre@gmail.com";
    private static final String tokenAuthenticationEmail = "nxapbjgedkyfblqq";
    private static final String hostName = "smtp.gmail.com";
    private static final int port = 587;
    
    public static void main(String[] args) {
        // Configurações do email
        String toEmail = "heliojanuariosimango@gmail.com"; // Endereço de email do destinatário
        String subject = "Enviando um HTML email";
        String htmlContent = "<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"    <style>\n" +
"        body {\n" +
"            font-family: Arial, sans-serif;\n" +
"        }\n" +
"        .container {\n" +
"            max-width: 600px;\n" +
"            margin: 0 auto;\n" +
"            padding: 20px;\n" +
"            background-color: #f4f4f4;\n" +
"        }\n" +
"        h1 {\n" +
"            color: #333;\n" +
"        }\n" +
"        p {\n" +
"            color: #666;\n" +
"        }\n" +
"    </style>\n" +
"</head>\n" +
"<body>\n" +
"    <div class=\"container\">\n" +
"        <h1>Código de verificação</h1>\n" +
"        <p>Veja,</p>\n" +
"        <p>Este é um exemplo de como formatar um e-mail usando HTML.</p>\n" +
"        <p>Você pode usar várias tags HTML para formatar o conteúdo, como <em>itálico</em>, <strong>negrito</strong>, <a href=\"https://www.example.com\">links</a>, listas e muito mais.</p>\n" +
"        <p>Atenciosamente,</p>\n" +
"        <p>Seu Nome</p>\n" +
"    </div>\n" +
"</body>\n" +
"</html>";
        try {
            // Inicialize um objeto HtmlEmail
            HtmlEmail email = new HtmlEmail();
            email.setHostName(hostName);
            email.setSmtpPort(port);
            email.setAuthenticator(new DefaultAuthenticator(fromEmail, tokenAuthenticationEmail));
            email.setStartTLSEnabled(true); 

            // Configurar o email
            email.setFrom(fromEmail);
            email.addTo(toEmail);
            email.setSubject(subject);
            email.setHtmlMsg(htmlContent);

            // Envie o email
            email.send();
            System.out.println("Email enviado com sucesso!");
        } catch (EmailException e) {
            e.printStackTrace();
            System.out.println("Falha ao enviar o email.");
        }
    }
}
