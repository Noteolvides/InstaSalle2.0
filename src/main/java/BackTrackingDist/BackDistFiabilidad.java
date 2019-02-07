package BackTrackingDist;

import Json.Nodes;
import Json.Server;
import Json.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class BackDistFiabilidad implements InterficieBacktraking {
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
        BackDistFiabilidad bd = new BackDistFiabilidad(nodes,from,to);
        Backtracking.backTracking(from-1,0,bd);
        System.out.println(bd.getBest());
        System.out.println(bd.winPath);
    }
    private Nodes[] nodes;
    private int from;
    private int to;
    private int best = 999999;
    public ArrayList<Integer> pathTemp = new ArrayList<Integer>();
    public ArrayList winPath;
    public BackDistFiabilidad(Nodes[] nodes, int from, int to) {
        this.nodes = nodes;
        this.from = from;
        this.to = to;
        nodes[from-1].setSelected();
        pathTemp.add(from);
    }


    public boolean bt(int i,int sum) {
        return nodes[i].getId() == to;
    }

    public void handleSolution(int i,int best) {
        nodes[i].setSelected();
        winPath = (ArrayList)pathTemp.clone();
        this.best = best;
    }

    public int getEndOptions(int i) {
        return nodes[i].getLenghtOfConexions();
    }

    public boolean promising(int sum,int i, int x) {
        return sum + nodes[i].getConnectsTo(x).getCost() < best && nodes[nodes[i].getConnectsTo(x).getTo() - 1].getSelected() != 1;
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

    public int agregation(int i,int x) {
        int cost =  nodes[i].getConnectsTo(x).getCost();
        return cost;
    }

    public int getBest() {
        return best;
    }
}