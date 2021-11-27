/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icecreamparlourmanagementsystem;

import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class IceCreamParlourManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Scanner inp=new Scanner(System.in);
        String mod = "";
        
        
        while(true){
            
            System.out.println("Enter 1 for Admin module, 2 for Customer module, 3 to exit");
            mod = inp.nextLine();
            switch(mod){
                case "1":
                    new Admin().operations();
                    break;
                case "2":
                    new Customer().operations();
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter Correct option");
                    System.out.println("please enter 1 or 2 or 3 \n\n");
                    
                    
            }
            
        }
        
    }
    
}
