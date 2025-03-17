package repositories;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Persistence;
import models.Carro;

public class CarroRepository {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("APIRest");

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public List<Carro> findAll() {
		EntityManager em = this.getEntityManager();

		try {
			TypedQuery<Carro> query = em.createQuery("SELECT c FROM Carro c", Carro.class);
			List<Carro> carros = query.getResultList();
			for (Carro carro : carros) {
				System.out.println(carro);
			}
			em.close();
			return carros;
		} catch (Exception e) {
			e.printStackTrace();
			em.close();
			return new ArrayList<Carro>();
		}

	}

	public Carro findByID(Long id) {
		EntityManager em = this.getEntityManager();
		Carro carro = null;
		try {
			TypedQuery<Carro> query = em.createQuery("SELECT c FROM Carro c WHERE c.id=:id", Carro.class);
			query.setParameter("id", id);
			carro = query.getSingleResult();
			em.close();
			return carro;

		} catch (Exception e) {
			em.close();
			e.printStackTrace();
			return carro;
		}

	}

	public void add(Carro carro) {
		EntityManager em = this.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(carro);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	public int update(Long id, String nome) {
		EntityManager em = this.getEntityManager();
		try {
			Carro carro =em.find(Carro.class, id);
			if (carro==null) {
				em.close();
				return 0;
			}
			em.getTransaction().begin();
			carro.setNome(nome);
			em.merge(carro);
			em.getTransaction().commit();
			em.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			em.close();
			return 0;
		}
		
	}
	
	public boolean delete(Long id) {
		EntityManager em = this.getEntityManager();
		em.getTransaction().begin();
		Carro carro = em.find(Carro.class, id);
		if (carro == null) {
			em.close();
			return false;
		}
		try {
			em.remove(carro);
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			em.close();
			return false;
		}
		
	}
}
