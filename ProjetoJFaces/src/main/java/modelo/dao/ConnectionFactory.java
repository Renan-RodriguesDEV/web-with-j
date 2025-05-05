package modelo.dao;

import jakarta.persistence.*;

public class ConnectionFactory {
private static final EntityManagerFactory fabrica =
Persistence.createEntityManagerFactory("ProjetoJFaces");

public static EntityManager getEntityManager() {
return fabrica.createEntityManager();
}
}