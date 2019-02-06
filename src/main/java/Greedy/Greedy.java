package Greedy;

public class Greedy {
    public static void greedy(InterficieGreedy operation) {
        boolean found = false;
        int i = 0;

        while (!found && operation.candidatesToCheck(i)) {
            int c = operation.select(i);
            if (operation.is_feasible(i, c)) {
                operation.addCandidate(i);
            }
            if (operation.is_solution(i)) {
                found = true;
            }
        }
    }
}
