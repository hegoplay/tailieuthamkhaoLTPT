package run;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.List;

import dao.CustomerDAO;
import entities.Customer;


public class ServerTCP {
	public static void main(String[] args) throws IOException {
//		write for me new cached thread pool
		
		ServerSocket server = new ServerSocket(8603);
		System.out.println("Server started at " + LocalDateTime.now());
		while (true) {
			new Thread(new ServerThread(server.accept())).start();
		}
	}
}	

class ServerThread implements Runnable{

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public ServerThread(Socket socket) throws IOException {
		super();
		this.socket = socket;
		in = new ObjectInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				String message = (String) in.readObject();
				if (message.equals("exit")) {
					break;
				}
				if(message.equalsIgnoreCase("list")){
					String productName = in.readUTF();
					String beginDate = in.readUTF();
					String endDate = in.readUTF();
					List<Customer> customers = CustomerDAO.listCustomers(productName, beginDate, endDate);
					out.writeObject(customers);
					out.flush();
				}
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
		try {
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
