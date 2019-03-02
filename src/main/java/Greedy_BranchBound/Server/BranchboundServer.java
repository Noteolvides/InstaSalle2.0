package Greedy_BranchBound.Server;

import BackTracking.BackTrackingServer.Haversine;
import Greedy.GreedyServer.GreedyServer;
import Json.Nodes;
import Json.Server;
import Json.User;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BranchboundServer {


    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        User[] users = gson.fromJson(new FileReader(args[0]), User[].class);
        Server[] servers = gson.fromJson(new FileReader(args[1]), Server[].class);
        for (int c = 0; c < users.length; c++) {
            users[c].setId(c);
            users[c].setUbication();
        }
        GreedyServer greedy = new GreedyServer(users,servers);
        BranchboundServer sd = new BranchboundServer(users,servers);
        Solution wins = sd.Branchbound(greedy);
        sd.printArray(wins.users,users);
        System.out.println("La distancia minima es : " + wins.costDist);
        System.out.println("La Actividad minima es : " + wins.diference);
    }

    private void printArray(Server[] x, User[] users) {
        for (int i = 0; i < x.length; i++) {
            System.out.println("El usuario: " + users[i].getUsername());
            System.out.println("Esta en el server: " + x[i].getCountry());
        }
        System.out.println();
    }

    private User[] usuarios;
    private Server[] servidores;

    private class Solution{

        public Server[] users;
        public double[] actividadServidores;
        public double costDist;
        public int level;
        public double diference = Double.MAX_VALUE;

        public Solution(){
            users = new Server[usuarios.length];
            actividadServidores = new double[servidores.length];
        }

        public Solution(Server[] users, double[] actividadServidores, double costDist, int level) {
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



    public Solution Branchbound(GreedyServer greedy) {
        Solution x = new Solution();
        Solution best = new Solution();
        PriorityQueue<Solution> live_nodes = new PriorityQueue<Solution>(usuarios.length, new Comparator<Solution>(){
            public int compare(Solution o1, Solution o2){
                if(o1.getDiference() > Double.MAX_VALUE || o2.getDiference() > Double.MAX_VALUE){
                    return o2.level - o1.level;
                }
                float aux1 = (float) (o1.getDiference()/o1.costDist);
                float aux2 = (float) (o2.getDiference()/o2.costDist);
                return (int) ((aux2 - aux1)*1000000000);
        }});

        best.costDist = greedy.getGlobalDistance();
        for (int i = 0; i < best.actividadServidores.length; i++){
            best.actividadServidores[i] = Double.MAX_VALUE;
        }

        x.level = 0;

        live_nodes.add(x);

        while (live_nodes.size() != 0) {
            x = live_nodes.poll();
            for (Server server : servidores) {
                Server[] aux = x.users.clone();
                double[] aux2 = x.actividadServidores.clone();
                Solution t  = new Solution(aux,aux2,x.costDist,x.level);
                if (t.level == usuarios.length) {
                    best = min(t, best);
                } else {
                    if (is_promising(t, best,server)) {
                        t.updateCarrega(server);
                        t.costDist += Haversine.distance(usuarios[t.level].getLatitude(),
                                usuarios[t.level].getLongitude(), server.getLocation().get(0),
                                server.getLocation().get(1));
                        t.actividadServidores[Integer.parseInt(server.getId()) - 1] += usuarios[t.level].getActivity(); //revisar els cost de la activitat
                        t.level++;
                        live_nodes.add(t);
                    }
                }
            }
        }
        return best;
    }

    private Double getDistanceActivity(Solution x) {
        Double minor = Double.MAX_VALUE ,mayor = Double.MIN_VALUE;
        for (Double actividad: x.actividadServidores) {
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
        if (aux <= bestaux && x.costDist < best.costDist && aux != Double.MAX_VALUE)  {
            best = x;
        }
        return best;
    }

    private boolean is_promising(Solution x, Solution best,Server server){
        Double disx = Haversine.distance(usuarios[x.level].getLatitude(),
                usuarios[x.level].getLongitude(), server.getLocation().get(0),
                server.getLocation().get(1)) + x.costDist;
        if (disx < best.costDist){
            return true;
        }else{
            return false;
        }
    }
}
