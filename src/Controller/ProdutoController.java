/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Produto;
import Model.Itemcardapio;
import Model.Categoriaproduto;
import Model.Empregado;
import Model.Produto;
import Util.HibernateUtil;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Kirov Mabasso
 */
public class ProdutoController {

    Session s = HibernateUtil.getSessionFactory().getCurrentSession();
    private Produto pro;

    public void store(int categoria, String nome, int precoCompra, int precoUnitario, int qtd, Date dataCompra, Date dataValidade) {
        try {
            Produto pro = new Produto();
            pro.setNomeProduto(nome);
            pro.setCategoriaProduto(categoria);
            pro.setPrecoCompraProduto(precoCompra);
            pro.setPrecoUnitarioProduto(precoCompra);
            pro.setDataCompraProduto(dataCompra);
            pro.setDataValidadeProduto(dataValidade);
            pro.setQuantidadeProduto(qtd);
            s.beginTransaction();
            s.save(pro);
            s.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }
    }

    public Produto pesquisar(int id) {
        try {

            s.beginTransaction();
            Produto fe = (Produto) s.get(Produto.class, id);
            if (fe == null) {
                JOptionPane.showMessageDialog(null, "Produto não encontrado");
            }
            s.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }
        return pro;
    }

    public void validadeProduto() {
        long numeroDias = 0;
        try {
            s.beginTransaction();
            List< Produto> lista = (List<Produto>) s.createQuery("From Produto").list();
            for (Produto pro : lista) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date hoje = new Date();
                long diffEmMillis = hoje.getTime() - pro.getDataValidadeProduto().getTime();
                numeroDias = TimeUnit.DAYS.convert(diffEmMillis, TimeUnit.MILLISECONDS);
                numeroDias = -numeroDias;
           
                if (numeroDias <= 0) {
                JOptionPane.showMessageDialog(null, pro.getNomeProduto()+" fora do prazo");

            } else if (numeroDias <= 3){
                JOptionPane.showMessageDialog(null, pro.getNomeProduto()+" têm mais "+
                        numeroDias+" dias de validade");
            }
            }
            s.getTransaction().commit();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        //new ProdutoController().pesquisar(55);
        new ProdutoController().validadeProduto();
        
    }
}
