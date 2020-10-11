package com.netty.lecture08;

import com.google.protobuf.Message;
import com.google.protobuf.RpcCallback;

public class PbRpcCallbackImpl implements RpcCallback {
    @Override
    public void run(Object parameter) {
        System.out.println("Callback:收到返回");
    }
}
