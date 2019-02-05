package BackTrackingNEW;

public interface BackTrackingInterN {
    void handleSolution();

    boolean bt();

    int getEndOptions();

    boolean promising();

    void set();

    BackTrackingInterN getNext();

    void unSet();
}
