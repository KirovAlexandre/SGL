/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Login;

import java.util.regex.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Kirov Mabasso
 */
public class Validacao {
    //casos de atributos vazios devem ser tratados a nível da interface gráfica
    public void validaNome(String teste) {

        if (teste.matches("^[A-Z]{1}[a-z]{5}$")) {
        } else {
            JOptionPane.showMessageDialog(null, "A primeira letra deve ser maúscula.\nTente Novamente!", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Validacao().validaNome("Aaaaaa");
    }

}
