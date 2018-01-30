package d_time_server;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ����������ڸ߲�������»ᱨ��
 * ������ܵ���msg����Ϣ����һ��UnsignedInt�ĳ��ȣ�4byte�����ᱨIndexOutOfBoundsException
 * �������һ���ô��������Ĵ����������TimeClientWithBufferHandler
 * ���������������TimeDecoderԤ����
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf m = (ByteBuf) msg;
		try {
			long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
			System.out.println(new Date(currentTimeMillis));
			ctx.close();
		} finally {
			m.release();
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
