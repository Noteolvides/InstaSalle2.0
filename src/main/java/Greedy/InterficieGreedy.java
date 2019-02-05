package Greedy;

import java.util.ArrayList;

public interface InterficieGreedy {
    ArrayList<Integer> init_solution();
    int[] init_candidates();
    boolean candidatesToCheck();
    int select(int[] candidates);
    boolean is_feasible(ArrayList<Integer> s, int c);
    boolean is_solution(ArrayList<Integer> s);
}
