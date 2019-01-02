public class Greedy {
   public Solution greedy(boolean found) {
        Candidate[] candidates;
        Solution s;
        found = false;

        //s = init solution
        //candidates = init candidates
        for (int  i = 0; i < candidates.length && !found; i++) {
            if (is_feasible(s, candidates[i])) {
                s = addSolution(s, candidates[i]);
            }
            if (is_solution(s)){
                found = true;
            }
        }
        return s;
   }

    private boolean is_solution (){
        boolean issolution;

        return issolution;
    }

    private boolean is_feasible(){
       boolean isfeasible;

       return  isfeasible;
    }

    private void addSolution(){

    }
}
