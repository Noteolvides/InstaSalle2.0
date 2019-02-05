package Greedy;

public interface InterficieGreedy {
    Solution init_solution();
    Candidate[] init_candidates();
    boolean candidatesToCheck();
    Candidate select(Candidate[] candidates);
    boolean is_feasible(Solution s, Candidate c);
    boolean is_solution(Solution s);
}
