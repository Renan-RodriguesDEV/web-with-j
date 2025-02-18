package com.api_java.app;

import java.io.*;
import java.net.*;
import javax.json.*;
import com.api_java.entities.*;

public class BuscaEnderecoJSON {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://viacep.com.br/ws/18705860/json/");
			InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			is.close();
			Endereco e = new Endereco();
			e.setLogradouro(obj.getString("logradouro"));
			e.setComplemento(obj.getString("complemento"));
			e.setBairro(obj.getString("bairro"));
			e.setCidade(obj.getString("localidade"));
			e.setUf(obj.getString("uf"));
			e.setCep(obj.getString("cep"));
			System.out.print(e);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
