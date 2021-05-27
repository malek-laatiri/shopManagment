/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Dao.ProductDao;
import Entity.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author malek
 */
public class Right extends JPanel implements ActionListener, ItemListener {

    JPanel right, diff, categories, optionP, container;
    JLabel lb_bien, lb_categ;
    JButton val;
    JComboBox<String> liste;
    JCheckBox one, two, three, four, five, six, seven, son, score, ecran, ombre;
    int countOptions;
    JPanel card, cardBody, content;
    JLabel name, desc, categorylb, price;
    JLabel lbname, lbdesc, lbcategory, lbprice;

    public Right(String nom, String prenom, String pseudo) {
        countOptions = 0;
        this.setLayout(new BorderLayout());
        lb_bien = new JLabel("Bienvenue " + nom + " " + prenom);
        lb_bien.setForeground(Color.decode("#ffa600"));
        lb_bien.setBackground(Color.decode("#003f5c"));
        lb_bien.setFont(new Font("Serif", Font.PLAIN, 36));
        lb_bien.setPreferredSize(new Dimension(400, 200));
        lb_bien.setOpaque(true);
        lb_bien.setHorizontalAlignment(JLabel.CENTER);
        //lb_bien.setPreferredSize(new Dimension(200, 40));

        diff = new JPanel();
        diff.setLayout(new GridLayout(2, 0, 10, 10));
        diff.setBorder(BorderFactory.createTitledBorder("Difficulté"));

        String[] option = {"Facile", "Intermediare", "Difficile"};
        liste = new JComboBox(option);
        liste.addActionListener(this);
        diff.add(liste);

        categories = new JPanel();
        categories.setLayout(new FlowLayout());
        lb_categ = new JLabel("Choisir la/les catégorie(s)");

        categories.add(lb_categ);
        one = new JCheckBox("1");

        two = new JCheckBox("2");
        three = new JCheckBox("3");
        four = new JCheckBox("4");
        five = new JCheckBox("5");
        six = new JCheckBox("6");
        seven = new JCheckBox("7");
        categories.add(one);
        categories.add(two);
        categories.add(three);
        categories.add(four);
        categories.add(five);
        categories.add(six);
        categories.add(seven);

        diff.add(categories);

        optionP = new JPanel();
        optionP.setBorder(BorderFactory.createTitledBorder("Options:" + countOptions));
        optionP.setLayout(new FlowLayout());
        son = new JCheckBox("Emettre son");
        son.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    countOptions = countOptions + 1;
                } else {
                    countOptions = countOptions - 1;
                }
                optionP.setBorder(BorderFactory.createTitledBorder("Options:" + countOptions));

            }
        });
        score = new JCheckBox("Afficher score");
        score.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    countOptions = countOptions + 1;
                } else {
                    countOptions = countOptions - 1;
                }
                optionP.setBorder(BorderFactory.createTitledBorder("Options:" + countOptions));

            }
        });
        ecran = new JCheckBox("Plein ecran");
        ecran.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    countOptions = countOptions + 1;
                } else {
                    countOptions = countOptions - 1;
                }
                optionP.setBorder(BorderFactory.createTitledBorder("Options:" + countOptions));

            }
        });
        ombre = new JCheckBox("Ombre");
        ombre.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    countOptions = countOptions + 1;
                } else {
                    countOptions = countOptions - 1;
                }
                optionP.setBorder(BorderFactory.createTitledBorder("Options:" + countOptions));

            }
        });
        optionP.add(son);
        optionP.add(score);
        optionP.add(ecran);
        optionP.add(ombre);
        container = new JPanel();
        container.setLayout(new GridLayout(2, 0, 10, 10));
        container.add(diff);
        container.add(optionP);
        this.add("North", lb_bien);
        User user = new User();
        user.setUser_name(prenom);
        ///////
        content = new JPanel();
        content.setLayout(new FlowLayout());
        content.setBorder(BorderFactory.createTitledBorder("All products"));
        try {
            ResultSet rs = new ProductDao().read();
            while (rs.next()) {

                card = new JPanel();
                card.setBackground(Color.WHITE);
                cardBody = new JPanel(new BorderLayout());
                card.setLayout(new GridLayout(8, 1, 10, 10));
                cardBody.setBorder(BorderFactory.createLineBorder(Color.black));
                name = new JLabel(rs.getString("product_name"));
                name.setFont(new Font("Serif", Font.PLAIN, 20));
                desc = new JLabel(rs.getString("product_description"));
                desc.setFont(new Font("Serif", Font.PLAIN, 20));
                categorylb = new JLabel(rs.getString("category_name"));
                categorylb.setFont(new Font("Serif", Font.PLAIN, 20));
                price = new JLabel(String.valueOf(rs.getDouble("product_price")) + "$");
                price.setFont(new Font("Serif", Font.PLAIN, 20));
                ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "/src/images/" + rs.getString("product_img"));
                Image image = img.getImage(); // transform it 
                Image newimg = image.getScaledInstance(200, 400, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                img = new ImageIcon(newimg);  // transform it back

                name.setMaximumSize(new Dimension(Integer.MAX_VALUE, name.getMinimumSize().height));
                lbname = new JLabel("Product name:");
                lbname.setForeground(Color.GRAY);
                lbname.setFont(new Font("Serif", Font.BOLD, 20));

                card.add(lbname);
                card.add(name);
                lbdesc = new JLabel("Description:");
                lbdesc.setForeground(Color.GRAY);
                lbdesc.setFont(new Font("Serif", Font.BOLD, 20));

                card.add(lbdesc);
                card.add(desc);
                lbcategory = new JLabel("Category:");
                lbcategory.setForeground(Color.GRAY);
                lbcategory.setFont(new Font("Serif", Font.BOLD, 20));

                card.add(lbcategory);
                card.add(categorylb);
                lbprice = new JLabel("Price:");
                lbprice.setForeground(Color.GRAY);
                lbprice.setFont(new Font("Serif", Font.BOLD, 20));

                card.add(lbprice);
                card.add(price);

                //cardBody.add(click, BorderLayout.SOUTH);
                card.setPreferredSize(new Dimension(400, 400));
                cardBody.add(card, BorderLayout.CENTER);

                cardBody.add(new JLabel(img), BorderLayout.WEST);
                content.add(cardBody);

            }

        } catch (SQLException e) {
            e.getMessage();
        }
///////

        this.add("Center", new JScrollPane(content));
        val = new JButton("Valider");
        val.addActionListener(this);
        this.add("South", val);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == liste) {
            if (liste.getSelectedItem().equals("Facile")) {
                System.out.println("yes");
                //disable others

            } else if (liste.getSelectedItem().equals("Intermediare")) {
                //disable others

            } else {
                //disable others

            }

        } else {
            try {
                FileWriter myWriter = new FileWriter("filename.txt");
                myWriter.write("Files in Java might be tricky, but it is fun enough!");
                System.out.println("Successfully wrote to the file.");

                if (ae.getSource() == val) {
                    System.out.println(liste.getSelectedItem());

                }
                if (one.isSelected()) {

                }
                if (two.isSelected()) {

                }
                if (three.isSelected()) {

                }
                if (four.isSelected()) {

                }
                if (five.isSelected()) {

                }
                if (six.isSelected()) {

                }
                if (seven.isSelected()) {

                }
                if (son.isSelected()) {

                }
                if (score.isSelected()) {

                }
                if (ecran.isSelected()) {

                }
                if (ombre.isSelected()) {

                }
                myWriter.close();

            } catch (IOException e) {
                System.out.println("An error occurred.");
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
