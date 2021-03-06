package com.pengshixin.mqlecture.sayHi.serializer;

import com.pengshixin.mqlecture.sayHi.TalkMessage;
import com.pengshixin.mqlecture.sayHi.TalkMessageSerializable;

import io.netty.buffer.ByteBuf;

/**
 * @author shixinpeng
 * @description 使用json格式进行编解码
 * @ClassName: TalkMessageJsonSerializer
 * @date 2020/8/30
 *
 */
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
