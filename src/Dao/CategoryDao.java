/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author malek
 */
public class CategoryDao implements CRUD<Category> {

    Connection con = null;
    PreparedStatement st = null;

    @Override
    public int create(Category object) {
        Connection con = DbDao.getConnection();
        int res = 0;
        if (con != null) {
            try {
                st = con.prepareStatement("insert into category(category_name) values (?)");
                st.setString(1, object.getCategory_name());

                res = st.executeUpdate();
                if (res != 0) {

                    return res;
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());

            }
        }
        return res;
    }

    @Override
    public int delete(int id) {
        Connection con = DbDao.getConnection();

        int res = 0;
        if (con != null) {
            try {

                //creer des requetes
                st = con.prepareStatement("delete from category where user_id=?");
                st.setInt(1, id);
                res = st.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }
        return res;
    }

    @Override
    public int update(Category object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet read() {
        Connection con = DbDao.getConnection();

        ResultSet rs = null;
        Statement st = null;
        if (st != null) {
            try {
                st = con.createStatement();

                rs = st.executeQuery("select * from category");
                return rs;
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }

        }
        return rs;
    }

}
