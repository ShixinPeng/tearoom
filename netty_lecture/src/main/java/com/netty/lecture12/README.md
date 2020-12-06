# 关于Doug Lea《Scalable IO in Java》文章解析
## 内容简介
```
* Scalable network services
* Event-driven processing
* Reactor pattern
    Basic version 
    Multithreaded versions 
    Other variants
* Walkthrough of java.nio nonblocking IO APIs

```
* 伸缩式的网络服务
* 事件驱动处理
* Reactor反应器模式（基础版本、多线程版本、其他变种）
* 浏览java.nio 包内的非阻塞IO api

## 网络服务
* web 服务，分布式对象，等
* 通用的基础结构
    * 读请求
    * 解码请求
    * 处理服务
    * 编码回复
    * 发送回复
* 不同点和开销在每一步中
    * XML解析，文件传输，web页面生成，计算服务
    
## 传统服务设计结构
* 每一事件在自己所属的线程中处理

## 传统ServerSocket 循环
每个连接在配置的线程中运行

## 可伸缩的指标
* 持续增长负载下优雅降级
* 资源消耗平缓递增
* 可用性以及性能指标
    * 低延迟
    * 高吞吐
    * 服务质量可调节
* 类似分治法的弹性架构目标

## 分治法
* 拆分处理为小的任务；每个任务无阻塞的完成
* 执行每个可执行的任务；经常采用触发器的形式
* 运行被javaNio支持
    * 无阻塞的读写
    * 分发，任务与被感知到的IO时间连接
* 多种扩展可能性
    * 面向事件驱动的体系
    
## 事件驱动
* 比其他选择的区分点
    * 更少的资源依赖：不用经常分配线程给客户端
    * 更少的开销：更少的上下问切换，更少的锁操作
    * 除了事件分发会更慢：在非常多的活动与事件绑定情况下
* 编程难点
    * 需要把处理过程必须分解为简单的无阻塞行为
        * 比GUI事件驱动粒度更小
        * 不能消除所有阻塞，GC/页缓存等
    * 必须维持服务的逻辑状态 
    
## 背景介绍:AWT中的事件处理
* IO事件驱动经常使用相似的思路，除了一些设计区别

## Reactor Pattern
### Reactor
通过分发给适当的处理来响应IO事件；对比AWT线程
### Handlers
提供无阻塞操作；对比AWT中的动作监听器
### 管理与事件绑定处理器 
对比：AWT中的#addActionListener()

## 基础的Reactor设计，单线程模式
java Nio的支持
### Channels
支持无阻塞的读取连接文件，socket
### Buffers
像数组一样可以被直接读写Channels里
### Selectors
告诉那个Channels有IO事件
### SelectionKeys
负责IO事件状态和绑定

## Reactor  单线程实现
#### 第一步：创建ServerSocket,等待连接
#### 第二步：事件的分发循环
#### 第三步：接受连接
#### 第四步：处理器设定
#### 第五步：请求被处理
    
### 不同状态处理器
通过attachment 绑定合适的处理器

## Reactor  多线程实现
* 有策略的增加线程为了弹性伸缩；主要适合多处理器
* 工作线程
    * Reactors应该快速触发处理器（处理器处理会比Reactor慢）
    * 拆解非IO处理过程到其他线程中
* 多Reactor Threads
    * Reactor线程可以充分用于IO处理
    * 多Reactor之间做负载（合理利用CPU和IO速率）
## Worker Threads
* 拆解非IO处理已提高Reactor线程的处理速度
* Reactor线程，比POSA2 Proactor 设计的更小
* 计算绑定处理比转换事件驱动的形式更简单【个人理解：这里表达的是如果使用Worker Threads，方便了handler的实现，如果都通过事件驱动，那么handler的实现必须依照Reactor中的要求。类似于我业务的实现要受网络连接上的处理规则限制】
    * 应该保持非阻塞计算（足够的处理消耗大于额外的开销消耗）
* 对于IO的持续处理比较困难，最好是一开始就读取到所有的输入到一个缓冲区内
* 使用线程池可以协调和控制，通常需要更小的线程服务更多设备

## Worker Thread Pools


