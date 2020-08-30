package com.pengshixin.mqlecture.sayHi;

import io.netty.buffer.ByteBuf;

/**
 * @author shixinpeng
 * @description 对话对象的序列化接口
 * @ClassName: TalkMessageSerializable
 * @date 2020/8/30
 *
 */
public interface TalkMessageSerializable {

    ByteBuf encode(TalkMessage message);

    TalkMessage decode(ByteBuf buf);
}
