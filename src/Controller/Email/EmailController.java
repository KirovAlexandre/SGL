/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Email;

import javax.swing.JOptionPane;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Kirov Mabasso
 */
public class EmailController {
    
    private final String hostEmail = "kirovalexandre@gmail.com";
    private final String tokenAuthenticationEmail = "nxapbjgedkyfblqq";
    private final String hostName = "smtp.gmail.com";
    /**
     * 
     * @param email recebe o email do recetor
     * @param subject recebe o assunto do email
     * @param message vai receber o codigo
     */
    public void sendEmail(String email, String subject, String message){
        SimpleEmail simple = new SimpleEmail();
        simple.setHostName(hostName);
        simple.setSmtpPort(587);
        simple.setAuthenticator(new DefaultAuthenticator(hostEmail,tokenAuthenticationEmail));
        simple.setSSLOnConnect(true);
        try{
            simple.setFrom(hostEmail);
            simple.setSubject("Sistema de Gestão de Lanchonete - "+subject);
            simple.setMsg(message );
            simple.addTo(email);
            simple.send();
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
    }
}
    public static void main(String[] args) {
         
       new EmailController().sendEmail("heliojanuariosimango@gmail.com","Recuperar a Senha", "");
    }
}
