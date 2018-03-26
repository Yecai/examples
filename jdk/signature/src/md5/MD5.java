package md5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * @Package: md5
 * @ClassName: MD5
 * @Description: 128λ��16�ֽڣ���ɢ��ֵ������ȷ����Ϣ��������һ��
 */
public class MD5 {
	
	public static final String MESSAGE = "fasdfsafasdf";
	
	public static void main(String[] args) {
		System.out.println(md5(MESSAGE));;
	}

	public static String md5(String message) {
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] byteDigest = md.digest(message.getBytes());
			digest = new BigInteger(1, byteDigest).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return digest;
	}
}
