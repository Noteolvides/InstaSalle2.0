package BackTracking;

import BackTracking.InterficieBacktraking;

public class Backtracking {
     public static void backTracking(int i, int sum, InterficieBacktraking operation){
        if (operation.bt(i,sum)){
            operation.handleSolution(i,sum);
        }else{
            for(int x = 0; x < operation.getEndOptions(i); x++){
                if (operation.promising(sum,i,x)){
                    operation.set(i,x);
                    backTracking(operation.next(i,x),sum + operation.agregation(i,x),operation);
                    operation.unSet(i,x);
                }
            }
        }
    }

}
