package org.library_manager;

import org.library_manager.controller.Controller;
import org.library_manager.database.MySQLConnection;
import org.library_manager.model.*;
import org.library_manager.view.GUI;
import javax.swing.*;
import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try{
        MySQLConnection vMySQL = new MySQLConnection();
        Connection vConnection = vMySQL.manageConnection();
        BookDAO vBookDAO = new BookDAO(vConnection);
        GenreDAO vGenreDAO = new GenreDAO(vConnection);
        SubGenreDAO vSubGenreDAO = new SubGenreDAO(vConnection);
        BookService vBookService = new BookService(vBookDAO, vSubGenreDAO);
        GenreService vGenreService = new GenreService(vGenreDAO);
        SubGenreService vSubGenreService = new SubGenreService(vSubGenreDAO, vGenreDAO);
        GUI vGui = new GUI(vBookService, vGenreService, vSubGenreService);
        Controller vController = new Controller(vGui);
        } catch (Exception e) {
        JOptionPane.showMessageDialog(
                null,
                "Cannot connect to the database !\nVerify MySQL is launched.\n\nDetails: " + e.getMessage(),
                "Error while launching the application",
                JOptionPane.ERROR_MESSAGE
        );
        e.printStackTrace();
        }
    }
}

