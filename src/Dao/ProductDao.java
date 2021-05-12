/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Product;
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
public class ProductDao implements CRUD<Product> {

    Connection con = null;
    PreparedStatement st = null;

    @Override
    public int create(Product object) {
        Connection con = DbDao.getConnection();
        int res = 0;
        if (con != null) {
            try {
                st = con.prepareStatement("insert into product(product_name,product_description,product_category,product_stock,product_price,product_img) values (?,?,?,?,?,?)");
                st.setString(1, object.getProduct_name());
                st.setString(2, object.getProduct_description());
                st.setInt(3, object.getProduct_category());
                st.setInt(4, object.getProduct_stock());
                st.setDouble(5, object.getProduct_price());
                st.setString(6, object.getProduct_img());

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Product object) {
        Connection con = DbDao.getConnection();
        int res = 0;
        if (con != null) {
            try {
                st = con.prepareStatement("update user set product_name=?,product_description=?,product_category=?,product_stock=?,product_price=?,product_img=?");
                st.setString(1, object.getProduct_name());
                st.setString(2, object.getProduct_description());
                st.setInt(3, object.getProduct_category());
                st.setInt(4, object.getProduct_stock());
                st.setDouble(5, object.getProduct_price());
                st.setString(6, object.getProduct_img());
                res = st.executeUpdate();
                if (res != 0) {
                    return res;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        }
        return res;
    }

    @Override
    public Product readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet read() {
        Connection con = DbDao.getConnection();

        ResultSet rs = null;
        Statement st = null;
            try {
                st = con.createStatement();

                rs = st.executeQuery("select product_id,product_name,product_description,product_stock,product_price,product_img,category_name from product,category where product.product_category=category.category_id");
                return rs;
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }

        
        return rs;
    }

    public ResultSet readByCategory(int category) {
        Connection con = DbDao.getConnection();

        ResultSet rs = null;
        Statement st = null;
            try {
                st = con.createStatement();

                rs = st.executeQuery("select * from product join category on product.product_category=category.category_id where product_category=" + category);
                return rs;
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }

        
        return rs;
    }
public int decrement(int quantity,int product_id){
     Connection con = DbDao.getConnection();

        int rs = 0;
        Statement st = null;
            try {
                st = con.createStatement();

                rs = st.executeUpdate("UPDATE product SET product_stock = product_stock - "+quantity+"  where product_id=" + product_id);
                return rs;
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }

        
        return rs;
}
}
