package httpcore.a1_fundamentals.a13_http_execution_context;

import org.apache.http.protocol.HttpProcessorBuilder;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.protocol.HttpProcessor;

public class A_content_sharing {
	public static void main(String[] args) throws HttpException, IOException {
		
		HttpProcessor httpproc  = HttpProcessorBuilder.create()
				.add(new HttpRequestInterceptor() {
					
					@Override
					public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
						String id = (String) context.getAttribute("session-id");
						if (null != id) {
							request.addHeader("Session-ID", id);
						}
					}
				})
				.build();
		
		HttpCoreContext context = HttpCoreContext.create();
		HttpRequest request = new BasicHttpRequest("GEY", "/");
		httpproc.process(request, context);
	}
}
