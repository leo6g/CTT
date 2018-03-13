package test.netty;
import java.io.UnsupportedEncodingException;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * Handles a server-side channel.
 */
public class DiscardServerDecodeHandler extends ByteToMessageDecoder { // (1)
	
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
//    	ByteBuf in = (ByteBuf) msg;
//    	System.out.println("channelRead");
//        try {
//        	byte[] b = new byte[in.readableBytes()];
//        	int index = 0;
//            while (in.isReadable()) { // (1)
//            	b[index] = in.readByte();
//            	index++;
//            }
//           String s =  new String(b,"utf-8");
//           System.out.println(s);
//           ctx.fireChannelRead(s);
//           ctx.fireChannelRead(s);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} finally {
//            ReferenceCountUtil.release(msg); // (2)
//        }
//    }
	 @Override
	    public void channelActive(final ChannelHandlerContext ctx) { // (1)
		 System.out.println("channelActive");
//	    	System.out.println("channelActive");
//	        final ByteBuf time = ctx.alloc().buffer(4); // (2)
//	        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
//	        
//	        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
//	        f.addListener(new ChannelFutureListener() {
//	            @Override
//	            public void operationComplete(ChannelFuture future) {
//	                assert f == future;
//	                ctx.close();
//	            }
//	        }); // (4)
	    }
	 @Override
	    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) { // (2)
		 System.out.println("read");
	        if (in.readableBytes() < 1) {
	            return; // (3)
	        }
	    	byte[] b = new byte[in.readableBytes()];
        	int index = 0;
            while (in.isReadable()) { // (1)
            	b[index] = in.readByte();
            	index++;
            }
           try {
			String s =  new String(b,"utf-8");
			out.add(s); // (4)
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    }
}
