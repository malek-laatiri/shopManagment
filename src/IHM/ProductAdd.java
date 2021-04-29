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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
    JLabel namelb, desclb, catlb, pricel, stocklb, imglb;
    JButton fichier, done;
    DefaultListModel model;
    JFileChooser jfc;

    public ProductAdd() {
        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        this.setBorder(BorderFactory.createTitledBorder("Add Product"));

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);
        namelb = new JLabel("Name");
        desclb = new JLabel("Description");
        catlb = new JLabel("Category");
        pricel = new JLabel("Price");
        stocklb = new JLabel("Stock");
        imglb = new JLabel("Image");
        fichier = new JButton("Upload image");
        inputName = new JTextField(10);
        inputPrice = new JTextField(10);
        inputStock = new JTextField(10);
        inputDesc = new JTextArea();

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(namelb, c);

        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(inputName, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(desclb, c);

        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(inputDesc, c);

        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(catlb, c);

        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;

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
        this.add(cat, c);

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(pricel, c);

        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(inputPrice, c);

        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(stocklb, c);

        c.gridx = 2;
        c.gridy = 5;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(inputStock, c);

        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        this.add(imglb, c);

        c.gridx = 2;
        c.gridy = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        fichier.addActionListener(new Ecouteur());
        this.add(fichier, c);
        done = new JButton("Add product");
        c.gridx = 1;
        c.gridy = 7;
        c.gridwidth = 2;
        c.gridheight = 1;
        done.addActionListener(new Ecouteur());
        this.add(done, c);

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
