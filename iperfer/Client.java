import java.io.OutputStream;
import java.net.Socket;

public class Client {
	private String host;
	private int port;
	private long time;

	Client(String host, int port, long time) {
		this.host = host;
		this.port = port;
		this.time = time;
	}

	void execute() {
		Socket socket;
		try {
			socket = new Socket(host, port);
			byte data[] = new byte[1000];
			OutputStream outstream = socket.getOutputStream();
			long endTime = System.currentTimeMillis() + time * 1000;
			long numKbSent = 0;
			while (System.currentTimeMillis() < endTime) {
				outstream.write(data);
				outstream.flush();
				numKbSent += 1;
			}
			socket.close();
			double rate = (numKbSent * 8.0) / (1000 * time);
			System.out.println("sent=" + numKbSent + " KB rate="
					+ String.format("%.3f", rate) + " Mbps");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
