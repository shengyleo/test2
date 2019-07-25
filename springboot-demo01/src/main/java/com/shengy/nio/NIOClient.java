package com.shengy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Auther: zhengying
 * @Date: 2019/7/14 23:56
 * @Description: TODO
 */
public class NIOClient {
    private static int blockSize=4096;
    private static int flag = 1;
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
    private static ByteBuffer receiveBuffer = ByteBuffer.allocate(blockSize);

    private final static InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1",7080);


    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(serverAddress);

        Set<SelectionKey> selectionKeys;
        Iterator<SelectionKey> keyIterator;
        SelectionKey selectionKey;
        SocketChannel client;
        String receiveText;
        String sendText;
        int count;
        while (true){
            selectionKeys = selector.selectedKeys();
            keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                selectionKey = keyIterator.next();
                if (selectionKey.isConnectable()){
                    System.out.println("client connect...");
                    client = (SocketChannel) selectionKey.channel();
                    if (client.isConnectionPending()){
                        client.finishConnect();
                        System.out.println("客户端完成链接操作");
                        sendBuffer.clear();
                        sendBuffer.put("Hello Server".getBytes());
                        sendBuffer.flip();
                        client.write(sendBuffer);
                    }
                    client.register(selector,SelectionKey.OP_READ);
                }else if (selectionKey.isReadable()){
                    client = (SocketChannel) selectionKey.channel();
                    receiveBuffer.clear();
                    count = client.read(receiveBuffer);
                    if (count>0){
                        receiveText = new String(receiveBuffer.array(),0,count);
                        System.out.println("客户端接收到服务端数据："+receiveText);
                        client.register(selector,SelectionKey.OP_WRITE);
                    }
                }else if (selectionKey.isWritable()){
                    sendBuffer.clear();
                    client = (SocketChannel) selectionKey.channel();
                    sendText = "Msg send to Server-->"+flag++;
                    sendBuffer.put(sendText.getBytes());
                    sendBuffer.flip();
                    client.write(sendBuffer);
                    System.out.println("客户端发送数据给服务器"+sendText);
                    client.register(selector,SelectionKey.OP_READ);
                }
            }
            selectionKeys.clear();
        }

    }
}
