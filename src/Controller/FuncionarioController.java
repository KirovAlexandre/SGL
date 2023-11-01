/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Empregado;
import Model.Funcaoempregado;
import Util.HibernateUtil;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 *
 * @author Kirov Mabasso
 */
public class FuncionarioController {

    Session s = HibernateUtil.getSessionFactory().getCurrentSession();
    Empregado em;

    public void store(Empregado em) {

        try {
            s.beginTransaction();
            s.save(em);
            s.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }
    }

    public void listar() {
        try {
            s.beginTransaction();
            List< Empregado> lista = (List<Empregado>) s.createQuery("From Empregado").list();
            for (Empregado fe : lista) {
                if (fe.getEstadoB() == true) {
                    System.out.println("Apelido: " + fe.getApelido());
                    System.out.println("Primeiro Nome: " + fe.getPrimeiroNome());
                    System.out.println("Email: " + fe.getEmail());
                    System.out.println("Senha: " + fe.getSenha());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());

        }
        s.getTransaction().commit();
    }

    public void delete(int id) {
        try {
            s.beginTransaction();
            Empregado fe = (Empregado) s.get(Empregado.class, id);
            if (fe.getEstadoB() == true) {
                fe.setEstadoB(Boolean.FALSE);
                JOptionPane.showMessageDialog(null, "Funcionario eliminado!");
            } else if (fe.getEstadoB() == false) {
                JOptionPane.showMessageDialog(null, "Este funcionário já foi eliminado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }
        s.getTransaction().commit();
    }

    public void restaurar(int id) {
        try {
            s.beginTransaction();
            Empregado fe = (Empregado) s.get(Empregado.class, id);
            if (fe.getEstadoB() == false) {
                fe.setEstadoB(Boolean.TRUE);
                JOptionPane.showMessageDialog(null, "Funcionario Restaurado!");
            } else if(fe.getEstadoB() == true){
                JOptionPane.showMessageDialog(null, "Este funcionario existe, não pode ser restaurado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }
        s.getTransaction().commit();
    }
    
    public Empregado editar(int id, String nome, String apelido){
         try{
            s.beginTransaction();
            em = (Empregado)s.get(Empregado.class, id);
            em.setPrimeiroNome(nome);
            em.setApelido(apelido);
            s.getTransaction().commit();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Ocorreu uma excepção do tipo: "+e.getMessage());
        }
         return em;
    }
    
    public void alterarSenha(String email, String senha1, String senha2){
        //  try{
              s.beginTransaction();
        Query query = s.createQuery("From  Empregado WHERE email = :email");
        query.setParameter("email", email);
        Empregado empregado = (Empregado) query.uniqueResult();
              //System.out.println(empregado.getApelido());
              if(empregado.getEmail().equalsIgnoreCase(email)){
                  if(senha1.equals(senha2)){
                      empregado.setSenha(senha2);
                      s.update(empregado);
                      s.getTransaction().commit();
                  }else{
                     JOptionPane.showMessageDialog(null,"As senhas devem ser iguais!"); 
                  }
              }else{
                  JOptionPane.showMessageDialog(null,"Email não cadastrado!");
              }
         /* }catch(Exception e){
              JOptionPane.showMessageDialog(null,"Ocorreu uma excepção do tipo: "+e.getMessage());
          }*/
      }

    public static void main(String[] args) {
        
    /*    int ano = 123; // 2023 (2023 - 1900)
int mes = 9;   // 10 (0-based, onde 0 é janeiro)
int dia = 15;*/
    
    //codigoAno = anoEspecifico - 1900;
    //mes eh de 0 a 11

    new FuncionarioController().alterarSenha("pedo@gmail.com", "12345", "1234");
    }

}
