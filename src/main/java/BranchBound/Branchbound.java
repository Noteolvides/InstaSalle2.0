package BranchBound;
import Json.Nodes;

import java.util.List;
import java.util.PriorityQueue;

public class Branchbound {
    public static List<Nodes> branchAndBound(int t, int sum, InterficieBranchBound operation) {
        List<Nodes> x, best;
        PriorityQueue<List<Nodes>> live_nodes = null;
        List<Nodes>[] options;

        //best = infinite;
        //x = set root node;
        live_nodes.add(x);

        while (!live_nodes.isEmpty()) {
            x = live_nodes.poll();
            options = operation.expand(x);

            for (int i = 0; i < options.length; i++) {
                if (operation.is_solution(options[i])) {
                    best = operation.minmax(options[i], best);
                } else {
                    if (operation.is_promising(options[i], best)) {
                        live_nodes.add(options[i]);
                    }
                }
            }
        }
        return best;
    }
}
