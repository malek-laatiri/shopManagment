/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Cart;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author malek
 */
public class CartDao implements CRUD<Cart> {

    Connection con = null;
    Statement st = null;

    @Override
    public int create(Cart object) {
        Connection con = DbDao.getConnection();
        int res = 0;
        if (con != null) {
            try {
                st = con.createStatement();
                //creer des requetes
                res = st.executeUpdate("insert into cart(user_id,product_id,quantity) values (" + object.getUser_id() + ",'" + object.getProduct_id() + "','" + object.getQuantity() + "')");

            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        }
        return res;
    }

    @Override
    public int delete(int id) {
        int res = 0;
        if (con != null) {
            try {

                st = con.createStatement();
                //creer des requetes
                res = st.executeUpdate("delete from personne where numero=" + id);

            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        }
        return res;
    }

    @Override
    public int update(Cart object) {
        int res = 0;
        Connection con = DbDao.getConnection();

        if (con != null) {
            try {
                st = con.createStatement();
                res = st.executeUpdate("update cart set quantity='" + object.getQuantity() + "' where cart_id=" + object.getCart_id());

            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }
        return res;
    }

    @Override
    public Cart readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResultSet readAll(int user_id) {
        ResultSet rs = null;
        Connection con = DbDao.getConnection();

        try {
            st = con.createStatement();

            rs = st.executeQuery("select p.product_id,p.product_name,cart_id,quantity,product_price,product_img from cart c,product p where ISNULL(c.orders_id)=1 and c.product_id=p.product_id and user_id=" + user_id);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return rs;
    }

    @Override
    public ResultSet read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int updateOrder(int cart_id, int order_id) {
        int res = 0;
        Connection con = DbDao.getConnection();

        if (con != null) {
            try {
                st = con.createStatement();
                res = st.executeUpdate("update cart set orders_id='" + order_id + "' where cart_id=" + cart_id);

            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }
        return res;
    }

}
