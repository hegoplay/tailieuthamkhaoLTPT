package run;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;

import entities.Customer;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 8603);
			System.out.println("Connected to server");
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			out.writeObject("list");
			out.writeUTF("Mix");
			out.writeUTF(LocalDateTime.of(2021,1,1,0,0).toString());
			out.writeUTF(LocalDateTime.of(2024,1,1,0,0).toString());
			out.flush();
			try {
				List<Customer> customers = (List<Customer>) in.readObject();
				customers.forEach(System.out::println);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.writeObject("exit");
			out.flush();
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
