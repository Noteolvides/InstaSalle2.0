package BranchBoundServer;

import Json.Server;
import Json.User;

public class BranchboundServer {
    public static void main(String[] args) {

    }

    private User[] usuarios;
    private Server[] servidores;
    private int[] reparticion;
    public int[] wins;
    public float minimaActividad = 9999999;
    public double minimaDistancia = 9999999;
    private double distanciaActual = 0;
    private int[] actividadActualServidores;
    private int puntero = 0;

    public BranchboundServer(User[] usuarios, Server[] servidores) {//Pasar UsersId Por Orden Mejor pero da igual
        this.usuarios = usuarios;
        this.servidores = servidores;
        reparticion = new int[usuarios.length];
        wins = new int[usuarios.length];
        actividadActualServidores = new int[servidores.length];
    }

    public void branchboundServer(){
        while (not_live_nodes()){
            x = dequeue(live_nodes);
            option = expand(x);
            for (int i = 0; i < /*options*/; i++){
                if (is_solution(option)){
                    best = min/max(option, best);
                }else {
                    if (is_promising(option, best)){
                        enqueue (live_nodes, option);
                    }
                }
            }
        }
    }
}
