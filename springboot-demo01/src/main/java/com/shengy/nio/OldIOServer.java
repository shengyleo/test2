package com.shengy.nio;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Auther: zhengying
 * @Date: 2019/7/15 23:48
 * @Description: TODO
 */
public class OldIOServer {
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(10101);
            System.out.println("服务器启动！");
            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("来了一个客户端");
                handler(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void handler(Socket socket){
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true){
                int read = inputStream.read(bytes);
                if (read!=-1){
                    System.out.println(new String(bytes,0,read));
                }else {
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                System.out.println("socket 关闭");
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
