package com.xiaopan.lambda._3_stream_example;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class _11_generate {
	public static void main(String[] args) {
		//�Լ�������
		//ͨ��ʵ�� Supplier �ӿڣ�������Լ��������������ɡ���������ͨ������������������� Stream��������Ҫǰ��Ԫ�ؼ�ά����ĳ��״̬��Ϣ�� Stream���� Supplier ʵ�����ݸ� Stream.generate() ���ɵ� Stream��Ĭ���Ǵ��У���� parallel ���ԣ�������ģ���� ordered ���ԣ��������������޵ģ��ڹܵ��У��������� limit ֮��Ĳ������� Stream ��С��
		//���� 10 ���������
		Random seed = new Random();
		Supplier<Integer> random = seed::nextInt;
		Stream.generate(random).limit(10).forEach(System.out::println);
		//������ʽ
		IntStream.generate(() -> (int) (System.nanoTime() % 100)).limit(10).forEach(System.out::println);
		
		//Stream.generate() �������Լ�ʵ�ֵ� Supplier.�����ڹ��캣���������ݵ�ʱ����ĳ���Զ��Ĺ����ÿһ��������ֵ���������ݹ�ʽ���� Stream ��ÿ��Ԫ��ֵ����Щ����ά��״̬��Ϣ�����Ρ�
		//��ʵ�� Supplier
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
