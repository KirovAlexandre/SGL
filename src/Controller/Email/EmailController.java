/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Email;

import Util.HibernateUtil;
import java.util.Random;
import javax.swing.JOptionPane;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.hibernate.Query;
import org.hibernate.Session;
import Util.HibernateUtil;
import Model.Empregado;

/**
 *
 * @author Kirov Mabasso
 */
public class EmailController {

    private final String hostEmail = "kirovalexandre@gmail.com";
    private final String tokenAuthenticationEmail = "nxapbjgedkyfblqq";
    private final String hostName = "smtp.gmail.com";
    Session s = HibernateUtil.getSessionFactory().getCurrentSession();
    private static Random random = new Random();
    private static final int codigo = random.nextInt(100000 - 9999 + 1) + 9999;

    /**
     *
     * @param email recebe o email do recetor
     * @param subject recebe o assunto do email
     * @param message vai receber o codigo
     */
    public void sendEmail(String email, String subject, String message) {

        s.beginTransaction();
        String hql = "FROM Empregado WHERE email = :email";
        Query query = s.createQuery(hql);
        query.setParameter("email", email);
        Empregado em = (Empregado) query.uniqueResult();
        s.getTransaction().commit();

        if (em != null) {
            SimpleEmail simple = new SimpleEmail();
            simple.setHostName(hostName);
            simple.setSmtpPort(587);
            simple.setAuthenticator(new DefaultAuthenticator(hostEmail, tokenAuthenticationEmail));
            simple.setSSLOnConnect(true);
            try {
                simple.setFrom(hostEmail);
                simple.setSubject("Sistema de Gestão de Lanchonete - " + subject);
                simple.setMsg(message + codigo);
                simple.addTo(email);
                simple.send();
                JOptionPane.showMessageDialog(null, "Verifique o código de validação no endereço: " + em.getEmail());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Email não encontrado!");
        }

    }
    
    public void verificaCodigo(int codigoInserido){
        if(codigo == codigoInserido){
            JOptionPane.showMessageDialog(null, "Altere a senha!");
        }else{
            JOptionPane.showMessageDialog(null, "Código inválido!");
        }
    }

    public void recuperarUsuario(String email, String senha, String senha2) {

        s.beginTransaction();
        String hql = "FROM Empregado WHERE email = :email";
        Query query = s.createQuery(hql);
        query.setParameter("email", email);

        Empregado em = (Empregado) query.uniqueResult();

        if (senha.equals(senha2)) {
            if (em != null) {
                em.setSenha(senha);
                s.update(em);
                JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");
                s.getTransaction().commit();
            } else {
                JOptionPane.showMessageDialog(null, "Email não encontrado!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Senhas diferentes!");
        }
    }

    public static void main(String[] args) {

        //new EmailController().sendEmail("lo@gmail.com", "Recuperar a Senha", "");
        //new EmailController().recuperar("lo@gmail.com", "alterou","outra");
        //new EmailController().verificaCodigo(codigo);
    }
}
