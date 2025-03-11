package modelo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import modelo.entities.Veiculo;

public class VeiculoDAO {
	public List<Veiculo> getList() {
		EntityManager em = ConnectionFactory.getEntityManager();
		TypedQuery<Veiculo> consulta = em.createQuery("Select v from Veiculo v order by v.modelo", Veiculo.class);
		List<Veiculo> termos = consulta.getResultList();
		em.close();
		return termos;
	}

	public void salvar(Veiculo v) {
		EntityManager em = ConnectionFactory.getEntityManager();
		em.getTransaction().begin();
		if (v.getId() == 0) {
			em.persist(v);
		} else {
			em.merge(v);
		}
		em.getTransaction().commit();
		em.close();
	}

	public Veiculo getVeiculo(int id) {
		EntityManager em = ConnectionFactory.getEntityManager();
		Veiculo v = em.find(Veiculo.class, id);
		em.close();
		return v;
	}

	public boolean excluir(int id) {
		EntityManager em = ConnectionFactory.getEntityManager();
		em.getTransaction().begin();
		Veiculo v = em.find(Veiculo.class, id);
		boolean excluido = false;
		if (v != null) {
			em.remove(v);
			excluido = true;
		}
		em.getTransaction().commit();
		em.close();
		return excluido;
	}
}
