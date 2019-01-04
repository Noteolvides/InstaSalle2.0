public class Backtracking {
     static void backTracking (int i,int sum,InterficieBacktraking interback){
        if (interback.bt(i)){
            interback.handleSolution(i,sum);
        }else{
            for(int x = 0; x < interback.getEndOptions(i); x++){
                if (interback.promising(sum,i,x)){
                    interback.set(i,x);
                    backTracking(interback.next(i,x),sum + interback.agregation(i,x),interback);
                    interback.unSet(i,x);
                }
            }
        }
    }

}
