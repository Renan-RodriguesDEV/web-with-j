package util;

import modelo.dao.ClienteDAO;
import modelo.entities.Cliente;

public class InsereCliente {
	public static void main(String[] args) {
		ClienteDAO dao = new ClienteDAO();
		Cliente c = new Cliente();
		c.setNome("Asdrubal");
		c.setEmail("a@a.com");
		c.setSenha("123");
		dao.salvar(c);
		System.out.println("Cliente cadastrado!");
	}
}
