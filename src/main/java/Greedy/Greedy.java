package Greedy;

public class Greedy {
    public static void greedy(int i, int to, InterficieGreedy operation) {
        boolean found = false;

        while (!found) {
            int c = operation.select(i);
            if (operation.is_feasible(i, c)) {
                i = operation.addCandidate(c);
            }
            if (operation.is_solution(i)) {
                found = true;
            }
        }
    }
}
