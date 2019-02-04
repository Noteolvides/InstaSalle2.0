package BranchBound;
import java.util.List;
import java.util.PriorityQueue;

public class BranchBound {
    public static List<Nodes> branchAndBound() {
        List<Nodes> x, best;
        PriorityQueue<List<Nodes>> live_nodes = null;
        List<Nodes>[] options;

        //best = infinite;
        //x = set root node
        live_nodes.add(x);

        while (!live_nodes.isEmpty()) {
            x = live_nodes.poll();
            options = expand(x);

            for (int i = 0; i < options.length; i++) {
                if (is_solution(options[i])) {
                    best = minmax(options[i], best);
                } else {
                    if (is_promising(options[i], best)) {
                        live_nodes.add(options[i]);
                    }
                }
            }
        }
        return best;
    }
}
