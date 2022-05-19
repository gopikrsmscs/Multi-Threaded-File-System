import java.io.File;
import java.io.FileOutputStream;

public class FileDownloadClient {

	public static String DOWN_LOAD_PATH = System.getProperty("user.dir")+"/clientfiles/downloads";

	public static void downloadFile(FileOperations stub, String serverFileName) {
		try {
			byte[] mydata = stub.downloadFile(serverFileName);
			File clientpathfile = new File(DOWN_LOAD_PATH + "/" + serverFileName);
			clientpathfile.createNewFile();
			FileOutputStream downloadFileData = new FileOutputStream(DOWN_LOAD_PATH + "/" + serverFileName);
			downloadFileData.write(mydata);
			downloadFileData.flush();
			downloadFileData.close();

		} catch (Exception e) {
			System.err.println("File Download Exception : " + e.toString());
			e.printStackTrace();
		}

	}
}
