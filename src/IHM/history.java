/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Dao.CategoryDao;
import Dao.OrderDao;
import Entity.Category;
import Entity.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import static java.lang.Integer.parseInt;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author malek
 */
public class history extends JPanel {

    JLabel total, header;
    JTable jt;
    JScrollPane scr;
    JPanel content, control;
    JCheckBox price;
    JComboBox cat;

    public history(User user) {
        this.setLayout(new BorderLayout());
        /**
         * *******HEADER*****
         */
        header = new JLabel("My orders");
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
        control = new JPanel();
        content.setLayout(new BorderLayout());
        control.setLayout(new FlowLayout());
        price = new JCheckBox("Price");
        ArrayList l = new ArrayList();

        try {
            ResultSet rs = null;
            rs = new OrderDao().dates(user.getUser_id());
            while (rs.next()) {
              
                l.add(rs.getString("orders_created_at"));

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        cat = new JComboBox(l.toArray());

        ModelCategory model = new ModelCategory(new OrderDao().readAllById(user.getUser_id()));
        jt = new JTable(model);
        jt.setRowHeight(200);
        //jt.addMouseListener(new Seller.Ecouteur());
        scr = new JScrollPane(jt);//!!!!!
        this.add("Center", scr);
    }

}
