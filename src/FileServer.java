
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class FileServer extends UnicastRemoteObject implements FileOperations {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String SERVER_DIRECTORY = System.getProperty("user.dir")+"/serverfiles/";

	private static final int PORT_NUMBER = 8080;

	protected FileServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) {

		try {
			Registry registry = LocateRegistry.createRegistry(PORT_NUMBER);
			registry.bind("FileOperations", new FileServer());

			System.err.println("Server is Up and Running.....");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public void uploadFile(byte[] fileByteData, String fileName) {
		try {

			File path = new File(SERVER_DIRECTORY + fileName);
			path.createNewFile();
			FileOutputStream writeToFile = new FileOutputStream(path);
			writeToFile.write(fileByteData);
			writeToFile.flush();
			writeToFile.close();

			System.out.println("File Uploaded Sucessfully to Folder: " + SERVER_DIRECTORY);

		} catch (IOException e) {
			System.err.println("File Upload Server exception: " + e.getMessage().toString());
			e.printStackTrace();
		}

	}

	@Override
	public byte[] downloadFile(String serverFileName) {
		String filePath = SERVER_DIRECTORY + serverFileName;
		File serverFile = new File(filePath);
		byte[] fileData = new byte[(int) serverFile.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			try {
				fileInputStream.read(fileData, 0, fileData.length);
				fileInputStream.close();
			} catch (IOException e) {

				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {

			System.out.println(e.getMessage().toString());
			e.printStackTrace();
		}

		return fileData;

	}

	@Override
	public void renameFile(String currentFileName, String newFileName) throws RemoteException {

		File path = new File(SERVER_DIRECTORY + currentFileName);
		File neFile = new File(SERVER_DIRECTORY + newFileName);
		path.renameTo(neFile);

	}

	@Override
	public void delteFile(String fileName) throws RemoteException {
		File path = new File(SERVER_DIRECTORY + fileName);
		boolean isDeleted = path.delete();
		if (isDeleted) {
			System.out.print("File Successfully Deleted ....");
		} else {
			System.out.print("Failed to Delete File....");
		}
	}

}
