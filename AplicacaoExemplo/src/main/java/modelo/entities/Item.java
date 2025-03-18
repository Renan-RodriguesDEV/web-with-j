package modelo.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Item {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	private Produto produto;
	private int quantidade;
	@Column(precision = 10, scale = 2)
	private BigDecimal precoVenda;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}
}
