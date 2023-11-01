/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Categoriaproduto;
import Util.HibernateUtil;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;

/**
 *
 * @author Kirov Mabasso
 */
public class CategoriaProdutoController {

    Session s = HibernateUtil.getSessionFactory().getCurrentSession();
    Categoriaproduto cp;

    public void store(String categoria, String descricao) {
        try {
            cp = new Categoriaproduto();
            s.beginTransaction();
            cp.setCategoria(categoria);
            cp.setDescricao(descricao);
            s.save(cp);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }
        s.getTransaction().commit();
    }

    public void delete(int id) {
        try {
            s.beginTransaction();
            cp = (Categoriaproduto) s.get(Categoriaproduto.class, id);
            if (cp != null) {
                s.delete(cp);
                s.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "Categoria eliminada!");
            } else {
                JOptionPane.showMessageDialog(null, "Esta categoria não existe!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }
    }

    public Categoriaproduto editar(int id, String categoria, String descricao) {
        try {
             s.beginTransaction();
             cp = (Categoriaproduto) s.get(Categoriaproduto.class, id);
            if(cp != null){
            cp.setCategoria(categoria);
            cp.setDescricao(descricao);
            s.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Categoria alterada!");
            }else{
              JOptionPane.showMessageDialog(null, "Categoria não existe!"); 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }
        return cp;
    }
    
    public void listar(){
        try{
        s.beginTransaction();
        List < Categoriaproduto > lista = (List<Categoriaproduto>)s.createQuery("From Categoriaproduto").list();
        for(Categoriaproduto fe : lista){
            System.out.println("Id: " + fe.getIdCategoriaProduto());
            System.out.println("Categoria: " + fe.getCategoria());
            System.out.println("Descricao: " + fe.getDescricao());
        }
       
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: "+e.getMessage());
    }
         s.getTransaction().commit();
    }

    public static void main(String[] args) {
         //new CategoriaProduto().store("Comida", "Comidinha de boas");
        //new CategoriaProduto().delete(1);
        //new CategoriaProduto().editar(3, "Salgados", "Bla");
        //new CategoriaProduto().listar();
        
    }
}
