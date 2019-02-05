package Greedy;

public class GreedyDist implements InterficieGreedy {

    public Solution init_solution() {
        return null;
    }

    public Candidate[] init_candidates() {
        return new Candidate[0];
    }

    public boolean candidatesToCheck() {
        return false;
    }

    public Candidate select(Candidate[] candidates) {
        return null;
    }

    public boolean is_feasible(Solution s, Candidate c) {
        return false;
    }

    public boolean is_solution(Solution s) {
        return false;
    }
}
