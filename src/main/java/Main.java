
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Bienvenido a la seleccion de programa");
        Scanner sc = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("1 - Si quieres hacer distancia y fiabilidad entre nodos");
            System.out.println("2 - Si quieres hacer reparto de pesos");
            System.out.println("3 - Si quieres salir");
            option = sc.nextInt();
            switch (option){
                case 1 :
                    caso1();
                    break;
                case 2:
                    caso2();
                    break;
            }
        }while (option != 3);
    }

    static void caso1(){
        Scanner sc = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("1 - Si quieres hacer un recorrido de nodo a nodo por distancia por Back");
            System.out.println("2 - Si quieres hacer un recorrido de nodo a nodo por distancia por Branch");
            System.out.println("3 - Si quieres hacer un recorrido de nodo a nodo por fiabilidad por Back");
            System.out.println("4 - Si quieres hacer un recorrido de nodo a nodo por fiabilidad por Branch");
            System.out.println("5 - Si quieres hacer un recorrido de nodo a nodo por distancia con greedy + Back");
            System.out.println("6 - Si quieres hacer un recorrido de nodo a nodo por distancia con greedy + Branch");
            System.out.println("7 - Si quieres hacer un recorrido de nodo a nodo por fiabilidad con greedy + Back");
            System.out.println("8 - Si quieres hacer un recorrido de nodo a nodo por fiabilidad con greedy + Branch");
            System.out.println("9 - Si quieres volver");
            option = sc.nextInt();
            switch (option){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
            }
        }while (option != 9);
    }

    static void caso2(){
        Scanner sc = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("1 - Si quieres repartir usuarios con bactra");
            System.out.println("2 - Si quieres hacer un recorrido con branch");
            System.out.println("3 - Si quieres hacer un recorrido con greeddy + Backtra");
            System.out.println("4 - Si quieres hacer un recorrido con greedy + branch");
            System.out.println("5 - Si quieres volver");
            option = sc.nextInt();
            switch (option){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        }while (option != 5);
    }
}

