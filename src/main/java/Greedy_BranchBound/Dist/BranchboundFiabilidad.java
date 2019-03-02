package Greedy_BranchBound.Dist;

import Greedy.GreedyDist.Greedy;
import Greedy.GreedyDist.GreedyFiable;
import Json.ConnectsTo;
import Json.Nodes;
import Json.Server;
import Json.User;

import java.util.*;


public class BranchboundFiabilidad {
    public static void main(String[] args, User[] users, Nodes[] nodes, Server[] servers) throws CloneNotSupportedException {
        for (int c = 0; c < users.length; c++) {
            users[c].setId(c);
        }
        ArrayList<User> usersList= new ArrayList<User>(Arrays.asList(users));

        int from = 1;
        int to = 4;
        GreedyFiable greedyFiable = new GreedyFiable(nodes, from, to);
        Greedy.greedy(from -1, to, greedyFiable);
        for (int i = 0; i < nodes.length; i++){
            nodes[i].clearSelected();
        }
        BranchboundFiabilidad bbd = new BranchboundFiabilidad(nodes,from,to, greedyFiable);
    }

    public BranchboundFiabilidad(Nodes[] nodes, int inicial, int fin, GreedyFiable greedyFiable) throws CloneNotSupportedException {
        this.nodes = nodes;
        this.inicial = inicial-1;
        this.fin = fin;

        Solution greedy = greedySolution(greedyFiable);

        BranchBound(greedy);
    }

    private Solution greedySolution(GreedyFiable greedy) {
        Solution greedy_sol = new Solution();
        greedy_sol.setCost((double)greedy.getBest());
        for (Integer pos:greedy.winPath){
            greedy_sol.track.add(nodes[pos-1]);
        }
        return greedy_sol;
    }

    private Nodes[] nodes;
    private int inicial;
    private int fin;

    private class Solution implements Cloneable{
        public void setTrack(LinkedList<Nodes> track) {
            this.track = track;
        }

        public LinkedList<Nodes> track  = new LinkedList<Nodes>();
        private Double cost = 1.0;


        public Double getCost() {
            return cost;
        }

        public void setCost(Double cost) {
            this.cost *= cost;
        }
        protected Object clone() throws CloneNotSupportedException{
            return super.clone();
        }
    }

    public void BranchBound(Solution greedy) throws CloneNotSupportedException {
        Solution x = new Solution();
        Solution best = greedy;
        PriorityQueue<Solution> lives_nodes = new PriorityQueue<Solution>(nodes.length,new Comparator<Solution>() {
            public int compare(Solution o1, Solution o2) {
                return (o2.getCost().compareTo(o1.getCost()));
            }
        });
        ArrayList<Solution> options = new ArrayList<Solution>();
        nodes[inicial].setSelected();
        x.track.add(nodes[inicial]);
        x.setCost(nodes[inicial].getReliability());
        lives_nodes.add(x);

        while (lives_nodes.size() != 0){
            x = lives_nodes.poll();
            List<ConnectsTo> t = x.track.getLast().getConnectsTo();
            for (ConnectsTo i: t){
                if (nodes[i.getTo()-1].getSelected() != 1){
                    Solution p = (Solution)x.clone();
                    p.setTrack((LinkedList<Nodes>) x.track.clone());
                    p.track.add(nodes[i.getTo()-1]);
                    p.setCost(nodes[i.getTo()-1].getReliability());
                    if (i.getTo() == fin){
                        if (p.getCost() > best.getCost()){
                            best = p;
                        }
                    }else{
                        if (p.getCost() > best.getCost()){
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
