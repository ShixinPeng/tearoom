
> 传统的java io 面向 stream（流）
>
> Nio的核心有三个：Buffer、Channel、Selector；面向块（block）或缓存区（buffer）编程。

### 关于本模块的练习内容
* NioTest01 通过基础数据的读写操作了解Buffer的关键属性和提供的方法作用
* NioTest02/NioTest03 通过文件的操作，大致了解Channel在IO中的作用
* NioTest04 ByteBuffer 存储多种类型的数据
* 通过两种方式模拟服务端与客户端的Socket通信,认识Selector带来的变化

### Java Nio的核心元素

#### Buffer
buffer是一个抽象类，代表着基础类型数据的容器（除Boolean之外）

#### 可以延伸的方向
java 传统io操作和Think in java中关于java的讲解