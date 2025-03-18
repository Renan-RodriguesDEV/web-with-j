package modelo.entities;

import jakarta.persistence.*;
import java.io.*;
import java.security.*;

@Entity
public class Cliente {
	@Id
	@GeneratedValue
	private int id;
	@Column(length = 50, nullable = false)
	private String nome;
	@Column(length = 50, nullable = false)
	private String email;
	@Column(length = 64, nullable = false)
	private String senha;

	public void setSenha(String senha) {
		this.senha = codificaSenha(senha);
	}

	public boolean verificaSenha(String senha) {
		return this.senha.equals(codificaSenha(senha));
	}

	private String codificaSenha(String senha) {
		// Trecho de código extraído do Blog da Caelum
		MessageDigest algorithm;
		String senhaTexto = null;
		try {
			algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			senhaTexto = hexString.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return senhaTexto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}
}
