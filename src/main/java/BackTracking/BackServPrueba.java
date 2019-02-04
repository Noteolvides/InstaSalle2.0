package BackTracking;

import java.util.ArrayList;

public class BackServPrueba implements InterficieBacktraking {
    public static void main(String[] args) {
        int[] userHoras = new int[]{1, 9, 2, 3, 2, 9, 2, 4, 3, 4};
        int[] userDistancia = new int[]{2, 5, 4, 8, 6, 4, 6, 3, 5, 8};
        BackServPrueba bs = new BackServPrueba(userHoras,userDistancia,20);
        Backtracking.backTracking(0,0,bs);
        System.out.println(bs.getBest());
        System.out.println(bs.winUsers);
    }
    private int[] usersHoras;
    private int[] usersDistancia;
    private int media;
    private int best;
    private int actualDist = 0;
    public ArrayList winUsers;
    private ArrayList<Integer> usersTemp = new ArrayList<Integer>();
    private int error = 0;

    public int getBest() {
        return best;
    }

    public BackServPrueba(int[] usersHoras,int[] usersDistancia, int media) {
        this.usersHoras = usersHoras;
        this.usersDistancia = usersDistancia;
        this.media = media;
        this.best = 999999999;
    }



    public boolean bt(int i,int sum) {
        if(sum >= media){
            error = 0;
            return true;
        }
        if (i > usersDistancia.length-1){
            error = 1;
            return true;
        }
        error = 0;
        return false;
    }

    public void handleSolution(int i, int best) {
        if (error == 0){
            this.best = actualDist;
            winUsers = (ArrayList)usersTemp.clone();
        }
    }

    public int getEndOptions(int i) {
        return 2;
    }

    public boolean promising(int sum, int i, int x) {
        return actualDist+usersDistancia[i] < best || x == 1;
    }

    public void set(int i, int x) {
        if (x == 0){
            actualDist+= usersDistancia[i];
            usersTemp.add(i);
        }
    }

    public void unSet(int i, int x) {
        if (x == 0){
            actualDist -= usersDistancia[i];
            usersTemp.remove(usersTemp.size()-1);
        }
    }

    public int next(int i, int x) {
        return i+1;
    }

    public int agregation(int i, int x) {
        if (x == 0){
            return usersHoras[i];
        }
        return 0;
    }
}
