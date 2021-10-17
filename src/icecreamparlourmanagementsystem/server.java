/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icecreamparlourmanagementsystem;

/**
 *
 * @author Dell
 */
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.ArrayList;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class server  implements Serializable {
    
    private static MongoClient mongoClient;
	public static void main(String[] args) throws Exception
	{
		DatagramSocket servers = new DatagramSocket(9876);
                System.out.println("this is the server");
                mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
                MongoDatabase db = mongoClient.getDatabase("IceCreamParlour");
                MongoCollection<Document> collection = mongoClient.getDatabase("IceCreamParlour").getCollection("IceCream");
                BasicDBObject b;
                FindIterable<Document> cursor;
                MongoCursor<Document> iterator ;
                DatagramPacket sendData ;
                InetAddress ip;
                Document doc ;
                int port ;
		while(true)
		{
                        
                        
                    Document document = new Document();
                    
                    byte[] rd = new byte[1024];
                    byte[] sd = new byte[1024];
                    DatagramPacket rp = new DatagramPacket(rd, rd.length);
                    servers.receive(rp);

                    // server recieves data in the form of bytes array/stream
                    byte[] Data= rp.getData();

                    ByteArrayInputStream bin = new ByteArrayInputStream(Data);
                    ObjectInputStream ois = new ObjectInputStream(bin);
                    IceCream iceCream = (IceCream)ois.readObject();
                    ArrayList<IceCream> PacketData = new ArrayList<IceCream>();

                    



                    //converting the byte stream back to the admin object that was sent from the servers

                    switch(iceCream.menuItem){

                        case 1:
                            document.append("id", iceCream.id);
                            document.append("flavour",iceCream.iceCream );
                            document.append("price", iceCream.price);
                            
                            db.getCollection("IceCream").insertOne(document);
                            
                            ip = rp.getAddress();
                            port = rp.getPort();
                            
                            sd = "dataAdded".getBytes();
                            
                            
                            sendData = new DatagramPacket(sd,sd.length,ip, port);
                            servers.send(sendData);
                            
                            
                            System.out.println("data added");
                            break;
                        case 2:
                            
                            b = new BasicDBObject("id", iceCream.id);
                            cursor = collection.find(b);
                            iterator = cursor.iterator();
                            
                            if(iterator.hasNext()){

                            
                                doc = iterator.next();
                                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                                ObjectOutputStream oos = new ObjectOutputStream(bos);
                                oos.writeObject(doc);
                                oos.flush();
                                
                                sd = bos.toByteArray();
                                
                                System.out.println(doc);
                            
                            
                            }
                            else{
                                sd = "record not found".getBytes();
                                
                            }
                            ip = rp.getAddress();
                            port = rp.getPort();
                            
                            sendData = new DatagramPacket(sd,sd.length,ip, port);
                            servers.send(sendData);
                            
                            
                                
                            
                            break;
                        
                        case 3:
                            cursor = collection.find();
                            iterator = cursor.iterator();
                            int count = 0;
                            
                            
                           // Document record = new Document();
                            ArrayList<IceCream> record = new ArrayList<IceCream>();
                            if(iterator.hasNext()){
                               // ArrayList<Document> record = new ArrayList<Document>();
                                while(iterator.hasNext()){

                                    doc = iterator.next();
                                    record.add(new IceCream(doc.getInteger("id"),doc.getString("flavour"),doc.getInteger("price")));
                                    count ++;





                                }
                                 System.out.println(record);
                                 
                                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                                    oos.writeObject(record);
                                    oos.flush();
                                    oos.close();
                                    
                                    
                                    sd = bos.toByteArray();
                                    System.out.println(sd);
                                    

                            
                            }
                            else{
                                sd = "record not found".getBytes();
                                
                            }
                            ip = rp.getAddress();
                            port = rp.getPort();
                            
                            sendData = new DatagramPacket(sd,sd.length,ip, port);
                            servers.send(sendData);
                            break;
                        default:
                            break;

                    }
                }
                
        }
        
}