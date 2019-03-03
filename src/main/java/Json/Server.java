package Json;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import javax.xml.transform.dom.DOMLocator;

public class Server {
    public Double activity = 0.0;
    private String id;

    private String country;

    private List<Double> location = null;
    @SerializedName("reachable_from")
    private int[] reachableFrom;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

}