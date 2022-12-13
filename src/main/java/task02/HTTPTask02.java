package task02;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;

public class HTTPTask02 {

    public static void main(String[] args) throws IOException {
        getCommentById();
    }
    public static void getCommentById() throws IOException {
        String document = Jsoup.connect("https://jsonplaceholder.typicode.com/posts/10/comments")
                .ignoreContentType(true).execute().body();
        JsonArray a = new JsonParser().parse(document).getAsJsonArray();
        JsonElement lastComment = a.get(a.size() -1);
        JsonObject jo = (JsonObject) lastComment;
        String postId = String.valueOf(jo.get("postId"));
        String id = String.valueOf(jo.get("id"));
        String body = String.valueOf(jo.get("body"));
    }
}
