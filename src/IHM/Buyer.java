/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Dao.CartDao;
import Dao.CategoryDao;
import Dao.ProductDao;
import Entity.User;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Buyer extends JFrame {

    JMenuBar barreMenu;
    JMenu products, currentOrder, history, profil;
    JMenuItem allProducts, byCateg, confirm, viewProfile, updateProfile, disconnect, orders;
    JLabel lb_help, lb_help_content, lb_nom, lb_prenom, lb_pseudo, lb_star;
    JTextField f_nom, f_prenom, f_pseudo;
    JButton valider;
    JPanel big;
    JSplitPane splitPane;
    DefaultListModel data;
    JTabbedPane tp;
    JTable jt;
    JScrollPane scr;

    public Buyer(User user) {
        barreMenu = new JMenuBar();
        products = new JMenu("Products");
        currentOrder = new JMenu("Orders");
        history = new JMenu("History");
        profil = new JMenu("Profil");
        allProducts = new JMenuItem("All product");
        byCateg = new JMenuItem("Products by Category");
        confirm = new JMenuItem("Confirm Order");
        viewProfile = new JMenuItem("My profile");
        updateProfile = new JMenuItem("Update profile");
        orders = new JMenuItem("All Orders");
                disconnect = new JMenuItem("Disconnect");

        products.add(allProducts);
        products.add(byCateg);
        currentOrder.add(confirm);
        history.add(orders);
        profil.add(viewProfile);
        profil.add(updateProfile);
        profil.addSeparator();
        profil.add(disconnect);

        allProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JPanel p1 = new JPanel();
                p1.setLayout(new FlowLayout());
                tp.add("All products", p1.add(new JScrollPane(new ViewProducts(user))));

            }

        });

        byCateg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JPanel p1 = new JPanel();
                p1.setLayout(new FlowLayout());
                tp.add("Products by category", p1.add(new JScrollPane(new ViewProductsByCategory(user))));

            }

        });

      

          confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JPanel p1 = new JPanel();
                p1.setLayout(new FlowLayout());
                String titleTab = "Confirm order";
                ///
                ModelCart model = new ModelCart(new CartDao().readAll(user.getUser_id()));
                jt = new JTable(model);
                jt.setRowHeight(200);

                //jt.addMouseListener(new Seller.Ecouteur());
                scr = new JScrollPane(jt);//!!!!!
               

                tp.add(titleTab, p1.add(scr));
                ////
                int index = tp.indexOfTab(titleTab);
                JPanel pnlTab = new JPanel(new GridBagLayout());
                pnlTab.setOpaque(false);
                JLabel lblTitle = new JLabel(titleTab);
                JButton btnClose = new JButton("x");

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
        
        
        viewProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JPanel p1 = new JPanel();
                p1.setLayout(new FlowLayout());
                tp.add("My profile", p1.add(new ProductAdd()));

            }

        });

        updateProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JPanel p1 = new JPanel();
                p1.setLayout(new FlowLayout());
                tp.add("Update profile", p1.add(new ProductAdd()));

            }

        });

        orders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JPanel p1 = new JPanel();
                p1.setLayout(new FlowLayout());
                tp.add("All orders", p1.add(new ProductAdd()));

            }

        });
        disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               dispose();
                new Login().setVisible(true);

            }

        });

        barreMenu.add(products);
        barreMenu.add(currentOrder);
        barreMenu.add(history);
        barreMenu.add(profil);
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
        this.setTitle("Buyer");
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
