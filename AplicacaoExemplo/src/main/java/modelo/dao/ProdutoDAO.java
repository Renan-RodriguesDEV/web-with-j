package modelo.dao;

import java.util.List;
import jakarta.persistence.*;
import modelo.entities.*;

public class ProdutoDAO {

	public void salvar(Produto p) {
		EntityManager em = ConnectionFactory.getEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
	}

	public List<Produto> getList() {
		EntityManager em = ConnectionFactory.getEntityManager();
		TypedQuery<Produto> consulta = em.createQuery("Select p from Produto p", Produto.class);
		List<Produto> produtos = consulta.getResultList();
		em.close();
		return produtos;
	}

	public Produto getById(int id) {
		EntityManager em = ConnectionFactory.getEntityManager();
		Produto p = em.find(Produto.class, id);
		em.close();
		return p;
	}
}
