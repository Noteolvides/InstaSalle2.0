package Greedy.GreedyServer;



import BackTracking.BackTrackingServer.Haversine;
import Json.Nodes;
import Json.Server;
import Json.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreedyServer {
    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        User[] users = gson.fromJson(new FileReader("Datasets/users.json"), User[].class);
        Nodes[] nodes = gson.fromJson(new FileReader("Datasets/nodes.json"), Nodes[].class);
        Server[] servers = gson.fromJson(new FileReader("Datasets/servers.json"), Server[].class);
        for (int c = 0; c < users.length; c++) {
            users[c].setId(c);
            users[c].setUbication();
        }
        GreedyServer gs = new GreedyServer(users,servers);
    }
    private User[] users;
    private Server[] servers;
    private int[] reparticion;
    private int media;
    private int[] activityServer;
    private float sumDistancia;
    private int minDist;

    public GreedyServer(User [] users,Server[] servers){
        this.users = users;
        this.servers = servers;
        this.reparticion = new int[users.length];
        activityServer = new int[servers.length];
        ArrayList<Integer> aux = new ArrayList<Integer>();
        for (int i = 0; i < users.length; i++) {
            aux.add((int) users[i].getActivity());
        }
        Collections.sort(aux);
        media = aux.get(aux.size()/2);
        Greedy();
        printArray(reparticion);
        System.out.println("La distancia minima es : " + sumDistancia);
        System.out.println("La Actividad minima es : " + minDist);
    }

    private void printArray(int[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
        System.out.println();
    }
    private void Greedy(){
        int counter = 0;
        int serverAct = 0;
        for (int i = 0; i < reparticion.length; i++) {
                reparticion[i] = serverAct;
                activityServer[serverAct] += (int) users[i].getActivity();
                List<Double> aux = servers[serverAct].getLocation();
                sumDistancia += Haversine.distance(users[i].getLatitude(),users[i].getLongitude(),aux.get(0),aux.get(1));
                counter += users[i].getActivity();
                if (counter >= media){
                    serverAct++;
                    counter = 0;
                }
                if (serverAct > servers.length-1){
                    serverAct = 0;
                }
        }
        //printArray(activityServer);
        int minor = 99999 ,mayor =0;
        for (int i = 0; i <activityServer.length ; i++) {
            if (activityServer[i] > mayor){
                mayor = activityServer[i];
            }
            if (activityServer[i] < minor && activityServer[i] != 0){
                minor = activityServer[i];
            }
        }
        minDist = mayor-minor;
    }
}
