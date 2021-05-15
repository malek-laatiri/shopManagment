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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author malek
 */
public class ProductAdd extends JPanel {

    JTextField inputName, inputPrice, inputStock;
    JTextArea inputDesc;
    JComboBox cat;
    JLabel namelb, desclb, catlb, pricel, stocklb, imglb, header;
    JButton fichier, done;
    DefaultListModel model;
    JFileChooser jfc;
    JPanel content, form;

    public ProductAdd() {
        this.setLayout(new BorderLayout());
        /**
         * *******HEADER*****
         */
        header = new JLabel("Add new product");
        header.setForeground(Color.decode("#ffa600"));
        header.setBackground(Color.decode("#003f5c"));
        header.setOpaque(true);
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setFont(new Font("Serif", Font.PLAIN, 36));
        header.setPreferredSize(new Dimension(400, 200));
        this.add("North", header);
        /**
         * *************
         */
        content = new JPanel();
        content.setLayout(new BorderLayout());
        form = new JPanel();
        form.setLayout(new GridLayout(6, 2));
        form.setBorder(BorderFactory.createTitledBorder("Add Product"));

        namelb = new JLabel("Name");
        namelb.setFont(new Font("Serif", Font.BOLD, 20));

        desclb = new JLabel("Description");
        desclb.setFont(new Font("Serif", Font.BOLD, 20));

        catlb = new JLabel("Category");
        catlb.setFont(new Font("Serif", Font.BOLD, 20));

        pricel = new JLabel("Price");
        pricel.setFont(new Font("Serif", Font.BOLD, 20));

        stocklb = new JLabel("Stock");
        stocklb.setFont(new Font("Serif", Font.BOLD, 20));

        imglb = new JLabel("Image");
        imglb.setFont(new Font("Serif", Font.BOLD, 20));

        fichier = new JButton("Upload image");
        inputName = new JTextField(10);
        inputPrice = new JTextField(10);
        inputStock = new JTextField(10);
        inputDesc = new JTextArea();

        form.add(namelb);

        form.add(inputName);

        form.add(desclb);

        form.add(inputDesc);

        form.add(catlb);

        ArrayList l = new ArrayList();

        try {
            ResultSet rs = null;
            rs = new CategoryDao().read();
            while (rs.next()) {
                Category category = new Category();
                category.setCategory_name(rs.getString("category_name"));
                category.setCategory_id(parseInt(rs.getString("category_id")));
                l.add(category);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        cat = new JComboBox(l.toArray());
        System.out.println(l.toArray());
        form.add(cat);

        form.add(pricel);

        form.add(inputPrice);

        form.add(stocklb);

        form.add(inputStock);

        form.add(imglb);

        fichier.addActionListener(new Ecouteur());
        form.add(fichier);
        done = new JButton("Add product");

        done.addActionListener(new Ecouteur());
        content.add("South", done);
        content.add("Center", form);
        this.add("Center", content);

    }

    class Ecouteur implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == fichier) {
                jfc = new JFileChooser("/Desktop");
                jfc.showOpenDialog(ProductAdd.this);
            } else {
                Path source = Paths.get(jfc.getSelectedFile().getAbsolutePath());
                Path target = Paths.get(System.getProperty("user.dir") + "/src/images/" + jfc.getSelectedFile().getName());
                try {
                    Files.move(source, target);
                } catch (Exception ex) {
                    ex.getMessage();
                }

                Product p = new Product();
                p.setProduct_description(inputDesc.getText());
                p.setProduct_img(jfc.getSelectedFile().getName());
                p.setProduct_name(inputName.getText());
                p.setProduct_price(Double.parseDouble(inputPrice.getText()));
                p.setProduct_stock(parseInt(inputStock.getText()));
                int iend = cat.getSelectedItem().toString().indexOf(".");
                p.setProduct_category(parseInt(cat.getSelectedItem().toString().substring(0, iend)));

                if (new ProductDao().create(p) != 0) {
                    ImageIcon icon = new ImageIcon(new ImageIcon("src/images/success.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
                    JOptionPane.showMessageDialog(null, "Insert Completed", "Seccessful", JOptionPane.INFORMATION_MESSAGE, icon);
                }
            }

        }

    }
}
