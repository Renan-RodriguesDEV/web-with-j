package apis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class TestAPI {

	// Classe modelo para armazenar os dados do ViaCEP
	class Endereco {
		private String cep;
		private String logradouro;
		private String complemento;
		private String bairro;
		private String localidade;
		private String uf;

		// Getters
		public String getCep() {
			return cep;
		}

		public String getLogradouro() {
			return logradouro;
		}

		public String getComplemento() {
			return complemento;
		}

		public String getBairro() {
			return bairro;
		}

		public String getLocalidade() {
			return localidade;
		}

		public String getUf() {
			return uf;
		}

		@Override
		public String toString() {
			return "CEP: " + cep + "\nLogradouro: " + logradouro + "\nBairro: " + bairro + "\nCidade: " + localidade
					+ "\nUF: " + uf;
		}
	}

	public static void main(String[] args) {
		String cep = "01001000"; // CEP de exemplo
		String url = "https://viacep.com.br/ws/" + cep + "/json/";

		try {
			// Criar conexão HTTP
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			// Verificar status da resposta
			if (conn.getResponseCode() != 200) {
				System.out.println("Erro na requisição! Código: " + conn.getResponseCode());
				return;
			}

			// Ler a resposta JSON
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				response.append(line);
			}
			conn.disconnect();

			// Converter JSON para Objeto Java (Usando Gson)
			Gson gson = new Gson();
			Endereco endereco = gson.fromJson(response.toString(), Endereco.class);

			// Exibir os dados
			System.out.println(endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
