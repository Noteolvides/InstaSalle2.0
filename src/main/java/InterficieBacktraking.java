public interface InterficieBacktraking {
    boolean bt(int i);
    void handleSolution(int best,int i);
    int getEndOptions(int i);
    boolean promising(int dist,int i,int x);
    void set(int i,int x);
    void unSet(int i,int x);
    int next(int i,int x);
    int agregation(int i,int x);
}
