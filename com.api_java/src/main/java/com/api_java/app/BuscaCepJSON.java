package com.api_java.app;

import java.io.InputStream;
import java.net.URL;
import javax.json.stream.JsonParser.Event;
import javax.json.Json;
import javax.json.stream.JsonParser;

public class BuscaCepJSON {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://viacep.com.br/ws/SP/Avare/Arlindo/json/");
			InputStream is = url.openStream();
			JsonParser parser = Json.createParser(is);
			String logradouro = null, complemento = null;
			String bairro = null, cep = null;
			StringBuilder texto = new StringBuilder();

			while (parser.hasNext()) {
				Event e = parser.next();
				if (e == Event.KEY_NAME) {
					switch (parser.getString()) {
					case "cep":
						parser.next();
						cep = parser.getString();
						texto.append(cep);
						texto.append(" - ");
						break;
					case "logradouro":
						parser.next();
						logradouro = parser.getString();
						texto.append(logradouro);
						texto.append(" - ");
						break;
					case "complemento":
						parser.next();
						complemento = parser.getString();
						texto.append(complemento);
						texto.append(" - ");
						break;
					case "bairro":
						parser.next();
						bairro = parser.getString();
						texto.append(bairro);
						System.out.println(texto);
						texto.delete(0, texto.length());
						break;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
