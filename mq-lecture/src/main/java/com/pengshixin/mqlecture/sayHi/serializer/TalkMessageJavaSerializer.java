package com.pengshixin.mqlecture.sayHi.serializer;

import com.pengshixin.mqlecture.sayHi.TalkMessage;
import com.pengshixin.mqlecture.sayHi.TalkMessageEnum;
import com.pengshixin.mqlecture.sayHi.TalkMessageSerializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class TalkMessageJavaSerializer implements TalkMessageSerializable {
    @Override
    public ByteBuf encode(TalkMessage message) {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(output);
            outputStream.writeObject(message);
            return Unpooled.copiedBuffer(output.toByteArray());
        }catch (Exception e){
            throw new RuntimeException("编码失败 e:"+e);
        }

    }

    @Override
    public TalkMessage decode(ByteBuf buf) {

        try {
            int bytesCount = buf.readableBytes();
            byte[] bytes = new byte[bytesCount];
            buf.readBytes(bytes);
            InputStream input = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(input);

            return  (TalkMessage)objectInputStream.readObject();
        }catch (Exception e){
            throw new RuntimeException("解码失败 e:"+e);
        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TalkMessage message = TalkMessageEnum.Q1.getMessage();
        System.out.println("开始编码...");
        System.out.println("编码对象："+message.toString());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(output);
        outputStream.writeObject(message);
        byte[] bytes = output.toByteArray();

        System.out.println("TalkMessageJavaSerializer编码bytes体积="+bytes.length);
        System.out.println("开始解码...");

        InputStream input = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(input);

        TalkMessage  inputMessage = (TalkMessage)objectInputStream.readObject();

        System.out.println("解码后对象：" + inputMessage.toString());

    }
}
