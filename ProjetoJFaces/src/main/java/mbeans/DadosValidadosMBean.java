package mbeans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import modelo.entities.DadosValidados;

@RequestScoped
@Named
public class DadosValidadosMBean {
	private DadosValidados dados = new DadosValidados();

	public DadosValidados getDados() {
		return dados;
	}

	public void setDados(DadosValidados dados) {
		this.dados = dados;
	}

	public void logica() {
		System.out.println("Lógica acionada.");
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Lógica de Negócio Realizada.",
				"Operação Concluída.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
}