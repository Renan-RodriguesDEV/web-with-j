import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.io.IOException;

public class Teste {
    public static void main(String[] args) {
    	try {
    	HttpRequest request = HttpRequest.newBuilder()
    			.uri(URI.create("https://spotify23.p.rapidapi.com/artist_singles/?id=7MiDcPa6UiV3In7lIM71IN&offset=0&limit=20"))
    			.header("x-rapidapi-key", "a732967d4bmsh28d07e8ea0b16ecp1cfe1cjsn4271543d1a51")
    			.header("x-rapidapi-host", "spotify23.p.rapidapi.com").GET().build();
    	HttpClient cliente = HttpClient.newHttpClient();
    	HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
    	System.out.println("Response Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
}
