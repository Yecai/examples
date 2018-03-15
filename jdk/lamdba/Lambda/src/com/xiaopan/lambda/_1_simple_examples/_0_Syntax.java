package com.xiaopan.lambda._1_simple_examples;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Package: com.xiaopan.lambda._1_simple_examples
 * @ClassName: _0_Syntax
 * @author: panhuaxiong
 * @date: 2017年8月17日 上午10:14:56
 * @Description: lambda语法
 */
public class _0_Syntax {

	/**
	 * 参考：http://colobu.com/2014/09/05/java-lambdas-hacking/
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//lamdba表达式
		BiFunction<Integer, Integer, Integer> bf1 = (Integer x, Integer y) -> {return x + y;};
		//如果body只有一个表达式，可以省略body的大括号和return
		BiFunction<Integer, Integer, Integer> bf2 = (Integer x, Integer y) -> x + y;
		//参数可以声明，也可以根据类型推断而省略
		BiFunction<Integer, Integer, Integer> bf3 = (x, y) -> x + y;
		//但是不能部分省略
//		BiFunction<Integer, Integer, Integer> bf4 = (Integer x, y) -> x + y;
		//单个参数可以省略括号
		Function<Integer, Integer> f1 = (x) -> x + 1;
		Function<Integer, Integer> f2 = x -> x + 1;
		//但是省略括号后不能加上类型声明
//		Function<Integer, Integer> f3 = Integer x -> x + 1;
		//如果没有参数，括号是必须的
		Supplier<Integer> s1 = () -> 2;
		Runnable r1 = () -> {System.out.println("123");};
	}
}
