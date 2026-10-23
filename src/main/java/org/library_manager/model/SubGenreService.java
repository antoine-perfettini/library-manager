package org.library_manager.model;

import java.sql.SQLException;
import java.util.List;

public class SubGenreService {

    private SubGenreDAO aSubGenreDAO;

    public SubGenreService(SubGenreDAO pSubGenreDAO, GenreDAO pGenreDAO) {
        this.aSubGenreDAO = pSubGenreDAO;
    }
    public List<SubGenre> getAllSubGenresService() throws SQLException, ClassNotFoundException {
        return aSubGenreDAO.getAllSubGenres();
    }
    public SubGenre getSingleSubGenreService(int pId) throws SQLException, ClassNotFoundException {
        return aSubGenreDAO.readSubGenre(pId);
    }
    public void deleteSubGenreService(final SubGenre pSubGenre) throws SQLException {
        aSubGenreDAO.deleteSubGenre(pSubGenre);
    }
    public void updateSubGenreService(final SubGenre pSubGenre) throws SQLException, ClassNotFoundException {
        aSubGenreDAO.updateSubGenre(pSubGenre);
    }
    public void insertSubGenreService(final SubGenre pSubGenre) throws SQLException, ClassNotFoundException {
        aSubGenreDAO.insertSubGenre(pSubGenre);
    }

}
