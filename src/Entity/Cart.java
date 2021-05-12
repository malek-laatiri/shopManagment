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
public class Cart {
  private int user_id ;
    private int product_id ;
  private int quantity ;
  private int orders_id ;

    private int cart_id;

    /**
     * Get the value of cart_id
     *
     * @return the value of cart_id
     */
    public int getCart_id() {
        return cart_id;
    }

    /**
     * Set the value of cart_id
     *
     * @param cart_id new value of cart_id
     */
    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(int orders_id) {
        this.orders_id = orders_id;
    }

    @Override
    public String toString() {
        return "Cart{" + "user_id=" + user_id + ", product_id=" + product_id + ", quantity=" + quantity + ", orders_id=" + orders_id + '}';
    }

   
}
