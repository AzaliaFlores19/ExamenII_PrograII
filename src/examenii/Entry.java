/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenii;

/**
 *
 * @author Azalia
 */
public class Entry {
    
    public String username;
    long posicion;
    Entry sig;
    
    public Entry(String username,long posicion){
        this.username=username;
        this.posicion=posicion;
        this.sig=null;
    }
    
    
    
}
