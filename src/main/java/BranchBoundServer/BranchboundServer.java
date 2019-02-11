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
    private int[] reparticion;
    public int[] wins;
    public float minimaActividad = 9999999;
    public double minimaDistancia = 9999999;
    private double distanciaActual = 0;
    private int[] actividadActualServidores;
    private int puntero = 0;

    private class Solution implements Cloneable{

    }

    public BranchboundServer(User[] usuarios, Server[] servidores) {//Pasar UsersId Por Orden Mejor pero da igual
        this.usuarios = usuarios;
        this.servidores = servidores;
        reparticion = new int[usuarios.length];
        wins = new int[usuarios.length];
        actividadActualServidores = new int[servidores.length];
    }



    public void Branchbound(){
        Solution x = new Solution();
        Solution best = new Solution();
        PriorityQueue<Solution> live_nodes = new PriorityQueue<Solution>();
        ArrayList<Solution> options;


        while (live_nodes.size() != 0){
            x = live_nodes.poll();
            options = expand(x);
            for (int i = 0; i < options.size(); i++){
                if (puntero == usuarios.length){
                    best = minmax(options.get(i), best);
                }else {
                    if (is_promising(options.get(i), best)){
                        live_nodes.add(options.get(i));
                    }
                }
            }
        }
    }

    private ArrayList<Solution> expand (Solution x){
        ArrayList<Solution> options = new ArrayList<Solution>();

    }

    private Solution minmax(Solution option, Solution best){

    }

    private boolean is_promising(Solution option, Solution best){
        return distanciaActual + Haversine.distance() < minimaActividad;
    }
}
