public class BackTracking {

  public void backTracking (Solution s) {
    if (bt(s)) {
      handleSolution(s);
    } else {
      for (int i = 0; i < option; i++) {
        if (promising(s, option)) {
          s = addOption(option);
          backTracking(s);
          s = removeOption(option);
        }
      }
    }
  }

  private boolean promising (){
    boolean ispromising;

    return ispromising;
  }

  private void handleSolution(){

  }

  private void addOption(){

  }

  private void removeOption(){

  }

  private boolean bt(){
    boolean isbt;

    return isbt;
  }
}
