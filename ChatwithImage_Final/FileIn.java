import java.io.*;
import java.net.*;
import java.util.*;

public class FileIn extends Thread
{
	private Socket socket;
	private OutputStream out;
	private FileInputStream fileIn;
	
	public FileIn(Socket socket)throws Exception
	{
		this.socket=socket;
		this.out=socket.getOutputStream(); 
		this.fileIn=new FileInputStream("test.jpg");
		
		
	}
	public void run() 
	{
		try
		{
			byte[]data=new byte[1024*8];
			int size;
			
			while((size=fileIn.read(data))!=-1)
			{
				out.write(data,0,size);
				out.flush();
			}
			this.socket.close();
		}
		catch(Exception e) {}
		
	}
	
}