package BranchBoundServer;

import BackTrackingServer.Haversine;
import Json.ConnectsTo;
import Json.Nodes;
import Json.Server;
import Json.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
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
        sd.printArray(sd.wins);
        System.out.println("La distancia minima es : " + sd.minimaDistancia);
        System.out.println("La Actividad minima es : " + sd.minimaActividad);
    }

    private void printArray(int[] x) {
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
        System.out.println();
    }

    private User[] usuarios;
    private Server[] servidores;
    private int[] actividadActualServidores;
    private int puntero = 0;

    private class Solution implements Cloneable{
        public int[] carrega = new int[usuarios.length];
        public float minActivitat = 999999;
        public double minDistancia = 999999;
        public double actualDistancia = 0;

        public void updateCarrega (int pos, int new_server){
            this.carrega[pos] = new_server;
        }
    }

    public BranchboundServer(User[] usuarios, Server[] servidores) {//Pasar UsersId Por Orden Mejor pero da igual
        this.usuarios = usuarios;
        this.servidores = servidores;
        actividadActualServidores = new int[servidores.length];
    }



    public void Branchbound() {
        Solution x = new Solution();
        Solution best = new Solution();
        PriorityQueue<Solution> live_nodes = new PriorityQueue<Solution>();
        ArrayList<Solution> options;

        while (live_nodes.size() != 0) {
            x = live_nodes.poll();
            for (int i = 0; i < servidores.length; i++) {
                if (puntero == usuarios.length) {
                    best = minmax(servidores[i], best);
                } else {
                    if (is_promising(servidores[i], best)) {
                        x.updateCarrega(servidores[i].getId());
                        live_nodes.add();
                    }
                }
            }
        }
    }

    private ArrayList<Solution> expand (Solution x){
        ArrayList<Solution> options = new ArrayList<Solution>();

    }

    private float getDistanceActivity() {
        float minor = 99999 ,mayor =0;
        for (int i = 0; i < actividadActualServidores.length ; i++) {
            if (actividadActualServidores[i] > mayor){
                mayor = actividadActualServidores[i];
            }
            if (actividadActualServidores[i] < minor && actividadActualServidores[i] != 0){
                minor = actividadActualServidores[i];
            }
            if(actividadActualServidores[i] == 0){
                return 999999999;
            }
        }
        return Math.abs(mayor - minor);
    }

    private Solution minmax(Solution option, Solution best){
        float aux = getDistanceActivity();
        if (aux < best.minActivitat && best.actualDistancia < best.actualDistancia) {
            best.minActivitat = aux;
            best.minDistancia = best.actualDistancia;
        }
    }

    private boolean is_promising(int servidor, Solution best){
        return option.actualDistancia + Haversine.distance(usuarios[puntero].getLatitude(),
                usuarios[puntero].getLongitude(), servidor.getLocation().get(0),
                servidor.getLocation().get(1)) < option.minDistancia;
    }
}
