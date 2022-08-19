import java.util.HashMap;
import java.net.*;
import java.io.*;
import javax.net.ssl.*;
import java.security.*;

public class MultipartMaker extends Thread {
	private MultipartListener listener;
	private String url;
	private HashMap<String,String> formDatas;
	private HashMap<String,InputStream> binaryDatas;
	private HashMap<String,String> binaryDataFilenames;
	private OutputStream out;
	private boolean secure;

	private final String disposition="a1234567890";

	public MultipartMaker(String url,MultipartListener listener) {
		this(url,listener,false);
	}

	public MultipartMaker(String url,MultipartListener listener,boolean secure) {
		this.listener=listener;
		this.url=url;
		this.secure=secure;
		formDatas = new HashMap<>();
		binaryDatas = new HashMap<>();
		binaryDataFilenames = new HashMap<String,String>();
	}

	public void addFormData(String key, String value) {
		formDatas.put(key,value);
	}

	public void addBinaryData(String key, InputStream in) {
		this.addBinaryData(key,"default",in);
	}

	public void addBinaryData(String key, String filename, InputStream in) {
		binaryDatas.put(key,in);
		binaryDataFilenames.put(key,filename);
	}


	public void run() {
		try{
			URL url = new URL(this.url);
			HttpsURLConnection sconnection=null;;
			HttpURLConnection connection=null;

			if(this.secure==true) {
				XTrustManager trustAllCerts[] = new XTrustManager[]{new XTrustManager()};
				SSLContext context = SSLContext.getInstance("SSL");
				context.init(null,trustAllCerts, new SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
				sconnection = (HttpsURLConnection)url.openConnection();
				sconnection.setDoOutput(true);
				sconnection.setDoInput(true);
				sconnection.setRequestMethod("POST");
				sconnection.setRequestProperty("Content-Type","multipart/form-data; boundary="+this.disposition);
				this.out=sconnection.getOutputStream();
			}else {
				connection = (HttpURLConnection)url.openConnection();
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setRequestMethod("POST");
				connection.setRequestProperty("Content-Type","multipart/form-data; boundary="+this.disposition);
				this.out=connection.getOutputStream();
			}

			this.addFormData();
			this.addBinaryData();
			this.addEndingBody();

			byte[] data = new byte[1024*1024*12];
			int size;

			InputStream in=null;
			if(secure==true)
				in = sconnection.getInputStream();
			else
				in = connection.getInputStream();

			this.listener.onResult(in);

			//while((size=in.read(data))!=-1)
			//	System.out.println(new String(data,0,size,"UTF-8"));

		}catch(Exception e) {System.out.println(e);}
	}

	private void addFormData() throws Exception {
		for(String key : formDatas.keySet()) {
			String str="";
			out.write("\r\n".getBytes());
			str="--"+this.disposition+"\r\n";
			out.write(str.getBytes());
			str="Content-Disposition: form-data; name="+key+"\r\n";
			out.write(str.getBytes());
			str="Content-Type: text/plain; charset=utf-8\r\n";
			out.write(str.getBytes());
			out.write("\r\n".getBytes());
			out.write(formDatas.get(key).getBytes("utf-8"));
			out.flush();
		}
	}

	private void addBinaryData() throws Exception {
		byte[] data = new byte[1024*4];
		int size;

		for(String key : binaryDatas.keySet()) {
			String str="";
			out.write("\r\n".getBytes());
			str="--"+this.disposition+"\r\n";
			out.write(str.getBytes());
			str="Content-Disposition: form-data; name="+key+"; filename="+binaryDataFilenames.get(key)+"\r\n";
			out.write(str.getBytes());
			out.write("\r\n".getBytes());
			out.flush();
			InputStream in = binaryDatas.get(key);
			while((size=in.read(data))!=-1) {
				out.write(data,0,size);
				out.flush();
			}
			in.close();
		}
	}

	private void addEndingBody() throws Exception {
		out.write("\r\n".getBytes());
		String str="--"+this.disposition+"--\r\n";
		out.write(str.getBytes());
		out.flush();

		out.close();
	}
}