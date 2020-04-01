package main.model;

import java.util.ArrayList;
import java.util.List;

public class IPTCModel {
    private String description = null;
    private Integer rating = null;
    private List<String> tags = new ArrayList<String>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void deleteTag(String tag) {
        this.tags.remove(tag);
    }
}
