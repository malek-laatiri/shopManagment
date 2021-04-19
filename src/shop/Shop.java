/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import Dao.DbDao;
import IHM.Login;
import java.sql.*;

/**
 *
 * @author malek
 */
public class Shop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection db=DbDao.getConnection();
         Login n = new Login();
        n.setVisible(true);
    }
    
}
