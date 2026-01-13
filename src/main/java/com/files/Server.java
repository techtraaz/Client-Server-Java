package com.files;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in;


    public Server(int port) {


        try{
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();

            File file = new File("C:\\Users\\ruchith_a\\Downloads\\sample.pdf");


            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            dos.writeUTF(file.getName());
            dos.writeLong(file.length());

            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int len;

            while ((len = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, len);
            }

            dos.flush();
            fis.close();


        }catch(Exception e){
            e.printStackTrace();
        }


    }

    public static void main(String[] args){
        new Server(5050);
    }

}
