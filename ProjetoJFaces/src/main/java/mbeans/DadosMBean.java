package mbeans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import modelo.entities.Dados;

@RequestScoped
@Named
public class DadosMBean {
	private Dados dados = new Dados();

	public Dados getDados() {
		return dados;
	}

	public void setDados(Dados dados) {
		this.dados = dados;
	}

	public String salvar() {
// Dados salvos no banco de dados
		return "RespostaDados";
	}
}