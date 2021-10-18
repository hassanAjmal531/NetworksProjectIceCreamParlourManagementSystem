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
 * @author Dell
 */
public class adminInterface implements Serializable {
    
    
    
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
                    IceCream iceCream = null;
                    
                   
                    System.out.println("Enter your choice press 1, 2, 3 or 4:\n1. Add IceCream\n2. Search Flavour\n3.View\n4. Exit");
                    Choice= in.nextInt();
                    switch(Choice){
                        case 1:
                            
                            System.out.println("Enter:\n1.id\n2.flavour\n3.price");
                            iceCream = new IceCream(in.nextInt(), in.next(), in.nextInt(), Choice);
                            bos = new ByteArrayOutputStream();
                            oos = new ObjectOutputStream(bos);
                            oos.writeObject(iceCream);
                            oos.flush();
                            
                            sd = bos.toByteArray();
                            sp = new DatagramPacket(sd, sd.length,ip,9876);
                            cs.send(sp);
                            
                            rp = new DatagramPacket(rd, rd.length);
                            cs.receive(rp);
                            String ms = new String(rp.getData());
                            System.out.println("From Server: "+ms);

                            
                            break;
                        case 2:
                            System.out.println("enter id to search");
                            
                            
                            iceCream = new IceCream(in.nextInt(), Choice);
                            
                            bos = new ByteArrayOutputStream();
                            oos = new ObjectOutputStream(bos);
                            oos.writeObject(iceCream);
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
                            iceCream = new IceCream(Choice);
                            System.out.println("please wait the server is processing the data");
                            
                            bos = new ByteArrayOutputStream();
                            oos = new ObjectOutputStream(bos);
                            oos.writeObject(iceCream);
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
