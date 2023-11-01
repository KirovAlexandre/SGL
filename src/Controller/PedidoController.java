/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import Model.Pedido;
import Model.Empregado;
import Model.Itemcardapio;
import Util.HibernateUtil;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;

/**
 *
 * @author Kirov Mabasso
 */
public class PedidoController {

    private Session s = HibernateUtil.getSessionFactory().getCurrentSession();
    // private static String situacao = "Pendente";
    private static Date dataPedido = new Date();
    private Pedido pedido;
    private Empregado emp;
    private Cliente cli;

    public void pedidoCliente(int id, String situacao) {
        pedido = new Pedido();

        //Date dataPedido = new Date();
        try {
            s.beginTransaction();
            cli = (Cliente) s.get(Cliente.class, id);
            pedido.setCliente(cli);
            pedido.setSituacaoPedido(situacao);
            pedido.setDataPedido(dataPedido);
            s.save(pedido);
            s.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }

    }
    //situacao, temporariamente esta a receber a lista com os pedidos
    public void pedidoBalcao(int id, String situacao) {
        pedido = new Pedido();
        try {
            s.beginTransaction();
            emp = (Empregado) s.get(Empregado.class, id);
            pedido.setEmpregado(emp);
            pedido.setSituacaoPedido(situacao);
            pedido.setDataPedido(dataPedido);
            s.save(pedido);
            s.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }
    }

    public int valorTotal(int id) {
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
    }
  

    public static void main(String[] args) {

        //new PedidoController().pedidoBalcao(13, "Hamburguer,Tosta,Sandes");
        //new PedidoController().valorTotal(5);
        //int i = 0 + new PedidoController().valorTotal(6);
        //System.out.println("Valor total do pedido" + i);
        //System.out.println(total(6));
        
    }
}
