/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

    public Right(String nom, String prenom, String pseudo) {
        countOptions = 0;
        this.setLayout(new BorderLayout());
        lb_bien = new JLabel("Bienvenue " + nom + " " + prenom);
        lb_bien.setForeground(Color.BLACK);
        lb_bien.setBackground(Color.GREEN);
        lb_bien.setOpaque(true);
        lb_bien.setHorizontalAlignment(JLabel.CENTER);
        lb_bien.setPreferredSize(new Dimension(200, 40));

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
        this.add("Center", container);
        val = new JButton("Valider");
        val.addActionListener(this);
        this.add("South", val);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == liste) {
           if(liste.getSelectedItem().equals("Facile")){
                          System.out.println("yes");
                          //disable others

           }
           else if(liste.getSelectedItem().equals("Intermediare")){
                                         //disable others

           }
           else{
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
