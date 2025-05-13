package mbeans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@RequestScoped
@Named
public class EventosMBean {
	private String texto = "Oi tudo bem!";

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void ouvinte() {
		System.out.println("Primeiro, dispara actionListener()");
		texto =

				"Tamo junto! É noise!";

	}

	public String acao() {
// Grava no Banco
		System.out.println("Depois, executa a Lógica de " +

				"negócio com action()");

		return null;
	}
}