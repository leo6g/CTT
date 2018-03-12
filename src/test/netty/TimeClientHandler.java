package test.netty;


import java.net.SocketAddress;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class TimeClientHandler extends ChannelOutboundHandlerAdapter {
	private static TimeClientHandler t = null;
	private TimeClientHandler() {};
	private ChannelHandlerContext ctx;
	public static TimeClientHandler getInstance() {
		if(t==null) {
			t= new TimeClientHandler();
		}
		return t;
	}
    
    @Override
	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress,
			ChannelPromise promise) throws Exception {
    	System.out.println("connect");
    	this.ctx=ctx;
    	  for(int i = 0;i<10;i++) {
          	try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
          	System.out.println("d");
      		TimeClientHandler.getInstance().sentMsg(i+"");
      	}
	}
    
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		super.write(ctx, msg, promise);
	}

	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
	public void sentMsg(String ss) {
		this.ctx.writeAndFlush(ss);
	}
}