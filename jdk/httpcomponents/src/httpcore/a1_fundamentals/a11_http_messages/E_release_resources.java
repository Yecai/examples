package httpcore.a1_fundamentals.a11_http_messages;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

/**
 * 

When working with streaming entities, one can use the EntityUtils#consume(HttpEntity) 
method to ensure that the entity content has been fully consumed and the underlying 
stream has been closed.
 */
public class E_release_resources {
	@SuppressWarnings("null")
	public static void main(String[] args) throws Exception{
		HttpResponse response = null;
		HttpEntity entity = response.getEntity();
		if (null != entity) {
			InputStream instream = entity.getContent();
			try {
				//do something useful
			} finally {
				instream.close();
			}
		}
	}
}
