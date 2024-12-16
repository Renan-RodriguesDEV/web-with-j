package aplication;

import java.util.Date;

import javax.persistence.*;

import modelo.PessoaEntity;
import modelo.Rg;

public class RelacaoTabela {

	public static void main(String[] args) {

		Rg rg = new Rg(1234, new Date(), "SP");
		PessoaEntity p = new PessoaEntity("Renan", rg);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			System.out.println("Sucess, person insert"+p.getNome());
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Deu Falha");
		}finally{
		
			em.close();
			emf.close();
		}

	}



}
