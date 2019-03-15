package d_time_server;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * 这个处理器在高并发情况下会报错
 * 如果接受到的msg里消息不满一个UnsignedInt的长度（4byte），会报IndexOutOfBoundsException
 * 解决方法一：用带缓冲区的处理器解决：TimeClientWithBufferHandler
 * 解决方法二：增加TimeDecoder预解码
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf m = (ByteBuf) msg;
		try {
			long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
			System.out.println(new Date(currentTimeMillis));
		} finally {
			m.release();
		}

		String rmsg = "hello world client";
		ByteBuf buf = ctx.alloc().buffer(rmsg.length());
		buf.writeBytes(rmsg.getBytes());
		ChannelFuture future = ctx.channel().writeAndFlush(buf);

		future.addListener(new GenericFutureListener<Future<? super Void>>() {
			@Override
			public void operationComplete(Future<? super Void> future) throws Exception {
				ctx.close();
			}
		});

	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
