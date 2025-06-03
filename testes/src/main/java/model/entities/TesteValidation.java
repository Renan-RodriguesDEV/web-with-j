package model.entities;

// importações necessarias 
import java.util.Date;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

// definindo a entidade
@Entity
@Table(name = "produtos")
public class TesteValidation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// campo não pode ser em branco
	@NotBlank(message = "Obrigatorio!! não deve ser branco")
	private String descricao;
	// valor minimo é 0.0 em decimal
	@DecimalMin(value = "0.0", message = "Somente positivos")
	private double preco;
	// campo de data, restrição para futuro somente!
	@Temporal(TemporalType.DATE)
	@Future(message = "Somente datas futuras")
	private Date validade;

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

}
