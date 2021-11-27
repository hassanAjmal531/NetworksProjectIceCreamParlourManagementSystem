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
public class Admin implements Serializable  {
    
    public boolean addFlavour(IceCream iceCream, int choice) throws Exception{
        BufferedReader ui = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket cs = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("DESKTOP-0P6U2PV");
        System.out.println(ip);
        ByteArrayOutputStream bos;
        ObjectOutputStream oos;
        DatagramPacket sp;
        DatagramPacket rp;
        
        byte data[];

        while(true)
        {
                
            byte[] sd = new byte[1024];
            byte[] rd = new byte[1024];
            

            packet packet = null;
           

                    try{


                        packet = new packet(iceCream, choice, true);

                        bos = new ByteArrayOutputStream();
                        oos = new ObjectOutputStream(bos);
                        oos.writeObject(packet);
                        oos.flush();

                        sd = bos.toByteArray();
                        sp = new DatagramPacket(sd, sd.length,ip,9876);
                        cs.send(sp);

                        rp = new DatagramPacket(rd, rd.length);
                        cs.receive(rp);
                        String ms = new String(rp.getData());
                        cs.close();
                        return true;
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("please enter the data in correct format");
                    }

        }

        
    }
    
    public Document search(IceCream iceCream,int choice) throws Exception{
        
        BufferedReader ui = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket cs = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("DESKTOP-0P6U2PV");
        System.out.println(ip);
        ByteArrayOutputStream bos;
        ObjectOutputStream oos;
        DatagramPacket sp;
        DatagramPacket rp;
        
        byte data[];

        while(true)
        {
                
            byte[] sd = new byte[1024];
            byte[] rd = new byte[1024];
            

            packet packet = null;
           
    
            
            packet = new packet(iceCream,choice, true);
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

                
                cs.close();
                return doc;

            }catch(Exception e){
                e.printStackTrace();
                System.out.println("no record found");
            }
        }
                            
    }
    
    public ArrayList<IceCream> view() throws Exception{
         BufferedReader ui = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket cs = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("DESKTOP-0P6U2PV");
        System.out.println(ip);
        ByteArrayOutputStream bos;
        ObjectOutputStream oos;
        DatagramPacket sp;
        DatagramPacket rp;
        
        byte data[];

        while(true)
        {
                
            byte[] sd = new byte[1024];
            byte[] rd = new byte[1024];
            

            packet packet = null;
           
    
             packet = new packet(3, true);
                            System.out.println("please wait the server is processing the data");
                            
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
                                
                                cs.close();
                                return record;
                                

                            }catch(Exception e){
                                e.printStackTrace();
                                System.out.println("no record found");
                            }
                            
            

            
        }
        
    }
    
    public void operations(ArrayList<Object> list) throws Exception{
        Scanner in = new Scanner(System.in);
        
      
        
        BufferedReader ui = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket cs = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("DESKTOP-0P6U2PV");
        System.out.println(ip);
        ByteArrayOutputStream bos;
        ObjectOutputStream oos;
        DatagramPacket sp;
        DatagramPacket rp;

        System.out.println("this is a client");
        byte data[];

        while(true)
        {
                
                    byte[] sd = new byte[1024];
                    byte[] rd = new byte[1024];
                    int Choice = 0;
                    IceCream iceCream = null;
                    packet packet = null;
                   try{
//                    System.out.println("Enter your choice press 1, 2, 3 or 4:\n1. Add IceCream\n2. Search Flavour\n3.View\n4. Exit");
//                    Choice= in.nextInt();
                      Choice = (int)list.get(0);
                    switch(Choice){
                        case 1:
                            try{
                            
                                System.out.println("Enter:\n1.id\n2.flavour\n3.price\n4.stock amount");
                                iceCream = new IceCream(in.nextInt(), in.next(), in.nextInt(), Choice, in.nextInt());
                                
                                packet = new packet(iceCream, Choice, true);
                                
                                bos = new ByteArrayOutputStream();
                                oos = new ObjectOutputStream(bos);
                                oos.writeObject(packet);
                                oos.flush();

                                sd = bos.toByteArray();
                                sp = new DatagramPacket(sd, sd.length,ip,9876);
                                cs.send(sp);

                                rp = new DatagramPacket(rd, rd.length);
                                cs.receive(rp);
                                String ms = new String(rp.getData());
                                System.out.println("From Server: "+ms);
                            }catch(Exception e){
                                e.printStackTrace();
                                System.out.println("please enter the data in correct format");
                            }

                            
                            break;
                        case 2:
                            System.out.println("enter id to search");
                            
                            
                            iceCream = new IceCream(in.nextInt(), Choice);
                            packet = new packet(iceCream,Choice, true);
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
                            //iceCream = new IceCream(Choice);
                            packet = new packet(Choice, true);
                            System.out.println("please wait the server is processing the data");
                            
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
        
        
       
        
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("please enter 1 or 2 or 3 or 4");
    }
        }
        
        
    }
    
    
    
    
   
}
