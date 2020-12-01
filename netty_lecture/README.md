## 涉及内容
* netty的基本组件使用
* Protocol Buffers的编解码以及服务声明



### demo模块

* lecture01 : 简单的http解析服务
* lecture02 : 服务端客户端模拟简单的应答服务
* lecture03 : 简单的模拟聊天服务
* lecture04 : netty心跳监控服务
* lecture05 : 使用netty搭建简单的websocket服务
* lecture06 : netty使用ProtocolBuf进行数据的编码解码传输
* lecture07 : Protocol Buffers 多个实例传输过程的区分
* lecture08 : Protocol Buffers 的service服务定义的本地调用用&RPC调用
* lecture09 : gRPC的使用
* lecture10 : [Java Nio组件的练习](src/main/java/com/netty/lecture10/README.md)
    * 关于buffer的使用
    * 关于channel的使用
    * 关于selector的使用
    * java传统io 进行socket编程
    * Nio进行socket编程
* lecture11 : 传统IO发送文件和Nio使用零拷贝进行文件传输的效率对比
* Reactor模式

### Protocol Buffers的使用

#####1.下载protoc编译器


#####2.进行IDL文件编译

```
protoc -I=$SRC_DIR --java_out=$DST_DIR $SRC_DIR/addressbook.proto

```
#####3.java项目中引入protocol buffers的依赖包


#####4.进行调用使用
