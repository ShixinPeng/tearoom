## netty

### demo模块

* lecture01 : 简单的http解析服务
* lecture02 : 服务端客户端模拟简单的应答服务
* lecture03 : 简单的模拟聊天服务
* lecture04 : 心跳监控服务
* lecture05 : 使用netty搭建简单的websocket服务
* lecture05 : netty使用protocolBuf进行对象编解码

### Protocol Buffers的使用

```
protoc -I=$SRC_DIR --java_out=$DST_DIR $SRC_DIR/addressbook.proto

```