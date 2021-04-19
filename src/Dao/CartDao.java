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
    Statement st=null;

    @Override
    public int create(Cart object) {
        Connection con = DbDao.getConnection();
        int res = 0;
        if (con != null) {
            try {
                st = con.createStatement();
                //creer des requetes
                res = st.executeUpdate("insert into personne values (" + num + ",'" + nom + "','" + prenom + "'," + moy + ")");

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

                st = con.createStatement();
                //creer des requetes
                res = st.executeUpdate("delete from personne where numero=" + num);

            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        }
        return res;
    }

    @Override
    public int update(Cart object) {
        int res = 0;
        if (con != null) {
            try {
                st = con.createStatement();
                //creer des requetes
                res = st.executeUpdate("update personne set nom='" + nom + "',prenom='" + prenom + "',moyenne='" + moy + "' where numero=" + num);

            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        }
        return res;
    }

    @Override
    public Cart readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet read() {
        ResultSet rs = null;

        if (st != null) {
            try {
                st = con.createStatement();

                rs = st.executeQuery("select * from personne");
                System.out.println("done getAllPersonne");
                return rs;
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }

        }
        return rs;
    }

}
