/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icecreamparlourmanagementsystem;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;
import org.bson.Document;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 *
 * @author DELL
 */
public class Customer {
    
    
    public void operations() throws Exception{
        Scanner in = new Scanner(System.in);
        
      
        
        BufferedReader ui = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket cs = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("DESKTOP-0P6U2PV");
        System.out.println(ip);
        ByteArrayOutputStream bos;
        ObjectOutputStream oos;
        DatagramPacket sp;
        DatagramPacket rp;

        

        String s = "Yes";
        System.out.println("this is a client");
        byte data[];

        while(true)
        {
                    
                    byte[] sd = new byte[1024];
                    byte[] rd = new byte[1024];
                    int Choice = 0;
                    IceCream iceCream;
                    packet packet;
                    
                    
                    
                    System.out.println("Enter your choice press 1, 2, 3 or 4:\n1. Buy Ice Cream\n2. Search Flavour\n3.View\n4. Exit");
                    Choice= in.nextInt();
                    
                            
                    switch(Choice){
                        case 1:
                            int bill=0;
                            while(true){
                                try{
                                System.out.println("Enter ID of flavour you wish to buy");
                                int stock = 0;
                                
                                iceCream = new IceCream(in.nextInt(),Choice);
                                packet = new packet(iceCream, Choice,false);
                                bos = new ByteArrayOutputStream();
                                oos = new ObjectOutputStream(bos);
                                oos.writeObject(packet);
                                oos.flush();
                            
                                sd = bos.toByteArray();
                                sp = new DatagramPacket(sd, sd.length,ip,9876);
                                cs.send(sp);
                            
                                rp = new DatagramPacket(rd, rd.length);
                                cs.receive(rp);
                                data = rp.getData();

                                
                            
                                try{
                                    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
                                    Object o = ois.readObject();
                                    Document doc = (Document) o;
                                    stock = doc.getInteger("stock");
                                    if ( stock != 0){
                                        System.out.println("enter the amount you want to buy");
                                        int amount = in.nextInt();
                                        stock = stock-amount;
                                        if(stock != 0){
                                            bill = bill +amount*doc.getInteger("price");

                                            }
 
                                    }else{
                                        System.out.println("this item is currently unavailable");
                                    }
                                    
                                }catch(Exception e){
                                    e.printStackTrace();
                                    System.out.println("no item on menu");            
                                }
                                System.out.println("press y if you want to enter another item, else n");
                                if(in.next().charAt(0)=='y')
                                    continue;
                                else{
                                    System.out.println("Bill="+bill);
                                    
                                }
                                    break;
                                }catch(Exception e){
                                    System.out.println("please enter a vaild id");
                                }
                            }
                            break;
                            
                        case 2:
                            System.out.println("enter id to search");
                            
                            
                            //iceCream = new packet(in.nextInt(), Choice);
                            
                            iceCream = new IceCream(in.nextInt(), Choice);
                            packet = new packet(iceCream,Choice, false);
                            
                            bos = new ByteArrayOutputStream();
                            oos = new ObjectOutputStream(bos);
                            oos.writeObject(packet);
                            oos.flush();
                            
                            sd = bos.toByteArray();
                            sp = new DatagramPacket(sd, sd.length,ip,9876);
                            cs.send(sp);
                            
                            rp = new DatagramPacket(rd, rd.length);
                            cs.receive(rp);
                            data = rp.getData();

                            
                            
                            try{
                                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
                                Object o = ois.readObject();
                                Document doc = (Document) o;
                            
                                System.out.println("Flavour id    FlavourName     Price");
                                System.out.println(doc.getInteger("id")+"            "+doc.getString("flavour")+"      "+doc.getInteger("price"));
                                System.out.println();
                            
                            }catch(Exception e){
                                e.printStackTrace();
                                System.out.println("no record found");
                            }
                            
                            
                            
                            
                            break;
                        case 3:
                           /// iceCream = new packet(Choice);
                            System.out.println("please wait the server is processing the data");
                            packet = new packet(Choice, false);
                            
                            bos = new ByteArrayOutputStream();
                            oos = new ObjectOutputStream(bos);
                            oos.writeObject(packet);
                            oos.flush();
                            
                            sd = bos.toByteArray();
                            System.out.println(sd);
                            sp = new DatagramPacket(sd, sd.length,ip,9876);
                            cs.send(sp);
                            
                            rp = new DatagramPacket(rd, rd.length);
                            cs.receive(rp);
                            data = rp.getData();
                            System.out.println(data);
                            
                            try{
                                
                                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
                                Object o = ois.readObject();
                                ArrayList<IceCream> record = (ArrayList)o;
                                System.out.println("Flavour id    FlavourName     Price");
                                for(IceCream i: record){
                                
                                   System.out.println(i.id+"           "+i.iceCream+"      "+i.price);
                                    
                                }
                                
                                System.out.println();
                                

                            }catch(Exception e){
                                e.printStackTrace();
                                System.out.println("no record found");
                            }
                            
                            
                            
                            break;
                            
                        case 4:
                            System.exit(0);
                            break;
                            
                        default:
                            System.out.println("please enter correct option");
                            
        }
        
        
       
        
    }
        
        
    }
    
    
}
