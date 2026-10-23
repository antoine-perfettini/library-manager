package org.library_manager.model;

public class SubGenre {

    private int aId;
    private String aTitle;
    private String aMainGenre;
    private int aNumberOfBooks;
    private String aUrl;

    public SubGenre() {

        this.aId = -1;
        this.aTitle = "";
        this.aMainGenre = "";
        this.aNumberOfBooks = 0;
        this.aUrl = "";

    }
    public SubGenre(int pId, String pTitle, String pMainGenre, int pNumberOfBooks, String pUrl) {

        this.aId = pId;
        this.aTitle = pTitle;
        this.aMainGenre = pMainGenre;
        this.aNumberOfBooks = pNumberOfBooks;
        this.aUrl = pUrl;

    }

    //Getters
    public int getId() {
        return aId;
    }
    public String getTitle() {
        return aTitle;
    }
    public String getMainGenre() {
        return aMainGenre;
    }
    public int getNumberOfBooks() {
        return aNumberOfBooks;
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
    public void setMainGenre(String pMainGenre) {
        this.aMainGenre = pMainGenre;
    }
    public void setNumberOfBooks(int pNumberOfBooks) {
        this.aNumberOfBooks = pNumberOfBooks;
    }
    public void setURL(String pURL) {
        this.aUrl = pURL;
    }
}
