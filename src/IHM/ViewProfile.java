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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
public class ViewProfile extends JPanel {
    
    private final JPanel panoImg;
    private final JPanel diff;
    JLabel header;
    JLabel lb_help_content, lb_help, lb_pass, lb_username, lb_login, lb_register;
    JLabel lb_reg_username, lb_reg_email, lb_reg_password, lb_reg_phone, lb_reg_role, lb_reg_img;
    JPasswordField f_pass, f_reg_pass;
    JTextField f_username, f_reg_username, f_reg_email, f_reg_phone;
    JButton login, register, upload;
    JPanel big, username, email, password, phone, role, image, submit;
    JRadioButton rbBuyer, rbSeller;
    ButtonGroup group;
    JFileChooser jfc;
    int user_id;
    String user_type;
    private static final String regex = "^(.+)@(.+)$";
    private static final Pattern isnum = Pattern.compile("-?\\d+(\\.\\d+)?");
    
    public ViewProfile(User user) {
        user_id=user.getUser_id();
        user_type=user.getUser_type();
        this.setLayout(new BorderLayout());
        /**
         * *******HEADER*****
         */
        header = new JLabel("Your profile");
        header.setForeground(Color.decode("#ffa600"));
        header.setBackground(Color.decode("#003f5c"));
        header.setOpaque(true);
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setFont(new Font("Serif", Font.PLAIN, 36));
        header.setPreferredSize(new Dimension(400, 200));
        this.add("North", header);
        /**
         * *******LEFT********
         */
        panoImg = new JPanel();
        panoImg.setLayout(new BorderLayout());
        ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "/src/images/" + user.getUser_img());
        Image image1 = img.getImage(); // transform it 
        Image newimg = image1.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        img = new ImageIcon(newimg);  // transform it back
        panoImg.add(new JLabel(img), BorderLayout.CENTER);

        /**
         * *******RIGHT********
         */
        diff = new JPanel();
        diff.setLayout(new BorderLayout());
        diff.setLayout(new GridLayout(7, 0, 10, 10));
        diff.setBorder(BorderFactory.createTitledBorder("Register"));
        
        JPanel username = new JPanel();
        username.setLayout(new FlowLayout());
        
        lb_reg_username = new JLabel("Username");
        lb_reg_username.setFont(new Font("Serif", Font.PLAIN, 20));
        
        username.add(lb_reg_username);
        
        f_reg_username = new JTextField(10);
        f_reg_username.setText(user.getUser_name());
        username.add(f_reg_username);
        
        diff.add(username);
        
        JPanel email = new JPanel();
        email.setLayout(new FlowLayout());
        lb_reg_email = new JLabel("Email");
        lb_reg_email.setFont(new Font("Serif", Font.PLAIN, 20));
        
        email.add(lb_reg_email);
        
        f_reg_email = new JTextField(10);
        f_reg_email.setText(user.getUser_email());
        
        email.add(f_reg_email);
        
        diff.add(email);
        JPanel password = new JPanel();
        password.setLayout(new FlowLayout());
        lb_reg_password = new JLabel("Password");
        lb_reg_password.setFont(new Font("Serif", Font.PLAIN, 20));
        
        password.add(lb_reg_password);
        
        f_reg_pass = new JPasswordField(10);
        f_reg_pass.setText(user.getUser_password());
        password.add(f_reg_pass);
        diff.add(password);
        JPanel phone = new JPanel();
        phone.setLayout(new FlowLayout());
        lb_reg_phone = new JLabel("Phone");
        lb_reg_phone.setFont(new Font("Serif", Font.PLAIN, 20));
        
        phone.add(lb_reg_phone);
        
        f_reg_phone = new JTextField(10);
        f_reg_phone.setText(user.getUser_phone());
        phone.add(f_reg_phone);
        diff.add(phone);
        JPanel role = new JPanel();
        role.setLayout(new FlowLayout());
        lb_reg_role = new JLabel("Role");
        lb_reg_role.setFont(new Font("Serif", Font.PLAIN, 20));
        
        role.add(lb_reg_role);
        
        JPanel image = new JPanel();
        image.setLayout(new FlowLayout());
        lb_reg_img = new JLabel("Image");
        lb_reg_img.setFont(new Font("Serif", Font.PLAIN, 20));
        
        lb_reg_img.addMouseListener(new Ecouteur());
        
        image.add(lb_reg_img);
        upload = new JButton("image");
        upload.addActionListener(new Ecouteur());
        image.add(upload);
        diff.add(image);
        
        JPanel submit = new JPanel();
        submit.setLayout(new FlowLayout());
        register = new JButton("Update");
        register.addActionListener(new Ecouteur());
        submit.add(register);
        diff.add(submit);
        panoImg.setSize(new Dimension(400, 400));
        this.add(panoImg, BorderLayout.EAST);
        this.add(diff, BorderLayout.CENTER);
        
    }
    
    class Ecouteur extends MouseAdapter implements ActionListener, FocusListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == upload) {
                jfc = new JFileChooser("/");
                jfc.showOpenDialog(ViewProfile.this);
                ImageIcon img = new ImageIcon(jfc.getSelectedFile().getAbsolutePath());
                panoImg.add(new JLabel(img));
            }
            
            if (e.getSource() == register) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(f_reg_email.getText());
                if (matcher.matches()) {
                    
                }
                if (isnum.matcher(f_reg_phone.getText()).matches()) {
                    
                }
                User user = new User();
                user.setUser_id(user_id);
                user.setUser_type(user_type);
                user.setUser_email(f_reg_email.getText());
                user.setUser_name(f_reg_username.getText());
                user.setUser_password(f_reg_pass.getText());
                user.setUser_phone(f_reg_phone.getText());
                user.setUser_img(jfc.getSelectedFile().getName());
                Path source = Paths.get(jfc.getSelectedFile().getAbsolutePath());
                Path target = Paths.get(System.getProperty("user.dir") + "/src/images/" + jfc.getSelectedFile().getName());
                
                try {
                    Files.move(source, target);
                } catch (IOException ex) {
                    ex.getMessage();
                }
                try {
                    
                    if (new UserDao().update(user) != 0) {
                        ImageIcon icon = new ImageIcon(new ImageIcon("src/images/success.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
                        JOptionPane.showMessageDialog(null, "Registration Completed", "Seccessful", JOptionPane.INFORMATION_MESSAGE, icon);
                    } else {
                        
                    }
                } catch (HeadlessException err) {
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
