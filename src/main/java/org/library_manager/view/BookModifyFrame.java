package org.library_manager.view;

import org.library_manager.model.Book;
import org.library_manager.model.Genre;
import org.library_manager.model.GenreService;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BookModifyFrame {

    private JFrame aModifyFrame;
    private Book aBook;
    private GenreService aGenreService;
    private JPanel aTitlePanel;
    private JLabel aTitleLabel;
    private JTextField aTitleField;
    private JPanel aAuthorPanel;
    private JLabel aAuthorLabel;
    private JTextField aAuthorField;
    private JPanel aMainGenrePanel;
    private JLabel aMainGenreLabel;
    private JComboBox<String> aMainGenreComboBox;
    private JPanel aSubGenrePanel;
    private JLabel aSubGenreLabel;
    private JTextField aSubGenreField;
    private JPanel aTypePanel;
    private JLabel aTypeLabel;
    private JTextField aTypeField;
    private JPanel aPricePanel;
    private JLabel aPriceLabel;
    private JTextField aPriceField;
    private JPanel aRatingPanel;
    private JLabel aRatingLabel;
    private JTextField aRatingField;
    private JPanel aNoPeopleRatedPanel;
    private JLabel aNoPeopleRatedLabel;
    private JTextField aNoPeopleRatedField;
    private JPanel aURLPanel;
    private JLabel aURLLabel;
    private JTextField aURLField;
    private JPanel aModifyPanel;
    private JPanel aSavePanel;
    private JButton aSaveButton;

    public BookModifyFrame(Book pBook, GenreService pGenreService) {
        this.aBook = pBook;
        this.aGenreService = pGenreService;
    }

    public JFrame showModifyFrame() {
        aModifyFrame = new JFrame();
        // -1 means it is a new book (default constructor), otherwise we modify an existing one
        aModifyFrame.setTitle(aBook.getId() == -1 ? "Add book" : "Modify book");
        aModifyFrame.setSize(450, 400);
        aModifyFrame.setResizable(false);
        aModifyFrame.setLocationRelativeTo(null);

        aModifyPanel = new JPanel();
        aModifyPanel.setLayout(new BoxLayout(aModifyPanel, BoxLayout.Y_AXIS));
        aModifyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        aTitlePanel = new JPanel();
        aTitlePanel.setLayout(new BorderLayout());
        aTitleLabel = new JLabel("Title : ");
        aTitleLabel.setPreferredSize(new Dimension(130, 25));
        aTitleField = new JTextField(aBook.getTitle());
        aTitlePanel.add(aTitleLabel, BorderLayout.WEST);
        aTitlePanel.add(aTitleField, BorderLayout.CENTER);

        aAuthorPanel = new JPanel();
        aAuthorPanel.setLayout(new BorderLayout());
        aAuthorLabel = new JLabel("Author : ");
        aAuthorLabel.setPreferredSize(new Dimension(130, 25));
        aAuthorField = new JTextField(aBook.getAuthor());
        aAuthorPanel.add(aAuthorLabel, BorderLayout.WEST);
        aAuthorPanel.add(aAuthorField, BorderLayout.CENTER);

        aMainGenrePanel = new JPanel();
        aMainGenrePanel.setLayout(new BorderLayout());
        aMainGenreLabel = new JLabel("Main genre : ");
        aMainGenreLabel.setPreferredSize(new Dimension(130, 25));
        aMainGenreComboBox = new JComboBox<String>();
        try {
            List<Genre> vGenres = aGenreService.getAllGenresService();
            for (Genre g : vGenres) {
                aMainGenreComboBox.addItem(g.getTitle());
            }
        } catch (Exception e) {
            // If the list cannot be loaded, keep at least the current value
        }
        aMainGenreComboBox.setSelectedItem(aBook.getMainGenre());
        // If the current genre is not in the list (or the list failed to load), add it so it is not lost
        if (aMainGenreComboBox.getSelectedIndex() == -1 && aBook.getMainGenre() != null && !aBook.getMainGenre().isEmpty()) {
            aMainGenreComboBox.addItem(aBook.getMainGenre());
            aMainGenreComboBox.setSelectedItem(aBook.getMainGenre());
        }
        aMainGenrePanel.add(aMainGenreLabel, BorderLayout.WEST);
        aMainGenrePanel.add(aMainGenreComboBox, BorderLayout.CENTER);

        aSubGenrePanel = new JPanel();
        aSubGenrePanel.setLayout(new BorderLayout());
        aSubGenreLabel = new JLabel("Sub genre : ");
        aSubGenreLabel.setPreferredSize(new Dimension(130, 25));
        aSubGenreField = new JTextField(aBook.getSubGenre());
        aSubGenrePanel.add(aSubGenreLabel, BorderLayout.WEST);
        aSubGenrePanel.add(aSubGenreField, BorderLayout.CENTER);

        aTypePanel = new JPanel();
        aTypePanel.setLayout(new BorderLayout());
        aTypeLabel = new JLabel("Type : ");
        aTypeLabel.setPreferredSize(new Dimension(130, 25));
        aTypeField = new JTextField(aBook.getType());
        aTypePanel.add(aTypeLabel, BorderLayout.WEST);
        aTypePanel.add(aTypeField, BorderLayout.CENTER);

        aPricePanel = new JPanel();
        aPricePanel.setLayout(new BorderLayout());
        aPriceLabel = new JLabel("Price : ");
        aPriceLabel.setPreferredSize(new Dimension(130, 25));
        aPriceField = new JTextField(aBook.getPrice());
        aPricePanel.add(aPriceLabel, BorderLayout.WEST);
        aPricePanel.add(aPriceField, BorderLayout.CENTER);

        aRatingPanel = new JPanel();
        aRatingPanel.setLayout(new BorderLayout());
        aRatingLabel = new JLabel("Rating : ");
        aRatingLabel.setPreferredSize(new Dimension(130, 25));
        aRatingField = new JTextField(""+aBook.getRating());
        aRatingPanel.add(aRatingLabel, BorderLayout.WEST);
        aRatingPanel.add(aRatingField, BorderLayout.CENTER);

        aNoPeopleRatedPanel = new JPanel();
        aNoPeopleRatedPanel.setLayout(new BorderLayout());
        aNoPeopleRatedLabel = new JLabel("No. people rated : ");
        aNoPeopleRatedLabel.setPreferredSize(new Dimension(130, 25));
        aNoPeopleRatedField = new JTextField(""+aBook.getNoPeopleRated());
        aNoPeopleRatedPanel.add(aNoPeopleRatedLabel, BorderLayout.WEST);
        aNoPeopleRatedPanel.add(aNoPeopleRatedField, BorderLayout.CENTER);

        aURLPanel = new JPanel();
        aURLPanel.setLayout(new BorderLayout());
        aURLLabel = new JLabel("URL : ");
        aURLLabel.setPreferredSize(new Dimension(130, 25));
        aURLField = new JTextField(aBook.getURL());
        aURLPanel.add(aURLLabel, BorderLayout.WEST);
        aURLPanel.add(aURLField, BorderLayout.CENTER);

        aModifyPanel.add(aTitlePanel);
        aModifyPanel.add(aAuthorPanel);
        aModifyPanel.add(aMainGenrePanel);
        aModifyPanel.add(aSubGenrePanel);
        aModifyPanel.add(aTypePanel);
        aModifyPanel.add(aPricePanel);
        aModifyPanel.add(aRatingPanel);
        aModifyPanel.add(aNoPeopleRatedPanel);
        aModifyPanel.add(aURLPanel);

        aSavePanel = new JPanel();
        aSaveButton = new JButton("Save");
        aSavePanel.add(aSaveButton);

        aModifyFrame.add(aModifyPanel);
        aModifyFrame.add(aSavePanel, BorderLayout.SOUTH);
        aModifyFrame.setVisible(true);
        return aModifyFrame;
    }
    public JFrame getModifyFrame(){
        return aModifyFrame;
    }
    public Book getModifyFrameBook() {
        return this.aBook;
    }
    public JButton getSaveButton() {
        return aSaveButton;
    }
    public void setModifiedBook(){
        aBook.setTitle(aTitleField.getText());
        aBook.setAuthor(aAuthorField.getText());
        aBook.setMainGenre((String) aMainGenreComboBox.getSelectedItem());
        aBook.setSubGenre(aSubGenreField.getText());
        aBook.setType(aTypeField.getText());
        aBook.setPrice(aPriceField.getText());
        aBook.setURL(aURLField.getText());
        try {
            aBook.setRating(Double.parseDouble(aRatingField.getText()));
            aBook.setNoPeopleRated(Integer.parseInt(aNoPeopleRatedField.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(aModifyFrame, "Rating and No. people rated must be numbers.", "Invalid value", JOptionPane.WARNING_MESSAGE);
        }
    }

}
