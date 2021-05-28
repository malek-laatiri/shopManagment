/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Dao.ProductDao;
import Entity.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
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
    JMenu produit, category, profil,connect;
    JMenuItem addProd, addCategory, allProd, allCategory, viewProfile, updateProfile, disconnect;
    JLabel lb_help, lb_help_content, lb_nom, lb_prenom, lb_pseudo, lb_star;
    JTextField f_nom, f_prenom, f_pseudo;
    JButton valider;
    JPanel big;
    JSplitPane splitPane;
    DefaultListModel data;
    JTabbedPane tp;
    JTable jt;
    JScrollPane scr;

    public Seller(User user) {
        barreMenu = new JMenuBar();
        produit = new JMenu("Products");
        addProd = new JMenuItem("Add product");
        addProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JPanel p1 = new JPanel();
                p1.setLayout(new FlowLayout());
                String titleTab = "Add prod";
                tp.add(titleTab, p1.add(new ProductAdd()));
                int index = tp.indexOfTab(titleTab);
                JPanel pnlTab = new JPanel(new GridBagLayout());
                pnlTab.setOpaque(false);
                JLabel lblTitle = new JLabel(titleTab);
                JButton btnClose = new JButton("x");
                btnClose.setForeground(Color.WHITE);
                btnClose.setBackground(Color.RED);
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1;

                pnlTab.add(lblTitle, gbc);

                gbc.gridx++;
                gbc.weightx = 0;
                pnlTab.add(btnClose, gbc);

                tp.setTabComponentAt(index, pnlTab);

                btnClose.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {

                        Component selected = tp.getSelectedComponent();
                        if (selected != null) {

                            tp.remove(selected);
                            // It would probably be worthwhile getting the source
                            // casting it back to a JButton and removing
                            // the action handler reference ;)

                        }

                    }
                });

            }

        });
        allProd = new JMenuItem("All products");
        allProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ModelProduct model = new ModelProduct(new ProductDao().read());
                jt = new JTable(model);
                jt.setRowHeight(200);

                jt.addMouseListener(new Ecouteur());
                scr = new JScrollPane(jt);//!!!!!
                JPanel p1 = new JPanel();
                p1.setLayout(new FlowLayout());
                String titleTab = "All products";

                tp.add(titleTab, p1.add(new allProducts()));
                int index = tp.indexOfTab(titleTab);
                JPanel pnlTab = new JPanel(new GridBagLayout());
                pnlTab.setOpaque(false);
                JLabel lblTitle = new JLabel(titleTab);
                JButton btnClose = new JButton("x");
                btnClose.setForeground(Color.WHITE);
                btnClose.setBackground(Color.RED);
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1;

                pnlTab.add(lblTitle, gbc);

                gbc.gridx++;
                gbc.weightx = 0;
                pnlTab.add(btnClose, gbc);

                tp.setTabComponentAt(index, pnlTab);

                btnClose.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {

                        Component selected = tp.getSelectedComponent();
                        if (selected != null) {

                            tp.remove(selected);
                            // It would probably be worthwhile getting the source
                            // casting it back to a JButton and removing
                            // the action handler reference ;)

                        }

                    }
                });
            }

        });
        produit.add(addProd);
        produit.add(allProd);

        category = new JMenu("Categories");
        addCategory = new JMenuItem("Add category");
        allCategory = new JMenuItem("All categories");
        category.add(addCategory);
        addCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JPanel p1 = new JPanel();
                p1.setLayout(new FlowLayout());
                String titleTab = "Add Category";

                tp.add(titleTab, p1.add(new CategoryAdd()));
                int index = tp.indexOfTab(titleTab);
                JPanel pnlTab = new JPanel(new GridBagLayout());
                pnlTab.setOpaque(false);
                JLabel lblTitle = new JLabel(titleTab);
                JButton btnClose = new JButton("x");
                btnClose.setForeground(Color.WHITE);
                btnClose.setBackground(Color.RED);
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1;

                pnlTab.add(lblTitle, gbc);

                gbc.gridx++;
                gbc.weightx = 0;
                pnlTab.add(btnClose, gbc);

                tp.setTabComponentAt(index, pnlTab);

                btnClose.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {

                        Component selected = tp.getSelectedComponent();
                        if (selected != null) {

                            tp.remove(selected);
                            // It would probably be worthwhile getting the source
                            // casting it back to a JButton and removing
                            // the action handler reference ;)

                        }

                    }
                });
            }

        });
        allCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                JPanel p1 = new JPanel();
                p1.setLayout(new FlowLayout());
                String titleTab = "All categories";

                tp.add(titleTab, p1.add(new allCategories()));
                int index = tp.indexOfTab(titleTab);
                JPanel pnlTab = new JPanel(new GridBagLayout());
                pnlTab.setOpaque(false);
                JLabel lblTitle = new JLabel(titleTab);
                JButton btnClose = new JButton("x");
                btnClose.setForeground(Color.WHITE);
                btnClose.setBackground(Color.RED);
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1;

                pnlTab.add(lblTitle, gbc);

                gbc.gridx++;
                gbc.weightx = 0;
                pnlTab.add(btnClose, gbc);

                tp.setTabComponentAt(index, pnlTab);

                btnClose.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {

                        Component selected = tp.getSelectedComponent();
                        if (selected != null) {

                            tp.remove(selected);
                            // It would probably be worthwhile getting the source
                            // casting it back to a JButton and removing
                            // the action handler reference ;)

                        }

                    }
                });

            }

        });
        category.add(allCategory);

        profil = new JMenu("profil");
        viewProfile = new JMenuItem("View Profile");
        viewProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                JPanel p1 = new JPanel();
                p1.setLayout(new FlowLayout());
                String titleTab = "View profile";

                tp.add(titleTab, p1.add(new ViewProfile(user)));
                int index = tp.indexOfTab(titleTab);
                JPanel pnlTab = new JPanel(new GridBagLayout());
                pnlTab.setOpaque(false);
                JLabel lblTitle = new JLabel(titleTab);
                JButton btnClose = new JButton("x");
                btnClose.setForeground(Color.WHITE);
                btnClose.setBackground(Color.RED);
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1;

                pnlTab.add(lblTitle, gbc);

                gbc.gridx++;
                gbc.weightx = 0;
                pnlTab.add(btnClose, gbc);

                tp.setTabComponentAt(index, pnlTab);

                btnClose.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {

                        Component selected = tp.getSelectedComponent();
                        if (selected != null) {

                            tp.remove(selected);
                            // It would probably be worthwhile getting the source
                            // casting it back to a JButton and removing
                            // the action handler reference ;)

                        }

                    }
                });

            }

        });
        updateProfile = new JMenuItem("Update profile");
        disconnect = new JMenuItem("disconnect");
        profil.add(viewProfile);
        //profil.add(updateProfile);
        profil.addSeparator();
        profil.add(disconnect);
        disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                new Login().setVisible(true);

            }

        });
        barreMenu.add(produit);
        barreMenu.add(category);
        barreMenu.add(profil);
        //connect=new JMenu("Chat");
        //barreMenu.add(connect);
        /**
         * *********CENTER********
         */
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

        tp.add(user.getUser_name(), p1.add(new Right(user.getUser_name(), user.getUser_name(), user.getUser_name())));

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, myList, tp);
        big.add("Center", splitPane);

        this.setJMenuBar(barreMenu);
        this.setTitle("Seller");
        this.setContentPane(big);
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public class Ecouteur extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == jt) {
                if (e.getButton() == e.BUTTON3) {//right click
                    //affichage menu
                    JPopupMenu pop = new JPopupMenu();
                    JMenuItem sup = new JMenuItem("supprimer");
                    sup.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            //model.supprimerPersonne(jt.getSelectedRow());
                        }
                    });

                    pop.add(sup);
                    pop.show(jt, e.getX(), e.getY());

                }
            }
        }

    }
}
