
import java.rmi.Naming;
import java.util.LinkedList;

public class FileClient implements Runnable {

	LinkedList<Job> inQueue;

	// In/Out Queue
	public FileClient(LinkedList<Job> inQueue) {
		this.inQueue = inQueue;
	}

	public Job getFollowingTask() {
		return this.inQueue.poll();
	}

	@Override
	public void run() {
		try {
			while (true) {
				Job currentTask = getFollowingTask();
				if (currentTask != null) {
					String operation = currentTask.getOperation();
					String fileName = currentTask.getFileName();
					System.out.println("-------------- New Thread is Running--------------");
					FileOperations stub = (FileOperations) Naming.lookup("rmi://localhost:8080/FileOperations");
					Thread.sleep(10000);
					if (operation.equals("UPLOAD")) {
						System.out.println("-------------- Performing Upload Operation. --------------");
						FileUploadClient.fileUpload(stub, fileName);
						System.out.println("-------------- File Upload Is Sucessfull. --------------");

					} else if (operation.equals("DOWNLOAD")) {
						System.out.println("-------------- Performing Download Operation. --------------");
						FileDownloadClient.downloadFile(stub, fileName);
						System.out.println("-------------- File Download Is Sucessfull. --------------");

					} else if (operation.equals("RENAME")) {
						System.out.println("-------------- Performing File Rename Operation. --------------");
						FileRenameClient.renameFile(stub, fileName, currentTask.getNewFileName());
						System.out.println("-------------- File Rename Is Sucessfull. --------------");

					} else if (operation.equals("DELETE")) {
						System.out.println("-------------- Performing File Delete Operation. --------------");
						FileDeleteClient.deleteFile(stub, fileName);
						System.out.println("-------------- File Delete Is Sucessfull. --------------");
					}
					
				}

			}

		} catch (Exception e) {
			System.err.println("File Client Exception : " + e.toString());
			e.printStackTrace();
		}

	}
}
