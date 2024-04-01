package chatroom.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class chatServer implements Server {


    @Override
    public void start() {

        log.info("开始初始化聊天室服务器");
        NioEventLoopGroup boss = null;
        NioEventLoopGroup worker = null;
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            boss = new NioEventLoopGroup();
            worker = new NioEventLoopGroup();
            serverBootstrap
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new LoggingHandler());
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
                            pipeline.addLast(new LengthFieldPrepender(4));
                            pipeline.addLast(new ObjectEncoder());
                            pipeline.addLast(new ObjectDecoder(new ClassResolver() {
                                @Override
                                public Class<?> resolve(String className) throws ClassNotFoundException {
                                    try {
//                                        log.info("接受请求：" + className);
                                        return Class.forName((className));
                                    } catch (ClassNotFoundException e) {
                                        log.warn("RPC请求类" + className + "不存在，将返回默认请求类");
                                        e.printStackTrace();
                                        return Class.forName("chatroom.common.Request");
                                    }
                                }
                            }
                            ));
                            pipeline.addLast(new chatRoomServerHandler());
                        }


                    });
            ChannelFuture sync = serverBootstrap.bind(8899).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (boss != null)
                boss.shutdownGracefully();
            if (worker != null)
                worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        chatServer chatServer = new chatServer();
        chatServer.start();
    }
}
