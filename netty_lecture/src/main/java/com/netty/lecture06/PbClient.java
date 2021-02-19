package com.netty.lecture06;


import com.netty.lecture03.MyChatClientHandler;
import com.netty.protobuf.DataInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class PbClient  {

    public static File file = new File("/Users/shixinpeng/tearoom/netty_lecture/src/main/java/com/netty/lecture06/student.code");
    public static File batchFile = new File("/Users/shixinpeng/tearoom/netty_lecture/src/main/java/com/netty/lecture06/batch-student.code");

    public static void main(String[] args) throws IOException, InterruptedException {

//        batchSaveInfos();
        batchReadInfos();
    }


    public static void startClient() throws IOException, InterruptedException{
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    // 添加对应的handler
                    pipeline.addLast(new ProtobufVarint32FrameDecoder());
                    pipeline.addLast(new ProtobufDecoder(DataInfo.Student.getDefaultInstance()));
                    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                    pipeline.addLast(new ProtobufEncoder());
                    pipeline.addLast(new SimpleChannelInboundHandler<DataInfo.Student>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {

                        }

                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            System.out.println("客户端已连接");

                            DataInfo.Student student = DataInfo.Student.newBuilder().setName("小红").setPhone("123456789").setAge(23).build();
                            ctx.writeAndFlush(student);
                        }
                    });
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8999).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void pbEncode() throws IOException {
        DataInfo.Student student = DataInfo.Student.newBuilder().setName("小红").setPhone("123456789").setAge(23).build();

        System.out.println("student"+student.toString());

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] bytes = student.toByteArray();
        System.out.println("bytes长度="+bytes.length);
        fileOutputStream.write(bytes);
        fileOutputStream.close();


    }

    public static void pbDecode() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(file);
        int available = fileInputStream.available();
        byte[] fileBytes = new byte[available];
        fileInputStream.read(fileBytes,0,available);
        DataInfo.Student student2 = DataInfo.Student.parseFrom(fileBytes);

        System.out.println("student2" + student2.toString());
        fileInputStream.close();

    }


    public static void batchSaveInfos() throws IOException{

        // 批量写入100个对象信息的pb二进制到文件中，在反向读出来
        FileOutputStream fileOutputStream = new FileOutputStream(batchFile);

        int infoCount = 100;
        Random random = new Random();
        long phone;
        DataInfo.Student student;
        for (int i = 0; i < infoCount; i++) {
            phone = random.nextLong();
            student = DataInfo.Student.newBuilder().setName("小红").setPhone(String.valueOf(phone)).setAge(i).build();

            System.out.println("student"+student.toString());

            student.writeDelimitedTo(fileOutputStream);
            byte[] bytes = student.toByteArray();
            System.out.println("bytes长度="+bytes.length);

        }
        fileOutputStream.close();

    }

    /**
     *   批量读取100个对象信息的pb二进制文件
     * @throws IOException
     */
    public static void batchReadInfos() throws IOException{
        FileInputStream fileInputStream = new FileInputStream(batchFile);

        int available = fileInputStream.available();
        while (available>0){
            DataInfo.Student student = DataInfo.Student.parseDelimitedFrom(fileInputStream);

            System.out.println(student.toString());
            available = fileInputStream.available();
        }

        fileInputStream.close();
    }
}
