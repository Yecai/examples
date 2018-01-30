package com.xiaopan.aio.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import com.xiaopan.bio.Calculator;

public class ReadHandler implements CompletionHandler<Integer, ByteBuffer>{
    //用于读取半包消息和发送应答
    private AsynchronousSocketChannel channel;
    
    public ReadHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }
    
    //读到消息后的处理
    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] message = new byte[attachment.remaining()];
        attachment.get(message);
        
        try {
            String expression = new String(message, "UTF-8");
            System.out.println("服务器收到消息：" + expression);
            String calrResult = null;
            try {
                calrResult = Calculator.cal(expression).toString();
            } catch (Exception e) {
                calrResult = "计算错误：" + e.getMessage();
            }
            //向客户端发送消息
            doWrite(calrResult);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void doWrite(String result) {
        byte[] bytes = result.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        //异步写数据，参数和前面的read一样
        channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {

            @Override
            public void completed(Integer result, ByteBuffer buffer) {
                if (buffer.hasRemaining()) {
                    channel.write(buffer, buffer, this);
                } else {
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    //异步读，第三个参数为接收消息回调的 业务handler
                    channel.read(readBuffer, readBuffer, new ReadHandler(channel));
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
