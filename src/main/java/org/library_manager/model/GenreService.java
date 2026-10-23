package org.library_manager.model;

import java.sql.SQLException;
import java.util.List;

public class GenreService {

    private GenreDAO aGenreDAO;

    public GenreService(GenreDAO pGenreDAO) {
        this.aGenreDAO = pGenreDAO;
    }
    public List<Genre> getAllGenresService() throws SQLException, ClassNotFoundException {
        return aGenreDAO.getAllGenres();
    }
    public Genre getSingleGenreService(int pId) throws SQLException, ClassNotFoundException {
        return aGenreDAO.readGenre(pId);
    }
    public void deleteGenreService(final Genre pGenre){
        aGenreDAO.deleteGenre(pGenre);
    }
    public void updateGenreService(final Genre pGenre) throws SQLException, ClassNotFoundException {
        aGenreDAO.updateGenre(pGenre);
    }
    public void insertGenreService(final Genre pGenre) throws SQLException, ClassNotFoundException {
        aGenreDAO.insertGenre(pGenre);
    }
}
