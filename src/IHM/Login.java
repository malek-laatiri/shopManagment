/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Dao.UserDao;
import Entity.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author malek
 */
public class Login extends JFrame {

    JLabel lb_help_content, lb_help, lb_pass, lb_username, lb_login, lb_register;
    JLabel lb_reg_username, lb_reg_email, lb_reg_password, lb_reg_phone, lb_reg_role, lb_reg_img;
    JPasswordField f_pass, f_reg_pass;
    JTextField f_username, f_reg_username, f_reg_email, f_reg_phone;
    JButton login, register, upload;
    JPanel diff, big;
    JRadioButton rbBuyer, rbSeller;
    ButtonGroup group;
    JFileChooser jfc;

    public Login() {
        big = new JPanel();
        big.setLayout(new BorderLayout());

        JPanel pano = new JPanel();
        pano.setLayout(new FlowLayout());
        pano.setBorder(BorderFactory.createTitledBorder("Login"));

        lb_username = new JLabel("Username");
        pano.add(lb_username);

        f_username = new JTextField(10);
        f_username.addFocusListener(new Ecouteur());
        pano.add(f_username);

        lb_pass = new JLabel("Password");
        pano.add(lb_pass);

        f_pass = new JPasswordField(10);
        f_pass.addFocusListener(new Ecouteur());
        pano.add(f_pass);

        login = new JButton("Valider");
        login.addActionListener(new Ecouteur());
        pano.add(login);

        big.add("North", pano);

        diff = new JPanel();
        diff.setLayout(new GridLayout(7, 2, 10, 10));
        diff.setBorder(BorderFactory.createTitledBorder("Register"));

        lb_reg_username = new JLabel("Username");
        lb_reg_username.addMouseListener(new Ecouteur());
        diff.add(lb_reg_username);

        f_reg_username = new JTextField(10);
        f_reg_username.addMouseListener(new Ecouteur());
        diff.add(f_reg_username);

        lb_reg_email = new JLabel("Email");
        lb_reg_email.addMouseListener(new Ecouteur());
        diff.add(lb_reg_email);

        f_reg_email = new JTextField(10);
        f_reg_email.addMouseListener(new Ecouteur());
        f_reg_email.addFocusListener(new Ecouteur());
        diff.add(f_reg_email);

        lb_reg_password = new JLabel("Password");
        lb_reg_password.addMouseListener(new Ecouteur());
        diff.add(lb_reg_password);

        f_reg_pass = new JPasswordField(10);
        f_reg_pass.addMouseListener(new Ecouteur());
        f_reg_pass.addFocusListener(new Ecouteur());
        diff.add(f_reg_pass);

        lb_reg_phone = new JLabel("Phone");
        diff.add(lb_reg_phone);
        lb_reg_phone.addMouseListener(new Ecouteur());

        f_reg_phone = new JTextField(10);
        f_reg_phone.addFocusListener(new Ecouteur());
        f_reg_phone.addMouseListener(new Ecouteur());

        diff.add(f_reg_phone);

        lb_reg_role = new JLabel("Role");
        diff.add(lb_reg_role);
        lb_reg_role.addMouseListener(new Ecouteur());

        JPanel panS = new JPanel();
        panS.setLayout(new FlowLayout());
        rbBuyer = new JRadioButton("Buyer");
        panS.add(rbBuyer);

        rbSeller = new JRadioButton("Seller");
        panS.add(rbSeller);

        group = new ButtonGroup();
        group.add(rbBuyer);
        group.add(rbSeller);

        diff.add(panS);
        lb_reg_img = new JLabel("Image");
        lb_reg_img.addMouseListener(new Ecouteur());

        diff.add(lb_reg_img);
        upload = new JButton("image");
        upload.addActionListener(new Ecouteur());
        diff.add(upload);

        register = new JButton("register");
        register.addActionListener(new Ecouteur());

        diff.add(register);
        big.add("Center", diff);

        JPanel help = new JPanel();
        help.setLayout(new FlowLayout());
        lb_help = new JLabel("Help");
        help.add(lb_help);
        lb_help_content = new JLabel();
        help.add(lb_help_content);
        big.add("South", help);

        this.setTitle("Welcome");
        this.setContentPane(big);
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class Ecouteur extends MouseAdapter implements ActionListener, FocusListener {

        @Override
        public void mouseExited(MouseEvent me) {
            lb_help_content.setText("");

            if (me.getSource() == lb_pass) {
                lb_pass.setForeground(Color.BLACK);
            }
            if (me.getSource() == lb_username) {
                lb_username.setForeground(Color.BLACK);
            }
            if (me.getSource() == lb_login) {
                lb_login.setForeground(Color.BLACK);
            }
            if (me.getSource() == lb_register) {
                lb_register.setForeground(Color.BLACK);
            }
            if (me.getSource() == lb_reg_username) {
                lb_reg_username.setForeground(Color.BLACK);
            }
            if (me.getSource() == lb_reg_email) {
                lb_reg_email.setForeground(Color.BLACK);
            }
            if (me.getSource() == lb_reg_password) {
                lb_reg_password.setForeground(Color.BLACK);
            }
            if (me.getSource() == lb_reg_phone) {
                lb_reg_phone.setForeground(Color.BLACK);
            }
            if (me.getSource() == lb_reg_role) {
                lb_reg_role.setForeground(Color.BLACK);
            }
            if (me.getSource() == lb_reg_img) {
                lb_reg_img.setForeground(Color.BLACK);
            }
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            if (me.getSource() == f_username) {
                lb_help_content.setText("Type your username");
            }
            if (me.getSource() == f_reg_username) {
                lb_help_content.setText("Type your username");
            }
            if (me.getSource() == f_reg_pass) {
                lb_help_content.setText("Type your password");
            }
            if (me.getSource() == f_reg_email) {
                lb_help_content.setText("Type your email");
            }
            if (me.getSource() == f_reg_phone) {
                lb_help_content.setText("Type the phone");
            }
            if (me.getSource() == register) {
                lb_help_content.setText("Click to register new account");
            }

            if (me.getSource() == lb_pass) {
                lb_pass.setForeground(Color.RED);
            }
            if (me.getSource() == lb_username) {
                lb_username.setForeground(Color.RED);
            }
            if (me.getSource() == lb_login) {
                lb_login.setForeground(Color.RED);
            }
            if (me.getSource() == lb_register) {
                lb_register.setForeground(Color.RED);
            }
            if (me.getSource() == lb_reg_username) {
                lb_reg_username.setForeground(Color.RED);
            }
            if (me.getSource() == lb_reg_email) {
                lb_reg_email.setForeground(Color.RED);
            }
            if (me.getSource() == lb_reg_password) {
                lb_reg_password.setForeground(Color.RED);
            }
            if (me.getSource() == lb_reg_phone) {
                lb_reg_phone.setForeground(Color.RED);
            }
            if (me.getSource() == lb_reg_role) {
                lb_reg_role.setForeground(Color.RED);
            }
            if (me.getSource() == lb_reg_img) {
                lb_reg_img.setForeground(Color.RED);
            }

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == upload) {
                jfc = new JFileChooser("/");
                jfc.showOpenDialog(Login.this);
            }
            if (e.getSource() == login) {

                User user = new User();

                user.setUser_name(f_username.getText());
                user.setUser_password(f_pass.getText());
                System.out.println(user);

                User ret = new UserDao().readByUsernamePassword(user);
                System.out.println(ret);
                if (ret.getUser_id() != 0) {
                    Login.getFrames()[0].dispose();
                    Seller s=new Seller(user);
                    s.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error credentials", "Error", JOptionPane.ERROR_MESSAGE);

                }
            }

            if (e.getSource() == register) {
                System.out.println("Register");

                User user = new User();
                user.setUser_email(f_reg_email.getText());
                user.setUser_name(f_reg_username.getText());
                user.setUser_password(f_reg_pass.getText());
                user.setUser_phone(f_reg_phone.getText());
                if (rbBuyer.isSelected()) {
                    user.setUser_type("ROLE_BUYER");

                } else {
                    user.setUser_type("ROLE_SELLER");

                }
                user.setUser_img(jfc.getSelectedFile().getName());
                try {
                    System.out.println("Register");
                    System.out.println(user);

                    if (new UserDao().create(user) != 0) {
                        ImageIcon icon = new ImageIcon(new ImageIcon("src/images/success.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
                        JOptionPane.showMessageDialog(null, "Registration Completed", "Seccessful", JOptionPane.INFORMATION_MESSAGE, icon);
                    } else {

                    }
                } catch (Exception err) {
                    err.getMessage();
                }
            }
        }

        @Override
        public void focusGained(FocusEvent fe) {
        }

        @Override
        public void focusLost(FocusEvent fe) {
        }

    }

}
