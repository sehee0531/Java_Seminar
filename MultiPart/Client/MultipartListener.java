import java.io.InputStream;

public interface MultipartListener {
	public void onResult(InputStream in) throws Exception;
}