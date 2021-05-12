/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Dao.ProductDao;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author malek
 */
public class ModelProduct extends AbstractTableModel {

    ResultSetMetaData rsmd;
    int nbligne;
    int nbc;
    ArrayList<Object[]> data = new ArrayList<Object[]>();
    ProductDao pm;

    public ModelProduct(ResultSet rs) {
        super();
        try {
            rsmd = rs.getMetaData();
            nbc = rsmd.getColumnCount();
            while (rs.next()) {
                nbligne++;
                Object[] ligne = new Object[rsmd.getColumnCount()];
                for (int i = 0; i < ligne.length; i++) {
                    if (i == 5) {
                        ImageIcon img = new ImageIcon(System.getProperty("user.dir") + "/src/images/" + rs.getObject(i + 1));
                        Image image = img.getImage(); // transform it 
                        Image newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                        img = new ImageIcon(newimg);  // transform it back
                        ligne[i] = img;
                        super.setValueAt(img, nbligne, i);

                    } else {
                        ligne[i] = rs.getObject(i + 1);

                    }
                }
                data.add(ligne);

            }

        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @Override
    public int getRowCount() {

        try {
            return data.size();
        } catch (Exception ex) {
            ex.getMessage();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        try {
            return new Object[rsmd.getColumnCount()].length;
        } catch (SQLException ex) {
            Logger.getLogger(ModelProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public Object getValueAt(int i, int c) {
        return (data.get(i))[c];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.get(rowIndex)[columnIndex] = aValue;
        Object[] p = data.get(rowIndex);
        //PersonneManager pm = new PersonneManager();
        // pm.update(p[nameToIndice("numero")], p[nameToIndice("nom")], p[nameToIndice("prenom")], p[nameToIndice("moyenne")]);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (getColumnName(columnIndex).equals("product_stock")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getColumnName(int column) {
        try {
            return rsmd.getColumnName(column + 1);
        } catch (Exception e) {
            e.getMessage();
        }
        return "0";
    }

    void supprimerPersonne(int selectedRow) {
        Object[] p = data.get(selectedRow);
        pm = new ProductDao();
        System.err.println(p[nameToIndice("numero")] + "");
        pm.delete(Integer.parseInt(p[nameToIndice("numero")] + ""));
        data.remove(selectedRow);
        this.fireTableDataChanged();

    }

    int nameToIndice(String nom) {
        int a = -1;
        for (int i = 0; i < getColumnCount(); i++) {
            if (getColumnName(i).equals(nom)) {
                return i;
            }
        }
        return a;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (column == 5) {
            return ImageIcon.class;
        }
        return Object.class;
    }
}
