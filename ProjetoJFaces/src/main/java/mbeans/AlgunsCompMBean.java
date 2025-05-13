package mbeans;

import java.util.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

@RequestScoped
@Named
public class AlgunsCompMBean {
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<String> completaTexto(String query) {
// Buscaria no banco de dados
		return Arrays.asList(new String[] { "Bolacha", "Biscoito", "Sorvete" });

	}

	public void selecionou(SelectEvent<String> event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Seleção", event.getObject()));

	}

	public void enviar() {
		System.out.println("Enviando...");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Confirmado", descricao));

	}
}