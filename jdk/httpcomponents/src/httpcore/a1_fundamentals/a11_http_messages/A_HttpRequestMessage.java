package httpcore.a1_fundamentals.a11_http_messages;

import org.apache.http.HttpRequest;
import org.apache.http.HttpVersion;
import org.apache.http.message.BasicHttpRequest;

// https://hc.apache.org/httpcomponents-core-ga/tutorial/html/index.html
public class A_HttpRequestMessage {

	public static void main(String[] args) {
		HttpRequest request = new BasicHttpRequest("GET", "/", HttpVersion.HTTP_1_1);
		
		System.out.println(request.getRequestLine().getMethod());
		System.out.println(request.getRequestLine().getUri());
		System.out.println(request.getProtocolVersion());
		System.out.println(request.getRequestLine().toString());
	}
}
