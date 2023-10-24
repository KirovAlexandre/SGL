/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Login;

import Model.Empregado;
import Model.Funcaoempregado;
import Util.HibernateUtil;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import java.lang.StringIndexOutOfBoundsException;
import org.apache.commons.mail.DefaultAuthenticator;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.apache.commons.mail.SimpleEmail;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;
import java.util.Scanner;
///////////
/**
 *
 * @author Kirov Mabasso
 */
public class Autenticacao {

    Session s = HibernateUtil.getSessionFactory().getCurrentSession();

    //String email, String senha, int nivel
    public void listar() {
        try {
            s.beginTransaction();
            List< Empregado> lista = (List<Empregado>) s.createQuery("From Empregado").list();
            for (Empregado fe : lista) {
                if (fe.getEstadoB() == true) {
                    System.out.println("Id: " + fe.getPrimeiroNome());
                    System.out.println("Status: " + fe.getApelido());
                    System.out.println("Função: " + fe.getEmail());
                    System.out.println("Descrição: " + fe.getSenha());
                }
            }
            s.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());

        }
    }

    public boolean autenticacao(String email, String senha, int nivel) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        s = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = s.beginTransaction();
            Query query = s.createQuery("From  Empregado WHERE email = :email AND senha = :senha AND nivel = :nivel ");
            query.setParameter("email", email);
            query.setParameter("senha", senha);
            query.setParameter("nivel", nivel);
            
            Empregado em = (Empregado)query.uniqueResult();
            transaction.commit();
            return em != null;
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
           return false;
        }
       
         
    }
    
    public Empregado redifinirSenha(String email, String senha){
        s.beginTransaction();
      Empregado fe = (Empregado) s.get(Empregado.class, email);  
      if(fe.getEmail().equals(email)){
          fe.setSenha(senha);
          JOptionPane.showMessageDialog(null, "Senha redifinida!");
          s.getTransaction().commit();
          return fe;
           
      }else{
          JOptionPane.showMessageDialog(null, "Verifique o email");
          return null;
      }
     
    }
     public void recuperarSenha(String email){
        /* boolean controle = false;
         

        try{
        s.beginTransaction();
       // Empregado fe = (Empregado) s.get(Empregado.class, email);
        
        
         String hql = "FROM Empregado e WHERE e.email = :email";
        Query query = s.createQuery(hql);
        query.setParameter("email", email);
        
        List<Empregado> emp = query.list();
        
        Empregado empregado =(Empregado) emp;
        
        if(empregado != null){
            controle = empregado.getEmail().equals(email) ? true: false;
        }else{
            JOptionPane.showMessageDialog(null, "Email invalido");
        }}catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepcao: "+e.getMessage());
        }
        while(controle){
            controle = false;
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        
        int codigo = random.nextInt(10000 - 9999 + 1) + 9999;
        String nome = "kirovalexandre@gmail.com";
        String senha = "nxapbjgedkyfblqq";
        SimpleEmail simple = new SimpleEmail();
        simple.setHostName("smtp.gmail.com");
        simple.setSmtpPort(587);
        simple.setAuthenticator(new DefaultAuthenticator(nome,senha));
        simple.setSSLOnConnect(true);
        
        try{
            simple.setFrom(nome);
            simple.setSubject("Recuperar senha.");
            simple.setMsg("Sistema de Gestão de Lanchonete /n Código de confirmação: " + codigo);
            simple.addTo(nome);
            //heliojanuariosimango@gmail.com
            simple.send();
            JOptionPane.showMessageDialog(null, "Verifique o código no Email!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }
         System.out.println("Insira o codigo");
        int cod = sc.nextInt();
        
        if(codigo == cod){
            JOptionPane.showMessageDialog(null, "Redifinir senha" );
            
        }else{
            JOptionPane.showMessageDialog(null, "Codigo de confirmacao errado, tente novamente! ");
        }
        }*/
       // s.getTransaction().commit();
     }
    
    public static void main(String[] args) {
        
        /*Autenticacao au = new Autenticacao();
        Validacao valida = new Validacao();
        
        if(au.autenticacao("lo@gmail.com", "123er", 0)){
            System.out.println("Administrador logado");
        }else if (au.autenticacao("lo@gmail.com", "23er", 2)){
            System.out.println("Funcionario logado");
        } else{
             System.out.println("Verifique o email, ou a senha!");
        }*/
        
        
        //new Autenticacao().recuperarSenha("lo@gmail.co");
        
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        
        int codigo = random.nextInt(10000 - 9999 + 1) + 9999;
        String nome = "kirovalexandre@gmail.com";
        String senha = "nxapbjgedkyfblqq";
        SimpleEmail simple = new SimpleEmail();
        simple.setHostName("smtp.gmail.com");
        simple.setSmtpPort(587);
        simple.setAuthenticator(new DefaultAuthenticator(nome,senha));
        simple.setSSLOnConnect(true);
        
        try{
            simple.setFrom(nome);
            simple.setSubject("Recuperar senha.");
            simple.setMsg("Sistema de Gestão de Lanchonete \n Código de confirmação: " + codigo);
            simple.addTo("heliojanuariosimango@gmail.com");
            //heliojanuariosimango@gmail.com
            simple.send();
            JOptionPane.showMessageDialog(null, "Verifique o código no Email!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }
         System.out.println("Insira o codigo");
        int cod = sc.nextInt();
        
        if(codigo == cod){
            JOptionPane.showMessageDialog(null, "Redifinir senha" );
            
        }else{
            JOptionPane.showMessageDialog(null, "Codigo de confirmacao errado, tente novamente! ");
        }
        }
     
    
}
