/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.*;

/**
 *
 * @author malek
 */
public class DbDao {

    static Connection con = null;
   static Statement st = null;

    private DbDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //charger le driver
            System.out.println("Chargement");
            String url = "jdbc:mysql://localhost:3306/shop"; //acces au base de donnes
            String login = "root";
            String pwd = "root";
            con = DriverManager.getConnection(url, login, pwd);
            System.out.println("connected");
            st = con.createStatement();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static Connection getConnection() {
       if(con==null){
           new DbDao();
       }
       return con;
    }
}
