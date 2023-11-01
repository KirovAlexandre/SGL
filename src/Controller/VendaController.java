/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import Model.Empregado;
import Model.Venda;
import Model.Pedido;
import Model.Itemcardapio;
import Util.HibernateUtil;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Model.Produto;
import org.hibernate.Query;

/**
 *
 * @author Kirov Mabasso
 */
public class VendaController {

    private Session s = HibernateUtil.getSessionFactory().getCurrentSession();
    private String situacao = "Pendente";
    private Date dataVenda = new Date();
    private Pedido pedido;
    private Empregado emp;
    private Itemcardapio ic;
    private Venda venda;
    private PedidoController pc;
    private Produto produto;
    
    /*public int valorTotal(int id) {
        //recebe um determinado pedido e calcula o valor total dos itens que estao disponiveis
        //nesse pedido
        int i = 0, total = 0;
        try {
            s.beginTransaction();
            pedido = (Pedido) s.get(Pedido.class, id);
            String p = pedido.getSituacaoPedido();
            String[] itens = p.split(",");
            List< Itemcardapio> lista = (List<Itemcardapio>) s.createQuery("From Itemcardapio").list();
            for (Itemcardapio ic : lista) {
                if (ic.getNomeItem().equalsIgnoreCase(itens[i])) {
                    total += ic.getPrecoUnitario();
                } else {
                    //JOptionPane.showMessageDialog(null, "Continuar sem "+itens[i]);
                }
                i++;
            }
            s.getTransaction().commit();
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }
        return total;
    }*/

    public void vender(int id) {

        try {
            int i = 0, total = 0;
            venda = new Venda();
            Date d = new Date();

            s.beginTransaction();
            pedido = (Pedido) s.get(Pedido.class, id);
            String p = pedido.getSituacaoPedido();

            String[] itens = p.split(",");
            int tamanhoItens = itens.length;

            List< Itemcardapio> lista = (List<Itemcardapio>) s.createQuery("From Itemcardapio").list();
            for (Itemcardapio ic : lista) {
                if ((ic.getNomeItem().equalsIgnoreCase(itens[i])) && (i < tamanhoItens)) {
                    total += ic.getPrecoUnitario();
                    System.out.println("Item: " + itens[i] + i);
                    System.out.println("ItemCardapio: " + ic.getNomeItem() + i);
                    i++;
                } else {
                    //JOptionPane.showMessageDialog(null, "Continuar sem "+itens[i]);
                }

            }
            venda.setSituacao(situacao);
            venda.setDataVenda(dataVenda);
            venda.setValorTotalVenda(total);
            s.save(venda);
            s.getTransaction().commit();
            System.out.println("Gravou");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }
    }
    
    public void itemCardapioVenda(String nomeItem){
        try{
        s.beginTransaction();
        Query query = s.createQuery("From  Itemcardapio WHERE nomeItem = :nomeItem");
        query.setParameter("nomeItem", nomeItem);
        Itemcardapio cardapio = (Itemcardapio) query.uniqueResult();
        
        if(cardapio.getNomeItem().equalsIgnoreCase("Hamburguer")){
            String nomeProduto = "queijo";
            Query q = s.createQuery("From  Produto WHERE nomeProduto = :nomeProduto");
            q.setParameter("nomeProduto", nomeProduto);
            Produto prod1 = (Produto) q.uniqueResult();
            
            int qQueijo = prod1.getQuantidadeProduto() - 1;
            prod1.setQuantidadeProduto(qQueijo);
            s.update(prod1);
            s.getTransaction().commit();
            System.out.println("Deu bom!");
        }
       }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }
        
         /* Query query = s.createQuery("From  Empregado WHERE primeiroNome = :primeiroNome ");
            query.setParameter("primeiroNome", "");
            System.out.println("Deu bm");*/
        
    }

    
    public static void main(String[] args) {
        /*
       new VendaController().vender();
        System.out.println(total);*/
       // new VendaController().vender(6);
        new VendaController().itemCardapioVenda("Hamburguer");

    }
}
