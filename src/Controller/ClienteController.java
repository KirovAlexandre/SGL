/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import Util.HibernateUtil;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;

/**
 *
 * @author Kirov Mabasso
 */
public class ClienteController {

    private Session s = HibernateUtil.getSessionFactory().getCurrentSession();
    private Cliente cliente;

    public void store(String Enderecoentrega, String primeiroNome, String apelido, String email, String senha,
            Date dataNascimento, char sexo, Date dataCadastro, String estado, String enderecoResidencia) {
            try {
            cliente = new Cliente();
            cliente.setEstadoB(true);
            cliente.setPrimeiroNome(primeiroNome);
            cliente.setApelido(apelido);
            cliente.setEmail(email);
            cliente.setEnderecoResidencia(enderecoResidencia);
            cliente.setSexo(sexo);
            cliente.setDataCadastro(dataCadastro);
            cliente.setEstado(estado);
            cliente.setDataNascimento(dataNascimento);
            cliente.setSenha(senha);
            s.beginTransaction();
            s.save(cliente);
            s.getTransaction().commit();
       } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma excepção do tipo: " + e.getMessage());
        }

    }

    public void listar() {
        try {
        cliente = new Cliente();
        s.beginTransaction();
        List< Cliente> lista = (List<Cliente>) s.createQuery("From Cliente").list();
        System.out.println(lista.get(0).getEmail());
        for (Cliente fe : lista) {
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
            cliente = new Cliente();
            s.beginTransaction();
            Cliente cliente = (Cliente) s.get(Cliente.class, id);
            if (cliente.getEstadoB() == true) {
                cliente.setEstadoB(Boolean.FALSE);
                JOptionPane.showMessageDialog(null, "Funcionario eliminado!");
            } else if (cliente.getEstadoB() == false) {
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
            Cliente fe = (Cliente) s.get(Cliente.class, id);
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
    
      public Cliente editar(int id, String nome, String apelido){
         try{
            s.beginTransaction();
            cliente = (Cliente)s.get(Cliente.class, id);
            cliente.setPrimeiroNome(nome);
            cliente.setApelido(apelido);
            s.update(cliente);
            s.getTransaction().commit();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Ocorreu uma excepção do tipo: "+e.getMessage());
        }
         return cliente;
    }
     
      
      
    public static void main(String[] args) {
        Date data = new Date();
       // new ClienteController().store("Inhagoia", "Arcidio", "Cossa", "pedos@gmail.com", "123456", data, 'M', data, "Activo", "T-3");
       // new ClienteController().delete(6);
       //new ClienteController().editar(6, "Rebeca","Chissano");
       
    }

}
