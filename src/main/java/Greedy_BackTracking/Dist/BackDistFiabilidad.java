package Greedy_BackTracking.Dist;

import Greedy.GreedyDist.Greedy;
import Greedy.GreedyDist.GreedyDist;
import Greedy.GreedyDist.GreedyFiable;
import Json.Nodes;
import Json.Server;
import Json.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

// TODO: 2019-03-01 No devuelve bien la informacion, el path
public class BackDistFiabilidad {
    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        User[] users = gson.fromJson(new FileReader("Datasets/users.json"), User[].class);
        Nodes[] nodes = gson.fromJson(new FileReader("Datasets/nodes.json"), Nodes[].class);
        Server[] servers = gson.fromJson(new FileReader("Datasets/servers.json"), Server[].class);
        for (int c = 0; c < users.length; c++) {
            users[c].setId(c);
            users[c].setUbication();
        }

        ArrayList<User> usersList= new ArrayList<User>(Arrays.asList(users));
        int from = 1;
        int to = 4;
        GreedyFiable greedy = new GreedyFiable(nodes, from, to);
        Greedy.greedy(from -1, to, greedy);
        BackDistFiabilidad bd = new BackDistFiabilidad(nodes,from,to, greedy);
        BacktrackingFiable.backTracking(from-1,nodes[from-1].getReliability().floatValue(),bd);
        System.out.println(bd.getBest());
        System.out.println(bd.winPath);
    }
    private Nodes[] nodes;
    private int from;
    private int to;
    private float best = 0;
    public ArrayList<Integer> pathTemp = new ArrayList<Integer>();
    public ArrayList winPath;
    public BackDistFiabilidad(Nodes[] nodes, int from, int to, GreedyFiable greedy) {
        this.nodes = nodes;
        this.from = from;
        this.to = to;
        nodes[from-1].setSelected();
        pathTemp.add(from);
        this.best = greedy.getBest();
    }


    public boolean bt(int i,float sum) {
        return nodes[i].getId() == to;
    }

    public void handleSolution(int i,float best) {
        nodes[i].setSelected();
        winPath = (ArrayList)pathTemp.clone();
        this.best = best;
    }

    public int getEndOptions(int i) {
        return nodes[i].getLenghtOfConexions();
    }

    public boolean promising(float sum, int i, int x) {
        return sum * nodes[nodes[i].getConnectsTo(x).getTo() - 1].getReliability().floatValue() > best && nodes[nodes[i].getConnectsTo(x).getTo() - 1].getSelected() != 1;
    }

    public void set(int i,int x) {
        nodes[nodes[i].getConnectsTo(x).getTo() - 1].setSelected();
        pathTemp.add(nodes[i].getConnectsTo(x).getTo());
    }

    public void unSet(int i,int x) {
        nodes[nodes[i].getConnectsTo(x).getTo() - 1].clearSelected();
        pathTemp.remove(pathTemp.size()-1);
    }

    public int next(int i,int x) {
        return nodes[i].getConnectsTo(x).getTo() - 1;
    }

    public float agregation(int i,int x) {
        return nodes[nodes[i].getConnectsTo(x).getTo() - 1].getReliability().floatValue();
    }

    public float getBest() {
        return best;
    }
}
