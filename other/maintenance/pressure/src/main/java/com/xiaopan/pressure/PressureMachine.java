package com.xiaopan.pressure;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 压力机
 */
public class PressureMachine {
	/** 被压url **/
	private String url;
	/** 频率（每秒发压次数） **/
	private long frequency;
	private RateLimiter rateLimiter;
	/** 线程池 **/
	private ExecutorService exectors;
	/** 成功发压数 **/
	private AtomicLong successTimes = new AtomicLong();
	/** 失败发压数 **/
	private AtomicLong failTimes = new AtomicLong();
	
	private final static String DEFAULT_POST_JSON = "{\"platform\":\"1\"}";

	public PressureMachine(String url , Long frequency) {
		Objects.requireNonNull(url);
		this.url = url;
		this.frequency = frequency;
		this.exectors = new ThreadPoolExecutor(10, 10, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		this.rateLimiter = RateLimiter.create(frequency);
	}
	
	public void start() {
		ExecutorService exec = new ThreadPoolExecutor(1, 1, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
		exec.execute(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					rateLimiter.acquire();
//					exectors.execute(new GetTask());
					exectors.execute(new PostTask());
				}
			}
		});
		
	}
	
	class PostTask implements Runnable {

		@Override
		public void run() {
			boolean success = false;
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			CloseableHttpResponse response = null;
			try {
				StringEntity entity = new StringEntity(DEFAULT_POST_JSON);
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
				response = httpClient.execute(httpPost);
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					success = true;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (null != response) {
					try {
						EntityUtils.consume(response.getEntity());
//						HttpEntity entity = response.getEntity();
//						BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
//						String line = null;
//						while ((line = reader.readLine()) != null) {
//							System.out.println(line);
//						}
						response.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			if (success) {
				successTimes.incrementAndGet();
//				System.out.println(LocalTime.now().getSecond() + "success!");
			} else {
				failTimes.incrementAndGet();
//				System.out.println(LocalTime.now().getSecond() + "fail!");
			}
		}
		
	}
	/**
	 * 发压任务
	 */
	class GetTask implements Runnable {

		@Override
		public void run() {
			get(url);
		}

		/**
		 * @param url
		 */
		private void get(String url) {
			boolean success = false;
			CloseableHttpResponse response = null;
			try {
				CloseableHttpClient httpClient = HttpClients.createDefault();
				HttpGet httpGet = new HttpGet(url);
				response = httpClient.execute(httpGet);
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					success = true;
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (null != response) {
					try {
						HttpEntity entity = response.getEntity();
						EntityUtils.consume(entity);
						response.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			if (success) {
				successTimes.incrementAndGet();
//				System.out.println(LocalTime.now().getSecond() + "success!");
			} else {
				failTimes.incrementAndGet();
//				System.out.println(LocalTime.now().getSecond() + "fail!");
			}
		}
		
	}

	public void printStatus() {
		long success = successTimes.get();
		long fail = failTimes.get();
		long total = success + fail;
		System.out.println("totalTimes:" + total + " ,success:" + success + " ,fail:" + fail);
	}
	
	
}
