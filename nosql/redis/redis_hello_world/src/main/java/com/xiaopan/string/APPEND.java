package com.xiaopan.string;

import redis.clients.jedis.Jedis;

public class APPEND {
	static Jedis jedis = new Jedis("localhost");
	
	public static void main(String[] args) {
		String key = "append";
		
		jedis.del(key);
		System.out.println("exists:" + jedis.exists(key));
		long length = jedis.append(key, "hello");
		System.out.println("value:" + jedis.get(key) + "   length:" + length);
		length = jedis.append(key, " world");
		System.out.println("value:" + jedis.get(key) + "   "
				+ "length:" + length);
	
	}
}
