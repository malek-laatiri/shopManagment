/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadChat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author malek
 */
public class Client {

    public static void main(String[] args) {

        System.out.println("Demarrage client:!");
        Scanner sc = new Scanner(System.in);
        try {
            Socket s = new Socket("127.0.0.1", 9000);
           

            ChatClient chat = new ChatClient(s, "Client");
            chat.start();

        } catch (UnknownHostException ex) {
            System.out.println("erreur addresse ip:" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("erreur connexion:" + ex.getMessage());
        }

    }
}
