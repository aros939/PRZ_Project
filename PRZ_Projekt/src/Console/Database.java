package Console;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager em;
	
	private static long loggedUserId;
	
	

	public static EntityManager getEntityManager(){
		if(entityManagerFactory == null)
			entityManagerFactory =  Persistence.createEntityManagerFactory("myDatabase");
		if(em == null)
			em=entityManagerFactory.createEntityManager();
		return em;
	}


	public static long getLoggedUserId() {
		return loggedUserId;
	}


	public static void setLoggedUserId(long id) {
		loggedUserId = id;
	}
	
	
	
	

}
