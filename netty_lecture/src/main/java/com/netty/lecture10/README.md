
> 传统的java io 面向 stream（流）
>
> Nio的核心有三个：Buffer、Channel、Selector；面向块（block）或缓存区（buffer）编程。

### 关于本模块的练习内容
* NioTest01 通过基础数据的读写操作了解Buffer的关键属性和提供的方法作用
* NioTest02/NioTest03 通过文件的操作，大致了解Channel在IO中的作用
* NioTest04 ByteBuffer 存储多种类型的数据
* NioTest05 ByteBuffer的实际使用类和只读ByteBuffer
* NioTest06 通过Scattering 和Gathering 和端口监听数据，进行模拟填充完整的数据加载 
* 通过OioSocketServer和NioSocketServer模拟服务端与多客户端的Socket通信,认识Selector带来的变化


### Java Nio的核心元素

#### Buffer 缓冲区
buffer是一个抽象类，代表着基础类型数据的容器（除Boolean之外）

#### channel 通道

#### Selector 选择器
* 可以利用SelectableChannel 多路复用的对象
* 可以通过open,openSelector 创建
* 一个选择器维护三个选项集合（keysSet、selected-key、cancelled-key）
* SelectableChannel 为了配合Selector，SelectableChannel需要注册到一个Selector上面。注册后，会返回一个SelectionKey，表示被Selector管理的channel对象，SelectableChannel的取消注册deregistered,意味着释放所有被Selector创建的资源
* SelectionKey 作为被注册到Selector上的channel的凭证，在被取消或者channel关闭或者Selector关闭前，它都是有效的。当取消SelectionKey 时，该凭证不会立即被销毁，而是被移动到Selector中的cancelled-key集合中，等待下一次选择器操作才会被真正的处理。SelectionKey中包含两个可操作集合[interest set ] 会被下一次选择器检测的操作 [ready set] 被检测器已经识别到的操作；SelectionKey支持的操作选项来源于SelectableChannel#validOps() 的结果。并且提供attachment。用于支持实现channel更高等级的协议（这个就是注册时可以选择传入的第三个参数，相当于可以扩展实现）

#### 可以延伸的方向
java 传统io操作和Think in java中关于java的讲解

mac使用命令行进行网络测试
```
nc localhost $port

```
