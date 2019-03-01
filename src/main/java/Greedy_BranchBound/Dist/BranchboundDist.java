package Greedy_BranchBound.Dist;

import Greedy.GreedyDist.Greedy;
import Greedy.GreedyDist.GreedyDist;
import Greedy.GreedyServer.GreedyServer;
import Json.ConnectsTo;
import Json.Nodes;
import Json.Server;
import Json.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

// TODO: 2019-03-01 Da la solucion del greedy

public class BranchboundDist {
    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
        Gson gson = new GsonBuilder().create();
        User[] users = gson.fromJson(new FileReader("Datasets/users.json"), User[].class);
        Nodes[] nodes = gson.fromJson(new FileReader("Datasets/nodes.json"), Nodes[].class);
        Server[] servers = gson.fromJson(new FileReader("Datasets/servers.json"), Server[].class);
        for (int c = 0; c < users.length; c++) {
            users[c].setId(c);
        }
        ArrayList<User> usersList= new ArrayList<User>(Arrays.asList(users));

        int from = 1;
        int to = 4;
        GreedyDist greedy = new GreedyDist(nodes, from, to);
        Greedy.greedy(from - 1, to, greedy);
        BranchboundDist bbd = new BranchboundDist(nodes,from,to, greedy);
    }



    public BranchboundDist(Nodes[] nodes, int inicial, int fin, GreedyDist greedyDist) throws CloneNotSupportedException {
        this.nodes = nodes;
        this.inicial = inicial-1;
        this.fin = fin;
        Solution greedy = greedySolution(greedyDist);
        BranchBound(greedy);
    }

    private Solution greedySolution(GreedyDist greedy) {
        Solution greedy_sol = new Solution();
        greedy_sol.setCost(greedy.getBest());
        for (Integer pos:greedy.winPath) {
            greedy_sol.track.add(nodes[pos - 1]);
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

    public void BranchBound(Solution greedy) throws CloneNotSupportedException {
        Solution x = new Solution();
        Solution best = greedy;
        PriorityQueue<Solution> lives_nodes = new PriorityQueue<Solution>(nodes.length,new Comparator<Solution>() {
            public int compare(Solution o1, Solution o2) {
                return o1.getCost() - o2.getCost();
            }
        });
        ArrayList<Solution> options = new ArrayList<Solution>();
        nodes[inicial].setSelected();
        x.track.add(nodes[inicial]);
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
