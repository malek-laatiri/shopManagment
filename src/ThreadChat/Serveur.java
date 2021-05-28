/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author malek
 */
public class Serveur {

    public static void main(String[] args) {
        int i = 0;
        System.out.println("Demarrage serveur:!");
        try {
            ServerSocket server = new ServerSocket(9000);
            System.out.println("Serveur en d'ecoute...:!");

         

                Socket s = server.accept();
                System.out.println("Je suis le serveur:un client est connecté");

                i++;
                ChatServer c = new ChatServer(s,"Serveur");
                c.start();
            

        } catch (IOException ex) {
            System.out.println("erreur port:" + ex.getMessage());
        }

    }
}
