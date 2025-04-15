package mbeans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import modelo.entities.Pessoa;

@RequestScoped
@Named
public class PessoaMBean {

	private Pessoa pessoa = new Pessoa();

	public Pessoa getPessoa() {
		return pessoa;

	}

	public void setPessoa(Pessoa pessoa) {

		this.pessoa = pessoa;

	}

// Método que aciona a lógica de negócio e retorna o outcome
// para onde o fluxo será direcionado.
	public String receberNome() {

		if (!pessoa.getNome().isEmpty()) {
			return "sucesso";

		}
		return "falha";

	}
}