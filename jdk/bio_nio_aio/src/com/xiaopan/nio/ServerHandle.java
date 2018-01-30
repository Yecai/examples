package com.xiaopan.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.xiaopan.bio.Calculator;

public class ServerHandle implements Runnable {
    private Selector selector;
    private ServerSocketChannel serverChannel;
    private volatile boolean started;

    public ServerHandle(int port) {
        try {
            //创建选择器
            selector = Selector.open();
            //打开监听通道
            serverChannel = ServerSocketChannel.open();
            // false 开启非阻塞模式（true为阻塞模式）
            serverChannel.configureBlocking(false);
            //绑定端口
            serverChannel.socket().bind(new InetSocketAddress(port), 1024);
            //监听客户端连接请求
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            //标记服务器已开启
            started = true;
            System.out.println("服务器已启动，端口号： " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
    }
    
    public void stop() {
        started = false;
    }
    
    @Override
    public void run() {
        //循环遍历selector
        while (started) {
            try {
                //无论是否有读写时间发生，selector每隔1s被唤醒一次
                selector.select(1000);
                //阻塞，只有当至少一个注册事件发生时才会继续
//                selector.select();
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        
        if (selector != null) {
            try {
                selector.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            //处理新接入的请求消息
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                //通过ServerSocketChannel的accept创建SocketChannel实例
                //完成该操作意味着完成TCP三次握手，TCP物理链路正式建立
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                //注册为读
                sc.register(selector, SelectionKey.OP_READ);
            }
            
            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                //创建ByteBuffer，并开辟一个1M的缓冲区
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                //读取请求码流
                int readBytes = sc.read(buffer);
                //读取到字节，对直接进行编解码
                if (readBytes > 0) {
                    //切换为读模式
                    buffer.flip();
                    //根据缓冲区可读字节创建字节数组
                    byte[] bytes = new byte[buffer.remaining()];
                    //将缓存区可读直接数组复制到新建数组
                    buffer.get(bytes);
                    String expression = new String(bytes, "UTF-8");
                    System.out.println("服务器收到消息：" + expression);
                    //处理数据
                    String result = null;
                    try {
                        result = Calculator.cal(expression).toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                        result = "计算错误：" + e.getMessage();
                    }
                    //发送应答消息
                    doWrite(sc, result);
                } else if (readBytes < 0) {
                    key.cancel();
                    sc.close();
                }
                
            }
        }
    }

    //异步发送应答信息
    private void doWrite(SocketChannel channel, String response) throws IOException {
        //将信息编码为字节数组
        byte[] bytes = response.getBytes();
        //根据数组容量创建ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        channel.write(writeBuffer);
    }
     
}
