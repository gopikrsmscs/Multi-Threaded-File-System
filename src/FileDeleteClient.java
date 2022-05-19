
public class FileDeleteClient {

	public static void deleteFile(FileOperations stub, String fileName) {

		try {
			stub.delteFile(fileName);

		} catch (Exception e) {
			System.err.println("File Delete Exception : " + e.toString());
			e.printStackTrace();
		}
	}

}
