package com.xiaopan.lambda._1_simple_examples;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Package: com.xiaopan.lambda._1_simple_examples
 * @ClassName: _0_Syntax
 * @author: panhuaxiong
 * @date: 2017��8��17�� ����10:14:56
 * @Description: lambda�﷨
 */
public class _0_Syntax {

	/**
	 * �ο���http://colobu.com/2014/09/05/java-lambdas-hacking/
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//lamdba���ʽ
		BiFunction<Integer, Integer, Integer> bf1 = (Integer x, Integer y) -> {return x + y;};
		//���bodyֻ��һ�����ʽ������ʡ��body�Ĵ����ź�return
		BiFunction<Integer, Integer, Integer> bf2 = (Integer x, Integer y) -> x + y;
		//��������������Ҳ���Ը��������ƶ϶�ʡ��
		BiFunction<Integer, Integer, Integer> bf3 = (x, y) -> x + y;
		//���ǲ��ܲ���ʡ��
//		BiFunction<Integer, Integer, Integer> bf4 = (Integer x, y) -> x + y;
		//������������ʡ������
		Function<Integer, Integer> f1 = (x) -> x + 1;
		Function<Integer, Integer> f2 = x -> x + 1;
		//����ʡ�����ź��ܼ�����������
//		Function<Integer, Integer> f3 = Integer x -> x + 1;
		//���û�в����������Ǳ����
		Supplier<Integer> s1 = () -> 2;
		Runnable r1 = () -> {System.out.println("123");};
	}
}
