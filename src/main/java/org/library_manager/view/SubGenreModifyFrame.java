package org.library_manager.view;

import org.library_manager.model.Genre;
import org.library_manager.model.GenreService;
import org.library_manager.model.SubGenre;
import javax.swing.*;
import java.awt.*;
import java.util.List;

    public class SubGenreModifyFrame {

        private JFrame aModifyFrame;
        private SubGenre aSubGenre;
        private GenreService aGenreService;
        private JPanel aTitlePanel;
        private JLabel aTitleLabel;
        private JTextField aTitleField;
        private JPanel aMainGenrePanel;
        private JLabel aMainGenreLabel;
        private JComboBox<String> aMainGenreComboBox;
        private JPanel aNumBooksPanel;
        private JLabel aNumBooksLabel;
        private JTextField aNumBooksField;
        private JPanel aURLPanel;
        private JLabel aURLLabel;
        private JTextField aURLField;
        private JPanel aModifyPanel;
        private JPanel aSavePanel;
        private JButton aSaveButton;

        public SubGenreModifyFrame(SubGenre pSubGenre, GenreService pGenreService) {
            this.aSubGenre = pSubGenre;
            this.aGenreService = pGenreService;
        }

        public void showModifyFrame() {
            aModifyFrame = new JFrame();
            // -1 means it is a new sub genre (default constructor), otherwise we modify an existing one
            aModifyFrame.setTitle(aSubGenre.getId() == -1 ? "Add SubGenre" : "Modify SubGenre");
            aModifyFrame.setSize(400, 250);
            aModifyFrame.setResizable(false);
            aModifyFrame.setLocationRelativeTo(null);

            aModifyPanel = new JPanel();
            aModifyPanel.setLayout(new BoxLayout(aModifyPanel, BoxLayout.Y_AXIS));
            aModifyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            aTitlePanel = new JPanel();
            aTitlePanel.setLayout(new BorderLayout());
            aTitleLabel = new JLabel("Title : ");
            aTitleLabel.setPreferredSize(new Dimension(140, 25));
            aTitleField = new JTextField(aSubGenre.getTitle());
            aTitlePanel.add(aTitleLabel, BorderLayout.WEST);
            aTitlePanel.add(aTitleField, BorderLayout.CENTER);

            aMainGenrePanel = new JPanel();
            aMainGenrePanel.setLayout(new BorderLayout());
            aMainGenreLabel = new JLabel("Main genre : ");
            aMainGenreLabel.setPreferredSize(new Dimension(140, 25));
            aMainGenreComboBox = new JComboBox<String>();
            try {
                List<Genre> vGenres = aGenreService.getAllGenresService();
                for (Genre g : vGenres) {
                    aMainGenreComboBox.addItem(g.getTitle());
                }
            } catch (Exception e) {
                // If the list cannot be loaded, keep at least the current value
            }
            aMainGenreComboBox.setSelectedItem(aSubGenre.getMainGenre());
            // If the current genre is not in the list (or the list failed to load), add it so it is not lost
            if (aMainGenreComboBox.getSelectedIndex() == -1 && aSubGenre.getMainGenre() != null && !aSubGenre.getMainGenre().isEmpty()) {
                aMainGenreComboBox.addItem(aSubGenre.getMainGenre());
                aMainGenreComboBox.setSelectedItem(aSubGenre.getMainGenre());
            }
            aMainGenrePanel.add(aMainGenreLabel, BorderLayout.WEST);
            aMainGenrePanel.add(aMainGenreComboBox, BorderLayout.CENTER);

            aNumBooksPanel = new JPanel();
            aNumBooksPanel.setLayout(new BorderLayout());
            aNumBooksLabel = new JLabel("No. of books : ");
            aNumBooksLabel.setPreferredSize(new Dimension(140, 25));
            aNumBooksField = new JTextField(String.valueOf(aSubGenre.getNumberOfBooks()));
            aNumBooksPanel.add(aNumBooksLabel, BorderLayout.WEST);
            aNumBooksPanel.add(aNumBooksField, BorderLayout.CENTER);

            aURLPanel = new JPanel();
            aURLPanel.setLayout(new BorderLayout());
            aURLLabel = new JLabel("URL : ");
            aURLLabel.setPreferredSize(new Dimension(140, 25));
            aURLField = new JTextField(aSubGenre.getURL());
            aURLPanel.add(aURLLabel, BorderLayout.WEST);
            aURLPanel.add(aURLField, BorderLayout.CENTER);

            aModifyPanel.add(aTitlePanel);
            aModifyPanel.add(aMainGenrePanel);
            aModifyPanel.add(aNumBooksPanel);
            aModifyPanel.add(aURLPanel);

            aSavePanel = new JPanel();
            aSaveButton = new JButton("Save");
            aSavePanel.add(aSaveButton);

            aModifyFrame.add(aModifyPanel, BorderLayout.CENTER);
            aModifyFrame.add(aSavePanel, BorderLayout.SOUTH);

            aModifyFrame.setVisible(true);
        }
        public JFrame getModifyFrame(){
            return aModifyFrame;
        }
        public SubGenre getModifyFrameSubGenre() {
            return this.aSubGenre;
        }
        public JButton getSaveButton() {
            return aSaveButton;
        }
        public void setModifiedSubGenre(){
            aSubGenre.setTitle(aTitleField.getText());
            aSubGenre.setMainGenre((String) aMainGenreComboBox.getSelectedItem());
            aSubGenre.setURL(aURLField.getText());
            try {
                int numBooks = Integer.parseInt(aNumBooksField.getText());
                aSubGenre.setNumberOfBooks(numBooks);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(aModifyFrame, "No. of books must be a number.", "Invalid value", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
