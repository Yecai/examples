package com.xiaopan.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ClientHandle implements Runnable {
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean started;
    
    public ClientHandle(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            started = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            SocketChannel sc = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (sc.finishConnect());
                else System.exit(1);
            }
            
            if (key.isReadable()) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(buffer);
                if (readBytes > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    String result = new String(bytes, "UTF-8");
                    System.out.println("客户端收到消息：" + result);
                } else if (readBytes < 0) {
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    public void stop() {
        started = false;
    }
    
    @Override
    public void run() {
        try {
            doConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        while(started) {
            try {
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                SelectionKey key = null;
                while(it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }

    private void doConnect() throws IOException {
        if (socketChannel.connect(new InetSocketAddress(host, port)))
            ;
        else
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        System.out.println("客户端已启动");
    }
    
    public void sendMsg(String msg) throws IOException {
        socketChannel.register(selector, SelectionKey.OP_READ);
        doWrite(socketChannel, msg);
    }

    private void doWrite(SocketChannel socketChannel, String msg) throws IOException {
        System.out.println("客户端发送数据：" + msg);
        byte[] bytes = msg.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
    }
    
}
