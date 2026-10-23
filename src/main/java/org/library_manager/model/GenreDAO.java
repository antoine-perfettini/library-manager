package org.library_manager.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {

    private Connection aConnection;

    public GenreDAO(Connection pConnection) {
        this.aConnection = pConnection;
    }
    /*
    Creation of CRUD methods
     */
    public List<Genre> getAllGenres() throws SQLException, ClassNotFoundException {

        List<Genre> vAllGenres = new ArrayList<Genre>();
        Statement vStatement = aConnection.createStatement();
        String sql = "SELECT * FROM lm_database.genre_df";
        ResultSet vResultSet = vStatement.executeQuery(sql);
        while(vResultSet.next()) {
            Genre vGenre = new Genre(vResultSet.getInt("id"),vResultSet.getString("Title"),vResultSet.getInt("Number_of_Sub_Genre"),vResultSet.getString("URLs"));
            vAllGenres.add(vGenre);

        }
        return vAllGenres;
    }
    //Get a genre from the database
    public Genre readGenre(final int pId) throws SQLException {

        Genre vGenre = null;
        String sql = "SELECT * FROM lm_database.genre_df WHERE id = ?";

        try (PreparedStatement vStatement = aConnection.prepareStatement(sql)) {

            vStatement.setInt(1, pId);
            ResultSet vResultSet = vStatement.executeQuery();

            if (vResultSet.next()) {

                vGenre = new Genre();

                vGenre.setId(vResultSet.getInt("id"));
                vGenre.setTitle(vResultSet.getString("Title"));
                vGenre.setNumberOfSubGenres(vResultSet.getInt("Number_of_Sub_Genre"));
                vGenre.setURL(vResultSet.getString("URLs"));

                return vGenre;
            }
            else{

                System.out.println("Genre not found");
            }

        }catch(SQLException e) {
            System.err.println("Error while reading genre : " + e.getMessage());
            throw e;
        }

        return vGenre;
    }
    //Update an existing genre in the database
    public void updateGenre(final Genre pGenre) {

        String sql = "UPDATE lm_database.genre_df SET Title=?, Number_Of_Sub_Genre=?, URLs=? WHERE id=?";

        try (PreparedStatement vStatement = aConnection.prepareStatement(sql)) {

            vStatement.setString(1, pGenre.getTitle());
            vStatement.setInt(2, pGenre.getNumberOfSubGenres());
            vStatement.setString(3, pGenre.getURL());

            vStatement.setInt(4, pGenre.getId());

            vStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Insert a new genre in the database
    public void insertGenre(final Genre pGenre) throws SQLException {

        String sql = "INSERT INTO lm_database.genre_df (Title, Number_Of_Sub_Genre, URLs) VALUES (?, ?, ?)";

        try (java.sql.PreparedStatement vStatement = aConnection.prepareStatement(sql)) {

            vStatement.setString(1, pGenre.getTitle());
            vStatement.setInt(2, pGenre.getNumberOfSubGenres());
            vStatement.setString(3, pGenre.getURL());

            vStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error during genre insertion: " + e.getMessage());
            throw e;
        }
    }
    //Delete a genre from the database
    public void deleteGenre(final Genre pGenre) {
        String sql = "DELETE FROM lm_database.genre_df WHERE id=?";

        try (PreparedStatement vStatement = aConnection.prepareStatement(sql)) {
            vStatement.setInt(1, pGenre.getId());
            vStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
