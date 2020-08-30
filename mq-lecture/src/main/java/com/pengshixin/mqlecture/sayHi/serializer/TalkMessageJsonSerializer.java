package com.pengshixin.mqlecture.sayHi.serializer;

import com.pengshixin.mqlecture.sayHi.TalkMessage;
import com.pengshixin.mqlecture.sayHi.TalkMessageSerializable;

import io.netty.buffer.ByteBuf;

public class TalkMessageJsonSerializer implements TalkMessageSerializable {

    @Override
    public ByteBuf encode(TalkMessage message) {
        // 对象转json串

        // json串转bytes

        return null;
    }

    @Override
    public TalkMessage decode(ByteBuf buf) {
        // Bytes 转json串

        // json串转对象
        return null;
    }
}
