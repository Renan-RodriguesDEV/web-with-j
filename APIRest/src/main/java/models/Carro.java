package models;

import jakarta.persistence.*;

@Entity
@Table(name="carros")
public class Carro{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String nome;
	public Carro() {
		// TODO Auto-generated constructor stub
	}
	public Carro(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}