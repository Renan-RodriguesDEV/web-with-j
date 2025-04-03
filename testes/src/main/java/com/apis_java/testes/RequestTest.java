package com.apis_java.testes;

import java.net.URI;
import java.net.http.*;
import java.util.Scanner;

import com.google.gson.Gson;

public class RequestTest {
	public static void main(String[] args) {
		System.out.println("Insira um CEP");
		Scanner getInput = new Scanner(System.in);
		String cep = getInput.next();
		String url = "http://viacep.com.br/ws/" + cep + "/json";
		System.out.println(url);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
		try {

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() != 200) {
				throw new Exception("não achei o CEP");
			}
			Gson json = new Gson();
			var body = response.body();
			System.out.println(body);
			Endereco endereco = json.fromJson(body, Endereco.class);
			System.out.println("Endereço: "+endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			getInput.close();
		}
	}
}