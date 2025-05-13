package modelo.dao;

import java.util.List;
import jakarta.persistence.*;
import modelo.entities.*;

public class FuncionarioDAO {
	public void salvar(Funcionario f) {
		EntityManager em = ConnectionFactory.getEntityManager();
		em.getTransaction().begin();
		em.merge(f);
		em.getTransaction().commit();
		em.close();
	}

	public void inserir(Funcionario f) {
		EntityManager em = ConnectionFactory.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(f);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public List<Funcionario> getList() {
		EntityManager em = ConnectionFactory.getEntityManager();
		TypedQuery<Funcionario> consulta = em.createQuery("Select f from Funcionario f", Funcionario.class);
		List<Funcionario> funcionarios = consulta.getResultList();
		em.close();
		return funcionarios;
	}

	public Funcionario getFuncionario(Long id) {
		EntityManager em = ConnectionFactory.getEntityManager();
		Funcionario f = em.find(Funcionario.class, id);
		em.close();
		return f;
	}

	public boolean excluir(Long id) {
		EntityManager em = ConnectionFactory.getEntityManager();
		em.getTransaction().begin();
		Funcionario f = em.find(Funcionario.class, id);
		boolean excluido = false;
		if (f != null) {
			em.remove(f);
			excluido = true;
		}
		em.getTransaction().commit();
		em.close();
		return excluido;
	}

	public List<Funcionario> getList(int inicio, int maximo, String clausulaWhere, String campo, String ordem) {
		EntityManager em = ConnectionFactory.getEntityManager();
		String ordenacao = campo + " " + ordem;

		String jpql =

				"Select f from Funcionario f " + clausulaWhere;

		if (!ordenacao.isBlank()) {
			jpql += " order by " + ordenacao;
		}
		System.out.println("JPQL: " + jpql);
		TypedQuery<Funcionario> consulta =

				em.createQuery(jpql, Funcionario.class);

		List<Funcionario> funcionarios =

				consulta.setMaxResults(maximo).setFirstResult(inicio).getResultList();

		em.close();
		return funcionarios;
	}

	public long qtdFuncionarios(String clausulaWhere) {
		EntityManager em = ConnectionFactory.getEntityManager();
		String jpql =

				"select count(f) from Funcionario f "

						+ clausulaWhere;
		TypedQuery<Long> consulta = em.createQuery(jpql, Long.class);
		long qtd = consulta.getSingleResult().longValue();
		em.close();
		return qtd;
	}
}