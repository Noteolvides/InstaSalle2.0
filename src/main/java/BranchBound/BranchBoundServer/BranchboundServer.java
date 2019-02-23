package BranchBound.BranchBoundServer;

import BackTracking.BackTrackingServer.Haversine;
import Json.Nodes;
import Json.Server;
import Json.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.transform.dom.DOMLocator;
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
        System.out.println(wins);
        //sd.printArray(wins.users);
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

    private class Solution{

        public Server[] users;
        public float[] actividadServidores;
        public double costDist;
        public int level;
        public double diference = Double.MAX_VALUE;

        public Solution(){
            users = new Server[usuarios.length];
            actividadServidores = new float[servidores.length];
        }

        public Solution(Server[] users, float[] actividadServidores, double costDist, int level) {
            this.users = users;
            this.actividadServidores = actividadServidores;
            this.costDist = costDist;
            this.level = level;
        }

        public void updateCarrega (Server new_server){
            this.users[level] = new_server;
        }

        public void setDiference(Double abs) {
            this.diference = abs;
        }

        public Double getDiference() {
            return diference;
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
                if(o1.getDiference() > 999 || o2.getDiference() > 999){
                    return o2.level - o1.level;
                }
                float aux1 = (float) (o1.getDiference()/o1.costDist);
                float aux2 = (float) (o2.getDiference()/o2.costDist);
                return (int) ((aux2 - aux1)*1000000000);
        }});

        best.costDist = 99999;
        for (int i = 0; i < best.actividadServidores.length; i++){
            best.actividadServidores[i] = 99999;
        }

        x.level = 0;

        live_nodes.add(x);

        while (live_nodes.size() != 0) {
            x = live_nodes.poll();
            for (Server server : servidores) {
                Server[] aux = x.users.clone();
                float[] aux2 = x.actividadServidores.clone();
                Solution t  = new Solution(aux,aux2,x.costDist,x.level);
                if (t.level == usuarios.length) {
                    best = min(t, best);
                } else {
                    if (is_promising(t, best)) {
                        t.updateCarrega(server);
                        t.costDist += Haversine.distance(usuarios[t.level].getLatitude(),
                                usuarios[t.level].getLongitude(), server.getLocation().get(0),
                                server.getLocation().get(1));
                        t.actividadServidores[Integer.valueOf(server.getId()) - 1] += usuarios[t.level].getActivity(); //revisar els cost de la activitat
                        t.level++;
                        live_nodes.add(t);
                    }
                }
            }
        }
        return best;
    }

    private Double getDistanceActivity(Solution x) {
        float minor = 99999 ,mayor =0;
        for (float actividad: x.actividadServidores) {
            if (actividad > mayor){
                mayor = actividad;
            }
            if (actividad < minor && actividad != 0){
                minor = actividad;
            }
            if(actividad == 0){
                x.setDiference(Double.MAX_VALUE);
                return Double.MAX_VALUE;
            }
        }
        x.setDiference((double) Math.abs(mayor - minor));
        return (Double) (double)Math.abs(mayor - minor);
    }

    private Solution min(Solution x,Solution best){
        Double aux = getDistanceActivity(x);
        x.setDiference(aux);
        Double bestaux = best.getDiference();
        Double xMayor = (aux/x.costDist);
        Double bestMayor =  (bestaux/best.costDist);
        if (aux <= bestaux && x.costDist < best.costDist && aux != Double.MAX_VALUE)  {
            best = x;
        }
        return best;
    }

    private boolean is_promising(Solution x, Solution best){
        Double aux = getDistanceActivity(x);
        x.setDiference(aux);
        //float aux = getDistanceActivity(x);
        //float bestaux = getDistanceActivity(best);
        //if (aux < bestaux && x.costDist < best.costDist)  {
          //  return true;
        //}
        return true;
    }
}