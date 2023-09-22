/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenii;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Azalia
 */
public class PSNUsers {
    
    private RandomAccessFile raf;
    private RandomAccessFile psn;
    public HashTable users;
    Date date;
     private static final String Registro_Archivos = "datos.bts";
    
    public PSNUsers(String archivoN){
       try {
        File file = new File(Registro_Archivos);

        if (!file.exists()) {
            file.createNewFile();
        }

        raf = new RandomAccessFile(file, "rw");
        psn = new RandomAccessFile(file, "rw");
        users = new HashTable();
        reloadHashTable();
    } catch (IOException e) {
        e.printStackTrace();
    }
        
    }
    
    private void reloadHashTable() {
      try {
       long actualPos = 0;
        raf.seek(0);
         while (actualPos<raf.length()) {
            String username=raf.readUTF();
             boolean activo=raf.readBoolean();
             if (activo) {
              users.add(username, actualPos);
         }
          actualPos = raf.getFilePointer();
        }
      } catch (IOException e) {
            e.printStackTrace();
      }
    }
    
    public void addUser(String username) {
        try {
            if (users.Search(username)!=-1) {
                JOptionPane.showMessageDialog(null, "El usuario ya existe");
                return;
            }
            // Agregar el usuario 
            raf.seek(raf.length()); 
            raf.writeUTF(username);
            raf.writeInt(0); 
            raf.writeLong(0); 
            raf.writeBoolean(true);
            // Agregar el usuario 
            users.add(username, raf.getFilePointer()- 25); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deactivateUser(String username) {
        long pos=users.Search(username);
        if (pos!=-1) {
            try {
                raf.seek(pos + 21); 
                raf.writeBoolean(false); 
                // Eliminar el usuario
                users.remove(username);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "El usuario no existe");
        }
        }
    
    public void addTrophieTo(String username, String trophyGame, String trophyName, Trophy type){
       long pos=users.Search(username);
       if (pos!=-1) {
            try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fecha= dateFormat.format(new Date());

                // Escribir el trofeo en el archivo
                psn.seek(pos);
                 int contadorTrofeo= psn.readInt();
                 psn.seek(pos);
                 psn.writeInt(contadorTrofeo+1);
                 psn.seek(raf.length());
                 
                 psn.seek(raf.length());
                 psn.writeUTF(username); 
                psn.writeUTF(type.name()); 
                psn.writeUTF(trophyGame); 
                psn.writeUTF(trophyName); 
                psn.writeUTF(fecha); 

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe el usuario");
        }
    }
     
    public void playerInfo(String username){
            long pos = users.Search(username);
        if (pos != -1) {
            try {
                raf.seek(pos);
                String storedUsername = raf.readUTF();
                long points = raf.readLong();
                int trophies = raf.readInt();
                boolean active = raf.readBoolean();

                System.out.println("Información del Usuario:");
                System.out.println("Username: " + storedUsername);
                System.out.println("Puntos por Trofeos: " + points);
                System.out.println("Número de Trofeos: " + trophies);
                System.out.println("Estado: " + (active ? "Activo" : "Inactivo"));

                try (RandomAccessFile trophiesFile = new RandomAccessFile("psn_trophies.dat", "rw")) {
                    trophiesFile.seek(0);
                    while (trophiesFile.getFilePointer() < trophiesFile.length()) {
                        String user = trophiesFile.readUTF();
                        String trophyType = trophiesFile.readUTF();
                        String game = trophiesFile.readUTF();
                        String trophy = trophiesFile.readUTF();
                        String date = trophiesFile.readUTF();

                        if (user.equals(username)) {
                            System.out.println("Fecha: " + date + " - Tipo de Trofeo: " + trophyType + " - Juego: " + game + " - Descripción: " + trophy);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
        
    }
    
    
    
    
    }


    

