/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenii;

/**
 *
 * @author Azalia
 */
public class HashTable {
    
   private Entry inicio=null;
     private int size=0;
    
    public HashTable(){
        inicio=null;
        size=0;
    }
    
    public boolean isEmpty(){
       return inicio==null;
       
   }
    
   public void add(String username, long pos){
       Entry nueva=new Entry(username,pos);
       if(inicio==null){
           inicio=nueva;
       }else{
           Entry actual=inicio;
           while (actual.sig!= null) {
                actual=actual.sig;
            }
            actual.sig=nueva;
       }
       size++;
   }
   

    public boolean remove(String username){
      if(!isEmpty())
         if (inicio.username.equals(username)) {
             inicio=inicio.sig;
             size--;
            return true; 
       
      }else{
             Entry tmp=inicio;
             while(tmp.sig!=null){
                 if(tmp.sig.username.equals(username)){
                     tmp.sig=tmp.sig.sig;
                     size--;
                     return true;
                 }
                 tmp=tmp.sig;
             }
         }
      return false;
   }
     
      public long Search(String username) {
        Entry actual = inicio;
        while (actual != null) {
            if (actual.username.equals(username)) {
                return actual.posicion;
            }
            actual = actual.sig;
        }
        return -1;
    }
}
