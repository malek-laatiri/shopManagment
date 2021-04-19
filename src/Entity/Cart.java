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
  private int review_id ;
   private String review_content ;
   private String review_created_at;

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }

    public String getReview_created_at() {
        return review_created_at;
    }

    public void setReview_created_at(String review_created_at) {
        this.review_created_at = review_created_at;
    }
   
   
}
