package chatroom.client;


import chatroom.common.serialization.requestBuilder;
import chatroom.common.serialization.Request;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Builder
@Data
public class chatClient {
    private final String host;
    private final int port;

    public void run() {
        log.info("正在初始化客户端连接");
        EventLoopGroup group = null;

        try {
            group = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new ProtobufVarint32FrameDecoder());
                    pipeline.addLast(new ProtobufDecoder(Request.getDefaultInstance()));
                    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                    pipeline.addLast(new ProtobufEncoder());
                    pipeline.addLast(new chatRoomClientHandler());
                }
            });
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            log.info("客户端初始化完毕");
            while (true) {
                Request request = requestBuilder.newRequestInstance();
                channel.writeAndFlush(request);
//                channel.writeAndFlush(("随机信息" + Math.random()));
                Thread.sleep(5000);
                log.info("已发送信息:" + request.getMessage().getMsg());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (group != null) group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {

//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                chatClient chatClient1 = new chatClient("127.0.0.1", 8899);
//                chatClient1.run();
//            }
//        };
//        thread.start();

        new Thread(() -> {
            chatClient chatClient1 = new chatClient("127.0.0.1", 8899);
            chatClient1.run();
        })
                .start();
    }
}
