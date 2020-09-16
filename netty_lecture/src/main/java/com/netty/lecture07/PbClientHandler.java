package com.netty.lecture07;

import com.netty.protobuf.Message;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PbClientHandler extends SimpleChannelInboundHandler<Message.OneOfMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message.OneOfMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {


        System.out.println("客户端已连接");


        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // 随机进行消息的发送
                int nextInt = new Random().nextInt(3);

                Message.OneOfMessage  message = null;

                if (nextInt==0){
                  message =  Message.OneOfMessage.newBuilder().setDataType(Message.OneOfMessage.dataType.CAT).setCat(Message.OneOfMessage.Cat.newBuilder().setName("大猫").setAge(3).build()).build();
                }else if (nextInt == 1){
                    message =  Message.OneOfMessage.newBuilder().setDataType(Message.OneOfMessage.dataType.DOG).setDog(Message.OneOfMessage.Dog.newBuilder().setName("小狗").setAge(1).setColour("黑色").build()).build();
                } else {
                    message =  Message.OneOfMessage.newBuilder().setDataType(Message.OneOfMessage.dataType.PANDA).setPanda(Message.OneOfMessage.Panda.newBuilder().setName("小熊猫").setAge(3).setWeight(100).build()).build();
                }

                ctx.writeAndFlush(message);

            }
        }, 2000, 5000);



    }
}
