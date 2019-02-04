package BranchBound;

import Json.Nodes;

import java.util.List;

public interface InterficieBranchBound {
    List<Nodes> expand(List<Nodes> x);
    boolean is_solution (Nodes option);
    List<Nodes> minmax (Nodes option, List<Nodes> best);
    boolean is_promising (Nodes option, List<Nodes> best);
}
