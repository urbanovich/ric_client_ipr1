/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ric_client_ipr1;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import org.json.simple.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 *
 * @author dzmitry
 */
public class Ric_client_ipr1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 
        String hostname = "localhost";
        int port = 9090;
 
        try (Socket socket = new Socket(hostname, port)) {

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            
            Scanner scn = new Scanner(System.in);
            String command, result, id, title, content, search;
 
            do {
                System.out.println("If you want add new an element set 1.");
                System.out.println("If you want to find an element set 2.");
                System.out.println("If you want to delete an element set 3.");
                System.out.println("If you want to see all items set 4.");
                System.out.println("If you want to exit set 5.");
                
                command = scn.nextLine();
                 
                JSONObject row = new JSONObject();
                row.put("command", command);
                
                switch (Integer.parseInt(command)) {
                    case 1:
                        
                        System.out.println("Set id:");
                        id = scn.nextLine();
                        row.put("id", id);

                        System.out.println("Set title:");
                        title = scn.nextLine();
                        row.put("title", title);

                        System.out.println("Set content:");
                        content = scn.nextLine();
                        row.put("content", content);
                        
                        break;
                    case 2:
                        
                        System.out.println("Enter document title:");
                        
                        search = scn.nextLine();
                        row.put("search", search);
                        
                        break;
                    case 3:
                        System.out.println("Enter document id:");
                        id = scn.nextLine();
                        row.put("id", id);
                        break;
                    case 4:
                        
                        break;
                }
                                
                dos.writeUTF(row.toString());
 
                result = dis.readUTF();                                
                System.out.println(result);

            } while (!command.equals("5"));
             
            socket.close();
            dis.close();
            dos.close();
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
    
}
