package connect;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ConnectDB {
	public EntityManager manager;
	public EntityManagerFactory factory;
	
	public static ConnectDB instance = new ConnectDB();
	
	private ConnectDB() {
		factory = jakarta.persistence.Persistence.createEntityManagerFactory("JPA_ORM_MSSQL");
		manager = factory.createEntityManager();
	}
	
	public void close() {
		manager.close();
		factory.close();
	}
}
