package com;

import java.net.*;
import java.io.*;

public class Server {


    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private DataInputStream dis = null;


    public Server(int port) {

        try
        {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");

            socket = serverSocket.accept();
            System.out.println("Client connected");


            dis = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String m = "";


            while (!m.equals("-99"))
            {
                try
                {
                    m = dis.readUTF();
                    System.out.println(m);

                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");


            socket.close();
            dis.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Server s = new Server(5000);
    }
}