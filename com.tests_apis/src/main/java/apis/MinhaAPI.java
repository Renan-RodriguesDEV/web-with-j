package apis;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import apis.TestAPI.Endereco;

public class MinhaAPI {
	public static String cep = "18734352";
	public static String addressURI = "https://viacep.com.br/ws/" + cep + "/json";

	// HttpURLConnection httpConnection = (HttpURLConnection) new
	// URL(addressURI).openConnection();
	public static void main(String[] args) {
		try {
			@SuppressWarnings({"deprecation" })
			//abrindo uma conexão através do "cast" de URL para HttpURLConnection
			HttpURLConnection httpConnection = (HttpURLConnection) new URL(addressURI).openConnection();
			
			//alterando o metodo de requisição
			httpConnection.setRequestMethod("GET");
			//alterando a propriedade da resposta para json
			httpConnection.setRequestProperty("Accept", "application/json");
			
			//verificando o codigo da resposta
			if (httpConnection.getResponseCode() != 200) {
				System.out.println("erro");
			} else {
				
				//pegando o InputStream da conexão (fluxo de entrada)
				InputStream is = httpConnection.getInputStream();
				// transformando o inputStream em inputStreamReader -> (leitor do fluxo de entrada)
				InputStreamReader isr = new InputStreamReader(is);
				//estanciando o BufferReader, leitor de buffer passando o leitor do fluxo de entrada
				BufferedReader buffread = new BufferedReader(isr);
				
				//resposta da API
				StringBuilder response = new StringBuilder();
				// str temporaria para acresentar no StringBuilder
				String str;
				
				// enquanto houver readline (str = readline)
				// readline (pega o conteudo lido pelo bufferReader do fluxo de entrada
				while ((str = buffread.readLine()) != null) {
					// acrescenta no response (resposta da API)
					response.append(str);
				}
				
				//fecha conexão
				httpConnection.disconnect();
				
				//estancia do JSON
				Gson json = new Gson()
;				
				// converte a response JSON em Classe
				Endereco end = json.fromJson(response.toString(), Endereco.class);
				System.out.println("Sua resposta:"+end);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
