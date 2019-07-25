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
 * @Date: 2019/7/14 23:24
 * @Description: TODO
 */
public class NIOServer {
    private int blockSize=4096;
    private int flag = 1;
    private ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
    private ByteBuffer receiveBuffer = ByteBuffer.allocate(blockSize);
    private Selector selector ;

    public NIOServer(int port) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //设置是否阻塞
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            //绑定IP和端口
            serverSocket.bind(new InetSocketAddress(port));
            //打开选择器
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("-------server start at ->"+port+"------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listen() throws IOException {
        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                handlerKey(key);
            }

        }
    }
    //处理业务
    public void handlerKey(SelectionKey key) throws IOException {
        ServerSocketChannel server = null;
        SocketChannel client = null;
        String receiveText;
        String sendText;
        int count = 0;
        if (key.isAcceptable()){
            server = (ServerSocketChannel) key.channel();
            client = server.accept();
            client.configureBlocking(false);
            client.register(selector,SelectionKey.OP_READ);

        }else if (key.isReadable()){
            client = (SocketChannel) key.channel();
            count = client.read(receiveBuffer);
            if (count>0){
                receiveText = new String(receiveBuffer.array(),0,count);
                System.out.println("服务端接收到客户端的信息："+receiveText);
                client.register(selector,SelectionKey.OP_WRITE);
            }
        }else if (key.isWritable()){
            sendBuffer.clear();
            client = (SocketChannel) key.channel();
            //发送的数据
            sendText = "msg send to client"+flag++;
            sendBuffer.put(sendText.getBytes());
            //写入缓冲区
            sendBuffer.flip();
            client.write(sendBuffer);
            System.out.println("服务端发送数据给客户端："+sendText);
        }
    }
    public static void main(String[] args) throws IOException {
        int port = 7080;
        NIOServer server = new NIOServer(port);
        server.listen();
    }
}
