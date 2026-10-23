package org.library_manager.model;

public class Book {

    private int aId;
    private String aTitle;
    private String aAuthor;
    private String aMainGenre;
    private String aSubGenre;
    private String aType;
    private String aPrice;
    private double aRating;
    private int aNoPeopleRated;
    private String aURL;

    public Book(){
        aId = -1;
        aTitle = "";
        aAuthor = "";
        aMainGenre = "";
        aSubGenre = "";
        aType = "";
        aPrice = "";
        aRating = 0.0;
        aNoPeopleRated = 0;
        aURL = "";

    }

    public Book(final int pId, final String pTitle, final String pAuthor,final String pMainGenre,final String pSubGenre, final String pType, final String pPrice, final double pRating, final int pNoPeopleRated, final String pURL) {
        aId = pId;
        aTitle = pTitle;
        aAuthor = pAuthor;
        aMainGenre = pMainGenre;
        aSubGenre = pSubGenre;
        aType = pType;
        aPrice = pPrice;
        aRating = pRating;
        aNoPeopleRated = pNoPeopleRated;
        aURL = pURL;
    }

    public Book(Book pBook) {
        this.aTitle = pBook.getTitle();
        this.aAuthor = pBook.getAuthor();
        this.aMainGenre = pBook.getMainGenre();
        this.aSubGenre = pBook.getSubGenre();
        this.aType = pBook.getType();
        this.aPrice = pBook.getPrice();
        this.aRating = pBook.getRating();
        this.aNoPeopleRated = pBook.getNoPeopleRated();
        this.aURL = pBook.getURL();
    }

    //Getters for every attributes
    public int getId() {
        return aId;
    }
    public String getTitle() {
        return aTitle;
    }
    public String getAuthor() {
        return aAuthor;
    }
    public String getMainGenre() {
        return aMainGenre;
    }
    public String getSubGenre() {
        return aSubGenre;
    }
    public String getType() {
        return aType;
    }
    public String getPrice() {
        return aPrice;
    }
    public double getRating() {
        return aRating;
    }
    public int getNoPeopleRated() {
        return aNoPeopleRated;
    }
    public String getURL() {
        return aURL;
    }

    //Setters for every attributes
    public void setId(final int pId) {
        aId = pId;
    }
    public void setTitle(final String pTitle) {
        aTitle = pTitle;
    }
    public void setAuthor(final String pAuthor) {
        aAuthor = pAuthor;
    }
    public void setMainGenre(final String pMainGenre) {
        aMainGenre = pMainGenre;
    }
    public void setSubGenre(final String pSubGenre) {
        aSubGenre = pSubGenre;
    }
    public void setType(final String pType) {
        aType = pType;
    }
    public void setPrice(final String pPrice) {
        aPrice = pPrice;
    }
    public void setRating(final double pRating) {
        aRating = pRating;
    }
    public void setNoPeopleRated(final int pNoPeopleRated) {
        aNoPeopleRated = pNoPeopleRated;
    }
    public void setURL(final String pURL) {
        aURL = pURL;
    }

    //show book
    public void showBook() {
        System.out.println("Information about the book:");
        System.out.println(this.getTitle());
        System.out.println(this.getAuthor());
        System.out.println(this.getMainGenre());
        System.out.println(this.getSubGenre());
        System.out.println(this.getType());
        System.out.println(this.getPrice());
        System.out.println(""+this.getRating());
        System.out.println(""+this.getNoPeopleRated());
        System.out.println(this.getURL());

    }

}
