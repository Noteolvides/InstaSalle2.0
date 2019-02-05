package BackTrackingNEW;

import Json.Server;
import Json.User;


import java.util.LinkedList;
import java.util.List;

public class ServersDist implements BackTrackingInterN {
    private User[] usuarios;
    private Server[] servidores;
    private LinkedList<Integer> reparticion = new LinkedList<Integer>();
    private int minimaActividad;
    private double minimaDistancia;
    private int serverActual;
    private double distanciaActual = 9999999;
    private int actividadActual = 9999999;

    public ServersDist(User[] usuarios, Server[] servidores) {//Pasar UsersId Por Orden Mejor pero da igual
        this.usuarios = usuarios;
        this.servidores = servidores;
    }


    public boolean bt() {
        return reparticion.size() == usuarios.length;
    }

    public void handleSolution() {
        if (actividadActual < minimaActividad && distanciaActual < minimaDistancia){
            minimaActividad = actividadActual;
            minimaDistancia = distanciaActual;
        }
    }

    public int getEndOptions() {
        return servidores.length;
    }

    public boolean promising(int next) {
        List<Double> aux = servidores[reparticion.get(next)].getLocation();
        /*
        return distanciaActual+
                Haversine.distance(usuarios[reparticion.size()-1].getLatitude(),usuarios[reparticion.size()-1].getLongitude(),aux.get(0),aux.get(1)) <
                minimaDistancia ||
                */
        return true;
    }

    public void set(int next) {
        Integer aux = reparticion.size();
        List<Double> aux2 = servidores[reparticion.get(next)].getLocation();
        actividadActual += usuarios[aux].getActivity();
        reparticion.add(aux);
        aux++;
        distanciaActual += Haversine.distance(usuarios[aux].getLatitude(),usuarios[aux].getLongitude(),aux2.get(0),aux2.get(1));
    }

    public BackTrackingInterN getNext(int next) {
        return null;
    }


    public void unSet(int next) {
        int aux = reparticion.size()-1;
        List<Double> aux2 = servidores[reparticion.get(next)].getLocation();
        actividadActual -= usuarios[aux].getActivity();
        distanciaActual -= Haversine.distance(usuarios[aux].getLatitude(),usuarios[aux].getLongitude(),aux2.get(0),aux2.get(1));
        reparticion.removeLast();
    }
}
