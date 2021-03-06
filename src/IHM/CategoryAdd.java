/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Dao.CategoryDao;
import Dao.ProductDao;
import Entity.Category;
import Entity.Product;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author malek
 */
public class CategoryAdd extends JPanel {

    JLabel label, header;
    JTextField input;
    JButton done;
    JPanel pano;

    public CategoryAdd() {
        this.setLayout(new BorderLayout());

        /**
         * *******HEADER*****
         */
        header = new JLabel("Add new category");
        header.setForeground(Color.decode("#ff7c43"));
        header.setBackground(Color.decode("#003f5c"));
        header.setOpaque(true);
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setFont(new Font("Serif", Font.PLAIN, 36));
        header.setPreferredSize(new Dimension(400, 200));
        this.add("North", header);
        /**
         * *************
         */
        pano = new JPanel();
        pano.setBorder(BorderFactory.createTitledBorder("Add Category"));

        pano.setLayout(new FlowLayout());
        label = new JLabel("Category title");
                label.setFont(new Font("Serif", Font.PLAIN, 20));

        pano.add(label);
        input = new JTextField(15);
        pano.add(input);
        done = new JButton("Add category");
        pano.add(done);
        done.addActionListener(new Ecouteur());

        this.add("Center", pano);

    }

    class Ecouteur implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Category category = new Category();
            category.setCategory_name(input.getText());

            if (new CategoryDao().create(category) != 0) {
                ImageIcon icon = new ImageIcon(new ImageIcon("src/images/success.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
                JOptionPane.showMessageDialog(null, "Insert Completed", "Seccessful", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }

    }
}
