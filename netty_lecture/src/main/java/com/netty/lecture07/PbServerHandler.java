package com.netty.lecture07;

import com.netty.protobuf.DataInfo;
import com.netty.protobuf.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PbServerHandler extends SimpleChannelInboundHandler<Message.OneOfMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message.OneOfMessage msg) throws Exception {

        Message.OneOfMessage.dataType dataType = msg.getDataType();

        if (dataType.equals(Message.OneOfMessage.dataType.CAT)){
            Message.OneOfMessage.Cat cat = msg.getCat();
            System.out.println("服务端接收 CAT：name="+cat.getName() + " 年龄="+cat.getAge());

        }else if (dataType.equals(Message.OneOfMessage.dataType.DOG)){
            Message.OneOfMessage.Dog dog = msg.getDog();
            System.out.println("服务端接收 DOG：name="+dog.getName() + " 颜色="+dog.getColour());

        }else {
            Message.OneOfMessage.Panda panda = msg.getPanda();
            System.out.println("服务端接收 PANDA：name="+panda.getName() + " 体重="+panda.getWeight());
        }


    }
}
