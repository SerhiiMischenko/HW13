package task01;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;

public class HTTPTask01 {

    private static final String TEST_URL = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) throws IOException, InterruptedException {
        createNewObject();
        updateObject();
        getUsers();
        deleteObject();
        getById(5);
        getByName("Karianne");
    }

    public static void createNewObject() throws IOException, InterruptedException {
        System.out.println("Enter in createNewObject() \n ===================================");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TEST_URL))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("user.json")))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        System.out.println("============================================");
    }

    public static void updateObject() throws IOException, InterruptedException {
        System.out.println("Enter in updateObject()");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TEST_URL))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("updateUser.json")))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        System.out.println("===============================================");
    }

    public static void getUsers() throws IOException, InterruptedException {
        System.out.println("Enter in getUsers()");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TEST_URL))
                .GET()
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        System.out.println("================================================");
    }

    public static void deleteObject() throws IOException, InterruptedException {
        System.out.println("Enter in deleteObject()");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TEST_URL))
                .DELETE().GET()
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code is " + response.statusCode());
        System.out.println("=================================================");
    }

    public static void getById(int id) throws IOException {
        System.out.println("Enter in getById(int id)");
        String document = Jsoup.connect("https://jsonplaceholder.typicode.com/users").ignoreContentType(true)
                .execute().body();
        JsonArray a = new JsonParser().parse(document).getAsJsonArray();
        try {
            System.out.println(a.get(id - 1));
        }catch (IndexOutOfBoundsException e) {
            System.out.println("Wrong id");
        }
        System.out.println("======================================");
    }

    public static void getByName(String userName) throws IOException {
        System.out.println("Enter in getByName(String userName)");
        String document = Jsoup.connect("https://jsonplaceholder.typicode.com/users").ignoreContentType(true)
                .execute().body();
        JsonArray a = new JsonParser().parse(document).getAsJsonArray();
        for (JsonElement js: a) {
            if(js.toString().toLowerCase().contains(userName.toLowerCase())) {
                System.out.println(js);
            }
        }
        System.out.println("==================================================");
    }
}
