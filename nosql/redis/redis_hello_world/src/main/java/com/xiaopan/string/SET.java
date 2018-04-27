package com.xiaopan.string;

import redis.clients.jedis.Jedis;

public class SET {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		jedis.set("set", "bar");
		String bar = jedis.get("set");
		System.out.println(bar);
	}

}
