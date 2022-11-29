package fi.faunasvc.svc1;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {

    private static String myurl = "http://localhost:8471/sample-api/1.0/ducks/red";
    // "https://postman-echo.com/get"

    public static void main(String[] args) {
        System.out.println("Hello World - uSvc");
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(myurl))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
