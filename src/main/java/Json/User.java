package Json;

import java.util.List;

public class User {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String username;

    private int followers;

    private int follows;

    private double activity;

    private List<Connection> connections = null;

    private List<Post> posts = null;

    private double distance;

    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }

    public int getSelected() {
        return selected;
    }

    private int selected = 1;

    public void setSelected(){
        selected = 1;
    }

    public void clearSelected() {
        selected = 0;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(Integer follows) {
        this.follows = follows;
    }

    public double getActivity() {
        return activity;
    }

    public void setActivity(double activity) {
        this.activity = activity;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setUbication() {
        List<Double> aux = this.posts.get(this.posts.size()-1).getLocation();
        latitude = aux.get(0);
        longitude = aux.get(1);
    }
}