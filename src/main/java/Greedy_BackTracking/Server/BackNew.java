package Greedy_BackTracking.Server;

public class BackNew {
    public static void backTracking(BackServer solution){
        if (solution.bt()){
            solution.handleSolution();
        }else{
            for(int x = 0; x < solution.getEndOptions(); x++){
                if (solution.promising(x)){
                    solution.set(x);
                    backTracking(solution.getNext(x));
                    solution.unSet(x);
                }
            }
        }
    }
}
