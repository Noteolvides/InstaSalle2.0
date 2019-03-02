package Greedy.GreedyDist;

import Json.Nodes;
import Json.Server;
import Json.User;

import java.util.ArrayList;
import java.util.Arrays;

public class GreedyFiable implements InterficieGreedy {
    private Nodes[] nodes;
    private int from;
    private int to;
    private int best = 999999;
    private float sum = 1;
    public ArrayList<Integer> winPath = new ArrayList<Integer>();
    public GreedyFiable(Nodes[] nodes, int from, int to) {
        this.nodes = nodes;
        this.from = from;
        this.to = to;
        nodes[from-1].setSelected();
        winPath.add(from);
        sum *= nodes[from-1].getReliability();
    }

    public static void main(String[] args, User[] users, Nodes[] nodes, Server[] servers)  {
        for (int c = 0; c < users.length; c++) {
            users[c].setId(c);
        }
        ArrayList<User> usersList= new ArrayList<User>(Arrays.asList(users));

        int from = 1;
        int to = 4;
        GreedyFiable gd = new GreedyFiable(nodes,from,to);
        Greedy.greedy(from -1, to, gd);
        System.out.println(gd.getBest());
        System.out.println(gd.winPath);
    }

    public boolean candidatesToCheck(int i) {
        return winPath.get(winPath.size()-1) == to;
    }

    public int select(int i) {
        Double fiabilidad = 0.0;
        int candidate = 0;
        for (int j = 0; j < nodes[i].getConnectsTo().size(); j++) {
            Nodes aux = nodes[nodes[i].getConnectsTo().get(j).getTo()-1];
            if (aux.getReliability() > fiabilidad  && aux.getSelected() != 1) {
                fiabilidad = aux.getReliability();
                candidate = nodes[i].getConnectsTo().get(j).getTo();
            }
        }
        this.sum *= fiabilidad;
        return candidate;
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

    public float getBest(){
        return sum;
    }
}
