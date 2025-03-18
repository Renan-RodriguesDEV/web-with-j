package util;

import modelo.dao.ClienteDAO;
import modelo.entities.Cliente;

public class InsereCliente {
	public static void main(String[] args) {
		ClienteDAO dao = new ClienteDAO();
		Cliente c = new Cliente();
		c.setNome("Renan de Souza Rodrigues");
		c.setEmail("renanrodrigues7110@gmail.com");
		c.setSenha("root");
		dao.salvar(c);
		System.out.println("Cliente cadastrado!");
	}
}
