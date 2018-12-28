import Json.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        User[] users = gson.fromJson(new FileReader("Datasets/users.json"), User[].class);
        Nodes[] nodes = gson.fromJson(new FileReader("Datasets/nodes.json"), Nodes[].class);
        Server[] servers = gson.fromJson(new FileReader("Datasets/servers.json"), Server[].class);
        //Distribucion carga
        //backtracking -> condicion que haya usuario, y que vaya añadiendo
        // hasta que vea que se ha pasado de peso(este ultimo tambien se añade)
        //branch&bound -> igual que en backtracking?
        //relacion greedy -> numero de horas/distancia

        //Camino mas rapido y mas fiable
        //backtracking -> camino mas rapido y mas fiable tsp
        //branch&bound -> " "
        //greedy -> ordenando por las conexiones mas rapidas y las mas fiables
    }
}
