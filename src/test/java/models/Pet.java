package models;

import enums.Status;

public class Pet {
    private long id;
    private String name;
    private String[] photoUrls;
    private Category category;
    private Tag[] tags;
    private Status status;

    public Pet(String name, String[] photoUrls, Category category, Tag[] tags, Status status) {
        this.name = name;
        this.photoUrls = photoUrls;
        this.category = category;
        this.tags = tags;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public Category getCategory() {
        return category;
    }

    public Tag[] getTags() {
        return tags;
    }

    public Status getStatus() {
        return status;
    }
}