package Greedy.GreedyServer;

import BackTracking.BackTrackingServer.Haversine;
import Json.Nodes;
import Json.Server;
import Json.User;


import java.io.FileNotFoundException;
import java.io.FileReader;

public class GreedyServer {
    private User[] users;
    private Server[] servers;
    private Double globalDistance = 0.0;
    private int[] wins;

    public GreedyServer(User[] users, Server[] servers) {
        this.users = users;
        this.servers = servers;
        Greedy();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        User[] users = gson.fromJson(new FileReader(args[0]), User[].class);
        Server[] servers = gson.fromJson(new FileReader(args[1]), Server[].class);
        for (int c = 0; c < users.length; c++) {
            users[c].setId(c);
            users[c].setUbication();
        }
        GreedyServer gs = new GreedyServer(users,servers);
        printArray(gs.wins,gs);
        System.out.println(gs.globalDistance);
        Double mayor = Double.MIN_VALUE;
        Double menor = Double.MAX_VALUE;
        for (Server s : servers){
            if (s.activity > mayor){
                mayor = s.activity;
            }
            if (s.activity < menor){
                menor = s.activity;
            }
        }
        System.out.println("La distancia minima entre servidores es: " +  (mayor-menor));
    }


    private static void printArray(int[] x, GreedyServer gs) {
        for (int i = 0; i < x.length; i++) {
            System.out.println("El usuario: " + gs.users[i].getUsername());
            System.out.println("Esta en el servidor: " + gs.servers[x[i]-1].getCountry() + "\n");
        }
        System.out.println();
    }

    public void Greedy(){
        int[] solution = new int[users.length];
        for(int i = 0 ; i < users.length ; i++){
            Double bestDistance = Double.MAX_VALUE;
            Double bestActivity = Double.MAX_VALUE;
            int bestServer = 0;
            for (Server s : servers){
                Double distance = Haversine.distance(users[i].getLatitude(),users[i].getLongitude(),s.getLocation().get(0),s.getLocation().get(1));
                if (s.activity < bestActivity && distance < bestDistance){
                    bestServer = Integer.parseInt(s.getId());
                    bestActivity = s.activity;
                    bestDistance = distance;
                }
            }
            servers[bestServer-1].activity += users[i].getActivity();
            solution[i] = bestServer;
            globalDistance += bestDistance;
        }
        wins = solution;
    }

    public Double getGlobalDistance() {
        return globalDistance;
    }
}
