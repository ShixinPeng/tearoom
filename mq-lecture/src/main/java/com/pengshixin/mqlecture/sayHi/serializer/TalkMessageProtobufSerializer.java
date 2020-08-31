package com.pengshixin.mqlecture.sayHi.serializer;

import com.pengshixin.mqlecture.sayHi.TalkMessage;
import com.pengshixin.mqlecture.sayHi.TalkMessageSerializable;

import io.netty.buffer.ByteBuf;

/**
 * @author shixinpeng
 * @description protobuf编解码器
 * @ClassName: TalkMessageProtobufSerializer
 * @date 2020/8/30
 *
 */
public class TalkMessageProtobufSerializer implements TalkMessageSerializable {

    @Override
    public ByteBuf encode(TalkMessage message) {
        return null;
    }

    @Override
    public TalkMessage decode(ByteBuf buf) {
        return null;
    }
}
