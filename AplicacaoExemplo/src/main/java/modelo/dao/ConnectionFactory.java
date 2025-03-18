package modelo.dao;

import jakarta.persistence.*;

public class ConnectionFactory {
	private static final EntityManagerFactory fabrica = 
		Persistence.createEntityManagerFactory("AplicacaoExemplo");
	
	public static EntityManager getEntityManager() {
		return fabrica.createEntityManager();
	}
}
