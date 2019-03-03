package GeneraDataSet;

import Json.ConnectsTo;

import java.util.List;

public class NodesG {

    public void setId(int id) {
        this.id = id;
    }

    public void setReliability(Double reliability) {
        this.reliability = reliability;
    }

    public void setConnectsTo(List<ConnectsTo> connectsTo) {
        this.connectsTo = connectsTo;
    }

    private int id;

    private Double reliability;

    private List<ConnectsTo> connectsTo = null;
}
