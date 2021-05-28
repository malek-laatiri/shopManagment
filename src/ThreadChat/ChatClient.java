/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author malek
 */
public class ChatClient extends Thread {

    Socket s;
    Scanner sc = new Scanner(System.in);
    String ligne;
    String who;

    public ChatClient(Socket s, String who) {
        this.s = s;
        this.who = who;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public ChatClient(Socket s) {
        this.s = s;
    }

    public void run() {
        new Ecriture().start();
        new Lecture().start();

    }

    class Ecriture extends Thread {

        public void run() {
            while (true) {
                try {

                    PrintWriter pw = new PrintWriter(s.getOutputStream());
                    pw.println(sc.nextLine());
                    pw.flush();//!!!!

                } catch (IOException e) {
                    e.getMessage();

                }
            }
        }
    }

    class Lecture extends Thread {

        public void run() {

            while (true) {
                try {

                    InputStreamReader input = new InputStreamReader(s.getInputStream());
                    BufferedReader br = new BufferedReader(input);
                    while ((ligne = br.readLine()) != null) {
                        System.out.println(ligne);

                    }

                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
    }
}
