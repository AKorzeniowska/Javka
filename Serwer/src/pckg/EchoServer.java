package pckg;

import java.io.*;
import java.net.*;

public class EchoServer extends Thread{
        ServerSocket serverSocket;

        public EchoServer(){
            serverSocket=null;
        }

        public void listen() {
            try {
                serverSocket = new ServerSocket(6666);
            } catch (IOException e) {
                System.out.println("Could not listen on port: 6666");
                System.exit(-1);
            }
        }

        public void connect() {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Accept failed: 6666");
                System.exit(-1);
            }

            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    out.println(inputLine);
                }
                out.close();
                in.close();
                clientSocket.close();
            } catch (IOException e){ }
        }

        public void close(){
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}

