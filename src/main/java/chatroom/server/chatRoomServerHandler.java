package chatroom.server;

import chatroom.common.Message;
import chatroom.common.Request;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
public class chatRoomServerHandler extends SimpleChannelInboundHandler<Request> {

    private static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request msg) throws Exception {
        Channel channel = ctx.channel();
        String sentence = "[" + channel.remoteAddress() + "]说:" + msg.getMessage().getMsg();
        Message build = Message.builder().msg(sentence).date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).uid(UUID.randomUUID().toString()).build();
        Request request = Request.builder().message(build).code(200).build();
//        log.info("服务器收到新消息");
        log.info(request.getMessage().getMsg());
        for (Channel cg : channelGroup) {
            if(channel != cg){
                cg.writeAndFlush(request);
            }
        }
    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        log.info("read收到信息" + msg);
//    }

//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
//        Channel channel = ctx.channel();
//        String sentence = "[" + channel.remoteAddress() + "]:" + msg;
//        Message build = Message.builder().msg(sentence).date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).uid(UUID.randomUUID().toString()).build();
//        Request request = Request.builder().message(build).code(200).build();
//        log.info("服务器收到新消息");
//        log.info(sentence);
//        for (Channel cg : channelGroup) {
//            if (channel != cg) {
//                cg.writeAndFlush(request);
//            }
//        }
//    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.add(channel);
        log.info("新用户" + channel.remoteAddress() + "上线了");
//        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        super.handlerRemoved(ctx);
        Channel channel = ctx.channel();
        channelGroup.remove(channel);
        log.info("用户" + channel.remoteAddress() + "下线了");
    }
}
