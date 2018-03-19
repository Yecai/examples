package httpcore.a1_fundamentals.a11_http_messages;

import java.io.IOException;

import org.apache.http.Consts;
import org.apache.http.ParseException;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

public class D_HttpEntity {
	public static void main(String[] args) throws ParseException, IOException {
		StringEntity myEntity = new StringEntity("important message", Consts.UTF_8);
		
		System.out.println(myEntity.getContentType());
		System.out.println(myEntity.getContentLength());
		System.out.println(EntityUtils.toString(myEntity));
		System.out.println(EntityUtils.toByteArray(myEntity).length);
	}
}
