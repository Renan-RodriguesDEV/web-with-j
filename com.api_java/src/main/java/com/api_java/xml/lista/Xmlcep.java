package com.api_java.xml.lista;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Xmlcep {
	private Enderecos enderecos;

	public Enderecos getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(Enderecos enderecos) {
		this.enderecos = enderecos;
	}
}

