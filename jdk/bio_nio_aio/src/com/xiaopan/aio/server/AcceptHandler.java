package com.xiaopan.aio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

//��Ϊhandler���տͻ�������
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServerHandler>{

    @Override
    public void completed(AsynchronousSocketChannel channel, AsyncServerHandler serverHandler) {
        //�������������ͻ��˵�����
        Server.clientCount++;
        System.out.println("���ӵĿͻ�������" + Server.clientCount);
        serverHandler.channel.accept(serverHandler, this);
        //����buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //�첽�� ����������Ϊ������Ϣ�ص���ҵ��Handle
        channel.read(buffer, buffer, new ReadHandler(channel));
    }

    @Override
    public void failed(Throwable exc, AsyncServerHandler serverHandle) {
        exc.printStackTrace();
        serverHandle.latch.countDown();
    }
    
}
