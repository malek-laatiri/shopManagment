/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import Dao.CategoryDao;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author malek
 */
public class ModelCategory extends AbstractTableModel {

    ResultSetMetaData rsmd;
    int nbligne;
    int nbc;
    ArrayList<Object[]> data = new ArrayList<Object[]>();
    CategoryDao pm;

    public ModelCategory(ResultSet rs) {
        super();
        try {
            rsmd = rs.getMetaData();
            nbc = rsmd.getColumnCount();
            while (rs.next()) {
                nbligne++;
                Object[] ligne = new Object[rsmd.getColumnCount()];
                for (int i = 0; i < ligne.length; i++) {
                    ligne[i] = rs.getObject(i + 1);
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
           ex.getMessage();
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
        pm = new CategoryDao();
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
}
