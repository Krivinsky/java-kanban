package managers.client;

public class KVTaskClient {
    private String url;
    private String apiToken;

    public KVTaskClient(int port) {
        url = "http://localhost:" + port + "/";
        apiToken = register(url);
    }

    private String register(String url) {
        return null;
    }

    public String load(String key) {
        return null;
    }

    public String put(String key, String json) {
        return null;
    }
}
