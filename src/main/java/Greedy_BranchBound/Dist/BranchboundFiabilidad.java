package Greedy_BranchBound.Dist;

import Json.ConnectsTo;
import Json.Nodes;
import Json.Server;
import Json.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class BranchboundFiabilidad {
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
        BranchboundFiabilidad bbd = new BranchboundFiabilidad(nodes,from,to);
    }

    public BranchboundFiabilidad(Nodes[] nodes, int inicial, int fin) throws CloneNotSupportedException {
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

    public void BranchBound() throws CloneNotSupportedException {
        Solution x = new Solution();
        Solution best = new Solution();
        PriorityQueue<Solution> lives_nodes = new PriorityQueue<Solution>(nodes.length,new Comparator<Solution>() {
            public int compare(Solution o1, Solution o2) {
                return (o2.getCost().compareTo(o1.getCost()));
            }
        });
        ArrayList<Solution> options = new ArrayList<Solution>();
        nodes[inicial].setSelected();
        x.track.add(nodes[inicial]);
        x.setCost(nodes[inicial].getReliability());
        best.setCost(0.0);
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
