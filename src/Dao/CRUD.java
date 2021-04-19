/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author malek
 * @param <T>
 */
public interface CRUD <T> {
    int create(T object);
    int delete(int id);
    int update(T object);
    T readById(int id);
    ResultSet read();
}
