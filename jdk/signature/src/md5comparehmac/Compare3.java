package md5comparehmac;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Compare3 {
	public static void main(String[] args) {
		
		CMd5 md5 = new CMd5();
		CHmac hmacmd5 = new CHmac(CHmac.HMAC_MD5_ALGORITHM);
		CHmac hmacsha1 = new CHmac(CHmac.HMAC_SHA1_ALGORITHM);
		CHmac hmacsha512 = new CHmac(CHmac.HMAC_SHA512_ALGORITHM);
		String m = "asdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasddsafdsafdsfsdafdsfdsafsdscafdsafsdafdsafdsfffdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsdasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsdafdsafdsafdsafsdafsadffsasdfdsafsdscafdsafsdafdsafdsffffsfdsfdsfadsfdasfasdfdsafdsafdsfsd";
		String message = m;
		for (int i = 1; i < 100; i++) {
			message = message + m;
		}
		System.out.println("message大小" + message.length() + "B");
		
		
		long count = 10000;
		long calCount = 10;
		
		List<Long> md5List = new ArrayList<Long>();
		List<Long> hmacMd5List = new ArrayList<>();
		List<Long> hmacSha1List = new ArrayList<>();
		List<Long> hmacSha512List = new ArrayList<>();
		
		//统计calCount次的平均值
		for (int j = 1; j <= calCount; j++) {
			System.out.println(j);
			long start = System.currentTimeMillis();
			for (int i = 1; i <= count; i++) {
				md5.digest(message);
			}
			long end = System.currentTimeMillis();
			long md5Mills = end - start;
			md5List.add(md5Mills);
			
			start = System.currentTimeMillis();
			for (int i = 1; i <= count; i++) {
				hmacmd5.digest(message);
			}
			end = System.currentTimeMillis();
			long hmacmd5Mills = end - start;
			hmacMd5List.add(hmacmd5Mills);
			
			start = System.currentTimeMillis();
			for (int i = 1; i <= count; i++) {
				hmacsha1.digest(message);
			}
			end = System.currentTimeMillis();
			long hmacsha1Mills = end - start;
			hmacSha1List.add(hmacsha1Mills);
			
			start = System.currentTimeMillis();
			for (int i = 1; i <= count; i++) {
				hmacsha512.digest(message);
			}
			end = System.currentTimeMillis();
			long hmacsha512Mills = end - start;
			hmacSha512List.add(hmacsha512Mills);
		}
		
		System.out.println(md5List.stream().mapToDouble(a -> a).average());
		System.out.println(hmacMd5List.stream().mapToDouble(a -> a).average());
		System.out.println(hmacSha1List.stream().mapToDouble(a -> a).average());
		System.out.println(hmacSha512List.stream().mapToDouble(a -> a).average());
	}
	
	
	
}

class CMd5 {
	private MessageDigest md = null;
	
	public CMd5() {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public String digest(String message) {
		return new BigInteger(1, md.digest(message.getBytes())).toString(16);
	}
}

class CHmac {
	public static final String HMAC_MD5_ALGORITHM = "HmacMD5";
	public static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	public static final String HMAC_SHA512_ALGORITHM = "HmacSHA512";
	public static final String KEY = "ae72a67f-f16d-4832-bcc3-f9be077b0afe";
	
	private SecretKeySpec signingKey;
	private Mac mac;
	
	public CHmac(String HMAC_ALGORITHM) {
		signingKey = new SecretKeySpec(KEY.getBytes(), HMAC_ALGORITHM);
		try {
			mac = Mac.getInstance(HMAC_ALGORITHM);
			mac.init(signingKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String digest(String message) {
		byte[] byteDigest = mac.doFinal(message.getBytes());
		return new BigInteger(1, byteDigest).toString(16);
	}
}
