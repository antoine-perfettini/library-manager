package org.library_manager.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private Connection aConnection;

    public BookDAO(Connection pConnection) {
        this.aConnection = pConnection;
    }
    /*
    Creation of CRUD methods
     */
    public List<Book> getAllBooks() throws SQLException, ClassNotFoundException {

        List<Book> vAllbBooks = new ArrayList<Book>();
        Statement vStatement = aConnection.createStatement();
        String sql = "SELECT * FROM lm_database.books_df";
        ResultSet vResultSet = vStatement.executeQuery(sql);
        while(vResultSet.next()) {
            Book vBook = new Book(vResultSet.getInt("id"),vResultSet.getString("Title"),vResultSet.getString("Author"),vResultSet.getString("Main_Genre"),vResultSet.getString("Sub_Genre"),vResultSet.getString("Type"),vResultSet.getString("Price"),vResultSet.getInt("Rating"),vResultSet.getInt("No_of_People_rated"),vResultSet.getString("URLs"));
            vAllbBooks.add(vBook);
        }
        return vAllbBooks;
    }
    //Create a new book in the database
    public void createBook(final Book pBook) {
        try {
            insertBook(pBook);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Get a book from the database
    public Book readBook(final int pId) throws SQLException {

        Book vBook = null;
        String sql = "SELECT * FROM lm_database.books_df WHERE id = ?";

        try (PreparedStatement vStatement = aConnection.prepareStatement(sql)) {

            vStatement.setInt(1, pId);
            ResultSet vResultSet = vStatement.executeQuery();

            if (vResultSet.next()) {

                vBook = new Book();

                vBook.setId(vResultSet.getInt("id"));
                vBook.setTitle(vResultSet.getString("Title"));
                vBook.setAuthor(vResultSet.getString("Author"));
                vBook.setMainGenre(vResultSet.getString("Main_Genre"));
                vBook.setSubGenre(vResultSet.getString("Sub_Genre"));
                vBook.setType(vResultSet.getString("Type"));
                vBook.setPrice(vResultSet.getString("Price"));
                vBook.setRating(vResultSet.getInt("Rating"));
                vBook.setNoPeopleRated(vResultSet.getInt("No_of_People_rated"));
                vBook.setURL(vResultSet.getString("URLs"));

                return vBook;
            }
            else{

                System.out.println("Book not found");
            }

        }catch(SQLException e) {
            System.err.println("Error while reading book : " + e.getMessage());
            throw e;
        }

        return vBook;
    }
    //Update an existing book in the database
    public void updateBook(final Book pBook) {

        String sql = "UPDATE lm_database.books_df SET Title=?, Author=?, Main_Genre=?, Sub_Genre=?, Type=?, Price=?, Rating=?, No_of_People_rated=?, URLs=? WHERE id=?";

            try (PreparedStatement vStatement = aConnection.prepareStatement(sql)) {

                vStatement.setString(1, pBook.getTitle());
                vStatement.setString(2, pBook.getAuthor());
                vStatement.setString(3, pBook.getMainGenre());
                vStatement.setString(4, pBook.getSubGenre());
                vStatement.setString(5, pBook.getType());
                vStatement.setString(6, pBook.getPrice());
                vStatement.setDouble(7, pBook.getRating());
                vStatement.setInt(8, pBook.getNoPeopleRated());
                vStatement.setString(9, pBook.getURL());

                vStatement.setInt(10, pBook.getId());

                vStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
    //Insert a new book in the database
    public void insertBook(final Book pBook) throws SQLException {

        String sql = "INSERT INTO lm_database.books_df (Title, Author, Main_Genre, Sub_Genre, Type, Price, Rating, No_of_People_rated, URLs) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (java.sql.PreparedStatement vStatement = aConnection.prepareStatement(sql)) {

            vStatement.setString(1, pBook.getTitle());
            vStatement.setString(2, pBook.getAuthor());
            vStatement.setString(3, pBook.getMainGenre());
            vStatement.setString(4, pBook.getSubGenre());
            vStatement.setString(5, pBook.getType());
            vStatement.setString(6, pBook.getPrice());
            vStatement.setDouble(7, pBook.getRating());
            vStatement.setInt(8, pBook.getNoPeopleRated());
            vStatement.setString(9, pBook.getURL());

            vStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion du livre : " + e.getMessage());
            throw e;
        }
    }
    //Delete a book from the database
    public void deleteBook(final Book pBook) {
        String sql = "DELETE FROM lm_database.books_df WHERE id=?";

        try (PreparedStatement vStatement = aConnection.prepareStatement(sql)) {
            vStatement.setInt(1, pBook.getId());
            vStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Get a sorted list of book
    public List<Book> getSortedBooks(final String pSortedBy, final String pSortedOrder) throws SQLException {
        List<Book> vAllbBooks = new ArrayList<Book>();
        Statement vStatement = aConnection.createStatement();
        String sql = "SELECT * FROM lm_database.books_df";

        // Only allow real column names to avoid SQL injection through ORDER BY
        List<String> vAllowedColumns = List.of("id", "Title", "Author", "Main_Genre", "Sub_Genre", "Type", "Price", "Rating", "No_of_People_rated", "URLs");

        if(pSortedBy!=null && vAllowedColumns.contains(pSortedBy)) {
            sql += " ORDER BY " + pSortedBy;

            if (pSortedOrder != null && (pSortedOrder.equals("ASC") || pSortedOrder.equals("DESC"))) {
                sql += " " + pSortedOrder;
            }
        }
        ResultSet vResultSet = vStatement.executeQuery(sql);
        while(vResultSet.next()) {
            Book vBook = new Book(vResultSet.getInt("id"),vResultSet.getString("Title"),vResultSet.getString("Author"),vResultSet.getString("Main_Genre"),vResultSet.getString("Sub_Genre"),vResultSet.getString("Type"),vResultSet.getString("Price"),vResultSet.getInt("Rating"),vResultSet.getInt("No_of_People_rated"),vResultSet.getString("URLs"));
            vAllbBooks.add(vBook);
        }
        return vAllbBooks;
    }
    //Get a filtered by genre list of book
    public List<Book> getBooksByMultipleGenres(List<String> pGenres) throws SQLException {
        List<Book> vResultList = new ArrayList<>();

        if (pGenres == null || pGenres.isEmpty()) {
            return vResultList; // Retourne une liste vide si aucun genre n'est choisi
        }

        // 1. Construction dynamique de la requête SQL
        // On veut obtenir : SELECT * FROM ... WHERE Main_Genre IN (?, ?, ?)
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM lm_database.books_df WHERE Main_Genre IN (");

        for (int i = 0; i < pGenres.size(); i++) {
            sqlBuilder.append("?");
            // On ajoute une virgule si ce n'est pas le dernier élément
            if (i < pGenres.size() - 1) {
                sqlBuilder.append(", ");
            }
        }
        sqlBuilder.append(")"); // On ferme la parenthèse

        // 2. Préparation et Remplissage des paramètres
        try (PreparedStatement vPs = aConnection.prepareStatement(sqlBuilder.toString())) {

            // On boucle pour remplacer chaque "?" par le nom du genre
            for (int i = 0; i < pGenres.size(); i++) {
                // JDBC commence à l'index 1, donc (i + 1)
                vPs.setString(i + 1, pGenres.get(i));
            }

            // 3. Exécution et Lecture
            ResultSet vResultSet = vPs.executeQuery();
            while(vResultSet.next()) {
                // Création de l'objet Book (copie ton code habituel ici)
                Book vBook = new Book(
                        vResultSet.getInt("id"),
                        vResultSet.getString("Title"),
                        vResultSet.getString("Author"),
                        vResultSet.getString("Main_Genre"),
                        vResultSet.getString("Sub_Genre"),
                        vResultSet.getString("Type"),
                        vResultSet.getString("Price"),
                        vResultSet.getInt("Rating"),
                        vResultSet.getInt("No_of_People_rated"),
                        vResultSet.getString("URLs")
                );
                vResultList.add(vBook);
            }
        }
        return vResultList;
    }
    //Get a filtered by subgenre list of book
    public List<Book> getBooksByMultipleSubGenres(List<String> pSubGenres) throws SQLException {
        List<Book> vResultList = new ArrayList<>();

        if (pSubGenres == null || pSubGenres.isEmpty()) {
            return vResultList;
        }

        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM lm_database.books_df WHERE Sub_Genre IN (");

        for (int i = 0; i < pSubGenres.size(); i++) {
            sqlBuilder.append("?");
            if (i < pSubGenres.size() - 1) {
                sqlBuilder.append(", ");
            }
        }
        sqlBuilder.append(")");

        try (PreparedStatement vPs = aConnection.prepareStatement(sqlBuilder.toString())) {
            for (int i = 0; i < pSubGenres.size(); i++) {
                vPs.setString(i + 1, pSubGenres.get(i));
            }

            ResultSet vResultSet = vPs.executeQuery();
            while(vResultSet.next()) {
                Book vBook = new Book(
                        vResultSet.getInt("id"),
                        vResultSet.getString("Title"),
                        vResultSet.getString("Author"),
                        vResultSet.getString("Main_Genre"),
                        vResultSet.getString("Sub_Genre"),
                        vResultSet.getString("Type"),
                        vResultSet.getString("Price"),
                        vResultSet.getInt("Rating"),
                        vResultSet.getInt("No_of_People_rated"),
                        vResultSet.getString("URLs")
                );
                vResultList.add(vBook);
            }
        }
        return vResultList;
    }
    //Get statistics by genre
    public String getGenreStatisticsReport() throws SQLException {

            String sql = "SELECT Main_Genre, " +
                    "COUNT(*) AS Total, " +
                    "AVG(Rating) AS AvgRate, " +
                    "AVG(CAST(REPLACE(REPLACE(REPLACE(Price, '$', ''), '₹', ''), '€','') AS DECIMAL(10,2))) AS AvgPrice " +
                    "FROM lm_database.books_df " +
                    "GROUP BY Main_Genre";

            StringBuilder vRapport = new StringBuilder();
            vRapport.append("=== STATISTICS BY GENRE ===\n\n");
            vRapport.append(String.format("%-25s | %-8s | %-10s | %-10s%n", "GENRE", "BOOKS", "AVG RATE.", "AVG PRICE."));
            vRapport.append("------------------------------------------------------------------\n");

            try (Statement stmt = aConnection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    String vGenre = rs.getString("Main_Genre");
                    int vTotal = rs.getInt("Total");
                    double AvgRate = rs.getDouble("AvgRate");
                    double AvgPrice = rs.getDouble("AvgPrice");

                    if (vGenre != null && !vGenre.isEmpty()) {
                        vRapport.append(String.format("%-25s | %-8d | %-10.1f | %-10.2f₹%n",
                                vGenre, vTotal, AvgRate, AvgPrice));
                    }
                }
            }
            return vRapport.toString();
        }
    //Get statistics by subgenre
    public String getSubGenreStatisticsReport() throws SQLException {

        String sql = "SELECT Sub_Genre, " +
                "COUNT(*) AS Total, " +
                "AVG(Rating) AS AvgRate, " +
                "AVG(CAST(REPLACE(REPLACE(REPLACE(Price, '$', ''), '₹', ''), '€','') AS DECIMAL(10,2))) AS AvgPrice " +
                "FROM lm_database.books_df " +
                "GROUP BY Sub_Genre";

        StringBuilder vRapport = new StringBuilder();
        vRapport.append("=== STATISTICS BY  SUB GENRE ===\n\n");
        vRapport.append(String.format("%-25s | %-8s | %-10s | %-10s%n", "SUB GENRE", "BOOKS", "AVG RATE.", "AVG PRICE."));
        vRapport.append("------------------------------------------------------------------\n");

        try (Statement stmt = aConnection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String vGenre = rs.getString("Sub_Genre");
                int vTotal = rs.getInt("Total");
                double AvgRate = rs.getDouble("AvgRate");
                double AvgPrice = rs.getDouble("AvgPrice");

                if (vGenre != null && !vGenre.isEmpty()) {
                    vRapport.append(String.format("%-25s | %-8d | %-10.1f | %-10.2f₹%n",
                            vGenre, vTotal, AvgRate, AvgPrice));
                }
            }
        }
        return vRapport.toString();
    }
}
