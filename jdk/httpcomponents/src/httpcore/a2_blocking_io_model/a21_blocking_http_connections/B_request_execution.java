package httpcore.a2_blocking_io_model.a21_blocking_http_connections;

import java.io.IOException;
import java.net.Socket;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.util.EntityUtils;

public class B_request_execution {
	public static void main(String[] args) throws IOException, HttpException {
		Socket socket = null;
		
		DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(8 * 1024);
		conn.bind(socket);
		
		HttpRequest request = new BasicHttpRequest("GET", "/");
		conn.sendRequestHeader(request);
		
		HttpResponse response = conn.receiveResponseHeader();
		conn.receiveResponseEntity(response);
		
		HttpEntity entity = response.getEntity();
		if (null != entity) {
			// Do something useful with the entity and , when done, ensure all
			// content has  been consumed, so that the underlying connection
			// can be re-used
			EntityUtils.consume(entity);
		}
	}
}
