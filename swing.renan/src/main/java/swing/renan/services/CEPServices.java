package swing.renan.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

import swing.renan.entities.Endereco;

public class CEPServices {

	public String query(String cep) {
		String addressAPI = "http://viacep.com.br/ws/" + cep + "/json";
		HttpRequest requests = HttpRequest.newBuilder().uri(URI.create(addressAPI)).GET().build();
		HttpClient client = HttpClient.newHttpClient();
		try {
			HttpResponse<String> response = client.send(requests, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() <= 299) {
				System.out.println(response.body());
				return response.body();
			}else {
				return "{\nStatus code:"+response.statusCode()+"\nMessage:"+response.body()+"\n}";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return e.getMessage();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return e.getMessage();
		}

		throw new RuntimeException("Internal server error"+"500");
	}
	
	//função para converter o json do body em classe java
	public Endereco JSONparseAddress(String body) {
		Gson gson=new Gson(); 
		return gson.fromJson(body, Endereco.class);
	}
}
