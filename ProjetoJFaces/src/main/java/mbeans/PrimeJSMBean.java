package mbeans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

@RequestScoped
@Named
public class PrimeJSMBean {
	public void logicaMudaEstado() {
		System.out.println("logicaMudaEstado");
		PrimeFaces instance = PrimeFaces.current();
		instance.executeScript("PF('chk').toggle()");
	}
}