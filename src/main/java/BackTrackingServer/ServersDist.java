package BackTrackingServer;

import Json.Nodes;
import Json.Server;
import Json.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class ServersDist implements BackTrackingInterN {

    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        User[] users = gson.fromJson(new FileReader("Datasets/users.json"), User[].class);
        Nodes[] nodes = gson.fromJson(new FileReader("Datasets/nodes.json"), Nodes[].class);
        Server[] servers = gson.fromJson(new FileReader("Datasets/servers.json"), Server[].class);
        for (int c = 0; c < users.length; c++) {
            users[c].setId(c);
            users[c].setUbication();
        }
        ServersDist sd = new ServersDist(users,servers);
        BackNew.backTracking(sd);
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
    public float minimaActividad = 999999999;
    public double minimaDistancia = 9999999;
    private double distanciaActual = 0;
    private int[] actividadActualServidores;
    private int puntero = 0;

    public ServersDist(User[] usuarios, Server[] servidores) {//Pasar UsersId Por Orden Mejor pero da igual
        this.usuarios = usuarios;
        this.servidores = servidores;
        reparticion = new int[usuarios.length];
        wins = new int[usuarios.length];
        actividadActualServidores = new int[servidores.length];
    }


    public boolean bt() {
        return puntero == usuarios.length;
    }

    public void handleSolution() {
        float aux = getDistanceActivity();
        if (aux < minimaActividad && distanciaActual < minimaDistancia){
            minimaActividad = aux;
            minimaDistancia = distanciaActual;
            wins = reparticion.clone();
            /*
            printArray(wins);
            System.out.println("La Minima distancia es " + minimaDistancia);
            System.out.println("La Minima actividad es " + minimaActividad);
            printArray(actividadActualServidores);
            */
        }
    }

    private float getDistanceActivity() {
        float minor = 99999 ,mayor =0;
        for (int i = 0; i <actividadActualServidores.length ; i++) {
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

    public int getEndOptions() {
        return servidores.length;
    }

    public boolean promising(int next) {
        List<Double> aux = servidores[next].getLocation();
        return distanciaActual +
                Haversine.distance(usuarios[puntero].getLatitude(),usuarios[puntero].getLongitude(),aux.get(0),aux.get(1))
                < minimaDistancia;
    }

    public void set(int next) {
        reparticion[puntero] = next;
        actividadActualServidores[next] += usuarios[puntero].getActivity();
        List<Double> aux2 = servidores[next].getLocation();
        distanciaActual += Haversine.distance(usuarios[puntero].getLatitude(),usuarios[puntero].getLongitude(),aux2.get(0),aux2.get(1));
    }

    public BackTrackingInterN getNext(int next) {
        puntero++;
        return this;
    }


    public void unSet(int next) {
        puntero--;
        reparticion[puntero] = -1;
        actividadActualServidores[next] -= usuarios[puntero].getActivity();
        List<Double> aux2 = servidores[next].getLocation();
        distanciaActual -= Haversine.distance(usuarios[puntero].getLatitude(),usuarios[puntero].getLongitude(),aux2.get(0),aux2.get(1));
    }
}