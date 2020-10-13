package com.netty.lecture08;

import com.google.protobuf.ByteString;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcChannel;
import com.google.protobuf.RpcController;
import com.google.protobuf.RpcUtil;
import com.google.protobuf.WrappersProto;

import com.netty.protobuf.ProtobufService;

import java.util.Objects;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author shixinpeng
 * @description RPC基础实现，只满足远程接口的调用
 * @ClassName: RpcChannelImpl
 * @date 2020/10/11
 *
 */
@ChannelHandler.Sharable
public class PbRpcChannelImpl extends SimpleChannelInboundHandler<ProtobufService.RpcWrapper> implements RpcChannel {

    /**
     * rpc通信的通道
     */
    private ChannelHandlerContext ctx;
    @Override
    public void callMethod(Descriptors.MethodDescriptor method, RpcController controller, Message request, Message responsePrototype, RpcCallback<Message> done) {
        // 根据方法的描述，通过RpcController，发送request，根据需要响应的信息类型，返回响应信息
        System.out.println("RpcChannel=>callMethod被调用");

        // 向下泛型
        RpcCallback<ProtobufService.HelloResponse> generalizeCallback = RpcUtil.specializeCallback(done);

        // 本地直接执行
        // helloService.search(controller,(ProtobufService.HelloRequest) request,generalizeCallback);

        // 执行远程调用
        if (Objects.isNull(ctx)||!ctx.channel().isActive()){
            throw new  RuntimeException("客户端连接还未建立");
        }
        ProtobufService.RpcWrapper.Builder builder = ProtobufService.RpcWrapper.newBuilder();
        // 描述要使用的service
        builder.setService(method.getService().getName());
        // 描述要使用的service的method
        builder.setMethod(method.getName());
        // 设置入参
        builder.setRequest(ByteString.copyFrom(request.toByteArray()));

        // 使用PB包装的一个方法描述和入参对象
        ProtobufService.RpcWrapper rpcWrapper = builder.build();

        // 使用netty的通道把PB对象送到服务端
        ctx.writeAndFlush(rpcWrapper);
        // 根据方法的描述，通过RpcController，发送request，根据需要响应的信息类型，返回响应信息
        System.out.println("RpcChannel=>通道调用远程方法："+method.getName());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtobufService.RpcWrapper msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端已连接服务器");
        this.ctx = ctx;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        super.channelInactive(ctx);
        System.out.println("channelInactive");
    }
}
