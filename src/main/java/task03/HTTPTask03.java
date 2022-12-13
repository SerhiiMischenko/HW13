package task03;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;

import java.io.IOException;

public class HTTPTask03 {
    public static void main(String[] args) throws IOException {
        getOpenTasks();
    }
    public static void getOpenTasks() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String document = Jsoup.connect("https://jsonplaceholder.typicode.com/users/1/todos")
                .ignoreContentType(true).execute().body();
        JsonArray a = new JsonParser().parse(document).getAsJsonArray();
        for (JsonElement elem : a) {
            if(elem.toString().toLowerCase().contains("false".toLowerCase())) {
                stringBuilder.append(elem);
            }
        }
        System.out.println(stringBuilder.toString());
    }
}
