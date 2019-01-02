package Disponibilidad;

import Json.Nodes;
import java.util.List;

public class BackTracking {

  /*public void backTracking (List<Nodes> s) {
    if (bt(s)) {
      handleSolution(s);
    } else {
      for (int i = 0; i < options; i++) {
        if (promising(s, option)) {
          s = addOption(option);
          backTracking(s);
          s = removeOption(option);
        }
      }
    }
  }*/

  public List<Nodes> millorPathBT (List<Nodes> s, List<Nodes> best){
    if (bt(s)){
      best = minmax(s, best);
    }else{
      for (int i = 0; i < s.size()/*options*/; i++){
        if (promising(s, option) && s <= best){
          s = addOption(s, option);
          best = millorPathBT(s, best);
          s = removeOption(s, option);
        }
      }
    }
  }

  private boolean promising (List<Nodes> s){
    boolean ispromising;

    return ispromising;
  }

  private List<Nodes> minmax(List<Nodes> s, List<Nodes> best){

    return best;
  }

  private void addOption(){

  }

  private void removeOption(){

  }

  private boolean bt(List<Nodes> s){
    boolean isbt;

    return isbt;
  }
}
