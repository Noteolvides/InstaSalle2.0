package Greedy.GreedyDist;

public interface InterficieGreedy {
    boolean candidatesToCheck(int i);
    int select(int i);
    boolean is_feasible(int i, int candidate);
    int addCandidate(int candidate);
    boolean is_solution(int i);
}
