package com.apis_java.testes;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

public class APIRequests {
	public static void main(String[] args) {
		while (true) {
			String cep = JOptionPane.showInputDialog("Insira seu cep");
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://viacep.com.br/ws/" + cep + "/json"))
					.GET().build();
			HttpClient client = HttpClient.newHttpClient();
			try {
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				if (response.statusCode() == 200) {
					Gson conv = new Gson();
					Endereco endereco = conv.fromJson(response.body(), Endereco.class);
					int isCorrect = JOptionPane.showConfirmDialog(null, endereco.toString());
					if (isCorrect == 0) {
						JOptionPane.showMessageDialog(null, "Endereço correto");
					} else {
						JOptionPane.showMessageDialog(null, "Endereço errado");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Não encontrei seu cep, desculpe!\n" + response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				int continuar = JOptionPane.showConfirmDialog(null, "Deseja continuar?");
				if (continuar != 0) {
					System.out.println("Bye Bye");
					break;
				}
			}
		}
	}
}
