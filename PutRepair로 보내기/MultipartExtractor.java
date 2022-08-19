
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.HashMap;

@MultipartConfig
public class MultipartExtractor {
	private HashMap<String,String> formDatas;
	private HashMap<String,InputStream> streamDatas;
	private HashMap<String,String> filenames;

	public MultipartExtractor(HttpServletRequest request) {
		try{
			formDatas = new HashMap<>();
			streamDatas = new HashMap<>();
			filenames = new HashMap<>();

			Collection<Part> parts = request.getParts();
			for(Part part : parts) {
				if(part.getSubmittedFileName()==null || part.getSubmittedFileName().equals(""))
					formDatas.put(part.getName(),this.getString(part.getInputStream()));
				else {
					streamDatas.put(part.getName(),part.getInputStream());
					filenames.put(part.getName(),part.getSubmittedFileName());
				}
			}
		}catch(Exception e) {}
	}

	//return String form-data from Content-Disposition Name position
	public String getFormData(String key) {
		return formDatas.get(key);
	}

	//return InputStream form-data from Content-Disposition Name position
	public InputStream getInputStream(String key) {
		return streamDatas.get(key);
	}

	public byte[] getBinaryData(String key) {
		return this.getBytes(streamDatas.get(key));
	}

	public String getFilename(String key) {
		return filenames.get(key);
	}


	private byte[] getBytes(InputStream in) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] data = new byte[1024*16];
		int size;

		try{
			while((size=in.read(data))!=-1)
				out.write(data,0,size);
		}catch(Exception e) {}

		return out.toByteArray();
	}

	private String getString(InputStream in) {
		byte[] data = new byte[1024*4];
		int size;
		String result="";

		try{
			while((size=in.read(data))!=-1)
				result=result+new String(data,0,size,"utf-8");
		}catch(Exception e) {}

		return result;
	}
}

