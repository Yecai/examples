package com.xiaopan.lambda._3_stream_example;

import java.util.stream.Stream;

public class _12_interate {
	public static void main(String[] args) {
		//iterate 跟 reduce 操作很像，接受一个种子值，和一个 UnaryOperator（例如 f）。然后种子值成为 Stream 的第一个元素，f(seed) 为第二个，f(f(seed)) 第三个，以此类推。
		//与 Stream.generate 相仿，在 iterate 时候管道必须有 limit 这样的操作来限制 Stream 大小
		//生成一个等差数列
		Stream.iterate(0, n -> n + 3).limit(10).forEach(x -> System.out.print(x + " "));
		
	}
}
