package modelo.entities;

import java.util.Date;
import java.util.Base64;
import jakarta.persistence.*;
import util.SemImagem;

@Entity
@Table(name="funcionario")
public class Funcionario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cargo;
	@Column(precision = 10, scale = 2)
	private double salario;
	@Temporal(TemporalType.DATE)
	private Date dataAdm;
	private boolean ativo;
	@Lob
	private byte[] foto;

	public String getFotoAsString() {
		if (foto == null) {
			foto = SemImagem.gerar();
		}
		return Base64.getEncoder().encodeToString(foto);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Date getDataAdm() {
		return dataAdm;
	}

	public void setDataAdm(Date dataAdm) {
		this.dataAdm = dataAdm;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

}