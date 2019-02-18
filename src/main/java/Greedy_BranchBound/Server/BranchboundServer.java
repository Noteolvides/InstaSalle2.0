package Greedy_BranchBound.Server;

import BackTracking.BackTrackingServer.Haversine;
import Json.Nodes;
import Json.Server;
import Json.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BranchboundServer {


    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        User[] users = gson.fromJson(new FileReader("Datasets/users.json"), User[].class);
        Nodes[] nodes = gson.fromJson(new FileReader("Datasets/nodes.json"), Nodes[].class);
        Server[] servers = gson.fromJson(new FileReader("Datasets/servers.json"), Server[].class);
        for (int c = 0; c < users.length; c++) {
            users[c].setId(c);
            users[c].setUbication();
        }
        BranchboundServer sd = new BranchboundServer(users,servers);
        Solution wins = sd.Branchbound();
        sd.printArray(wins.users);
        System.out.println("La distancia minima es : " + wins.costDist);
        System.out.println("La Actividad minima es : " + wins.actividadServidores[0]);
    }

    private void printArray(Server[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i].getId());
        }
        System.out.println();
    }

    private User[] usuarios;
    private Server[] servidores;
    private double actualDistancia = 0;
    private int puntero = 0;

    private class Solution implements Cloneable{
        public Server[] users = new Server[usuarios.length];
        public float[] actividadServidores = new float[servidores.length];
        public double costDist;
        public int level;
        public int last;

        public void updateCarrega (Server new_server){
            this.users[level] = new_server;
        }
    }

    public BranchboundServer(User[] usuarios, Server[] servidores) {//Pasar UsersId Por Orden Mejor pero da igual
        this.usuarios = usuarios;
        this.servidores = servidores;
    }



    public Solution Branchbound() {
        Solution x = new Solution();
        Solution best = new Solution();
        PriorityQueue<Solution> live_nodes = new PriorityQueue<Solution>(usuarios.length, new Comparator<Solution>(){
            public int compare(Solution o1, Solution o2){
                //Float difAct = o1.costAct - o2.costAct;
                Double difDist = o1.costDist - o2.costDist;
                return /*difAct.intValue()/*/difDist.intValue(); //media actividad de los servidores?
            }
        });

        best.costDist = 99999;
        for (int i = 0; i < best.actividadServidores.length; i++){
            best.actividadServidores[i] = 99999;
        }

        x.level = 0;
        x.last = 0;

        live_nodes.add(x);

        while (live_nodes.size() != 0) {
            x = live_nodes.poll();
            for (Server server : servidores) {
                if (x.level == usuarios.length) {
                    best = min(x, best);
                } else {
                    if (is_promising(x, server, best)) {
                        x.updateCarrega(server);
                        x.costDist += Haversine.distance(usuarios[x.level].getLatitude(),
                                usuarios[x.level].getLongitude(), server.getLocation().get(0),
                                server.getLocation().get(1));
                        x.actividadServidores[Integer.valueOf(server.getId()) - 1] += usuarios[x.level].getActivity(); //revisar els cost de la activitat
                        live_nodes.add(x);
                    }
                }
            }
            x.level++;
        }
        return best;
    }

    private float getDistanceActivity(Solution x) {
        float minor = 99999 ,mayor =0;
        for (float actividad: x.actividadServidores) {
            if (actividad > mayor){
                mayor = actividad;
            }
            if (actividad < minor && actividad != 0){
                minor = actividad;
            }
            if(actividad == 0){
                return 999999;
            }
        }
        return Math.abs(mayor - minor);
    }

    private Solution min(Solution x,Solution best){
        float aux = getDistanceActivity(x);
        float bestaux = getDistanceActivity(best);
        if (aux < bestaux && x.costDist < best.costDist) {
            best = x;
        }
        return best;
    }

    private boolean is_promising(Solution x,Server option, Solution best){
        return x.actividadServidores[Integer.valueOf(option.getId()) - 1] < best.actividadServidores[Integer.valueOf(option.getId()) - 1];
    }
}
