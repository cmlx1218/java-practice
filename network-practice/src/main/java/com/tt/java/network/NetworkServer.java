package com.tt.java.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * @Desc socket 服务端
 * @Author cmlx
 * @Date 2020-5-13 0013 18:08
 */
public class NetworkServer extends Thread{

    private ServerSocket serverSocket;

    public NetworkServer(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("Waiting for client on port " +
                        serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Just connected to "
                        + server.getRemoteSocketAddress());
                DataInputStream in =
                        new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());
                DataOutputStream out =
                        new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting to "
                        + server.getLocalSocketAddress() + "\nGoodbye!");
                server.close();
            }catch(SocketTimeoutException s)
            {
                System.out.println("Socket timed out!");
                break;
            }catch(IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
    public static void main(String [] args)
    {
        int port = Integer.parseInt(args[0]);
        try
        {
            Thread t = new NetworkServer(port);
            t.start();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }



}
