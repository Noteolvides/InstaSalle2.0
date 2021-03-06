package BackTracking.BackTrackingServer;

import Json.Nodes;
import Json.Server;
import Json.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import javax.jws.soap.SOAPBinding;
import javax.sound.midi.Soundbank;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class BackServer {

    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        User[] users = gson.fromJson(new FileReader(args[0]), User[].class);
        Server[] servers = gson.fromJson(new FileReader(args[1]), Server[].class);
        for (int c = 0; c < users.length; c++) {
            users[c].setId(c);
            users[c].setUbication();
        }
        BackServer sd = new BackServer(users,servers);
        BackNew.backTracking(sd);
        System.out.println("Asi es como quedan estarian los usuarios");;
        sd.printArray(sd.wins,users,servers);
        System.out.println("La distancia minima es : " + sd.minimaDistancia);
        System.out.println("La Actividad minima es : " + sd.minimaActividad);
    }

    private void printArray(int[] x, User[] users,Server[] servers) {
        for (int i = 0; i < x.length; i++) {
            System.out.println("El usuario: " + users[i].getUsername());
            System.out.println("Esta en el servidor: " + servers[x[i]-1].getCountry() + "\n");
        }
        System.out.println();
    }

    private User[] usuarios;
    private Server[] servidores;
    private int[] reparticion;
    public int[] wins;
    public Double minimaActividad = Double.MAX_VALUE;
    public Double minimaDistancia = Double.MAX_VALUE;
    private double distanciaActual = 0;
    private int[] actividadActualServidores;
    private int puntero = 0;

    public BackServer(User[] usuarios, Server[] servidores) {//Pasar UsersId Por Orden Mejor pero da igual
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
        Double aux = getDistanceActivity();
        if (aux <= minimaActividad && distanciaActual < minimaDistancia && aux != Double.MAX_VALUE){
            minimaActividad = aux;
            minimaDistancia = distanciaActual;
            wins = reparticion.clone();
        }
    }

    private Double getDistanceActivity() {
        Double minor = Double.MAX_VALUE ,mayor = Double.MIN_VALUE;
        for (int i = 0; i < actividadActualServidores.length ; i++) {
            if (actividadActualServidores[i] > mayor){
                mayor = (Double)(double) actividadActualServidores[i];
            }
            if (actividadActualServidores[i] < minor && actividadActualServidores[i] != 0){
                minor = (Double)(double) actividadActualServidores[i];
            }
            if(actividadActualServidores[i] == 0){
                return Double.MAX_VALUE;
            }
        }
        return (Double) (double)Math.abs(mayor - minor);
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
        reparticion[puntero] = next+1;
        actividadActualServidores[next] += usuarios[puntero].getActivity();
        List<Double> aux2 = servidores[next].getLocation();
        distanciaActual += Haversine.distance(usuarios[puntero].getLatitude(),usuarios[puntero].getLongitude(),aux2.get(0),aux2.get(1));
    }

    public BackServer getNext(int next) {
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
