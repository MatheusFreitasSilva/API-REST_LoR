package org.example.entities;

public class Set extends _BaseEntity{
    private String name;
    private String patch;
    private String releaseDate;
    private int size;

    public Set(){}
    public Set(String name, String patch, String releaseDate, int size) {
        this.name = name;
        this.patch = patch;
        this.releaseDate = releaseDate;
        this.size = size;
    }

    public Set(int id, String name, String patch, String releaseDate, int size) {
        super(id);
        this.name = name;
        this.patch = patch;
        this.releaseDate = releaseDate;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Set{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", patch='" + patch + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", size=" + size +
                "} ";
    }
}
