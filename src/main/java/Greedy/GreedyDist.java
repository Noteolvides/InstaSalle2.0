package Greedy;

import Json.ConnectsTo;
import Json.Nodes;

import java.util.ArrayList;
import java.util.List;

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

    public boolean candidatesToCheck(int i) {
        return i <= 2;
    }

    public int select(int i) {
        List<ConnectsTo> candidates = nodes[i].getConnectsTo();
        ConnectsTo best_candidate = new ConnectsTo();
        best_candidate.setCost(999999);
        for (int j = 0; j < candidates.size(); j++) {
            this.sum += candidates.get(j).getCost();
            if (this.sum <= best_candidate.getCost()) {
                best_candidate = candidates.get(j);
            }else{
                this.sum -= candidates.get(j).getCost();
            }
        }
        return best_candidate.getTo();
    }

    public boolean is_feasible(int i, int candidate) {
        return true;
    }

    public void addCandidate(int candidate) {
        winPath.add(candidate);
    }

    public boolean is_solution(int i) {
        return winPath.get(winPath.size() - 1) == this.to;
    }

    public int getBest(){
        return sum;
    }
}
