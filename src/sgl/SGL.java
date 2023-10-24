/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgl;
import Model.Funcaoempregado;
import Controller.FuncaoEmpregadoController;
import Controller.FuncionarioController;
import Model.Empregado;
import Model.Funcaoempregado;
import Util.HibernateUtil;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import Controller.Login.Autenticacao;
/**
 *
 * @author Kirov Mabasso
 */
public class SGL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Date c = new Date();
        
      /*  Funcaoempregado fe = new Funcaoempregado();
        FuncaoEmpregadoController fc = new FuncaoEmpregadoController();
        FuncionarioController f =  new FuncionarioController();
        Empregado e = new Empregado();
        try{
         s.beginTransaction();
         
         fe.setFuncao("Copo");
         fe.setDescricao("Limpador de copo");
         s.save(fe);
         
        e.setPrimeiroNome("Bruninho");
        e.setApelido("Mabunda");
        e.setEmail("tiotio@gmail.com");
        e.setDataNascimento(c);
        e.setEstado("Activo");
        e.setNivel(1);
        e.setDataCadastro(c);
        e.setSalario(15000);
        e.setSexo('M');
        e.setSenha("Senhor");
        e.setEnderecoResidencia("Matola");
        e.setFuncaoempregado(fe);
        s.save(e);
        
        s.getTransaction().commit();*/
        //Autenticacao a = new Autenticacao();
        new FuncaoEmpregadoController().listar();
        /*try{
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Deu merda!"+ex.getMessage());
        }  */     
        
        
        
    }
    
}
