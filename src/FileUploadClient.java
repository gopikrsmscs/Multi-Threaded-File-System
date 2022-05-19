
import java.io.File;
import java.io.FileInputStream;

public class FileUploadClient {

	/** CLIENT FOLDER IS THE FOLDER WHERE ALL THE CLIENT FILES ARE LOCATED */

	private static String CLIENT_FOLDER = System.getProperty("user.dir")+"/clientfiles";

	public static void fileUpload(FileOperations stub, String clientFileName) {
		try {

			System.out.println("Client File path :" + CLIENT_FOLDER + "/" + clientFileName);
			File clientFile = new File(CLIENT_FOLDER + "/" + clientFileName);
			byte[] fileByteData = new byte[(int) clientFile.length()];
			FileInputStream fileInputStream = new FileInputStream(clientFile);
			fileInputStream.read(fileByteData, 0, fileByteData.length);
			stub.uploadFile(fileByteData, clientFileName);
			fileInputStream.close();
		} catch (Exception e) {
			System.err.println("File Upload Exception : " + e.toString());
			e.printStackTrace();
		}
	}

}
