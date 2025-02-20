package apis;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TesteSpotify {

    public static void main(String[] args) {
        try {
            // Criando a requisição HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://spotify23.p.rapidapi.com/artists/?ids=2YWOP324cKGfkTo2PydgJR"))
                    .header("x-rapidapi-key", "a732967d4bmsh28d07e8ea0b16ecp1cfe1cjsn4271543d1a51") // Substitua pela sua chave real
                    .header("x-rapidapi-host", "spotify23.p.rapidapi.com")
                    .GET()
                    .build();

            // Criando o cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Enviando a requisição e obtendo resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Exibindo a resposta
            System.out.println(response.body());

        } catch (Exception e) {
            System.err.println("Erro ao fazer a requisição: " + e.getMessage());
        }
    }
}
