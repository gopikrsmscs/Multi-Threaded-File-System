

public class FileRenameClient {

	public static void renameFile(FileOperations stub, String currentFileName, String newFileName) {
		try {
			stub.renameFile(currentFileName, newFileName);

		} catch (Exception e) {
			System.err.println("File Rename Exception : " + e.toString());
			e.printStackTrace();
		}

	}

}
