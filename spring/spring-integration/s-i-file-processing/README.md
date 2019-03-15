文件批处理示例
==================

根据spring Integration官方simples写的示例
主要用于学习和后期回顾参考


此示例包含内容
* 并发处理消息流
* 顺序处理消息流

他两的不同在于poller配置成线程池还是单线程

轮询器（Poller）配置在文件输入适配器上，在找到文件后会在本线程将其转为成message。因此文件将会根据他们在目录中的创建顺序来读取并进行处理。

sequentialFileProcessing-config.xml配置：FileProcessor类随机延迟的处理文件，但是不管延迟多久，处理的顺序仍保持不变

如果处理顺序不重要，则可以并发处理文件。只需要给Poller配置task-executor。所以处理顺序取决于task-executor线程池中线程的可用性。
