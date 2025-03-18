package modelo.dao;

import java.util.List;
import jakarta.persistence.*;
import modelo.entities.*;

public class ClienteDAO {

	public void salvar(Cliente c) {
		EntityManager em = ConnectionFactory.getEntityManager();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.close();
	}

	public List<Cliente> getList() {
		EntityManager em = ConnectionFactory.getEntityManager();
		TypedQuery<Cliente> consulta = em.createQuery("Select c from Cliente c", Cliente.class);
		List<Cliente> clientes = consulta.getResultList();
		em.close();
		return clientes;
	}

	public Cliente getByEmail(String email) {
		try {
			EntityManager em = ConnectionFactory.getEntityManager();
			TypedQuery<Cliente> consulta = em.createQuery("Select c from Cliente c where c.email = :email",
					Cliente.class);
			consulta.setParameter("email", email);
			Cliente c = consulta.getSingleResult();
			em.close();
			return c;
		} catch (NoResultException ex) {
			return null;
		}
	}
}
