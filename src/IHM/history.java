/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Dao.CategoryDao;
import Entity.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author malek
 */
public class history extends JPanel{
    JLabel total, header;
    JTable jt;
    JScrollPane scr;
    Double totalPrice = 0.0;
    ResultSet rs = null;
    JButton confirm;
    User user1;

    public history() {
        this.setLayout(new BorderLayout());
        /**
         * *******HEADER*****
         */
        header = new JLabel("");
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
        ModelCategory model = new ModelCategory(new CategoryDao().read());
        jt = new JTable(model);
        jt.setRowHeight(200);
        //jt.addMouseListener(new Seller.Ecouteur());
        scr = new JScrollPane(jt);//!!!!!
        this.add("Center", scr);
    }

}
