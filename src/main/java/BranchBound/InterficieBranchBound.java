package BranchBound;

import Json.Nodes;

import java.util.List;

public interface InterficieBranchBound {
    List<Nodes>[] expand(List<Nodes> x);
    boolean is_solution (List<Nodes> option);
    List<Nodes> minmax (List<Nodes> option, List<Nodes> best);
    boolean is_promising (List<Nodes> option, List<Nodes> best);
}
