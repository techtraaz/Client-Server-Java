package com;

import com.server.ClientHandler;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server() {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getPort());


                Thread clientThread =
                        new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
