package modelo.dao;

import java.util.List;
import jakarta.persistence.*;
import modelo.entities.*;

public class PedidoDAO {

	public void salvar(Pedido p) {
		EntityManager em = ConnectionFactory.getEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
	}

	public List<Pedido> getList() {
		EntityManager em = ConnectionFactory.getEntityManager();
		TypedQuery<Pedido> consulta = em.createQuery("Select p from Pedido p", Pedido.class);
		List<Pedido> pedidos = consulta.getResultList();
		em.close();
		return pedidos;
	}
}
