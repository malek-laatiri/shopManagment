/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.User;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author malek
 */
public class UserDao implements CRUD<User> {

    Connection con = null;
    PreparedStatement st = null;

    public UserDao() {
    }

    @Override
    public int create(User object) {
        Connection con = DbDao.getConnection();
        int res = 0;
        if (con != null) {
            try {
                st = con.prepareStatement("insert into user(user_name,user_email,user_password,user_type,user_phone,user_img) values (?,?,?,?,?,?)");
                st.setString(1, object.getUser_name());
                st.setString(2, object.getUser_email());
                st.setString(3, object.getUser_password());
                st.setString(4, object.getUser_type());
                st.setString(5, object.getUser_phone());
                st.setString(6, object.getUser_img());
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
    public int delete(int id) {
        int res = 0;
        if (con != null) {
            try {

                //creer des requetes
                st = con.prepareStatement("delete from user where user_id=?");
                st.setInt(1, id);
                res = st.executeUpdate();

            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        }
        return res;
    }

    @Override
    public int update(User object) {
        Connection con = DbDao.getConnection();
        int res = 0;
        if (con != null) {
            try {
                st = con.prepareStatement("update user set user_name=?,user_email=?,user_password=?,user_type=?,user_phone=?,user_img=?");
                st.setString(1, object.getUser_name());
                st.setString(2, object.getUser_email());
                st.setString(3, object.getUser_password());
                st.setString(4, object.getUser_type());
                st.setString(5, object.getUser_phone());
                st.setString(6, object.getUser_img());
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
    public User readById(int id) {
        ResultSet rs = null;
        User user = new User();
        if (st != null) {
            try {
                st = con.prepareStatement("select * from user where user_id=?");
                st.setInt(1, id);
                rs = st.executeQuery();

                while (rs.next()) {

                    user.setUser_email(rs.getString("user_email"));
                    user.setUser_id(rs.getInt("user_id"));
                    user.setUser_img(rs.getString("user_img"));
                    user.setUser_name(rs.getString("user_name"));
                    user.setUser_password(rs.getString("user_password"));
                    user.setUser_phone(rs.getString("user_phone"));
                    user.setUser_type(rs.getString("user_type"));

                }
                return user;
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }

        }
        return user;
    }

    @Override
    public ResultSet read() {
        ResultSet rs = null;
        Statement st = null;
        if (st != null) {
            try {
                st = con.createStatement();

                rs = st.executeQuery("select * from user");
                return rs;
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }

        }
        return rs;
    }

}
