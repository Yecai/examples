package com.xiaopan.pressure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tests {
	public static void main(String[] args) {
		List<Integer> olist = Arrays.asList((new Integer[]{1,2,3,4}));
		List<String> nlist = listTolist(olist);
		for (String n : nlist) {
			System.out.println(n);
		}
	}
	

	
	private static <O, N> List<N> listTolist(List<O> oldList) {
		List<N> newList = new ArrayList<N>(oldList.size());
		for (O o : oldList) {
			N n = (N) o;
			newList.add(n);
		}
		return newList;
	}
}
