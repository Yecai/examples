package com.xiaopan.lambda._3_stream_example;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class _11_generate {
	public static void main(String[] args) {
		//自己生成流
		//通过实现 Supplier 接口，你可以自己来控制流的生成。这种情形通常用于随机数、常量的 Stream，或者需要前后元素间维持着某种状态信息的 Stream。把 Supplier 实例传递给 Stream.generate() 生成的 Stream，默认是串行（相对 parallel 而言）但无序的（相对 ordered 而言）。由于它是无限的，在管道中，必须利用 limit 之类的操作限制 Stream 大小。
		//生成 10 个随机整数
		Random seed = new Random();
		Supplier<Integer> random = seed::nextInt;
		Stream.generate(random).limit(10).forEach(System.out::println);
		//其他方式
		IntStream.generate(() -> (int) (System.nanoTime() % 100)).limit(10).forEach(System.out::println);
		
		//Stream.generate() 还接受自己实现的 Supplier.例如在构造海量测试数据的时候，用某种自动的规则给每一个变量赋值；或者依据公式计算 Stream 的每个元素值。这些都是维持状态信息的情形。
		//自实现 Supplier
		Stream.generate(new PersonSupplier())
			.limit(10)
			.forEach(p -> System.out.println(p.getName() + "," + p.getAge()));
	}


	private static class PersonSupplier implements Supplier<Person> {
		private int index = 0;
		private Random random = new Random();
		@Override
		public Person get() {
			return new Person(index++, "StormTestUser" + index, random.nextInt(100));
		}
		
	}
}
