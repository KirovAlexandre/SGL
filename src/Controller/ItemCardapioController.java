/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Itemcardapio;
import Util.HibernateUtil;
import javax.swing.JOptionPane;
import org.hibernate.Session;

/**
 *
 * @author Kirov Mabasso
 */
public class ItemCardapioController {

    private Session s = HibernateUtil.getSessionFactory().getCurrentSession();
    Itemcardapio ic;

    public void store(String nome, int preco) {
        ic = new Itemcardapio();
        try {
            ic.setNomeItem(nome);
            ic.setPrecoUnitario(preco);
            ic.setEstado(true);
            s.beginTransaction();
            s.save(ic);
            s.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }

    }
    public static void main(String[] args) {
        new ItemCardapioController().store("Sandes", 50);
    }

}
