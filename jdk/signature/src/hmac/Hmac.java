package hmac;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Package: hmac
 * @ClassName: Hmac
 * @date: 2017年11月9日 下午5:23:24
 * @Description: 
 */
public class Hmac {
	
	public static final String HMAC_MD5_ALGORITHM = "HmacMD5";
	public static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	public static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
	public static final String HMAC_SHA512_ALGORITHM = "HmacSHA512";
	public static final String KEY = "fwerwerw";
	public static final String MESSAGE = "fdsafds";
	public static void main(String[] args) {
		System.out.println(hmacsha1(HMAC_SHA256_ALGORITHM, MESSAGE));
	}

	public static String hmacsha1(String HMAC_ALGORITHM, String message) {
		String digest = null;
		try {
			SecretKeySpec signingKey = new SecretKeySpec(KEY.getBytes(), HMAC_ALGORITHM);
			Mac mac = Mac.getInstance(HMAC_ALGORITHM);
			mac.init(signingKey);
			byte[] byteDigest = mac.doFinal(message.getBytes());
			digest = new BigInteger(1, byteDigest).toString(16);
//			digest = toHexString(mac.doFinal(MESSAGE.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		return digest;
	}
	
//	@SuppressWarnings("resource")
//	private static String toHexString(byte[] bytes) {
//		Formatter formatter = new Formatter();
//		for (byte b : bytes) {
//			formatter.format("%02x", b);
//		}
//		return formatter.toString();
//	}
}
