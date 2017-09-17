package com.arief.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


class ServerReadThreaded extends Thread{
    DataInputStream dataInput;

    public ServerReadThreaded(String name, DataInputStream dataInput) {
        super(name);
        this.dataInput = dataInput;
        this.start();
    }

    @Override
    public void run() {
        while (true){
            try {
                String pesan = dataInput.readUTF();
                if(pesan!=null){
                    Thread.sleep(50);
                    System.out.println("\nMessage From Client : " + pesan);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class MainServer {




    public static void main(String []x){
        try {
            ServerSocket server = new ServerSocket(9000);

            System.out.println("Server Start");

            Socket s = server.accept();

            DataInputStream data = new DataInputStream(s.getInputStream());



            ServerReadThreaded serverThread = new ServerReadThreaded("ServerThread1",data);


            DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());

            String str = "";


            Scanner scan = new Scanner(System.in);

            while (!str.equals("exit")){
                System.out.print("in Server : ");
                str = scan.next();

                dataOut.writeUTF(str);
                dataOut.flush();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
