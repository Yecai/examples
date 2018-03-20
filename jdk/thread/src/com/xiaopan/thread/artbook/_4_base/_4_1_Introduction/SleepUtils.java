package com.xiaopan.thread.artbook._4_base._4_1_Introduction;

import java.util.concurrent.TimeUnit;

public class SleepUtils {
	public static final void second(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			
		}
	}
}
