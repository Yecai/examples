package httpcore.a1_fundamentals.a11_http_messages;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;

public class F_create_entitys {
	
	public static void main(String[] args) throws IOException {
		basicHttpEntity();
		byteArrayEntity();
		stringEntity();
		fileEntity();
		bufferedHttpEntity();
	}

	@SuppressWarnings({ "null", "unused" })
	private static void bufferedHttpEntity() throws IOException {
		BasicHttpEntity myNonRepeatableEntity = null;
		InputStream is = null;
		myNonRepeatableEntity.setContent(is);
		BufferedHttpEntity myBufferedEntity = new BufferedHttpEntity(myNonRepeatableEntity);
	}

	@SuppressWarnings("unused")
	private static void fileEntity() {
		File staticFile = null;
		HttpEntity entity = new FileEntity(staticFile, ContentType.create("application/java=archive"));
	}

	@SuppressWarnings("unused")
	private static void stringEntity() throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		Map<String, String> env = System.getenv();
		for (Map.Entry<String, String> envEntry : env.entrySet()) {
			sb.append(envEntry.getKey()).append(":").append(envEntry.getValue()).append("\r\n");
		}
		
		//construct without a character encoding (defaults to ISO-8859-1)
		HttpEntity myEntity1 = new StringEntity(sb.toString());
		
		//alternatively construct with an encoding (mime type defaults to "text/plain")
		HttpEntity myEntity2 = new StringEntity(sb.toString(), Consts.UTF_8);
		
		//alternatively construct with an encoding and a mime type
		HttpEntity myEntity3 = new  StringEntity(sb.toString(), ContentType.create("text/plain", Consts.UTF_8));
		
	}

	private static void basicHttpEntity() {
		BasicHttpEntity myEntity = new BasicHttpEntity();
		InputStream is = null;
		myEntity.setContent(is);
		myEntity.setContentLength(340);
		
	}

	@SuppressWarnings("unused")
	private static void byteArrayEntity() {
		ByteArrayEntity myEntity = new ByteArrayEntity(new byte[] {1,2,3}, ContentType.APPLICATION_OCTET_STREAM);
	}
	
}
