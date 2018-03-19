package httpcore.a1_fundamentals.a11_http_messages;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;

/**
 * @Package: httpcore.a1
 * @ClassName: C_HttpProperties
 * @date: 2017年12月29日 下午6:06:44
 * @Description: HTTP message common properties and methods
 */
public class C_HttpProperties {
	
	public static void main(String[] args) {
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
		
		response.addHeader("Set-Cookie", "c1=a;path=/;domain=localhost");
		response.addHeader("Set-Cookie", "c2=b;path=\"/\",c3=c;domain=\"localhost\"");
		
		//1
		Header h1 = response.getFirstHeader("Set-Cookie");
		System.out.println(h1);
		Header h2 = response.getLastHeader("Set-Cookie");
		System.out.println(h2);
		Header[] hs = response.getHeaders("Set-Cookie");
		System.out.println(hs.length);
		
		//2
		HeaderIterator it = response.headerIterator("Set-Cookie");
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		//3
		HeaderElementIterator heit = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));
		while (heit.hasNext()) {
			HeaderElement elem = heit.nextElement();
			System.out.println(elem.getName() + "=" + elem.getValue());
			NameValuePair[] params = elem.getParameters();
			for (int i = 0; i < params.length ; i++) {
				System.out.println(" " + params[i]);
			}
		}
	}
}
