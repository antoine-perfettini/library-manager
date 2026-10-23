package org.library_manager.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubGenreDAO {

    private Connection aConnection;

    public SubGenreDAO(Connection pConnection) {
        this.aConnection = pConnection;
    }
    /*
    Creation of CRUD methods
     */
    public List<SubGenre> getAllSubGenres() throws SQLException, ClassNotFoundException {

        List<SubGenre> vAllSubGenres = new ArrayList<SubGenre>();
        Statement vStatement = aConnection.createStatement();
        String sql = "SELECT * FROM lm_database.sub_genre_df";
        ResultSet vResultSet = vStatement.executeQuery(sql);
        while(vResultSet.next()) {
            SubGenre vSubGenre = new SubGenre(vResultSet.getInt("id"), vResultSet.getString("Title"), vResultSet.getString("Main_Genre"), vResultSet.getInt("Number_of_Books"),vResultSet.getString("URLs"));
            vAllSubGenres.add(vSubGenre);

        }
        return vAllSubGenres;
    }
    //Get a sub genre from the database
    public SubGenre readSubGenre(final int pId) throws SQLException {

        SubGenre vSubGenre = null;
        String sql = "SELECT * FROM lm_database.sub_genre_df WHERE id = ?";

        try (PreparedStatement vStatement = aConnection.prepareStatement(sql)) {

            vStatement.setInt(1, pId);
            ResultSet vResultSet = vStatement.executeQuery();

            if (vResultSet.next()) {

                vSubGenre = new SubGenre();

                vSubGenre.setId(vResultSet.getInt("id"));
                vSubGenre.setTitle(vResultSet.getString("Title"));
                vSubGenre.setMainGenre(vResultSet.getString("Main_Genre"));
                vSubGenre.setNumberOfBooks(vResultSet.getInt("Number_of_Books"));
                vSubGenre.setURL(vResultSet.getString("URLs"));

                return vSubGenre;
            }
            else{

                System.out.println("Sub genre not found");
            }
        }catch(SQLException e) {
            System.err.println("Error while reading sub genre : " + e.getMessage());
            throw e;
        }

        return vSubGenre;
    }
    //Update an existing sub genre in the database
    public void updateSubGenre(final SubGenre pSubGenre) {

        String sql = "UPDATE lm_database.sub_genre_df SET Title=?, Main_Genre=?, Number_of_Books=?, URLs=? WHERE id=?";

        try (PreparedStatement vStatement = aConnection.prepareStatement(sql)) {

            vStatement.setString(1, pSubGenre.getTitle());
            vStatement.setString(2, pSubGenre.getMainGenre());
            vStatement.setInt(3, pSubGenre.getNumberOfBooks());
            vStatement.setString(4, pSubGenre.getURL());

            vStatement.setInt(5, pSubGenre.getId());

            vStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Insert a new sub genre in the database
    public void insertSubGenre(final SubGenre pSubGenre) throws SQLException {

        String sql = "INSERT INTO lm_database.sub_genre_df (Title, Main_Genre, Number_of_Books, URLs) VALUES (?, ?, ?, ?)";

        try (java.sql.PreparedStatement vStatement = aConnection.prepareStatement(sql)) {

            vStatement.setString(1, pSubGenre.getTitle());
            vStatement.setString(2, pSubGenre.getMainGenre());
            vStatement.setInt(3, pSubGenre.getNumberOfBooks());
            vStatement.setString(4, pSubGenre.getURL());

            vStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error during sub genre insertion: " + e.getMessage());
            throw e;
        }
    }
    //Delete a genre from the database
    public void deleteSubGenre(final SubGenre pSubGenre) {
        String sql = "DELETE FROM lm_database.sub_genre_df WHERE id=?";

        try (PreparedStatement vStatement = aConnection.prepareStatement(sql)) {
            vStatement.setInt(1, pSubGenre.getId());
            vStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
