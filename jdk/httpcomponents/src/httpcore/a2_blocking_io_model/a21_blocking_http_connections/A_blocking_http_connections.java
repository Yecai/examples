package httpcore.a2_blocking_io_model.a21_blocking_http_connections;

import java.io.IOException;
import java.net.Socket;

import org.apache.http.HttpConnectionMetrics;
import org.apache.http.impl.DefaultBHttpClientConnection;

public class A_blocking_http_connections {
	public static void main(String[] args) throws IOException {
		Socket socket = null;
		
		DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(8 * 1024);
		conn.bind(socket);
		
		System.out.println(conn.isOpen());
		HttpConnectionMetrics metrics = conn.getMetrics();
		System.out.println(metrics.getRequestCount());
		System.out.println(metrics.getResponseCount());
		System.out.println(metrics.getReceivedBytesCount());
		System.out.println(metrics.getSentBytesCount());
	}
}
