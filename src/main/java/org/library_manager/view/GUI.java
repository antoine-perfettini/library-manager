package org.library_manager.view;

import org.library_manager.model.BookService;
import org.library_manager.model.GenreService;
import org.library_manager.model.SubGenreService;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GUI {

    private JFrame aMainWindow;
    private JPanel aNavBar;
    private JButton aNavBarButtonGenres;
    private JButton aNavBarButtonSubGenres;
    private JButton aNavBarButtonAllBooks;
    private CardLayout aMultiPagesLayout;
    private JPanel aMultiPagesPanel;
    private JPanel aGenresPanel;
    private JPanel aAllBooksPanel;
    private JTable aAllBooksTable;
    private JScrollPane aScrollPaneAllBooks;
    private BookService aBookService;
    private JPanel aControlBar;
    private JButton aControlBarDetailsButton;
    private JPanel aSortingBar;
    private JTextField aSearchSortBarField;
    private JLabel aSearchSortBarLabel;
    private JPanel aSearchSortBarPanel;
    private JComboBox<String> aSortingByComboBox;
    private JComboBox<String> aSortingOrderComboBox;
    private JButton aAddBookButton;
    private JButton aExportBookButton;
    private JButton aApplyMultiFilterButton;
    private JButton aApplySubGenreFilterButton;
    private JButton aReloadButton;
    private JTable aGenresTable;
    private GenreService aGenreService;
    private JScrollPane aScrollPaneGenres;
    private JPanel aControlBarGenre;
    private JButton aControlBarDetailsGenreButton;
    private JButton aAddGenreButton;
    private JPanel aGenreMainBar;
    private JButton aExportGenreButton;
    private JPanel aSearchMainBarPanel;
    private JTextField aSearchMainBarField;
    private JLabel aSearchMainBarLabel;
    private JPanel aSubGenrePanel;
    private JButton aInfoGenreButton;
    private JTable aSubGenreTable;
    private JScrollPane aScrollPaneSubGenre;
    private JPanel aSubGenreMainBar;
    private JButton aExportSubGenreButton;
    private JButton aInfoSubGenreButton;
    private JPanel aSearchMainBarSubGenrePanel;
    private JTextField aSearchMainBarSubGenreField;
    private JLabel aSearchMainBarSubGenreLabel;
    private JPanel aControlBarSubGenre;
    private JButton aControlBarDetailsSubGenreButton;
    private JButton aAddSubGenreButton;
    private SubGenreService aSubGenreService;

    public GUI(BookService pBookService, GenreService pGenreService, SubGenreService pSubGenreService) throws SQLException, ClassNotFoundException {
        aBookService = pBookService;
        aGenreService = pGenreService;
        aSubGenreService = pSubGenreService;
        this.createGUI();
    }

    public void createGUI() throws SQLException, ClassNotFoundException {
        //set the main window
        aMainWindow = new JFrame();
        aMainWindow.setTitle("Library Manager");
        ImageIcon vIcon = new ImageIcon(getClass().getResource("/frameIcon_img.png"));
        aMainWindow.setIconImage(vIcon.getImage());
        aMainWindow.setSize(1000, 800);
        aMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
            settings of the navbar
         */
        aNavBar = new JPanel();
        aNavBarButtonGenres = new JButton("Genres");
        aNavBarButtonSubGenres = new JButton("Sub Genres");
        aNavBarButtonAllBooks = new JButton("All Books");
        //add buttons in navbar
        aNavBar.add(aNavBarButtonGenres);
        aNavBar.add(aNavBarButtonSubGenres);
        aNavBar.add(aNavBarButtonAllBooks);
        aMainWindow.add(aNavBar, BorderLayout.NORTH);
        /*
            creation of a multi-pages panel
         */
        aMultiPagesLayout = new CardLayout();
        aMultiPagesPanel = new JPanel();
        aMultiPagesPanel.setLayout(aMultiPagesLayout);
        ////////////////////
        //Config Genres panel
        ////////////////////
        aGenresPanel = new JPanel();
        aGenresPanel.setLayout(new BorderLayout());
        GenreTableModele vGenreTableModele = new GenreTableModele(aGenreService.getAllGenresService());
        aGenresTable = new JTable(vGenreTableModele);
        aGenresTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        aScrollPaneGenres = new JScrollPane(aGenresTable);
        aGenresPanel.add(aScrollPaneGenres, BorderLayout.CENTER);
        //////
        aGenreMainBar = new JPanel();
        aGenreMainBar.setLayout(new GridLayout(1,4,10,0));
        aExportGenreButton = new JButton("Export");
        aInfoGenreButton = new JButton("Infos");
        aSearchMainBarPanel = new JPanel(new BorderLayout());
        aSearchMainBarField = new JTextField();
        aSearchMainBarLabel = new JLabel(" Search:");
        aSearchMainBarPanel.add(aSearchMainBarField, BorderLayout.CENTER);
        aSearchMainBarPanel.add(aSearchMainBarLabel, BorderLayout.WEST);
        aGenreMainBar.add(aSearchMainBarPanel);
        aGenreMainBar.add(aInfoGenreButton);
        aGenreMainBar.add(aExportGenreButton);
        aGenresPanel.add(aGenreMainBar, BorderLayout.NORTH);
        //Control bar
        aControlBarGenre = new JPanel();
        //button creation of the control bar
        aControlBarDetailsGenreButton = new JButton("Details");
        aAddGenreButton = new JButton("Add Genre");
        //add buttons in control bar
        aControlBarGenre.add(aAddGenreButton);
        aControlBarGenre.add(aControlBarDetailsGenreButton);
        //add control bar panel to the AllBooks panel
        aGenresPanel.add(aControlBarGenre, BorderLayout.SOUTH);
        /////////////////////////////////////////////////////////////end genre
        ////////////////////
        //Config Sub Genres panel
        ////////////////////
        aSubGenrePanel = new JPanel();
        aSubGenrePanel.setLayout(new BorderLayout());
        SubGenreTableModele vSubGenreTableModele = new SubGenreTableModele(aSubGenreService.getAllSubGenresService());
        aSubGenreTable = new JTable(vSubGenreTableModele);
        aSubGenreTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        aScrollPaneSubGenre = new JScrollPane(aSubGenreTable);
        aSubGenrePanel.add(aScrollPaneSubGenre, BorderLayout.CENTER);
        //////
        aSubGenreMainBar = new JPanel();
        aSubGenreMainBar.setLayout(new GridLayout(1,4,10,0));
        aExportSubGenreButton = new JButton("Export");
        aInfoSubGenreButton = new JButton("Infos");
        aSearchMainBarSubGenrePanel = new JPanel(new BorderLayout());
        aSearchMainBarSubGenreField = new JTextField();
        aSearchMainBarSubGenreLabel = new JLabel(" Search:");
        aSearchMainBarSubGenrePanel.add(aSearchMainBarSubGenreField, BorderLayout.CENTER);
        aSearchMainBarSubGenrePanel.add(aSearchMainBarSubGenreLabel, BorderLayout.WEST);
        aSubGenreMainBar.add(aSearchMainBarSubGenrePanel);
        aSubGenreMainBar.add(aInfoSubGenreButton);
        aSubGenreMainBar.add(aExportSubGenreButton);
        aSubGenrePanel.add(aSubGenreMainBar, BorderLayout.NORTH);
        //Control bar
        aControlBarSubGenre = new JPanel();
        //button creation of the control bar
        aControlBarDetailsSubGenreButton = new JButton("Details");
        aAddSubGenreButton = new JButton("Add Sub Genre");
        //add buttons in control bar
        aControlBarSubGenre.add(aAddSubGenreButton);
        aControlBarSubGenre.add(aControlBarDetailsSubGenreButton);
        //add control bar panel to the AllBooks panel
        aSubGenrePanel.add(aControlBarSubGenre, BorderLayout.SOUTH);
        /////////////////////////////////////////////////////////////end subgenre
        ////////////////////
        //Config AllBooks panel
        //////////////////
        aAllBooksPanel = new JPanel();
        aAllBooksPanel.setLayout(new BorderLayout());
        BookTableModele vBookTableModele = new BookTableModele(aBookService.getAllBooksService());
        aAllBooksTable = new JTable(vBookTableModele);
        aAllBooksTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        aScrollPaneAllBooks = new JScrollPane(aAllBooksTable);
        aAllBooksPanel.add(aScrollPaneAllBooks, BorderLayout.CENTER);
        //Control bar
        aControlBar = new JPanel();
        //button creation of the control bar
        aControlBarDetailsButton = new JButton("Details");
        aAddBookButton = new JButton("Add Book");
        //add buttons in control bar
        aControlBar.add(aAddBookButton);
        aControlBar.add(aControlBarDetailsButton);
        //add control bar panel to the AllBooks panel
        aAllBooksPanel.add(aControlBar, BorderLayout.SOUTH);
        // Sorting bar
        aSortingBar = new JPanel();
        aSortingBar.setLayout(new GridLayout(1,4,10,0));
        //button creation of the sorting bar
        String[] vSortingByCriterias = {"-- Sort by --", "Price", "Rating"};
        String[] vSortingOrderCriterias = {"-- Order --","ASC", "DESC"};
        aSortingByComboBox = new JComboBox<String>(vSortingByCriterias);
        aSortingOrderComboBox = new JComboBox<String>(vSortingOrderCriterias);
        aApplyMultiFilterButton = new JButton("Filter by Genre");
        aApplySubGenreFilterButton = new JButton("Filter by Sub Genre");
        aReloadButton = new JButton("Show all books");
        //
        aExportBookButton = new JButton("Export");
        //Search bar
        aSearchSortBarPanel = new JPanel(new BorderLayout());
        aSearchSortBarField = new JTextField();
        aSearchSortBarLabel = new JLabel(" Search:");
        aSearchSortBarPanel.add(aSearchSortBarField, BorderLayout.CENTER);
        aSearchSortBarPanel.add(aSearchSortBarLabel, BorderLayout.WEST);
        aSearchSortBarField.setPreferredSize(new java.awt.Dimension(100, 30));
        //add buttons in control bar
        aSortingBar.add(aSearchSortBarPanel);
        aSortingBar.add(aSortingByComboBox);
        aSortingBar.add(aSortingOrderComboBox);
        aSortingBar.add(aApplyMultiFilterButton);
        aSortingBar.add(aApplySubGenreFilterButton);
        aSortingBar.add(aReloadButton);
        aSortingBar.add(aExportBookButton);
        //add control bar panel to the AllBooks panel
        aAllBooksPanel.add(aSortingBar, BorderLayout.NORTH);
        //add panels to the aMultiPagesPanel
        aMultiPagesPanel.add(aGenresPanel, "Genres");
        aMultiPagesPanel.add(aSubGenrePanel, "SubGenres");
        aMultiPagesPanel.add(aAllBooksPanel, "All Books");
        //add aMultiPagesPanel to the frame
        aMainWindow.add(aMultiPagesPanel, BorderLayout.CENTER);
        //show the window once everything is built
        aMainWindow.setVisible(true);
    }
    public JFrame getMainWindow(){
        return this.aMainWindow;
    }
    public JButton getNavBarButtonGenres() {
        return this.aNavBarButtonGenres;
    }
    public JButton getNavBarButtonSubGenres(){
        return this.aNavBarButtonSubGenres;
    }
    public JButton getNavBarButtonAllBooks() {
        return this.aNavBarButtonAllBooks;
    }
    public CardLayout getMultiPagesLayout() {
        return this.aMultiPagesLayout;
    }
    public JPanel getMultiPagesPanel(){
        return this.aMultiPagesPanel;
    }
    public JButton getControlBarDetailsButton(){
        return this.aControlBarDetailsButton;
    }
    public JButton getControlBarDetailsGenreButton(){
        return this.aControlBarDetailsGenreButton;
    }
    public JButton getControlBarAddBookButton(){
        return this.aAddBookButton;
    }
    public JTable getAllBooksTable(){
        return this.aAllBooksTable;
    }
    public JComboBox<String> getSortingByComboBox(){
        return this.aSortingByComboBox;
    }
    public JComboBox<String> getSortingOrderComboBox(){
        return this.aSortingOrderComboBox;
    }
    public JTable getAllGenresTable(){
        return this.aGenresTable;
    }
    public BookService getBookService(){
        return this.aBookService;
    }
    public GenreService getGenreService(){
        return this.aGenreService;
    }
    public JButton getAddGenreButton(){
        return this.aAddGenreButton;
    }
    public JButton getExportBookButton(){
        return this.aExportBookButton;
    }
    public JButton getExportGenreButton(){
        return this.aExportGenreButton;
    }
    public JButton getControlBarDetailsSubGenreButton(){
        return this.aControlBarDetailsSubGenreButton;
    }
    public JTable getAllSubGenresTable(){
        return this.aSubGenreTable;
    }
    public SubGenreService getSubGenreService(){
        return this.aSubGenreService;
    }
    public JButton getAddSubGenreButton(){
        return this.aAddSubGenreButton;
    }
    public JButton getExportSubGenreButton(){
        return this.aExportSubGenreButton;
    }
    public JButton getInfoGenreButton(){
        return this.aInfoGenreButton;
    }
    public JButton getInfoSubGenreButton(){
        return this.aInfoSubGenreButton;
    }
    public JTextField getBookSearchBar(){
        return this.aSearchSortBarField;
    }
    public JTextField getGenreSearchBar(){
        return this.aSearchMainBarField;
    }
    public JTextField getSubGenreSearchBar(){
        return this.aSearchMainBarSubGenreField;
    }
    public JButton getApplyMultiFilterButton() { return this.aApplyMultiFilterButton; }
    public JButton getApplySubGenreFilterButton() { return this.aApplySubGenreFilterButton; }
    public JButton getReloadButton() { return this.aReloadButton; }
}
