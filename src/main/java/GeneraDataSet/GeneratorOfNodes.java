package GeneraDataSet;

import Json.ConnectsTo;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GeneratorOfNodes {
    private static final int numeroOfNodes = 100;


    public static void main(String[] args) throws IOException {
        NodesG[] nodes = new NodesG[numeroOfNodes];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new NodesG();
            nodes[i].setId(i+1);
            nodes[i].setReliability(GeneratorOfUsers.generaNumeroDouble(0,1));
            int aux = GeneratorOfUsers.generaNumeroInt(1,numeroOfNodes);
            List<ConnectsTo> aux2 = new ArrayList<ConnectsTo>();
            for (int j = 0; j < aux; j++) {
                ConnectsTo ct = new ConnectsTo();
                ct.setName(" ");
                ct.setCost(GeneratorOfUsers.generaNumeroInt(1,20));
                ct.setTo(GeneratorOfUsers.generaNumeroInt(1,numeroOfNodes));
                aux2.add(ct);
            }
            nodes[i].setConnectsTo(aux2);
        }
        Gson gson = new Gson();
        String jsonInString = gson.toJson(nodes);
        System.out.println(jsonInString);
        FileWriter fileWriter = new FileWriter("Datasets/nodesRandom"+numeroOfNodes+".json");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(jsonInString);
        printWriter.close();
    }
}
