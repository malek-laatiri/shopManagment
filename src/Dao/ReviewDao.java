/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Review;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author malek
 */
public class ReviewDao implements CRUD<Review>{

    @Override
    public int create(Review object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Review object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Review readById(int id) {
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

                rs = st.executeQuery("select * from product natural join category");
                return rs;
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }

        }
        return rs;    }

    
    
}
