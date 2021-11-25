package fi.faunausvc.faunafarm;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class FarmController {

    @RequestMapping("farm-api/1.0/info")
    public ResponseEntity<?> farmInfo() {
        return new ResponseEntity<>("info", HttpStatus.OK);
    }

    /* curl http://localhost:8471/farm-api/1.0/id/10 */
    @RequestMapping("farm-api/1.0/ducks/{id}")
    public ResponseEntity<?> findDuck(@PathVariable String id){
        String data = fetchDucks(id);
        String responseText = "Duck info for " + id + ":\n" + data + "\n";
        return new ResponseEntity<>(responseText, HttpStatus.OK);
    }
    private String fetchDucks(String id) {
        String retvalue = "";

        String myurl = "http://localhost:8471/sample-api/1.0/ducks/red";

        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(myurl))
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            retvalue = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retvalue;
    }
}


