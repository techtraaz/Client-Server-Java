package com.files;


import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Client {

    String rc = "C:\\Users\\ruchith_a\\Desktop\\Personal\\ClientServer\\receive";
    String send = "C:\\Users\\ruchith_a\\Desktop\\Personal\\ClientServer\\send";


    String file = "C:\\Users\\ruchith_a\\Desktop\\Personal\\ClientServer\\send\\img_server.png";

    private Socket s = null;
    private DataInputStream in = null;


    public Client(String addr, int port)
    {

        try {
            s = new Socket(addr, port);
            System.out.println("Connected");

            in = new DataInputStream(s.getInputStream());

            String fileName = in.readUTF();
            Long  fileSize = in.readLong();

            FileOutputStream fos = new FileOutputStream(fileName);

            byte[] buffer = new byte[4096];
            long remaining = fileSize;
            int bytesRead;

            System.out.println("File Transfer Started :" + fileName);

            while (remaining > 0 && (bytesRead = in.read(buffer, 0, (int)Math.min(buffer.length, remaining))) != -1) {
                fos.write(buffer, 0, bytesRead);
                remaining -= bytesRead;
            }

            System.out.println("File Transfer Completed :" + fileName);

            fos.close();



        }
        catch (Exception u) {
            System.out.println(u);
            return;
        }



        try {
            in.close();
            s.close();
        }
        catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        new Client("127.0.0.1", 5050);
    }

}