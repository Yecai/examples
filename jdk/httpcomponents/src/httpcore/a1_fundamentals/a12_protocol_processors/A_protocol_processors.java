package httpcore.a1_fundamentals.a12_protocol_processors;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;

/**
Please note the BasicHttpProcessor class does not synchronize access to its internal structures and therefore may not be thread-safe.
 */
public class A_protocol_processors {
	public static void main(String[] args) throws HttpException, IOException {
		HttpProcessor httpproc = HttpProcessorBuilder.create()
				//Required protocol interceptors
				.add(new RequestContent())
				.add(new RequestTargetHost())
				// Recommented protocol interceptors
				.add(new RequestConnControl())
				.add(new RequestUserAgent("MyAgent-HTTP/1.1"))
				// Optional protocol interceptors
				.add(new RequestExpectContinue(true))
				.build();
		
		HttpCoreContext content = HttpCoreContext.create();
		HttpRequest request = new BasicHttpRequest("GET", "/");
		httpproc.process(request, content);
	}
}
