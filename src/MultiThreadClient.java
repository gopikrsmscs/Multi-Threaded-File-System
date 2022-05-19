import java.util.LinkedList;
import java.util.Scanner;

public class MultiThreadClient {

	private static FileClient fileClient;
	private static Thread worker;
	private static LinkedList<Job> inQueue = new LinkedList<Job>();

	public static void main(String args[]) {
		System.out.println("****************** Multi Thread Client is Running*********************");
		
		lp: while (true) {
			try {
				System.out.println("Enter the Operation You want to perform : ");
				Scanner sc = new Scanner(System.in);
				String line = sc.nextLine();
				String input[] = line.split(" ");
				String operation = input[0];
				Job job = new Job();
				
				if (operation.equals("UPLOAD") || operation.equals("DOWNLOAD") || operation.equals("DELETE")) {
					job.setOperation(operation);
					job.setFileName(input[1]);

				} else if (operation.equals("RENAME")) {
					job.setOperation(operation);
					job.setFileName(input[1]);
					job.setNewFileName(input[2]);

				} else {
					break lp;
				}
				inQueue.add(job);
				fileClient = new FileClient(inQueue);
				worker = new Thread(fileClient);

				if (worker.isAlive() != true) {
					worker.start();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
