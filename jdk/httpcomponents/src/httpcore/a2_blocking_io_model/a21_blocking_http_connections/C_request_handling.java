package httpcore.a2_blocking_io_model.a21_blocking_http_connections;

import java.io.IOException;
import java.net.Socket;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultBHttpServerConnection;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

public class C_request_handling {
	public static void main(String[] args) throws HttpException, IOException {
		Socket socket = null;
		
		DefaultBHttpServerConnection conn = new DefaultBHttpServerConnection(8 * 1024);
		conn.bind(socket);
		
		HttpRequest request = conn.receiveRequestHeader();
		if (request instanceof HttpEntityEnclosingRequest) {
			conn.receiveRequestEntity((HttpEntityEnclosingRequest) request);
			HttpEntity entity = ((HttpEntityEnclosingRequest)request).getEntity();
			if (entity != null) {
				// Do something useful with the entity and , when done, ensure all
				// content has  been consumed, so that the underlying connection
				// can be re-used
				EntityUtils.consume(entity);
			}
		}
		
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, 200, "OK");
		response.setEntity(new StringEntity("Got it"));
		conn.sendResponseHeader(response);
		conn.sendResponseEntity(response);
	}
}
