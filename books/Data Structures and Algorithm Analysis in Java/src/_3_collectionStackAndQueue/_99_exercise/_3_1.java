package _3_collectionStackAndQueue._99_exercise;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 给定表L和表P，都包含以升序排列的数组
 * 操作printLots(L,P)将打印L中那些由P所指定的位置上的元素
 * 例如，如果P=1，3，4，6，那么，L中位于第1、第3、第4和第6个位置上的元素被打印出来
 * 写出过程printLots(L,P)
 * 只可使用public型的Collection API容器操作。
 * 该过程的运行时间是多少？
 */
public class _3_1 {
	public static void main(String[] args) {
		Collection<String> l = new ArrayList<>();
		Collection<Integer> p = new ArrayList<>();
		
		for (int i = 0; i< 100; i++) {
			l.add("l" + i);
		}
		p.add(1);
		p.add(3);
		p.add(4);
		p.add(6);
		
		printLots(l, p);
		System.out.println("假设p大小为N，运行时间为O(N)");
	}

	private static void printLots(Collection<String> l, Collection<Integer> p) {
		String[] lArr = new String[l.size()];
		l.toArray(lArr);
		
		for (Integer pi : p) {
			if (pi < l.size()) {
				System.out.println(lArr[pi]);
			}
		}
	}
}
