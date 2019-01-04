package Json;


import java.util.List;

public class Nodes {

    private int id;

    private Double reliability;

    private int selected = 0;

    private List<ConnectsTo> connectsTo = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getReliability() {
        return reliability;
    }

    public void setReliability(Double reliability) {
        this.reliability = reliability;
    }

    public List<ConnectsTo> getConnectsTo() {
        return connectsTo;
    }

    public void setConnectsTo(List<ConnectsTo> connectsTo) {
        this.connectsTo = connectsTo;
    }

    public void setSelected(){
        selected = 1;
    }

    public void clearSelected() {
        selected = 0;
    }

    public ConnectsTo getConnectsTo(int x){
        return connectsTo.get(x);
    }

    public int getSelected(){return selected;}

    public int getLenghtOfConexions(){
        return connectsTo.size();
    }
}