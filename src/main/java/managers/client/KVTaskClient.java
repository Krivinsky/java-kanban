package managers.client;

import exeptions.ManagerLoadException;
import exeptions.ManagerSaveException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class KVTaskClient {
    private String url;
    private String apiToken;

    public KVTaskClient(int port) {
        url = "http://localhost:" + port;
        apiToken = register(url);
    }

    private String register(String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url + "/register"))
                    .GET()
                    .build();
            HttpResponse<String>  response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != HttpURLConnection.HTTP_OK) {
                throw new ManagerSaveException("Ошибка" + response.statusCode());
            }
            return response.body();
        } catch (Exception e) {
            throw new ManagerSaveException(e); //todo создать свое исключение
        }


        return null;
    }


    public String put(String key, String json) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url + ))
        }
        return null;
    }

    public String load(String key) {
        try {

        } catch (IOException e) {
            throw new ManagerLoadException(e);
        }



        return null;
    }
}
