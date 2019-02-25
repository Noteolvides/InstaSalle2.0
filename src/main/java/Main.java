import BackTracking.BackTrackingDist.BackDist;
import BackTracking.BackTrackingDist.BackDistFiabilidad;
import BackTracking.BackTrackingServer.ServersDist;
import BranchBound.BranchBoundDist.BranchboundDist;
import BranchBound.BranchBoundDist.BranchboundFiabilidad;
import BranchBound.BranchBoundServer.BranchboundServer;
import Greedy.GreedyDist.GreedyDist;
import Greedy.GreedyDist.GreedyFiable;
import Greedy.GreedyServer.GreedyServer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int algorismo = 0;
        do {
            Scanner scanner = new Scanner(System.in);
            algorismo = scanner.nextInt();
            switch (algorismo) {
                case 1:
                    //BackTracking Distancia
                    BackDist.main(args);
                    //BackTracking Distancia fiable
                    BackDistFiabilidad.main(args);
                    //BackTracking servidores
                    ServersDist.main(args);
                    break;
                case 2:
                    //Branch&Bound Distancia
                    BranchboundDist.main(args);
                    //Branch&Bound Distancia fiable
                    BranchboundFiabilidad.main(args);
                    //Branch&Bound servidores
                    BranchboundServer.main(args);
                    break;
                case 3:
                    //Greedy Distancia
                    GreedyDist.main(args);
                    //Greedy Distancia fiable
                    GreedyFiable.main(args);
                    //Greedy servidores
                    GreedyServer.main(args);
                    break;
                case 4:
                    //Greedy + BackTracking
                    break;
                case 5:
                    //Greedy + Branch&Bound
                    break;
                default:
                    System.out.println("Opcio incorrecte");
                    break;
            }
        }while (algorismo == 0);
    }
}
        /*
        //Distribucion carga
        //BacktrackingDist -> condicion que haya usuario, y que vaya añadiendo
        // hasta que vea que se ha pasado de peso(este ultimo tambien se añade)
        //branch&bound -> igual que en BacktrackingDist?
        //relacion greedy -> numero de horas/distancia

        //Camino mas rapido y mas fiable
        //BacktrackingDist -> camino mas rapido y mas fiable tsp
        //branch&bound -> " "
        //greedy -> ordenando por las conexiones mas rapidas y las mas fiables
        */


