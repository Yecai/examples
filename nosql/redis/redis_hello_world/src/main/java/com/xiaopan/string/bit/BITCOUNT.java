package com.xiaopan.string.bit;

import redis.clients.jedis.Jedis;

///被设置为 1 的位的数量
public class BITCOUNT {
	static Jedis jedis = new Jedis("localhost");
	
	public static void main(String[] args) {
		String key = "append";
		
		jedis.del(key);
		jedis.set(key, "foobar");
		
		System.out.println("bitcount(key):" + jedis.bitcount(key));
		System.out.println("bitcount(key):" + jedis.bitcount(key, 0, 0));
		System.out.println("bitcount(key):" + jedis.bitcount(key, 0, 1));
		System.out.println("bitcount(key):" + jedis.bitcount(key, 0, 2));
		System.out.println("bitcount(key):" + jedis.bitcount(key, 1, 1));
	
	}
}
