/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author malek
 */
public class Window extends JFrame {

    JTextField input;
    public static JTextArea chat;
    JButton send;

    public Window() {
        //ServerSocket server = new ServerSocket(9000);
        //Socket sClient = new Socket("127.0.0.1", 9000);
        //Socket s = server.accept();
        
        //new ChatServer(s).start();
        //new ChatClient(sClient).start();
        input = new JTextField(15);
        chat = new JTextArea();
        send = new JButton("send");
        JPanel pano = new JPanel();
        pano.setLayout(new BorderLayout());
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        bottom.add(input);
        bottom.add(send);
        pano.add("Center", chat);
        pano.add("South", bottom);
        this.getContentPane().add(pano);
        this.setTitle("chat");
        this.setSize(300, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void Text(String t) {
        this.chat.append(t);
    }
}
