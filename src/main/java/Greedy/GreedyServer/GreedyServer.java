package Greedy.GreedyServer;

import BackTracking.BackTrackingServer.Haversine;
import Json.Nodes;
import Json.Server;
import Json.User;

import java.util.Arrays;

public class GreedyServer {
    private User[] users;
    private Server[] servers;
    private Double globalDistance = 0.0;

    public GreedyServer(User[] users, Server[] servers) {
        this.users = users;
        this.servers = servers;
        Greedy();
    }

    // TODO: 2019-03-01 Hacer el print en el main
    public static void main(String[] args, User[] users, Nodes[] nodes, Server[] servers) {
        for (int c = 0; c < users.length; c++) {
            users[c].setId(c);
            users[c].setUbication();
        }
        GreedyServer gs = new GreedyServer(users,servers);
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
        System.out.println(Arrays.toString(solution));
        System.out.println(globalDistance);
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
        System.out.println(mayor-menor);
    }

    public Double getGlobalDistance() {
        return globalDistance;
    }
}
