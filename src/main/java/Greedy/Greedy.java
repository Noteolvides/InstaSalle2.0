package Greedy;

public class Greedy {
    public static Solution greedy(InterficieGreedy operation) {
        Candidate[] candidates;
        Solution s;
        boolean found = false;

        s = operation.init_solution();
        candidates = operation.init_candidates();

        while (!found && operation.candidatesToCheck()) {
            Candidate c = operation.select(candidates);
            if (operation.is_feasible(s, c)) {
                s = add(s, c);
            }
            if (operation.is_solution(s)) {
                found = true;
            }
        }

        if (!found){
            s = null;
        }

        return s;
    }
}
