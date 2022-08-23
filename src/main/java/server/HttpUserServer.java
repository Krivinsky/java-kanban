package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import managers.Managers;
import managers.TaskManager;
import managers.UserManager;

import java.io.IOException;
import java.net.InetSocketAddress;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpUserServer {
    public static  final int PORT = 8080;
    private HttpServer server;
    private Gson gson;

    private UserManager userManager;
    private TaskManager taskManager;

    public HttpUserServer() {
        this(Managers.getDefaultUser());
        }

    public HttpUserServer(UserManager userManager) throws IOException {
        this.userManager = userManager;
        this.taskManager = userManager.getTaskManager();
        gson = Managers.getGson();
        server = HttpServer.create(new InetSocketAddress("localhost", PORT), 0);
        server.createContext("/api/v1/users", this::handleUsers);
    }

    private void handleUsers(HttpExchange exchange) {
        try {
            System.out.println("\n/api/v1/users: " + exchange.getRequestURI());
            String requestMethod = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            switch (requestMethod) {
                case "GET" :  {

                    break;
                }
                case  "DELETE" : {

                    break;
                }
                default:{
                    System.out.println(requestMethod + " - неверный запрос");
                }
            }
        } catch (Exception ex) {

        } finally {
            exchange.close();
        }
    }

    public void start() {
        System.out.println("Запускаем сервер на порту " + PORT);
        System.out.println("Открой в браузере http://localhost:" + PORT + "/");
        server.start();
    }

    public void stop() {
        server.stop(0);
        System.out.println("Остановили сервер на порту" + PORT);
    }

    protected String readText(HttpExchange h) throws IOException {
        return new String(h.getRequestBody().readAllBytes(), UTF_8);
    }

    protected void sendText(HttpExchange h, String text) throws IOException {
        byte[] resp = text.getBytes(UTF_8);
        h.getResponseHeaders().add("Content-Type", "application/json");
        h.sendResponseHeaders(200, resp.length);
        h.getResponseBody().write(resp);
    }
}
