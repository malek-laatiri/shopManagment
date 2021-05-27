/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Order;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author malek
 */
public class OrderDao implements CRUD<Order> {

    Connection con = null;
    Statement st = null;

    @Override
    public int create(Order object) {
        Connection con = DbDao.getConnection();
        int res = 0;
        long generatedKeys = 0;
        if (con != null) {
            try {
                PreparedStatement ps = (PreparedStatement) con.prepareStatement("insert into orders(user_id,total_price) values (" + object.getUser_id() + "," + object.getTotal_price() + ")", Statement.RETURN_GENERATED_KEYS);
                //creer des requetes
                res = ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    generatedKeys = rs.getLong(1);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }
        return (int) generatedKeys;
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Order object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResultSet readAllById(int user_id) {
        ResultSet rs = null;
        Connection con = DbDao.getConnection();

        try {
            st = con.createStatement();

            rs = st.executeQuery("select product_name,product_price,quantity,total_price,orders_created_at from orders o,product p,cart c\n"
                    + "where o.user_id=" + user_id + " and o.orders_id=c.orders_id and c.product_id=p.product_id\n"
                    + "order by total_price desc  ");
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return rs;
    }

    public ResultSet dates(int user_id,String date) {
        ResultSet rs = null;
        Connection con = DbDao.getConnection();

        try {
            st = con.createStatement();

            rs = st.executeQuery("select product_name,product_price,quantity,total_price,orders_created_at from orders o,product p,cart c\n"
                    + "where orders_created_at='"+date+"' and  o.user_id=" + user_id + " and o.orders_id=c.orders_id and c.product_id=p.product_id\n"
                    + "order by total_price desc");
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return rs;
    }
      public ResultSet prices(int user_id) {
        ResultSet rs = null;
        Connection con = DbDao.getConnection();

        try {
            st = con.createStatement();

            rs = st.executeQuery("select product_name,product_price,quantity,total_price,orders_created_at from orders o,product p,cart c\n"
                    + "where o.user_id=" + user_id + " and o.orders_id=c.orders_id and c.product_id=p.product_id\n"
                    + "order by total_price asc");
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return rs;
    }
}
