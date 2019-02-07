package GreedyDist;

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

public class GreedyDist implements InterficieGreedy {
    private Nodes[] nodes;
    private int from;
    private int to;
    private int best = 999999;
    private int sum = 0;
    public ArrayList<Integer> winPath = new ArrayList<Integer>();
    public GreedyDist(Nodes[] nodes, int from, int to) {
        this.nodes = nodes;
        this.from = from;
        this.to = to;
        nodes[from-1].setSelected();
        winPath.add(from);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        User[] users = gson.fromJson(new FileReader("Datasets/users.json"), User[].class);
        Nodes[] nodes = gson.fromJson(new FileReader("Datasets/nodes.json"), Nodes[].class);
        Server[] servers = gson.fromJson(new FileReader("Datasets/servers.json"), Server[].class);
        for (int c = 0; c < users.length; c++) {
            users[c].setId(c);
        }
        ArrayList<User> usersList= new ArrayList<User>(Arrays.asList(users));

        int from = 1;
        int to = 4;
        GreedyDist gd = new GreedyDist(nodes,from,to);
        Greedy.greedy(from -1, to, gd);
        System.out.println(gd.getBest());
        System.out.println(gd.winPath);
    }

    public boolean candidatesToCheck(int i) {
        return winPath.get(winPath.size()-1) == to;
    }

    public int select(int i) {
        ConnectsTo best_candidate = new ConnectsTo();
        best_candidate.setCost(999999);
        for (int j = 0; j < nodes[i].getConnectsTo().size(); j++) {
            //mirar if selected
            if (nodes[i].getConnectsTo().get(j).getCost() <= best_candidate.getCost() && nodes[nodes[i].getConnectsTo().get(j).getTo()-1].getSelected() != 1) {
                best_candidate = nodes[i].getConnectsTo().get(j);
            }
        }
        this.sum += best_candidate.getCost();
        return best_candidate.getTo();
    }

    public boolean is_feasible(int i, int candidate) {
        return true;
    }

    public int addCandidate(int candidate) {
        winPath.add(candidate);
        nodes[candidate-1].setSelected();
        return candidate - 1;
    }

    public boolean is_solution(int i) {
        return winPath.get(winPath.size() - 1) == this.to;
    }

    public int getBest(){
        return sum;
    }
}
