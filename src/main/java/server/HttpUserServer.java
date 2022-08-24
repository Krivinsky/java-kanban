package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import managers.Managers;
import managers.TaskManager;
import managers.UserManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpUserServer {
    public static  final int PORT = 8080;
    private HttpServer server;
    private Gson gson;

    private UserManager userManager;
    private TaskManager taskManager;

    public HttpUserServer() throws IOException {
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

                    if(Pattern.matches("^/api/v1/users$", path)) {
                        final String response = gson.toJson(userManager.getAll());
                        sendText(exchange, response);
                        return;
                    }

                    break;
                }
                case  "DELETE" : {
                    if(Pattern.matches("^/api/v1/users\\d+$", path)) {
                        String idString = path.replaceFirst("/api/v1/users", "");
                        int id = parsePathId(idString);
                        if (id != -1) {
                            userManager.delete(id);
                            System.out.println("Удалили пользователя с id - " + id);
                            exchange.sendResponseHeaders(200,0);
                        }
                    }

                    break;
                }
                default:{
                    System.out.println(requestMethod + " - неверный запрос");
                    exchange.sendResponseHeaders(405, 0);
                }
            }
        } catch (Exception ex) {

        } finally {
            exchange.close();
        }
    }

    private int parsePathId(String idString) {
        try {
            return Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            return -1;
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
