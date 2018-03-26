package md5comparehmac;

import java.util.ArrayList;
import java.util.List;

import hmac.Hmac;
import md5.MD5;

public class Compare2 {
	public static void main(String[] args) {
//		String message = "abcdefighl";
		String message = "asdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasddsafdsafdsfsdafdsfdsafsdscafdsafsdafdsafdsfffdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsdasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsd";
//		for (int i = 0; i < 4; i++) {
//			message = message + message;
//		}
		System.out.println("message大小" + message.length() + "B");
//		System.out.println("message大小" + (message.length() / 1024) + "KB");
		
		long count = 10000;
		long calCount = 1;
		
		List<Long> md5List = new ArrayList<Long>();
		List<Long> hmacMd5List = new ArrayList<>();
		List<Long> hmacSha1List = new ArrayList<>();
		List<Long> hmacSha512List = new ArrayList<>();
		
		//统计1000次的平均值
		for (int j = 0; j < calCount; j++) {
			long start = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				MD5.md5(message);
			}
			long end = System.currentTimeMillis();
			long md5Mills = end - start;
			md5List.add(md5Mills);
			
			start = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				Hmac.hmacsha1(Hmac.HMAC_MD5_ALGORITHM, message);
			}
			end = System.currentTimeMillis();
			long hmacMd5Mills = end - start;
			hmacMd5List.add(hmacMd5Mills);
			
			start = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				Hmac.hmacsha1(Hmac.HMAC_SHA1_ALGORITHM, message);
			}
			end = System.currentTimeMillis();
			long hmacSha1Mills = end - start;
			hmacSha1List.add(hmacSha1Mills);
			
			start = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				Hmac.hmacsha1(Hmac.HMAC_SHA512_ALGORITHM, message);
			}
			end = System.currentTimeMillis();
			long hmacSha512Mills = end - start;
			hmacSha512List.add(hmacSha512Mills);
		}
		
		System.out.println(md5List.stream().mapToDouble(a -> a).average());
		System.out.println(hmacMd5List.stream().mapToDouble(a -> a).average());
		System.out.println(hmacSha1List.stream().mapToDouble(a -> a).average());
		System.out.println(hmacSha512List.stream().mapToDouble(a -> a).average());
		
	}
}
