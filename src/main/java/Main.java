import BackTracking.BackDist;
import BackTracking.Backtracking;
import Json.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        User[] users = gson.fromJson(new FileReader("Datasets/users.json"), User[].class);
        Nodes[] nodes = gson.fromJson(new FileReader("Datasets/datasets++/nodes_plus.json"), Nodes[].class);
        Server[] servers = gson.fromJson(new FileReader("Datasets/servers.json"), Server[].class);
        for (int c = 0; c < users.length; c++) {
            users[c].setId(c);
            users[c].setUbication();
        }

        ArrayList<User> usersList= new ArrayList<User>(Arrays.asList(users));

        int from = 1;
        int to = 9;
        BackDist bd = new BackDist(nodes,from,to);
        Backtracking.backTracking(from-1,0,bd);
        System.out.println(bd.getBest());
        System.out.println(bd.winPath);
        }
    }
        /*
        //Distribucion carga
        //Backtracking -> condicion que haya usuario, y que vaya añadiendo
        // hasta que vea que se ha pasado de peso(este ultimo tambien se añade)
        //branch&bound -> igual que en Backtracking?
        //relacion greedy -> numero de horas/distancia

        //Camino mas rapido y mas fiable
        //Backtracking -> camino mas rapido y mas fiable tsp
        //branch&bound -> " "
        //greedy -> ordenando por las conexiones mas rapidas y las mas fiables
        */


