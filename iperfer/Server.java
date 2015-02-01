import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private int port;

	Server(int port) {
		this.port = port;
	}

	void execute() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			Socket clientSocket = serverSocket.accept();
			long time = System.currentTimeMillis();
			byte data[] = new byte[1000];
			long dataReceived = 0;
			long n;
			InputStream inputStream = clientSocket.getInputStream();
			while ((n = inputStream.read(data)) != -1) {
				dataReceived += n;
			}
			clientSocket.close();
			serverSocket.close();
			time -= System.currentTimeMillis();
			time /= 1000;
			dataReceived /= 1000;
			double rate = (dataReceived * 8.0) / (1000 * time);
			System.out.println("received=" + dataReceived + " KB rate="
					+ String.format("%.3s", rate) + " Mbps");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
