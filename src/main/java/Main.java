<<<<<<< HEAD
import BackTracking.*;
import Greedy.*;
=======
import BackTracking.BackDist;
import BackTracking.Backtracking;
>>>>>>> Prueba_Gus
import Json.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
<<<<<<< HEAD
    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        User[] users = gson.fromJson(new FileReader("Datasets/users.json"), User[].class);
        Nodes[] nodes = gson.fromJson(new FileReader("Datasets/datasets++/nodes_plus.json"), Nodes[].class);
        Server[] servers = gson.fromJson(new FileReader("Datasets/servers.json"), Server[].class);
        for (int c = 0; c < users.length; c++) {
            users[c].setId(c);
        }
        ArrayList<User> usersList= new ArrayList<User>(Arrays.asList(users));


        int from = 1;
        int to = 9;
        BackDist bd = new BackDist(nodes,from,to);
        Backtracking.backTracking(from-1,0,bd);
        System.out.println(bd.getBest());
        System.out.println(bd.winPath);


        from = 1;
        to = 9;
        GreedyDist gd = new GreedyDist(nodes,from,to);
        Greedy.greedy(from -1, to, gd);
        System.out.println(gd.getBest());
        System.out.println(gd.winPath);

        /*
        int totalActitvity;
        ArrayList<Double> activities = new ArrayList<Double>();
        for (User h : usersList){
            activities.add((h.getActivity()));
        }
        Collections.sort(activities);
        totalActitvity= (int) Math.round((activities.get(activities.size() / 2)));

        System.out.println(totalActitvity+"\n");

        for (int j = 0; j < servers.length; j++){
            for (User i: usersList){
                List<Double> locationSer = servers[j].getLocation();
                List<Double> location = i.getPosts().get(i.getPosts().size()-1).getLocation();
                i.setDistance(Haversine.distance(location.get(0),location.get(1),locationSer.get(0),locationSer.get(1)));
            }
            BackServ bs = new BackServ(usersList,totalActitvity);
            Backtracking.backTracking(0,0,bs);
            System.out.println("Best - Media " + bs.getBestMedia());
            System.out.println("Best - Dist " + bs.getbestDist());
            System.out.println("Array de usuarios " + bs.winUsers);
            User selec = null;
            for (Integer k: bs.winUsers) {
                for (User m: usersList){
                    if(m.getId() == (int)k){
                        selec = m;
                    }
                }
                usersList.remove(selec);
            }
            System.out.println("Cuantos Quedan " + usersList.size() +"\n");
        }*/
=======

>>>>>>> Prueba_Gus
    }
        /*
        //Distribucion carga
        //Backtracking -> condicion que haya usuario, y que vaya añadiendo
        // hasta que vea que se ha pasado de peso(este ultimo tambien se añade)
        //branch&bound -> igual que en Backtracking?
        //relacion greedy -> numero de horas/distancia

        //Camino mas rapido y mas fiable
        //Backtracking -> camino mas rapido y mas fiable tsp
        //branch&bound -> " "
        //greedy -> ordenando por las conexiones mas rapidas y las mas fiables
        */


