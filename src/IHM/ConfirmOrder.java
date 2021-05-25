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
import static IHM.Buyer.myList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
    static User user1;
    ModelCart model;
    DefaultListModel data;

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
        model = new ModelCart(new CartDao().readAll(user.getUser_id()));
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
        jt.addMouseListener(new Ecouteur());

        jt.setRowHeight(200);
        scr = new JScrollPane(jt);//!!!!!
        this.add("Center", scr);
        confirm = new JButton("Confirm order");
        confirm.addActionListener(new Ecouteur());
        this.add("South", confirm);
    }

    public class Ecouteur extends MouseAdapter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == confirm) {
                Order order = new Order();
                order.setUser_id(user1.getUser_id());
                order.setTotal_price(totalPrice);
                int insert = new OrderDao().create(order);
                System.out.println(insert);
                rs = (new CartDao().readAll(user1.getUser_id()));

                try {
                    File f = new File("filename.html");

                    FileWriter myWriter = new FileWriter("filename.html");
                    myWriter.write("<!DOCTYPE html>");
                    myWriter.write("<html lang='en'>");
                    myWriter.write("    <head>");
                    myWriter.write("        <style>");
                    myWriter.write("            body {");
                    myWriter.write("                font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;");
                    myWriter.write("                text-align: center;");
                    myWriter.write("                color: #777;");
                    myWriter.write("            }");
                    myWriter.write("");
                    myWriter.write("            body h1 {");
                    myWriter.write("                font-weight: 300;");
                    myWriter.write("                margin-bottom: 0px;");
                    myWriter.write("                padding-bottom: 0px;");
                    myWriter.write("                color: #000;");
                    myWriter.write("            }");
                    myWriter.write("");
                    myWriter.write("            body h3 {");
                    myWriter.write("                font-weight: 300;");
                    myWriter.write("                margin-top: 10px;");
                    myWriter.write("                margin-bottom: 20px;");
                    myWriter.write("                font-style: italic;");
                    myWriter.write("                color: #555;");
                    myWriter.write("            }");
                    myWriter.write("");
                    myWriter.write("            body a {");
                    myWriter.write("                color: #06f;");
                    myWriter.write("            }");
                    myWriter.write("");
                    myWriter.write("            .invoice-box {");
                    myWriter.write("                max-width: 800px;");
                    myWriter.write("                margin: auto;");
                    myWriter.write("                padding: 30px;");
                    myWriter.write("                border: 1px solid #eee;");
                    myWriter.write("                box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);");
                    myWriter.write("                font-size: 16px;");
                    myWriter.write("                line-height: 24px;");
                    myWriter.write("                font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;");
                    myWriter.write("                color: #555;");
                    myWriter.write("            }");
                    myWriter.write("");
                    myWriter.write("            .invoice-box table {");
                    myWriter.write("                width: 100%;");
                    myWriter.write("                line-height: inherit;");
                    myWriter.write("                text-align: left;");
                    myWriter.write("                border-collapse: collapse;");
                    myWriter.write("            }");
                    myWriter.write("");
                    myWriter.write("            .invoice-box table td {");
                    myWriter.write("                padding: 5px;");
                    myWriter.write("                vertical-align: top;");
                    myWriter.write("            }");
                    myWriter.write("");
                    myWriter.write("            .invoice-box table tr td:nth-child(2) {");
                    myWriter.write("                text-align: right;");
                    myWriter.write("            }");
                    myWriter.write("");
                    myWriter.write("            .invoice-box table tr.top table td {");
                    myWriter.write("                padding-bottom: 20px;");
                    myWriter.write("            }");
                    myWriter.write("");
                    myWriter.write("            .invoice-box table tr.top table td.title {");
                    myWriter.write("                font-size: 45px;");
                    myWriter.write("                line-height: 45px;");
                    myWriter.write("                color: #333;");
                    myWriter.write("            }");
                    myWriter.write("");
                    myWriter.write("            .invoice-box table tr.information table td {");
                    myWriter.write("                padding-bottom: 40px;");
                    myWriter.write("            }");
                    myWriter.write("");
                    myWriter.write("            .invoice-box table tr.heading td {");
                    myWriter.write("                background: #eee;");
                    myWriter.write("                border-bottom: 1px solid #ddd;");
                    myWriter.write("                font-weight: bold;");
                    myWriter.write("            }");
                    myWriter.write("");
                    myWriter.write("            .invoice-box table tr.details td {");
                    myWriter.write("                padding-bottom: 20px;");
                    myWriter.write("            }");
                    myWriter.write("");
                    myWriter.write("            .invoice-box table tr.item td {");
                    myWriter.write("                border-bottom: 1px solid #eee;");
                    myWriter.write("            }");
                    myWriter.write("");
                    myWriter.write("            .invoice-box table tr.item.last td {");
                    myWriter.write("                border-bottom: none;");
                    myWriter.write("            }");
                    myWriter.write("");
                    myWriter.write("            .invoice-box table tr.total td:nth-child(2) {");
                    myWriter.write("                border-top: 2px solid #eee;");
                    myWriter.write("                font-weight: bold;");
                    myWriter.write("            }");
                    myWriter.write("");
                    myWriter.write("            @media only screen and (max-width: 600px) {");
                    myWriter.write("                .invoice-box table tr.top table td {");
                    myWriter.write("                    width: 100%;");
                    myWriter.write("                    display: block;");
                    myWriter.write("                    text-align: center;");
                    myWriter.write("                }");
                    myWriter.write("");
                    myWriter.write("                .invoice-box table tr.information table td {");
                    myWriter.write("                    width: 100%;");
                    myWriter.write("                    display: block;");
                    myWriter.write("                    text-align: center;");
                    myWriter.write("                }");
                    myWriter.write("            }");
                    myWriter.write("        </style>");
                    myWriter.write("    </head>");
                    myWriter.write("    <body>");
                    myWriter.write("        <h1>Your cart invoice</h1>");
                    myWriter.write("        <div class='invoice-box'>");
                    myWriter.write("            <table>");
                    myWriter.write("                <tr class='top'>");
                    myWriter.write("                    <td colspan='3'>");
                    myWriter.write("                        <table>");
                    myWriter.write("                            <tr>");
                    myWriter.write("                                <td class='title'>Java Project</td>");
                    myWriter.write("                                <td>");
                    myWriter.write("                                    Invoice #: " + insert + "<br />");
                    myWriter.write("                                    Created: " + new Date(System.currentTimeMillis()) + "<br />");
                    myWriter.write("                                    Due: " + new Date(System.currentTimeMillis()) + " ");
                    myWriter.write("                                </td>");
                    myWriter.write("                            </tr>");
                    myWriter.write("                        </table>");
                    myWriter.write("                    </td>");
                    myWriter.write("                </tr>");
                    myWriter.write("                <tr class='information'>");
                    myWriter.write("                    <td colspan='3'>");
                    myWriter.write("                        <table>");
                    myWriter.write("                            <tr>");
                    myWriter.write("                                <td>");
                    myWriter.write("                                    Business<br />Java project<br />FIA 4.1");
                    myWriter.write("                                </td>");
                    myWriter.write("                                <td>");
                    myWriter.write("                                    " + user1.getUser_name() + "<br />");
                    myWriter.write("                                    " + user1.getUser_email() + " ");
                    myWriter.write("                                </td>");
                    myWriter.write("                            </tr>");
                    myWriter.write("                        </table>");
                    myWriter.write("                    </td>");
                    myWriter.write("                </tr>");
                    myWriter.write("                <tr class='heading'>");
                    myWriter.write("                    <td>Item</td>");
                    myWriter.write("                    <td>Quantity</td>");
                    myWriter.write("                    <td>Price</td>");
                    myWriter.write("                </tr>");
                    try {
                        while (rs.next()) {
                            new ProductDao().decrement(rs.getInt("quantity"), rs.getInt("product_id"));
                            new CartDao().updateOrder(rs.getInt("cart_id"), insert);
                            myWriter.write("                <tr class='item'>");
                            myWriter.write("                    <td>" + rs.getString("product_name") + "</td>");
                            myWriter.write("                    <td>" + rs.getInt("quantity") + "</td>");
                            myWriter.write("                    <td>" + rs.getDouble("product_price") + "$</td>");
                            myWriter.write("                </tr>");
                        }

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }

                    myWriter.write("                <tr class='total'>");
                    myWriter.write("                    <td></td>");
                    myWriter.write("                    <td></td>");
                    myWriter.write("                    <td>Total: " + totalPrice + "$</td>");
                    myWriter.write("                </tr>");
                    myWriter.write("            </table>");
                    myWriter.write("        </div>");
                    myWriter.write("    </body>");
                    myWriter.write("</html>");
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                    Desktop.getDesktop().open(f);

                    ImageIcon icon = new ImageIcon(new ImageIcon("src/images/success.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
                    JOptionPane.showMessageDialog(null, "Your purchase order is well received", "Seccessful", JOptionPane.INFORMATION_MESSAGE, icon);
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

            }
        }

        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == jt) {
                if (e.getButton() == e.BUTTON3) {//right click
                    //affichage menu
                    JPopupMenu pop = new JPopupMenu();
                    JMenuItem sup = new JMenuItem("supprimer");
                    sup.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            System.out.println(jt.getSelectedRow());
                            model.supprimerPersonne(jt.getSelectedRow());
                            model = new ModelCart(new CartDao().readAll(user1.getUser_id()));

                            //jt = new JTable(model);
                            jt.setModel(model);
                            data = new DefaultListModel();

                            rs = (new CartDao().readAll(user1.getUser_id()));

                            try {
                                while (rs.next()) {
                                    int quantite = rs.getInt("quantity");
                                    Double price = rs.getDouble("product_price");
                                    data.addElement(rs.getInt("cart_id") + "." + rs.getString("product_name") + "*" + rs.getInt("quantity"));

                                }
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }

                            Buyer.myList.setModel(data);
                            //scr = new JScrollPane(jt);//!!!!!

                        }
                    });

                    pop.add(sup);
                    pop.show(jt, e.getX(), e.getY());

                }
            }
        }
    }

}
