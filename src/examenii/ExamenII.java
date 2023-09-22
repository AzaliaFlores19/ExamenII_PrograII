/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examenii;

/**
 *
 * @author Azalia
 */
public class ExamenII {
    public static PSNUsers psn;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      PSNUsers psn = new PSNUsers("");
        Menu menu = new Menu(psn);
        menu.setVisible(true);

    }
    
}
