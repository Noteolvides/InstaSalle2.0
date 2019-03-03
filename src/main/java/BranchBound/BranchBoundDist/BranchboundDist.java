package BranchBound.BranchBoundDist;

import Json.ConnectsTo;
import Json.Nodes;
import Json.Server;
import Json.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class BranchboundDist {
    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
        Gson gson = new GsonBuilder().create();
        Nodes[] nodes = gson.fromJson(new FileReader(args[1]), Nodes[].class);
        int from = Integer.parseInt(args[3]);
        int to = Integer.parseInt(args[4]);
        BranchboundDist bbd = new BranchboundDist(nodes,from,to);
    }

    public BranchboundDist(Nodes[] nodes, int inicial, int fin) throws CloneNotSupportedException {
        this.nodes = nodes;
        this.inicial = inicial-1;
        this.fin = fin;
        BranchBound();
    }

    private Nodes[] nodes;
    private int inicial;
    private int fin;

    private class Solution implements Cloneable{
        public void setTrack(LinkedList<Nodes> track) {
            this.track = track;
        }

        public LinkedList<Nodes> track  = new LinkedList<Nodes>();
        private Integer cost = 0;


        public Integer getCost() {
            return cost;
        }

        public void setCost(Integer cost) {
            this.cost += cost;
        }
        protected Object clone() throws CloneNotSupportedException{
            return super.clone();
        }
    }

    public void BranchBound() throws CloneNotSupportedException {
        Solution x = new Solution();
        Solution best = new Solution();
        PriorityQueue<Solution> lives_nodes = new PriorityQueue<Solution>(nodes.length,new Comparator<Solution>() {
            public int compare(Solution o1, Solution o2) {
                return o1.getCost() - o2.getCost();
            }
        });
        ArrayList<Solution> options = new ArrayList<Solution>();
        nodes[inicial].setSelected();
        x.track.add(nodes[inicial]);
        best.setCost(Integer.MAX_VALUE);
        lives_nodes.add(x);

        while (lives_nodes.size() != 0){
            x = lives_nodes.poll();
            List<ConnectsTo> t = x.track.getLast().getConnectsTo();
            for (ConnectsTo i: t){
                if (nodes[i.getTo()-1].getSelected() != 1){
                    Solution p = (Solution)x.clone();
                    p.setTrack((LinkedList<Nodes>) x.track.clone());
                    p.track.add(nodes[i.getTo()-1]);
                    p.setCost(i.getCost());
                    if (i.getTo() == fin){
                        if (p.getCost() < best.getCost()){
                            best = p;
                        }
                    }else{
                        if (p.getCost() < best.getCost()){
                            nodes[i.getTo()-1].setSelected();
                            lives_nodes.add(p);
                        }
                    }
                }
            }
        }
        for (Nodes i : best.track){
            System.out.print(i.getId() + " ");
        }
        System.out.println("\nCoste " + best.getCost());
    }
}
