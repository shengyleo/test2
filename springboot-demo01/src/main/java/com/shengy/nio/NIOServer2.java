package com.shengy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Auther: zhengying
 * @Date: 2019/7/16 00:34
 * @Description: TODO
 */
public class NIOServer2 {
    private static int blockSize = 4096;
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
    private static ByteBuffer receiveBuffer = ByteBuffer.allocate(blockSize);
    private static Selector selector;
    private static int flag = 1;

    public NIOServer2(int port){
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress(port));
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("server start on port -->"+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //监听事件，即监听客户端连接
    public static void listen() throws IOException {
        while (true) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                handlerKey(selectionKey);
            }
        }
    }
    //处理每一个selectionKey
    public static void handlerKey(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel client = null;
        String receiveText;
        String sendText;
        int count = 0;
        if (selectionKey.isAcceptable()) {
            serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            client = serverSocketChannel.accept();
            client.configureBlocking(false);
            client.register(selector,SelectionKey.OP_READ);
        }else if (selectionKey.isReadable()){
            client = (SocketChannel) selectionKey.channel();
            count = client.read(receiveBuffer);
            if (count>0){
                receiveText = new String(receiveBuffer.array(),0,count);
                System.out.println("服务器收到客户端的消息："+receiveText);
                client.register(selector,SelectionKey.OP_WRITE);
            }
        }else if (selectionKey.isWritable()){
            sendBuffer.clear();
            client = (SocketChannel) selectionKey.channel();
            sendText = "msg send to client:"+flag++;
            sendBuffer.put(sendText.getBytes());
            sendBuffer.flip();//写入缓冲区
            client.write(sendBuffer);
            System.out.println("服务端发送数据给客户端:"+sendText);
        }

    }
    public static void main(String[] args) throws IOException {
        int port = 7080;
        NIOServer2 server2 = new NIOServer2(port);
        server2.listen();
    }
}
