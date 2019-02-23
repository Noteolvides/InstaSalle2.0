package Greedy.GreedyServer;

import BackTracking.BackTrackingServer.Haversine;
import Json.Nodes;
import Json.Server;
import Json.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class GreedyServer {
    private User[] users;
    private Server[] servers;

    public GreedyServer(User[] users, Server[] servers) {
        this.users = users;
        this.servers = servers;
        Greedy();
    }

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

    public void Greedy(){
        Double globalDistance = 0.0;
        int[] solution = new int[users.length];
        for(int i = 0 ; i < users.length ; i++){
            Double bestDistance = Double.MAX_VALUE;
            Double bestActivitie = Double.MAX_VALUE;
            int bestServer = 0;
            for (Server s : servers){
                Double distance = Haversine.distance(users[i].getLatitude(),users[i].getLongitude(),s.getLocation().get(0),s.getLocation().get(1));
                if (s.activitie < bestActivitie && distance < bestDistance){
                    bestServer = Integer.parseInt(s.getId());
                    bestActivitie = s.activitie;
                    bestDistance = distance;
                }
            }
            servers[bestServer-1].activitie += users[i].getActivity();
            solution[i] = bestServer;
            globalDistance += bestDistance;
        }
        System.out.println(Arrays.toString(solution));
        System.out.println(globalDistance);
        Double mayor = Double.MIN_VALUE;
        Double menor = Double.MAX_VALUE;
        for (Server s : servers){
            if (s.activitie > mayor){
                mayor = s.activitie;
            }
            if (s.activitie < menor){
                menor = s.activitie;
            }
        }
        System.out.println(mayor-menor);
    }


}
