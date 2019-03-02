
import BackTracking.BackTrackingDist.BackDist;
import BackTracking.BackTrackingDist.BackDistFiabilidad;
import BackTracking.BackTrackingServer.BackServer;
import BranchBound.BranchBoundDist.BranchboundDist;
import BranchBound.BranchBoundDist.BranchboundFiabilidad;
import BranchBound.BranchBoundServer.BranchboundServer;
import Greedy.GreedyDist.GreedyDist;
import Greedy.GreedyDist.GreedyFiable;
import Greedy.GreedyServer.GreedyServer;
import Json.Nodes;
import Json.Server;
import Json.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static User[] users;
    public static Nodes[] nodes;
    public static Server[] servers;

    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {

        int file;

        do {
            System.out.println("Seleccione el dataset a utilizar");
            Scanner sc = new Scanner(System.in);
            System.out.println("1 - Datasets");
            System.out.println("2 - Datasets++");
            System.out.println("3 - Otros");
            System.out.println("4 - Salir");
            file = sc.nextInt();
            Gson gson = new GsonBuilder().create();
            switch (file) {
                case 1:
                    users = gson.fromJson(new FileReader("Datasets/users.json"), User[].class);
                    nodes = gson.fromJson(new FileReader("Datasets/nodes.json"), Nodes[].class);
                    servers = gson.fromJson(new FileReader("Datasets/servers.json"), Server[].class);
                    algorsimo(args);
                    break;
                case 2:
                    users = gson.fromJson(new FileReader("Datasets/users.json"), User[].class);
                    nodes = gson.fromJson(new FileReader("Datasets/datasets++/nodes_plus.json"), Nodes[].class);
                    servers = gson.fromJson(new FileReader("Datasets/datasets++/servers_plus.json"), Server[].class);
                    algorsimo(args);
                    break;
                case 3:
                    System.out.println("Introduce el path de users");
                    String path = sc.next();
                    users = gson.fromJson(new FileReader(path), User[].class);
                    System.out.println("Introduce el path de nodes");
                    path = sc.next();
                    nodes = gson.fromJson(new FileReader(path), Nodes[].class);
                    System.out.println("Introduce el path de servers");
                    path = sc.next();
                    servers = gson.fromJson(new FileReader(path), Server[].class);
                    algorsimo(args);
                    break;
                case 4:
                    break;
            }
        } while (file != 4);
    }

    private static void algorsimo(String[] args) throws CloneNotSupportedException {
        int option;

        do {
            System.out.println("Bienvenido a la seleccion de programa");
            Scanner sc = new Scanner(System.in);

            System.out.println("1 - Distancia entre nodos");
            System.out.println("2 - Fiabilidad entre nodos");
            System.out.println("3 - Repartir usuarios");
            System.out.println("4 - Volver");
            option = sc.nextInt();
            switch (option){
                case 1 :
                    caso1(args);
                    break;
                case 2:
                    caso2(args);
                    break;
                case 3:
                    caso3(args);
                    break;
                case 4:
                    break;
            }
        }while (option != 4);
    }

    private static void caso1(String[] args) throws CloneNotSupportedException {
        int option;
        do {
            System.out.println("\nDistancia entre nodos");
            Scanner sc = new Scanner(System.in);
            System.out.println("\t1 - Recorrido de nodo a nodo por distancia por Back");
            System.out.println("\t2 - Recorrido de nodo a nodo por distancia por Branch");
            System.out.println("\t3 - Recorrido de nodo a nodo por distancia por Greedy");
            System.out.println("\t4 - Recorrido de nodo a nodo por distancia con greedy + Back");
            System.out.println("\t5 - Recorrido de nodo a nodo por distancia con greedy + Branch");
            System.out.println("\t6 - Volver");
            option = sc.nextInt();
            switch (option){
                case 1:
                    BackDist.main(args, users, nodes, servers);
                    break;
                case 2:
                    BranchboundDist.main(args, users, nodes, servers);
                    break;
                case 3:
                    GreedyDist.main(args, users, nodes, servers);
                    break;
                case 4:
                    Greedy_BackTracking.Dist.BackDist.main(args, users, nodes, servers);
                    break;
                case 5:
                    Greedy_BranchBound.Dist.BranchboundDist.main(args, users, nodes, servers);
                    break;
                case 6:
                    break;
            }
        }while (option != 6);
    }

    private static void caso2(String[] args) throws CloneNotSupportedException {
        int option;
        do {
            System.out.println("\nFiabilidad entre nodos");
            Scanner sc = new Scanner(System.in);
            System.out.println("\t1 - Recorrido de nodo a nodo por fiabilidad por Back");
            System.out.println("\t2 - Recorrido de nodo a nodo por fiabilidad por Branch");
            System.out.println("\t3 - Recorrido de nodo a nodo por fiabilidad por Greedy");
            System.out.println("\t4 - Recorrido de nodo a nodo por fiabilidad con greedy + Back");
            System.out.println("\t5 - Recorrido de nodo a nodo por fiabilidad con greedy + Branch");
            System.out.println("\t6 - Volver");
            option = sc.nextInt();
            switch (option){
                case 1:
                    BackDistFiabilidad.main(args, users, nodes, servers);
                    break;
                case 2:
                    BranchboundFiabilidad.main(args, users, nodes, servers);
                    break;
                case 3:
                    GreedyFiable.main(args, users, nodes, servers);
                    break;
                case 4:
                    Greedy_BackTracking.Dist.BackDistFiabilidad.main(args, users, nodes, servers);
                    break;
                case 5:
                    Greedy_BranchBound.Dist.BranchboundFiabilidad.main(args, users, nodes, servers);
                    break;
                case 6:
                    break;
            }
        }while (option != 6);
    }

    private static void caso3(String[] args) {
        int option;
        do {
            System.out.println("\nRepartir usuarios");
            Scanner sc = new Scanner(System.in);
            System.out.println("\t1 - Repartir usuarios con bactra");
            System.out.println("\t2 - Repartir usuarios con branch");
            System.out.println("\t3 - Repartir usuarios con greedy");
            System.out.println("\t4 - Repartir usuarios con greeddy + Backtra");
            System.out.println("\t5 - Repartir usuarios con greedy + branch");
            System.out.println("\t6 - Volver");
            option = sc.nextInt();
            switch (option){
                case 1:
                    BackServer.main(args, users, nodes, servers);
                    break;
                case 2:
                    BranchboundServer.main(args, users, nodes, servers);
                    break;
                case 3:
                    GreedyServer.main(args, users, nodes, servers);
                    break;
                case 4:
                    Greedy_BackTracking.Server.BackServer.main(args, users, nodes, servers);
                    break;
                case 5:
                    Greedy_BranchBound.Server.BranchboundServer.main(args, users, nodes, servers);
                    break;
                case 6:
                    break;
            }
        }while (option != 6);
    }
}

