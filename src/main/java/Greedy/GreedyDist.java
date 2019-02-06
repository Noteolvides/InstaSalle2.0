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
    public ArrayList<Integer> pathTemp = new ArrayList<Integer>();
    public ArrayList winPath;
    public GreedyDist(Nodes[] nodes, int from, int to) {
        this.nodes = nodes;
        this.from = from;
        this.to = to;
        nodes[from-1].setSelected();
        pathTemp.add(from);
    }

    public boolean candidatesToCheck(int i) {
        return nodes[i].getConnectsTo().size() != 0;
    }

    public int select(int i) {
        List<ConnectsTo> candidates = nodes[i].getConnectsTo();
        ConnectsTo best_candidate = new ConnectsTo();
        best_candidate.setCost(99999999);
        for (int j; j < candidates.size(); j++) {
            if (candidates.get(j).getCost() <= best_candidate.getCost()) {
                best_candidate = candidates.get(j);
            }
        }
        return 0;// fer el return be
    }

    public boolean is_feasible(int i, int candidate) {
        return false;
    }

    public void addCandidate(int candidate) {

    }

    public boolean is_solution(int i) {
        return false;
    }
}
