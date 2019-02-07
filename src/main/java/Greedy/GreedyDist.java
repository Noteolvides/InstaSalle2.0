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
        return winPath.get(winPath.size()-1) == to;
    }

    public int select(int i) {
        ConnectsTo best_candidate = new ConnectsTo();
        best_candidate.setCost(999999);
        for (int j = 0; j < nodes[i].getConnectsTo().size(); j++) {
            //mirar if selected
            if (nodes[i].getConnectsTo().get(j).getCost() <= best_candidate.getCost()) {
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
        int lvl = 0;
        winPath.add(candidate);
        for (int j = 0; j < nodes.length; j++){
            if (nodes[j].getId() == candidate){
                lvl = j;
            }
            nodes[lvl].setSelected();
            /*for(int x = 0; x < nodes[j].getConnectsTo().size(); x++) {
                if (nodes[j].getConnectsTo(x).getTo() == candidate){
                    nodes[nodes[j].getConnectsTo(x).getTo()].setSelected();
                }
            }*/
        }
        return lvl;
    }

    public boolean is_solution(int i) {
        return winPath.get(winPath.size() - 1) == this.to;
    }

    public int getBest(){
        return sum;
    }
}
