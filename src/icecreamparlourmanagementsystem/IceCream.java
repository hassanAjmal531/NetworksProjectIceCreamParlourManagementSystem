/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icecreamparlourmanagementsystem;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class IceCream implements Serializable {
    
    public int id;
    public String iceCream;
    public int price;
    public int menuItem;
    public int stock;
    
    public IceCream(int id, String iceCream, int price, int menuItem) {
    
        this.id = id;
        this.iceCream = iceCream;
        this.price = price;
        this.menuItem = menuItem;
    }
    
    public IceCream(int id, String iceCream, int price, int menuItem, int stock) {
    
        this.id = id;
        this.iceCream = iceCream;
        this.price = price;
        this.menuItem = menuItem;
        this.stock = stock;
    }
    
    
    
    public IceCream(int id , String iceCream, int price){
        this.id = id;
        this.iceCream = iceCream;
        this.price = price;
    
    }
    
    public IceCream(int id, int MenuItem){
        this.id = id;
        this.menuItem = MenuItem;
    
    }
    
    public IceCream(int MenuItem){
        this.menuItem = MenuItem;
    }
    
    
}
