package util;

import modelo.dao.VeiculoDAO;
import modelo.entities.Veiculo;
public class TesteDB {
	public static void main(String[] args) {
		Veiculo v = new Veiculo();
		v.setMarca("Ferrari");
		v.setModelo("F50");
		v.setAno(2022);
		v.setPreco(1_500_000);		
		VeiculoDAO dao = new VeiculoDAO();
		dao.salvar(v);
		System.out.println("Veículo cadastrado!");
	}
}

