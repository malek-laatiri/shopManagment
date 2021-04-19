/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author malek
 */
public class Order {

    private int orders_id;
    private int user_id;
    private String orders_created_at;

    public int getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(int orders_id) {
        this.orders_id = orders_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getOrders_created_at() {
        return orders_created_at;
    }

    public void setOrders_created_at(String orders_created_at) {
        this.orders_created_at = orders_created_at;
    }

}
