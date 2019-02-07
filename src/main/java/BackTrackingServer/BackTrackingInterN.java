package BackTrackingServer;

public interface BackTrackingInterN {
    void handleSolution();

    boolean bt();

    int getEndOptions();

    boolean promising(int next);

    void set(int next);

    BackTrackingInterN getNext(int next);

    void unSet(int next);
}
