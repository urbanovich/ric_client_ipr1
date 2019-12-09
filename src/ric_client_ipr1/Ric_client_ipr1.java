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
 
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
 
            Scanner scn = new Scanner(System.in);
            String command;
 
            do {
                System.out.println("If you want add new an element set 1.");
                System.out.println("If you want to find an element set 2.");
                System.out.println("If you want to delete an element set 3.");
                System.out.println("If you want to exit set 4.");
                
                command = scn.nextLine();
 
                JSONObject row = new JSONObject();
                row.put("command", command);
                
                switch (Integer.parseInt(command)) {
                    case 1:
                        
                        System.out.println("Set id:");
                        String id = scn.nextLine();
                        row.put("id", id);

                        System.out.println("Set title:");
                        String title = scn.nextLine();
                        row.put("title", title);

                        System.out.println("Set content:");
                        String content = scn.nextLine();
                        row.put("content", content);
                        
                        
                        break;
                    case 2:
                        
                        break;
                    case 3:
                        
                        break;
                    case 4:
                        
                        break;
                }
                
                writer.println(row.toString());
 
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String result = new String();                
                String line = new String();                
                while ((line = reader.readLine()) != null) {
                    if (line.isEmpty()) {
                        break;
                    }
                    result += line + "\n";
                }
 
                System.out.println(result);
 
            } while (!command.equals("4"));
 
            socket.close();
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
    
}
