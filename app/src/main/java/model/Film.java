package model;

public class Film {
    private String title;
    private String year;
    private String id;
    private String image_url;

    public Film() {
    }

    public Film(String title, String year, String id, String image_url) {
        this.title = title;
        this.year = year;
        this.id = id;
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}

