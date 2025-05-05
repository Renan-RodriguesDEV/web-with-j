package mbeans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@RequestScoped
@Named
public class CargoMBean {
	private String cargos[] = { "Dono", "Quase dono", "Ajudante" };

	public String[] getCargos() {
		return cargos;
	}

	public void setCargos(String[] cargos) {
		this.cargos = cargos;
	}
}