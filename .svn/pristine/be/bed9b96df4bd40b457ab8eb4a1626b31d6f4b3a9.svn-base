package y1j2x34.utils;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.security.Permission;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 用于提交http或https的post、get请求
 * @author yangjianxin
 *
 */
public class HttpUtils {
	private static final String BOUNDARY = "----WebKitFormBoundaryz0Lh7IZcihjAspLX";  // 定义数据分隔线 
	public static enum RequestMethod{
		GET,POST;
	}
	static{
		// 解决异常: javax.net.ssl.SSLProtocolException: handshake alert:  unrecognized_name
		System.setProperty ("jsse.enableSNIExtension", "false");
		
	}
	
	/**
	 * 响应模型
	 */
	public static class Response {
		private InputStream stream;
		private String cookies;
		private Map<String,String> hearder;
		private ReadOnlyConnection connection;
		private int statusCode;
		private String message;
		private Response(){};
		public InputStream getStream() {
			return stream;
		}
		public String getCookies() {
			return cookies;
		}
		public String getUTF8() throws IOException{
			return getResponseText(Charset.forName("UTF-8"));
		}
		public String getGBK() throws IOException{
			return getResponseText(Charset.forName("GBK"));
		}
		public String getGB2312() throws IOException{
			return getResponseText(Charset.forName("GB2312"));
		}
		public String getISO_8859_1() throws IOException{
			return getResponseText(Charset.forName("ISO8859-1"));
		}
		public String getResponseText(Charset charset) throws IOException{
			StringBuilder respTxt = new StringBuilder();
			/*
			 byte[] buf = new byte[1024];
			try{
				int l = 0;
				while((l=stream.read(buf)) != -1){
					respTxt.append(new String(buf,0,l,charset));
				}
			}finally{
				stream.close();
			}*/
			ReadableByteChannel rbc = Channels.newChannel(stream);
			try{
				int len = connection.getContentLength();
				if(len <= 0){
					len = 2048;
				}
				ByteBuffer buffer = ByteBuffer.allocate(len);
				while(true){
					int b = rbc.read(buffer);
					if(b == -1)break;
					buffer.flip();
					String s = new String(buffer.array(),buffer.position(),buffer.limit(),charset);
					respTxt.append(s);
				}
			}finally{
				rbc.close();
			}
			return new String(respTxt);
		}
		public Map<String,String> getHeader(){
			return hearder;
		}
		/**
		 * @return Readable Connection
		 */
		public ReadOnlyConnection getConnection() {
			return connection;
		}
		public int getStatusCode() {
			return statusCode;
		}
		void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}
		/**
		 * <pre>
		 * HTTP/1.0 200 OK
 		 * HTTP/1.0 404 Not Found
 		 * </pre>
		 * @return
		 */
		public String getMessage() {
			return message;
		}
		void setMessage(String message) {
			this.message = message;
		}
	}
	public static class Data{
		private InputStream stream;
		private String fileName;
		private String fileField;
		private String fileType="application/octet-stream";
		private long fileLength;
		
		public Data(byte[] data) {
			stream = new ByteArrayInputStream(data);
		}
		public Data(InputStream data) {
			stream = data;
		}
		public InputStream getStream() {
			return stream;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFileField() {
			return fileField;
		}
		public void setFileField(String fileField) {
			this.fileField = fileField;
		}
		public void setFileType(String fileType) {
			this.fileType = fileType;
		}
		public String getFileType() {
			return fileType;
		}
		public void setFileLength(long fileLength) {
			this.fileLength = fileLength;
		}
		public long getFileLength() {
			return fileLength;
		}
	}
	/**
	 * 请求模型
	 */
	public static class RequestParams{
		private String url;
		private Map<String,String> head;
		private RequestMethod method;
		private Map<String,String> query;
		//证书信任管理器
		private TrustManager[] tm = new TrustManager[]{new MyX509TrustManager()};
		private Data data;
		private boolean useCache;
		private int connectTimeout;
		
		public RequestParams() {}
		public RequestParams(String url){
			setUrl(url);
		}
		/**
		 * 设置请求的url地址
		 * @param url
		 * @return
		 */
		public RequestParams setUrl(String url) {
			this.url = url;
			return this;
		}
		/**
		 * 设置请求头
		 * @param head
		 * @return
		 */
		public RequestParams setHead(Map<String, String> head) {
			this.head = head;
			return this;
		}
		/**
		 * 添加请求头信息
		 * @param key
		 * @param value
		 * @return
		 */
		public RequestParams addHead(String key,String value){
			if(head == null){
				head = new HashMap<String,String>();
			}
			if(key != null){
				head.put(key, value);
			}
			return this;
		}
		/**
		 * 设置证书信任管理器，用于HTTPS请求
		 * @param tm
		 * @return
		 */
		public RequestParams setTm(TrustManager[] tm) {
			this.tm = tm;
			return this;
		}
		/**
		 * 请求的数据
		 * @param postData
		 * @return
		 */
		public RequestParams setPostData(byte[] postData) {
			this.data = new Data(postData);
			this.data.setFileLength(postData.length);
			return this;
		}
		/**
		 * 设置到请求地址的参数
		 * @param query
		 * @return
		 */
		public RequestParams setQuery(Map<String, String> query) {
			this.query = query;
			return this;
		}
		/**
		 * 添加到请求地址的参数
		 * @param key
		 * @param value
		 * @return
		 */
		public RequestParams addQuery(String key,String value){
			if(query == null){
				query = new HashMap<String,String>();
			}
			if(key != null){
				query.put(key, value==null?"":value);
			}
			return this;
		}
		public RequestParams addQuery(String key,Number value){
			return addHead(key, String.valueOf(value));
		}
		/**
		 * 是否使用缓存
		 * @return
		 */
		public boolean isUseCache() {
			return useCache;
		}
		/**
		 * 设置是否使用缓存
		 * @param useCache
		 * @return
		 */
		public RequestParams setUseCache(boolean useCache) {
			this.useCache = useCache;
			return this;
		}
		/**
		 * 获取完整的（添加了请求参数）的地址
		 * @return
		 */
		public String getURI(){
			if(query != null){
				StringBuilder sb = new StringBuilder();
				for(Entry<String,String> entry:query.entrySet()){
					String key = entry.getKey();
					String value = entry.getValue();
					if(key != null && ! "".equals(key)){
						value = value == null?"":value;
						sb.append(key).append('=').append(value).append('&');
					}
				}
				sb.deleteCharAt(sb.length()-1);
				int lq = url.lastIndexOf('?');
				if(lq == -1){
					sb.insert(0, '?');
				}else if(lq != url.length()-1 && url.lastIndexOf('&') != url.length() - 1){
					sb.insert(0, '&');
				}
				/*if(!(url.lastIndexOf('?') == url.length()-1 
						|| url.lastIndexOf('&') == url.length()-1)){
					sb.insert(0, '?');
				}*/
				return sb.insert(0, url).toString();
			}
			return url;
		}
		public RequestParams setData(Data data){
			this.data = data;
			return this;
		}
		public Data getData() {
			return data;
		}
		public int getConnectTimeout() {
			return connectTimeout;
		}
		public void setConnectTimeout(int connectTimeout) {
			this.connectTimeout = connectTimeout;
		}
	}
	private static final class MyX509TrustManager implements X509TrustManager {  
	    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}  
	    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}  
	    public X509Certificate[] getAcceptedIssuers() {return null;}  
	}
	private static final HostnameVerifier ALL_HOSTS_VALID = new HostnameVerifier() {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	};
	/**
	 * 发送post请求
	 * @param params
	 * @return
	 * @throws RequestException
	 */
	public static Response post(RequestParams params) throws RequestException{
		if(params.url != null){
			params.method = RequestMethod.POST;
			return request(params);
		}
		return null;
	}
	/**
	 * 发送get请求
	 * @param params
	 * @return
	 * @throws RequestException
	 */
	public static Response get(RequestParams params) throws RequestException{
		if(params.url != null){
			params.method = RequestMethod.GET;
			return request(params);
		}
		return null;
	}
	/**
	 * 只读的连接
	 */
	public static interface ReadOnlyConnection{
		public boolean usingProxy() ;
		public String getHeaderFieldKey(int n) ;
		public String getHeaderField(int n) ;
		public boolean getInstanceFollowRedirects();
		public String getRequestMethod() ;
		public int getResponseCode() throws IOException ;
		public String getResponseMessage() throws IOException;
		public long getHeaderFieldDate(String name, long Default) ;
		public Permission getPermission() throws IOException;
		public int getConnectTimeout();
		public int getReadTimeout() ;
		public URL getURL() ;
		public int getContentLength() ;
		public String getContentType() ;
		public String getContentEncoding() ;
		public long getExpiration() ;
		public long getDate() ;
		public long getLastModified();
		public String getHeaderField(String name) ;
		public Map<String, List<String>> getHeaderFields();
		public int getHeaderFieldInt(String name, int Default) ;
		public Object getContent() throws IOException ;
		public Object getContent(@SuppressWarnings("rawtypes") Class[] classes) throws IOException ;
		public String toString() ;
		public void setDoInput(boolean doinput) ;
		public boolean getDoInput();
		public boolean getDoOutput() ;
		public boolean getAllowUserInteraction() ;
		public boolean getUseCaches() ;
		public long getIfModifiedSince() ;
		public boolean getDefaultUseCaches() ;
		public String getRequestProperty(String key);
		public Map<String, List<String>> getRequestProperties() ;
		public void disconnect();
	}
	private static class MReadOnlyConnection extends HttpURLConnection implements ReadOnlyConnection {
		private final HttpURLConnection mReal;
		protected MReadOnlyConnection(HttpURLConnection real) {
			super(null);
			this.mReal = real;
		}
		public void connect() throws IOException {
			throw new UnsupportedOperationException();
		}
		public boolean usingProxy() {
			return mReal.usingProxy();
		}
		public String getRequestMethod() {
			return mReal.getRequestMethod();
		}
		public int getResponseCode() throws IOException {
			return mReal.getResponseCode();
		}
		public String getResponseMessage() throws IOException {
			return mReal.getResponseMessage();
		}
		public long getHeaderFieldDate(String name, long Default) {
			return mReal.getHeaderFieldDate(name, Default);
		}
		public int getConnectTimeout() {
			return mReal.getConnectTimeout();
		}
		public int getReadTimeout() {
			return mReal.getReadTimeout();
		}
		public URL getURL() {
			return mReal.getURL();
		}
		public int getContentLength() {
			return mReal.getContentLength();
		}
		public String getContentType() {
			return mReal.getContentType();
		}
		public String getContentEncoding() {
			return mReal.getContentEncoding();
		}
		public long getExpiration() {
			return mReal.getExpiration();
		}
		public long getDate() {
			return mReal.getDate();
		}
		public long getLastModified() {
			return mReal.getLastModified();
		}
		public String getHeaderField(String name) {
			return mReal.getHeaderField(name);
		}
		public Map<String, List<String>> getHeaderFields() {
			return mReal.getHeaderFields();
		}
		public int getHeaderFieldInt(String name, int Default) {
			return mReal.getHeaderFieldInt(name, Default);
		}
		public Object getContent() throws IOException {
			return mReal.getContent();
		}
		public Object getContent(@SuppressWarnings("rawtypes") Class[] classes) throws IOException {
			return mReal.getContent(classes);
		}
		public String toString() {
			return mReal.toString();
		}
		public boolean getDoInput() {
			return mReal.getDoInput();
		}
		public boolean getDoOutput() {
			return mReal.getDoOutput();
		}
		public boolean getAllowUserInteraction() {
			return mReal.getAllowUserInteraction();
		}
		public boolean getUseCaches() {
			return mReal.getUseCaches();
		}
		public long getIfModifiedSince() {
			return mReal.getIfModifiedSince();
		}
		public boolean getDefaultUseCaches() {
			return mReal.getDefaultUseCaches();
		}
		public String getRequestProperty(String key) {
			return mReal.getRequestProperty(key);
		}
		public Map<String, List<String>> getRequestProperties() {
			return mReal.getRequestProperties();
		}
		public void disconnect() {
			mReal.disconnect();
		}
	}
	private static Response request(RequestParams params) throws RequestException{
		if(params.url == null) throw new NullPointerException("请求地址不能为空");
		try {
			
			URL url = new URL(params.getURI());
			final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			if(conn instanceof HttpsURLConnection){
				SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
	            sslContext.init(null, params.tm, new java.security.SecureRandom());  
	            ((HttpsURLConnection) conn).setSSLSocketFactory(sslContext.getSocketFactory());
	            ((HttpsURLConnection) conn).setHostnameVerifier(ALL_HOSTS_VALID);
			}
			
			RequestMethod reqMethod = params.method==null?RequestMethod.GET:params.method;
			
			conn.setUseCaches(reqMethod == RequestMethod.GET && params.useCache);//POST类型的请求不需要使用缓存
			
			conn.setRequestMethod(reqMethod.name());
			
			conn.setConnectTimeout(params.getConnectTimeout());
			conn.setDoInput(true);
			
			
			final Response resp = new Response();
			
			if(reqMethod == RequestMethod.GET){
				conn.connect();
				resp.setStatusCode(conn.getResponseCode());
				resp.setMessage(conn.getResponseMessage());
			}
			if(params.head != null){
				if(params.head.containsKey(null)){
					params.head.remove(null);
				}
				for(Entry<String,String> entry:params.head.entrySet()){
					String key = entry.getKey();
					String value = entry.getValue();
					conn.setRequestProperty(key, value);
				}
			}
			Data data = params.data;
			
			if(data != null && data.fileField != null && data.fileField.length() > 0 && data.getStream() != null){
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Type", "multipart/form-data; boundary="+BOUNDARY);
				////1.先写文字形式的post流 
	            //头 
	            String boundary = BOUNDARY; 
	            //中 
	            StringBuffer resSB = new StringBuffer("\r\n"); 
	            //尾 
	            String endBoundary = "\r\n--" + boundary + "--\r\n";
	            
	            
				try {
					OutputStream os = conn.getOutputStream();
					//写出流 
					os.write( ("--"+boundary).getBytes() ); 
					
					//写文件形式的POST流
					resSB.append("Content-Disposition: form-data; name=\"").append(data.fileField).append("\"; filename=\"").append(data.fileName).append("\"\r\n").append("Content-Type: ").append(data.fileType).append("\r\n\r\n");
					os.write(resSB.toString().getBytes());
					
					InputStream dataStream = data.stream;
					try {
						byte[] buff = new byte[1024];
						while(true){
							int b = dataStream.read(buff);
							if(b == -1)break;
							os.write(buff);
						}
						//写结尾
						os.write(endBoundary.getBytes());
					}finally{
						resp.setStatusCode(conn.getResponseCode());
						resp.setMessage(conn.getResponseMessage());
						os.close();
						dataStream.close();
					}
				} catch (IOException e) {
					throw new RequestException("数据提交错误！", e);
				}
			}else if(data != null && data.stream != null){
				conn.setDoOutput(true);
				try {
					OutputStream os = conn.getOutputStream();
					
					InputStream dataStream = data.stream;
					try {
						byte[] buff = new byte[1024];
						while(true){
							int b = dataStream.read(buff);
							if(b == -1)break;
							os.write(buff);
						}
					}finally{
						resp.setStatusCode(conn.getResponseCode());
						resp.setMessage(conn.getResponseMessage());
						os.close();
						dataStream.close();
					}
				} catch (IOException e) {
					throw new RequestException("数据提交错误！", e);
				}
			}
			
			resp.hearder = new HashMap<String,String>();
			resp.connection = new MReadOnlyConnection(conn);
			
			StringBuilder cookie = new StringBuilder();
			Map<String,List<String>> headerFields = conn.getHeaderFields();
			for(Entry<String,List<String>> entry:headerFields.entrySet()){
				if("Set-Cookie".equalsIgnoreCase(entry.getKey())){
					for(String str:entry.getValue()){
						cookie.append(str);
					}
				}
				StringBuilder sb = new StringBuilder();
				for(String str:entry.getValue()){
					sb.append(str);
				}
				resp.hearder.put(entry.getKey(), new String(sb));
			}
			resp.cookies = new String(cookie);
			resp.stream =  new DataInputStream(conn.getInputStream()){
				@Override
				public synchronized void close() throws IOException {
					try {
						super.close();
					} finally{
						resp.stream = null;
						conn.disconnect();
					}
				}
			};
			return resp;
		} catch(RequestException e){
			throw e;
		}catch (Throwable e) {
			throw new RequestException(e);
		}
	}
}