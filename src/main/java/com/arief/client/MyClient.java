package com.arief.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class ClientReadThreaded extends Thread{
    DataInputStream dataInput;

    public ClientReadThreaded(String name, DataInputStream dataInput) {
        super(name);
        this.dataInput = dataInput;
        this.start();
    }

    @Override
    public void run() {
       while (true){
           try {
               String pesanServer = dataInput.readUTF();

               if(pesanServer!=null){
                   Thread.sleep(50);
                   System.out.println("\nPesan Server : " + pesanServer);
               }

           } catch (IOException e) {
               e.printStackTrace();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

       }
    }
}

public class MyClient {




    public static void main(String []x){
        try {

            Socket socket = new Socket("localhost",9000);

            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

            DataInputStream dataIn = new DataInputStream(socket.getInputStream());

            ClientReadThreaded clientThread = new ClientReadThreaded("ClientThread",dataIn);


            String str = "";


            Scanner scan = new Scanner(System.in);

            while (!str.equals("exit")){
                System.out.print("in Client : ");
                str = scan.next();

                dataOut.writeUTF(str);
                dataOut.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
