package modelo.entities;

import java.util.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class DadosValidados {
	@Min(value = 5, message = "Valor entre 5 e 10")
	@Max(value = 10, message = "Valor entre 5 e 10")
	private int inteiro;
	@DecimalMin("1.0")
	@DecimalMax("5.0")
	private double decimal;

	@Digits

	(integer = 3, fraction = 2)

	private double digitos;

	@Email
	@NotBlank
	private String email;

	@CNPJ
	private String cnpj;

	@NotBlank
	@CPF
	private String cpf;
	@Future
	@NotNull
	private Date futuro;
	@FutureOrPresent
	private Date futuroOuPresente;

	@Past
	private Date passado;
	@PastOrPresent
	private Date passadoOuPresente;

	@Negative
	private double negativo;
	@NegativeOrZero
	private double negativoOuZero;
	@Positive
	private double positivo;
	@PositiveOrZero
	private double positivoOuZero;
	@NotEmpty
	private ArrayList<String> lista = new ArrayList<>();
	@NotEmpty
	@Size(min = 2, max = 5)
	private ArrayList<String> listaLimite = new ArrayList<>();

	// Criar getters e setters
	public int getInteiro() {
		return inteiro;
	}

	public void setInteiro(int inteiro) {
		this.inteiro = inteiro;
	}

	public double getDecimal() {
		return decimal;
	}

	public void setDecimal(double decimal) {
		this.decimal = decimal;
	}

	public double getDigitos() {
		return digitos;
	}

	public void setDigitos(double digitos) {
		this.digitos = digitos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getFuturo() {
		return futuro;
	}

	public void setFuturo(Date futuro) {
		this.futuro = futuro;
	}

	public Date getFuturoOuPresente() {
		return futuroOuPresente;
	}

	public void setFuturoOuPresente(Date futuroOuPresente) {
		this.futuroOuPresente = futuroOuPresente;
	}

	public Date getPassado() {
		return passado;
	}

	public void setPassado(Date passado) {
		this.passado = passado;
	}

	public Date getPassadoOuPresente() {
		return passadoOuPresente;
	}

	public void setPassadoOuPresente(Date passadoOuPresente) {
		this.passadoOuPresente = passadoOuPresente;
	}

	public double getNegativo() {
		return negativo;
	}

	public void setNegativo(double negativo) {
		this.negativo = negativo;
	}

	public double getNegativoOuZero() {
		return negativoOuZero;
	}

	public void setNegativoOuZero(double negativoOuZero) {
		this.negativoOuZero = negativoOuZero;
	}

	public double getPositivo() {
		return positivo;
	}

	public void setPositivo(double positivo) {
		this.positivo = positivo;
	}

	public double getPositivoOuZero() {
		return positivoOuZero;
	}

	public void setPositivoOuZero(double positivoOuZero) {
		this.positivoOuZero = positivoOuZero;
	}

	public ArrayList<String> getLista() {
		return lista;
	}

	public void setLista(ArrayList<String> lista) {
		this.lista = lista;
	}

	public ArrayList<String> getListaLimite() {
		return listaLimite;
	}

	public void setListaLimite(ArrayList<String> listaLimite) {
		this.listaLimite = listaLimite;
	}

}