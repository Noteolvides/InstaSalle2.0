package GreedyDist;

import Json.Nodes;

import java.util.ArrayList;

public class GreedyFiable implements InterficieGreedy{
    private Nodes[] nodes;
    private int from;
    private int to;
    private int best = 999999;
    private int sum = 0;
    public ArrayList<Integer> winPath = new ArrayList<Integer>();
    public GreedyFiable(Nodes[] nodes, int from, int to) {
        this.nodes = nodes;
        this.from = from;
        this.to = to;
        nodes[from-1].setSelected();
        winPath.add(from);
    }

    public boolean candidatesToCheck(int i) {
        return false;
    }

    public int select(int i) {
        return 0;
    }

    public boolean is_feasible(int i, int candidate) {
        return false;
    }

    public int addCandidate(int candidate) {
        return 0;
    }

    public boolean is_solution(int i) {
        return false;
    }
}
