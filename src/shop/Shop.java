/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import Dao.DbDao;
import Dao.UserDao;
import Entity.User;
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
        User user=new User();
        user.setUser_name("mm");
        user.setUser_password("mm");
        //System.out.println(new UserDao().readByUsernamePassword(user));
         Login n = new Login();
        n.setVisible(true);
    }
    
}
