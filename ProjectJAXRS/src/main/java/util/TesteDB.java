package util;

import modelo.dao.VeiculoDAO;
import modelo.entities.Veiculo;
public class TesteDB {
	public static void main(String[] args) {
		Veiculo v = new Veiculo();
		v.setMarca("Maclaren");
		v.setModelo("Senna");
		v.setAno(2029);
		v.setPreco(5_500_000);		
		VeiculoDAO dao = new VeiculoDAO();
		dao.salvar(v);
		System.out.println("Veículo cadastrado!");
	}
}

