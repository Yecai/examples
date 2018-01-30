package d_time_server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {
	
	public static void main(String[] args) throws Exception {
//		String host = args[0];
//		int port = Integer.parseInt(args[1]);
		String host = "localhost";
		int port = 8080;
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			Bootstrap b = new Bootstrap(); //client”√Bootstrap
			b.group(workerGroup);
			b.channel(NioSocketChannel.class); //client”√NioSocketChannel
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
//					ch.pipeline().addLast(new TimeClientHandler());
//					ch.pipeline().addLast(new TimeClientWithBufferHandler());
					ch.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());
				}
			});
			
			//start the client
			ChannelFuture f = b.connect(host, port).sync();
			
			//Wait until the connection is closed
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
}
