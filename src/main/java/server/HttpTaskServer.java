package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import managers.Managers;
import managers.TaskManager;
import tasks.Task;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpTaskServer {

    public static  final int PORT = 8080;
    private HttpServer server;
    private Gson gson;
    private TaskManager taskManager;

    public HttpTaskServer() throws IOException {
        this(Managers.getDefault());
        gson = Managers.getGson();
        server = HttpServer.create(new InetSocketAddress("localhost", PORT), 0);
        server.createContext("/tasks", this::handler);
    }

    public static void main(String[] args) throws IOException {
        final HttpTaskServer httpTaskServer = new HttpTaskServer();
        httpTaskServer.start();
//        httpTaskServer.stop();
    }

    private void handler(HttpExchange exchange) {
        try {
            System.out.println("/\tasks" + exchange.getRequestURI());   //todo проверить "/\tasks"
            final String path = exchange.getRequestURI().getPath().replaceFirst("/tasks", "");
            switch (path) {
                case "task":
                    handleTasks(exchange);
                    break;
                case "subtask":
                    handleSubtask(exchange);
                    break;
                case "epic":
                    handleEpic(exchange);
                    break;
                default:
            }
        } catch (IOException exception) {

        } finally {
            exchange.close();
        }
    }

    private void handleEpic(HttpExchange exchange) {
        //todo method handleEpic
    }

    private void handleSubtask(HttpExchange exchange) {
        //todo method handleSubtask
    }

    private void handleTasks(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        String query = exchange.getRequestURI().getQuery();
        switch (requestMethod) {
            case "GET" :
                if (Objects.nonNull(query)) {
                    String idString = query.substring(2);
                    int id = Integer.parseInt(idString);
                    Task task = taskManager.getTaskFromId(id);
                    String response = gson.toJson(task);
                    sendText(exchange, response);
                    break;
                } else {

                }
        }
    }

    public HttpTaskServer(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void start() {
        System.out.println("Запускаем сервер Задач " + PORT);
        System.out.println("http://localhost:" + PORT + "/tasks");
        server.start();
    }

    public void stop() {
        server.stop(0);
        System.out.println("Остановили сервер на порту" + PORT);
    }

    protected String readText(HttpExchange exchange) throws IOException {
        return new String(exchange.getRequestBody().readAllBytes(), UTF_8);
    }

    protected void sendText(HttpExchange exchange, String text) throws IOException {
        byte[] resp = text.getBytes(UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, resp.length);
        exchange.getResponseBody().write(resp);
    }
}
