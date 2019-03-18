#JMS 示例

这里例子演示了spring integration对java消息服务（JMS）的支持，包括以下几个方面：

* JMS消息驱动通道适配器
* JMS输入通道
* JMS输出通道

同时，使用了以下几个组件

1. 轮询器 Poller
2. Stdout通道适配器（来自流支持模块）
3. Stdin通道适配器（来自流支持模块）
4. 聚合器 Aggregator

同时还展示了使用spring profiles配置来修改测试用例配置的例子

Stdout和Stdin通道允许将允许你通过控制台与JMS交互。它使用一个嵌入式的ActiveMQ代理。

运行程序后，系统会提示你运行以下三个演示程序之一
1. GatewayDemo
2. ChannelAdapterDemo
3. AggregationDemo

