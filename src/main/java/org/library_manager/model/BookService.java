package org.library_manager.model;


import java.sql.SQLException;
import java.util.List;

public class BookService {

    private BookDAO aBookDAO;
    //
    public BookService(BookDAO pBookDAO, SubGenreDAO pSubGenreDAO) {
        this.aBookDAO = pBookDAO;
    }
    //
    public List<Book> getAllBooksService() throws SQLException, ClassNotFoundException {
        return aBookDAO.getAllBooks();
    }
    //
    public Book getSingleBookService(final int pId) throws SQLException, ClassNotFoundException {
        return aBookDAO.readBook(pId);
    }
    //
    public List<Book> getAllBooksSortedService(final String pSortedBy, final String pSortedOrder) throws SQLException {
        return aBookDAO.getSortedBooks(pSortedBy, pSortedOrder);
    }
    //
    public void updateBookService(Book pBook) throws SQLException, ClassNotFoundException {
        aBookDAO.updateBook(pBook);
    }
    //
    public void insertBookService(Book pBook) throws SQLException, ClassNotFoundException {
        aBookDAO.insertBook(pBook);
    }
    //
    public void deleteBookService(final Book pBook) throws SQLException, ClassNotFoundException {
        aBookDAO.deleteBook(pBook);
    }
    //
    public String getGenreStatisticsReportService() throws SQLException {
        return aBookDAO.getGenreStatisticsReport();
    }
    //
    public String getSubGenreStatisticsReportService() throws SQLException {
        return aBookDAO.getSubGenreStatisticsReport();
    }
    //
    public List<Book> getBooksByMultipleGenresService(List<String> pGenres) throws SQLException, ClassNotFoundException {
        return aBookDAO.getBooksByMultipleGenres(pGenres);
    }
    //
    public List<Book> getBooksByMultipleSubGenresService(List<String> pSubGenres) throws SQLException, ClassNotFoundException {
        return aBookDAO.getBooksByMultipleSubGenres(pSubGenres);
    }
}
