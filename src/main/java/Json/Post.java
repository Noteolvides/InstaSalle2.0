package Json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {

    private int id;

    private long published;

    private List<Double> location = null;

    private String category;
    @SerializedName("liked_by")
    private List<String> likedBy = null;
    @SerializedName("commented_by")
    private List<String> commentedBy = null;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<String> likedBy) {
        this.likedBy = likedBy;
    }

    public List<String> getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(List<String> commentedBy) {
        this.commentedBy = commentedBy;
    }

}