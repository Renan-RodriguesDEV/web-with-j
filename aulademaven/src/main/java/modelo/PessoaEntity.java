package modelo;

import javax.persistence.*;

@Entity
public class PessoaEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	@OneToOne(cascade = {CascadeType.ALL})
	private Rg rg;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Rg getRg() {
		return rg;
	}
	public void setRg(Rg rg) {
		this.rg = rg;
	}
	
	public PessoaEntity() {
		
	};
	
	public PessoaEntity(String nome, Rg rg) {
		super();
		this.nome = nome;
		this.rg = rg;
	}
	
	
}
