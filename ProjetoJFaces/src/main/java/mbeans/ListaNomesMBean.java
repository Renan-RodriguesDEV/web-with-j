package mbeans;

import java.io.Serializable;
import java.util.*;
import jakarta.enterprise.context.*;
import jakarta.inject.Named;

@SessionScoped
@Named
public class ListaNomesMBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<String> lista = new ArrayList<>();
	private String nome;

	public void adicionaComActionListener() {
		lista.add(nome);
	}

	public String adicionaComAction() {
		lista.add(nome);
		return null;
	}

	public List<String> getLista() {
		return lista;
	}

	public void setLista(List<String> lista) {
		this.lista = lista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}