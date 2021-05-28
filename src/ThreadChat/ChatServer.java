/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadChat;

import Dao.ProductDao;
import IHM.Window;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author malek
 */
public class ChatServer extends Thread {

    Socket s;
    Scanner sc = new Scanner(System.in);
    String ligne;
    String who;

    public ChatServer(Socket s, String who) {
        this.s = s;
        this.who = who;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public ChatServer(Socket s) {
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
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(s.getOutputStream());
            } catch (IOException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            pw.println("Hi,Welcome to our shop");
            pw.flush();//!!!!
            while (true) {
                try {

                    InputStreamReader input = new InputStreamReader(s.getInputStream());
                    BufferedReader br = new BufferedReader(input);

                    //Client.setChatA("Hi,Welcome to our shop");
                    pw.println("Choose just an option:");
                    //Client.chatA.append("Choose just an option:");

                    pw.flush();//!!!!
                    pw.println("0/All products");
                    //Client.chatA.append("0/All products");

                    pw.flush();//!!!!
                    pw.println("1/look for product by name");
                    //Client.chatA.append("1/look for product by name");

                    pw.flush();//!!!!

                    pw.println("2/Available Quantity");
                    //Client.chatA.append("2/Available Quantity");

                    pw.flush();//!!!!

                    pw.println("3/Price ");
                    //Client.chatA.append("3/Price");

                    pw.flush();//!!!!
                    pw.println("4/Quit ");
                    // Client.chatA.append("4/Quit");

                    pw.flush();//!!!!

                    ProductDao productDao = new ProductDao();
                    ligne = br.readLine();
                    if (ligne.charAt(0) == '1') {
                        pw.println("Product name");
                        //Client.chatA.append("Product name");

                        pw.flush();//!!!!
                        ligne = br.readLine();
                        ResultSet rs = productDao.readByName(ligne);
                        try {
                            while (rs.next()) {//parcour des lignes
                                pw.println(rs.getString("product_name") + "\t" + rs.getString("product_description") + "\t" + rs.getString("category_name") + "\t" + rs.getDouble("product_price"));
                                pw.flush();//!!!!
                                //Client.chatA.append(rs.getString("product_name") + "\t" + rs.getString("product_description") + "\t" + rs.getString("category_name") + "\t" + rs.getDouble("product_price"));

                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //System.out.println(ligne);

                    } else if (ligne.charAt(0) == '2') {
                        pw.println("Product name");
                        pw.flush();//!!!!
                        ligne = br.readLine();
                        ResultSet rs = productDao.readByName(ligne);
                        try {
                            while (rs.next()) {//parcour des lignes
                                pw.println("The available quantity is " + rs.getInt("product_stock"));
                                pw.flush();//!!!!

                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (ligne.charAt(0) == '3') {
                        pw.println("Product name");
                        pw.flush();//!!!!
                        ligne = br.readLine();
                        ResultSet rs = productDao.readByName(ligne);
                        try {
                            while (rs.next()) {//parcour des lignes
                                pw.println("The price is " + rs.getDouble("product_price"));
                                pw.flush();//!!!!

                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (ligne.charAt(0) == '4') {
                        pw.println("GoodBye");
                        pw.flush();//!!!!
                        Thread.currentThread().stop();

                    } else {

                        ResultSet rs = productDao.read();
                        try {
                            while (rs.next()) {//parcour des lignes
                                pw.println(rs.getString("product_name"));
                                pw.flush();//!!!!

                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //System.out.println(ligne);

                    }
                    System.out.println(ligne);

                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
    }
}
