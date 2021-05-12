/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Dao.CartDao;
import Dao.OrderDao;
import Dao.ProductDao;
import Entity.Order;
import Entity.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author malek
 */
public class ConfirmOrder extends JPanel {

    JLabel total;
    JTable jt;
    JScrollPane scr;
    Double totalPrice = 0.0;
    ResultSet rs = null;
    JButton confirm;
    User user1;

    public ConfirmOrder(User user) {
        user1 = user;
        this.setLayout(new BorderLayout());
        rs = (new CartDao().readAll(user.getUser_id()));
        try {
            while (rs.next()) {
                int quantite = rs.getInt("quantity");
                Double price = rs.getDouble("product_price");
                totalPrice += quantite * price;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        total = new JLabel("Total is " + totalPrice + "$");
        total.setForeground(Color.BLACK);
        total.setBackground(Color.GREEN);
        total.setOpaque(true);
        total.setHorizontalAlignment(JLabel.CENTER);
        total.setFont(new Font("Serif", Font.PLAIN, 36));
        total.setPreferredSize(new Dimension(400, 200));
        this.add("North", total);
        ModelCart model = new ModelCart(new CartDao().readAll(user.getUser_id()));
        TableModelEvent e = null;

        jt = new JTable(model);
        jt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                System.out.println("now");
                totalPrice = 0.0;
                rs = (new CartDao().readAll(user.getUser_id()));
                try {
                    while (rs.next()) {
                        int quantite = rs.getInt("quantity");
                        Double price = rs.getDouble("product_price");
                        totalPrice += quantite * price;
                        total.setText("Total is " + totalPrice.toString() + "$");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ConfirmOrder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jt.setRowHeight(200);
        //jt.addMouseListener(new Seller.Ecouteur());
        scr = new JScrollPane(jt);//!!!!!
        this.add("Center", scr);
        confirm = new JButton("Confirm order");
        confirm.addActionListener(new Ecouteur());
        this.add("South", confirm);
    }

    public class Ecouteur implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == confirm) {
                Order order = new Order();
                order.setUser_id(user1.getUser_id());
                int insert = new OrderDao().create(order);
                System.out.println(insert);
                rs = (new CartDao().readAll(user1.getUser_id()));
                try {
                    while (rs.next()) {
                        new ProductDao().decrement(rs.getInt("quantity"),rs.getInt("product_id"));
                        new CartDao().updateOrder(rs.getInt("cart_id"), insert);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ConfirmOrder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
