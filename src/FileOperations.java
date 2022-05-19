
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileOperations extends Remote {

	void uploadFile(byte[] fileByteData, String fileName) throws RemoteException;

	byte[] downloadFile(String serverFileName) throws RemoteException;

	void renameFile(String currentFileName, String newFileName) throws RemoteException;

	void delteFile(String filePath) throws RemoteException;

}
