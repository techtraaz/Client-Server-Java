package com.server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.Socket;


public class ClientHandler implements Runnable {

    private Socket socket;

    public  ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try{
            DataInputStream dis = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));



            while(true){
                String data = dis.readUTF();

                if(data.equals("-99")){
                    System.out.println("Client disconnected");
                    break;
                }

                System.out.println(data);
            }

            dis.close();
            socket.close();


        }catch(Exception e){
            e.printStackTrace();
        }


    }

}
