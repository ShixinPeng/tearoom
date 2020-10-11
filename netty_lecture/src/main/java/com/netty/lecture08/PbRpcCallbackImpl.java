package com.netty.lecture08;

import com.google.protobuf.Message;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcUtil;

public class PbRpcCallbackImpl implements RpcCallback<Message> {
    @Override
    public void run(Message parameter) {

        System.out.println("Callback:收到返回");
    }
}
