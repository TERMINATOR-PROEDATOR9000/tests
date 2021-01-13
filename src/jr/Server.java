package jr;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    public static void main(String[] args) {
	int port = 4444;

	try (ServerSocket serverSocket = new ServerSocket(port)) {
	    while (true) {
		Socket socket = serverSocket.accept();
		new Handler(socket).start();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private static class Handler extends Thread {
	private Socket socket;

	public Handler(Socket socket) {
	    this.socket = socket;
	}

	@Override
	public void run() {
	    try (Connection connection = new Connection(socket)) {
		while (true) {
		    String message = connection.receive();

		    if (message.equals("exit"))
			break;

		    System.out.println(message);

		    connection.send("Echo: " + message);
		}
	    } catch (Exception e) {
	    }
	}

    }
}

class Connection implements Closeable {
    private final Socket socket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;

    public Connection(Socket socket) throws Exception {	
	    this.socket = socket;
	    this.out = new ObjectOutputStream(socket.getOutputStream());
	    this.in = new ObjectInputStream(socket.getInputStream());	    	
    }

    public void send(String message) throws Exception {
	out.writeObject(message);
    }

    public String receive() throws Exception {
	return (String) in.readObject();
    }

    @Override
    public void close() throws IOException {
	in.close();
	out.close();
	socket.close();
    }
}

class Client {
    private Connection connection;

    private String getServerAddress() {
	return "localhost";
    }

    private int getServerPort() {
	return 4444;
    }

    public void run() {
	BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

	try {
	    connection = new Connection(new Socket(getServerAddress(), getServerPort()));

	    SocketThread socketThread = new SocketThread();
	    socketThread.setDaemon(true);
	    socketThread.start();

	    while (true) {
		String text = bis.readLine();
		if (text.equalsIgnoreCase("exit"))
		    break;
		connection.send(text);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	Client client = new Client();
	client.run();
    }

    public class SocketThread extends Thread {
	@Override
	public void run() {
	    try {
		while (true) {
		    String message = connection.receive();
		    System.out.println(message);
		}

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

}
