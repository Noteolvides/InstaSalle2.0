package BackTrackingNEW;

public class BackNew {
    public static void backTracking(BackTrackingInterN solution){
        if (solution.bt()){
            solution.handleSolution();
        }else{
            for(int x = 0; x < solution.getEndOptions(); x++){
                if (solution.promising()){
                    solution.set();
                    backTracking(solution.getNext());
                    solution.unSet();
                }
            }
        }
    }
}
