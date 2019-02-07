package GeneraDataSet;

import Json.Connection;
import Json.Post;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratorOfUsers {

    private static int generaNumeroInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private static double generaNumeroDouble(int min, int max) {
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    public static void main(String[] args) throws IOException {
        UserG[] users = new UserG[10];
        for (int i = 0; i < users.length; i++) {
            users[i] = new UserG();
            users[i].setActivity(GeneratorOfUsers.generaNumeroInt(1,50));
            List <Post> posts = new ArrayList<Post>();
            List <Double> location = new ArrayList<Double>();
            Post p = new Post();
            location.add(GeneratorOfUsers.generaNumeroDouble(0,90));
            location.add(GeneratorOfUsers.generaNumeroDouble(0,90));
            p.setLocation(location);
            posts.add(p);
            users[i].setPosts(posts);
            List<Connection> c = new ArrayList<Connection>();
            c.add(new Connection());
            users[i].setConnections(c);
        }
        Gson gson = new Gson();
        String jsonInString = gson.toJson(users);
        System.out.println(jsonInString);
        FileWriter fileWriter = new FileWriter("usersNew.json");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(jsonInString);
        printWriter.close();
    }

}
