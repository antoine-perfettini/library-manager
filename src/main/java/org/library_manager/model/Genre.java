package org.library_manager.model;

public class Genre {

    private int aId;
    private String aTitle;
    private int aNumberOfSubGenres;
    private String aUrl;

    public Genre() {
        this.aId = -1;
        this.aTitle = "";
        this.aNumberOfSubGenres = 0;
        this.aUrl = "";
    }

    public Genre(int pId, String pTitle, int pNumberOfSubGenres, String pUrl) {
        this.aId = pId;
        this.aTitle = pTitle;
        this.aNumberOfSubGenres = pNumberOfSubGenres;
        this.aUrl = pUrl;
    }

    //Getters
    public int getId() {
        return aId;
    }
    public String getTitle() {
        return aTitle;
    }
    public int getNumberOfSubGenres() {
        return aNumberOfSubGenres;
    }
    public String getURL() {
        return aUrl;
    }

    //Setters
    public void setId(int pId) {
        this.aId = pId;
    }
    public void setTitle(String pTitle) {
        this.aTitle = pTitle;
    }
    public void setNumberOfSubGenres(int pNumberOfSubGenres) {
        this.aNumberOfSubGenres = pNumberOfSubGenres;
    }
    public void setURL(String pUrl) {
        this.aUrl = pUrl;
    }

}
