package Disponibilidad;

import Json.Nodes;
import java.util.List;

public class BackTracking {
  private Nodes[] nodes;

  public Solution millorPathBT (Solution s, Solution best){
    if (bt(s)){
      best = minmax(s, best);
    }else{
      int size = s.getPath().size();
      Nodes option;
      for (int i = 0; i < s.getPath().get(size).getConnectsTo().size()/*options*/; i++){
        for (int j = 0; j < nodes.length; j++) {
          if (nodes[j].getId() == s.getPath().get(size).getConnectsTo().get(i).getTo()) {
            option = nodes[j];
          }
        }
        if (promising(s, option) && s.getTotalcost() <= best.getTotalcost()){
          s.addNode(option);
          best = millorPathBT(s, best);
          s.removeNode(s.getPath().size());
        }
      }
    }
    return best;
  }

  private boolean promising (Solution s, Nodes option){
    boolean ispromising;

    return ispromising;
  }

  private Solution minmax(Solution s, Solution best){

    return best;
  }

  private boolean bt(Solution s){
    boolean isbt;

    return isbt;
  }
}
