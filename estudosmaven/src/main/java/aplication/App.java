package aplication;

import javax.persistence.*;

import models.Arma;
import models.Pistola;

public class App {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
		EntityManager em = emf.createEntityManager();
		
		Arma pistola = new Arma();
		
	}
}
