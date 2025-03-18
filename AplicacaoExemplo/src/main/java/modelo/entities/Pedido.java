package modelo.entities;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Pedido {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	private Cliente cliente;
	@Temporal(TemporalType.DATE)
	private Date emissao;
	@OneToMany(cascade = { CascadeType.ALL })
	private List<Item> itens = new ArrayList<Item>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getEmissao() {
		return emissao;
	}

	public void setEmissao(Date emissao) {
		this.emissao = emissao;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
}
