import Json.User;

import java.util.ArrayList;

public class BackServ implements InterficieBacktraking {
    private ArrayList<User> users;
    private int media;
    private int bestDist;

    private int bestMedia;

    private int actualDist = 0;
    public ArrayList<Integer> winUsers;
    private ArrayList<Integer> usersTemp = new ArrayList<Integer>();
    private int error = 0;
    public int getbestDist() {
        return bestDist;
    }

    public int getBestMedia() {
        return bestMedia;
    }

    public BackServ(ArrayList<User> users, int media) {
        this.users = users;
        this.media = media;
        this.bestDist = 999999999;
    }



    public boolean bt(int i,int sum) {
        if(sum >= media){
            error = 0;
            return true;
        }
        if (i > users.size()-1){
            error = 1;
            return true;
        }
        error = 0;
        return false;
    }

    public void handleSolution(int i, int best) {
        if (error == 0){
            this.bestDist = actualDist;
            this.bestMedia = best;
            winUsers = (ArrayList)usersTemp.clone();
        }
    }

    public int getEndOptions(int i) {
        return 2;
    }

    public boolean promising(int sum, int i, int x) {
        return actualDist+users.get(i).getDistance() < bestDist || x == 1;
    }

    public void set(int i, int x) {
        if (x == 0){
            actualDist+= users.get(i).getDistance();
            usersTemp.add(users.get(i).getId());
        }
    }

    public void unSet(int i, int x) {
        if (x == 0){
            actualDist -= users.get(i).getDistance();
            usersTemp.remove(usersTemp.size()-1);
        }
    }

    public int next(int i, int x) {
        return i+1;
    }

    public int agregation(int i, int x) {
        if (x == 0){
            return (int) users.get(i).getActivity();
        }
        return 0;
    }
}
