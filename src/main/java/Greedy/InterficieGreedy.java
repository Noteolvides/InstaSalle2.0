package Greedy;

import java.util.ArrayList;

public interface InterficieGreedy {
    boolean candidatesToCheck(int i);
    int select(int i);
    boolean is_feasible(int i, int candidate);
    void addCandidate(int candidate);
    boolean is_solution(int i);
}
