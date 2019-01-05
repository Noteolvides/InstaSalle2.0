public interface InterficieBacktraking {
    boolean bt(int i,int sum);
    void handleSolution(int i,int best);
    int getEndOptions(int i);
    boolean promising(int sum,int i,int x);
    void set(int i,int x);
    void unSet(int i,int x);
    int next(int i,int x);
    int agregation(int i,int x);
}
