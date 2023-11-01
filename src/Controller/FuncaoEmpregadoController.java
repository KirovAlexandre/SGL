/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.Empregado;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import Model.Funcaoempregado;
import Util.HibernateUtil;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import javax.swing.JOptionPane;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Kirov Mabasso
 */
public class FuncaoEmpregadoController {
    
    private Session s = HibernateUtil.getSessionFactory().getCurrentSession();
    private Funcaoempregado fe;
    
    public Funcaoempregado pesquisa(Integer id){
        try{
            s.beginTransaction();
            Funcaoempregado fe = (Funcaoempregado)s.get(Funcaoempregado.class, id);
            s.getTransaction().commit();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Ocorreu uma excepção do tipo: "+e.getMessage());
        }
        return fe;
    }
    //Receber atributos via parametro, rever metodo
    public void store(Funcaoempregado fe){
        try{
        s.beginTransaction();
        s.save(fe);
        s.getTransaction().commit();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Ocorreu uma excepção do tipo: "+e.getMessage());
        }
    }
    public void listar(){ 
        try{
        s.beginTransaction();
        List < Funcaoempregado > lista = (List<Funcaoempregado>)s.createQuery("From Funcaoempregado").list();
        for(Funcaoempregado fe : lista){
            if(fe.getStatus()){
            System.out.println("Id: " + fe.getIdFuncao());
            System.out.println("Status: " + fe.getStatus());
            System.out.println("Função: " + fe.getFuncao());
            System.out.println("Descrição: " + fe.getDescricao());
        }
        }
        s.getTransaction().commit();
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: "+e.getMessage());
    }
   }
    
    public void delete(Integer id){
        try{
            s.beginTransaction();
            Funcaoempregado fe = (Funcaoempregado)s.get(Funcaoempregado.class, id);
            if(fe.getStatus()){
                fe.setStatus(Boolean.FALSE);
            }
            s.getTransaction().commit();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Ocorreu uma excepção do tipo: "+e.getMessage());
        }
    }
    
    public Funcaoempregado editar(Integer id, String funcao, String descricao){
        try{
            s.beginTransaction();
            fe = (Funcaoempregado)s.get(Funcaoempregado.class, id);
            fe.setFuncao(funcao);
            fe.setDescricao(descricao);
            s.getTransaction().commit();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Ocorreu uma excepção do tipo: "+e.getMessage());
        }
        
        return fe;
    }
}
