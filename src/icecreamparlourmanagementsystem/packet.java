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
public class packet implements Serializable  {
    public IceCream iceCream;
    public int choice;
    public boolean usertype;
    public int value;
    
    
    public packet(){}
    
    public packet(IceCream iceCream, int choice, boolean userType){
        this.iceCream =iceCream;
        this.choice = choice;
        this.usertype = userType;
    }
    
    public packet(int choice, boolean userType){
        this.choice = choice;
        this.usertype = userType;
    }
    
    public packet(int value, int choice, boolean usertype){
        this.value = value;
        this.choice = choice;
        this.usertype = usertype;
    }
    
}
