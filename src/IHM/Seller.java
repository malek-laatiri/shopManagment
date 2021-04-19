/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author malek
 */
public class Seller extends JFrame {

    JMenuBar barreMenu;
    JMenu produit, category, profil;
    JMenuItem addProd, addCategory, allProd, allCategory, viewProfile, updateProfile, disconnect;
 JLabel lb_help, lb_help_content, lb_nom, lb_prenom, lb_pseudo, lb_star;
    JTextField f_nom, f_prenom, f_pseudo;
    JButton valider;
    JPanel big;
    JSplitPane splitPane;
    DefaultListModel data;
    JTabbedPane tp;
    public Seller() {
        barreMenu = new JMenuBar();
        produit = new JMenu("Products");
        addProd = new JMenuItem("Add product");
        allProd = new JMenuItem("All products");
        produit.add(addProd);
        produit.add(allProd);
        
        category = new JMenu("Categories");
        addCategory = new JMenuItem("Add category");
        allCategory = new JMenuItem("All categories");
        category.add(addCategory);
        category.add(allCategory);
        
        profil = new JMenu("profil");
        viewProfile = new JMenuItem("View Profile");
        updateProfile = new JMenuItem("Update profile");
        disconnect = new JMenuItem("disconnect");
        profil.add(viewProfile);
        profil.add(updateProfile);
        profil.addSeparator();
        profil.add(disconnect);
        barreMenu.add(produit);
        barreMenu.add(category);
        barreMenu.add(profil);
        /***********CENTER*********/
         big = new JPanel();
        big.setLayout(new BorderLayout());
         data = new DefaultListModel();
        JList myList = new JList(data);
        myList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                final JPopupMenu popupmenu = new JPopupMenu("Edit");
                JMenuItem cut = new JMenuItem("Supprimer");
                JMenuItem copy = new JMenuItem("Supprimer tous");
                JMenuItem paste = new JMenuItem("Renommer");
                popupmenu.add(cut);
                popupmenu.add(copy);
                popupmenu.add(paste);
                myList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        popupmenu.show(myList, e.getX(), e.getY());
                        System.out.println(e.getSource());
                    }
                });
            }
        });
        myList.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                JList l = (JList) e.getSource();
                ListModel m = l.getModel();
                int index = l.locationToIndex(e.getPoint());
                if (index > -1) {
                    l.setToolTipText(m.getElementAt(index).toString());

                }
            }
        });
        tp = new JTabbedPane();
        tp.setBounds(50, 50, 200, 200);
         JPanel p1 = new JPanel();
                    p1.setLayout(new FlowLayout());

                    tp.add("malek", p1.add(new Right("seller", "seller", "seller")));

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, myList, tp);
        big.add("Center", splitPane);
        
        this.setJMenuBar(barreMenu);
        this.setTitle("Seller");
        this.setContentPane(big);
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
