import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

/**
 * @author shen
 * @desc
 * @date 2016年9月30日
 */
public class FTPTest {
	
	/*@Test
	public void testFtpclient() throws SocketException, IOException	{
		
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.1.108",21);
		ftpClient.login("shenImg", "123456");
		
		FileInputStream inputStream = new FileInputStream(new File("D:\\File\\img\\145.jpg"));//读取本地文件
		ftpClient.changeWorkingDirectory("/temp");//上传路径
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.storeFile("buibui.jpg", inputStream);
		
		ftpClient.logout();
	}*/
	
	
	public static void main(String[] args) throws Exception {
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.1.108",21);
		ftpClient.login("shenImg", "123456");
		
		File tsetFile = new File("D:\\File\\img\\145.jpg");
		
		//读取本地文件
		FileInputStream inputStream = new FileInputStream(tsetFile);
		ftpClient.changeWorkingDirectory("/temp");//上传路径
		
		String pathName = "\\sehn";
		boolean check = ftpClient.makeDirectory(pathName);
		
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.storeFile("test.jpg", inputStream);
		
		ftpClient.logout();
	}
	
}



