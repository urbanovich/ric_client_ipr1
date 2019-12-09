/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ric_client_ipr1;

import java.net.*;
import java.io.*;

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
 
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String text;
 
            do {
                System.out.println("Enter text: ");
                
                text = in.readLine();
 
                writer.println(text);
 
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
                String time = reader.readLine();
 
                System.out.println(time);
 
            } while (!text.equals("bye"));
 
            socket.close();
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
    
}
