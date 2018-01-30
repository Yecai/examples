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
            //����ѡ����
            selector = Selector.open();
            //�򿪼���ͨ��
            serverChannel = ServerSocketChannel.open();
            // false ����������ģʽ��trueΪ����ģʽ��
            serverChannel.configureBlocking(false);
            //�󶨶˿�
            serverChannel.socket().bind(new InetSocketAddress(port), 1024);
            //�����ͻ�����������
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            //��Ƿ������ѿ���
            started = true;
            System.out.println("���������������˿ںţ� " + port);
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
        //ѭ������selector
        while (started) {
            try {
                //�����Ƿ��ж�дʱ�䷢����selectorÿ��1s������һ��
                selector.select(1000);
                //������ֻ�е�����һ��ע���¼�����ʱ�Ż����
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
            //�����½����������Ϣ
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                //ͨ��ServerSocketChannel��accept����SocketChannelʵ��
                //��ɸò�����ζ�����TCP�������֣�TCP������·��ʽ����
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                //ע��Ϊ��
                sc.register(selector, SelectionKey.OP_READ);
            }
            
            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                //����ByteBuffer��������һ��1M�Ļ�����
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                //��ȡ��������
                int readBytes = sc.read(buffer);
                //��ȡ���ֽڣ���ֱ�ӽ��б����
                if (readBytes > 0) {
                    //�л�Ϊ��ģʽ
                    buffer.flip();
                    //���ݻ������ɶ��ֽڴ����ֽ�����
                    byte[] bytes = new byte[buffer.remaining()];
                    //���������ɶ�ֱ�����鸴�Ƶ��½�����
                    buffer.get(bytes);
                    String expression = new String(bytes, "UTF-8");
                    System.out.println("�������յ���Ϣ��" + expression);
                    //��������
                    String result = null;
                    try {
                        result = Calculator.cal(expression).toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                        result = "�������" + e.getMessage();
                    }
                    //����Ӧ����Ϣ
                    doWrite(sc, result);
                } else if (readBytes < 0) {
                    key.cancel();
                    sc.close();
                }
                
            }
        }
    }

    //�첽����Ӧ����Ϣ
    private void doWrite(SocketChannel channel, String response) throws IOException {
        //����Ϣ����Ϊ�ֽ�����
        byte[] bytes = response.getBytes();
        //����������������ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        channel.write(writeBuffer);
    }
     
}
