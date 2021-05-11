/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Dao.CartDao;
import Dao.ProductDao;
import Entity.Cart;
import Entity.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author malek
 */
public class ViewProducts extends JPanel {

    JPanel card, cardBody;
    JLabel name, desc, category, price;
    JLabel lbname, lbdesc, lbcategory, lbprice;

    JButton click;

    public ViewProducts(User user) {
        this.setLayout(new FlowLayout());
        this.setBorder(BorderFactory.createTitledBorder("All products"));
        try {
            ResultSet rs = new ProductDao().read();
            while (rs.next()) {

                card = new JPanel();
                cardBody = new JPanel(new BorderLayout());
                card.setLayout(new GridLayout(8, 1, 10, 10));
                cardBody.setBorder(BorderFactory.createLineBorder(Color.black));
                name = new JLabel(rs.getString("product_name"));
                desc = new JLabel(rs.getString("product_description"));
                category = new JLabel(rs.getString("category_name"));
                price = new JLabel(String.valueOf(rs.getDouble("product_price")));
                ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "/src/images/" + rs.getString("product_img"));
                Image image = img.getImage(); // transform it 
                Image newimg = image.getScaledInstance(200, 400, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                img = new ImageIcon(newimg);  // transform it back
                click = new JButton(String.valueOf(rs.getInt("product_id")) + ".Add to cart");
                click.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        try {
                            String s = ((JButton) ae.getSource()).getText();
                            int iend = s.indexOf(".");
                            System.out.println(parseInt(s.substring(0, iend)));
                            Cart cart=new Cart();
                            cart.setProduct_id(parseInt(s.substring(0, iend)));
                            cart.setQuantity(1);
                            System.out.println(user.getUser_id());
                            cart.setUser_id(user.getUser_id());
                            new CartDao().create(cart);
                        } catch (Exception ex) {
                            ex.getMessage();
                        }

                    }

                });
                name.setMaximumSize(new Dimension(Integer.MAX_VALUE, name.getMinimumSize().height));
                lbname = new JLabel("Product name:");
                lbname.setForeground(Color.GRAY);

                card.add(lbname);
                card.add(name);
                lbdesc = new JLabel("Description:");
                lbdesc.setForeground(Color.GRAY);

                card.add(lbdesc);
                card.add(desc);
                lbcategory = new JLabel("Category:");
                lbcategory.setForeground(Color.GRAY);

                card.add(lbcategory);
                card.add(category);
                lbprice = new JLabel("Price:");
                lbprice.setForeground(Color.GRAY);

                card.add(lbprice);
                card.add(price);
                click.setMaximumSize(new Dimension(Integer.MAX_VALUE, click.getMinimumSize().height));

                cardBody.add(click, BorderLayout.SOUTH);
                card.setPreferredSize(new Dimension(400, 400));
                cardBody.add(card, BorderLayout.CENTER);

                cardBody.add(new JLabel(img), BorderLayout.WEST);
                this.add(cardBody);

            }

        } catch (SQLException e) {
            e.getMessage();
        }

    }

}
