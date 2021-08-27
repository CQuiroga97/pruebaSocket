/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristhian Quiroga
 */
public class Socket {

    /**
     * @param args the command line arguments
     */ 
    public static void main(String[] args) {
        
        try {
            FileWriter writer = new FileWriter("archivo.txt", true);
            ServerSocket server = new ServerSocket(3000);
            
            while(true){
                int cuenta = 0;
                float valor = 0;
                java.net.Socket s = server.accept();
                DataInputStream entrada = new DataInputStream(s.getInputStream());
                String texto = entrada.readUTF();
                cuenta = Integer.parseInt(texto.split(";")[0]);
                valor = Float.parseFloat(texto.split(";")[1]);
                System.out.println(cuenta);
                writer.write(Integer.toString(cuenta) + "," + Float.toString(valor) + "\n");
                writer.close();
                DataOutputStream outToClient = new DataOutputStream(s.getOutputStream());
                outToClient.writeBytes("OK");
                System.out.println("Enviado!");
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Socket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
