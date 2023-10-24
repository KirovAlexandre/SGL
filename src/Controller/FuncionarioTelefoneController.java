/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Funcaoempregado;
import Model.Empregado;
import Model.TelefoneFuncionario;
import Util.HibernateUtil;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;

/**
 *
 * @author Kirov Mabasso
 */
public class FuncionarioTelefoneController {

    Session s = HibernateUtil.getSessionFactory().getCurrentSession();
    /*
    receber o id do empregado por parametro e no corpo do metodo retornar o empregado
    linkado a esse id e usar para preencher o telefone
    */
    public void store(int telefone, int idEmpregado){ 
        TelefoneFuncionario tf = new TelefoneFuncionario();
        try{
            s.beginTransaction();
            Empregado empregado = (Empregado)s.get(Empregado.class, idEmpregado);
            tf.setContacto(telefone);
            tf.setEmpregado(empregado);
            s.save(tf);
            s.getTransaction().commit();
        }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Ocorreu uma excepção do tipo: "+e.getMessage());
                    }
        }
    public void listar(){
        try{
            s.beginTransaction();
            List < TelefoneFuncionario > lista = (List<TelefoneFuncionario>)s.createQuery("From TelefoneFuncionario").list();
            for(TelefoneFuncionario fe : lista){
                if(true){
            System.out.println("Id: " + fe.getIdTelefoneCli());
            System.out.println("Numero: " + fe.getContacto());
            System.out.println("Nome Empregado: " + fe.getEmpregado().getPrimeiroNome());
                     
                }
           
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Ocorreu uma excepção do tipo: "+e.getMessage());
        }
            s.getTransaction().commit();
    }
    

    public static void main(String[] args) {
        new FuncionarioTelefoneController().listar();
    }
    }

