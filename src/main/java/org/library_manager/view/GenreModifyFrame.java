package org.library_manager.view;

import org.library_manager.model.Genre;
import javax.swing.*;
import java.awt.*;

    public class GenreModifyFrame {

        private JFrame aModifyFrame;
        private Genre aGenre;
        private JPanel aTitlePanel;
        private JLabel aTitleLabel;
        private JTextField aTitleField;
        private JPanel aNumSubGenrePanel;
        private JLabel aNumSubGenreLabel;
        private JTextField aNumSubGenreField;
        private JPanel aURLPanel;
        private JLabel aURLLabel;
        private JTextField aURLField;
        private JPanel aModifyPanel;
        private JPanel aSavePanel;
        private JButton aSaveButton;

        public GenreModifyFrame(Genre pGenre) {
            this.aGenre = pGenre;
        }

        public JFrame showModifyFrame() {
            aModifyFrame = new JFrame();
            // -1 means it is a new genre (default constructor), otherwise we modify an existing one
            aModifyFrame.setTitle(aGenre.getId() == -1 ? "Add Genre" : "Modify Genre");
            aModifyFrame.setSize(400, 200);
            aModifyFrame.setResizable(false);
            aModifyFrame.setLocationRelativeTo(null);

            aModifyPanel = new JPanel();
            aModifyPanel.setLayout(new BoxLayout(aModifyPanel, BoxLayout.Y_AXIS));
            aModifyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            aTitlePanel = new JPanel();
            aTitlePanel.setLayout(new BorderLayout());
            aTitleLabel = new JLabel("Title : ");
            aTitleLabel.setPreferredSize(new Dimension(140, 25));
            aTitleField = new JTextField(aGenre.getTitle());
            aTitlePanel.add(aTitleLabel, BorderLayout.WEST);
            aTitlePanel.add(aTitleField, BorderLayout.CENTER);

            aNumSubGenrePanel = new JPanel();
            aNumSubGenrePanel.setLayout(new BorderLayout());
            aNumSubGenreLabel = new JLabel("No. of sub-genres : ");
            aNumSubGenreLabel.setPreferredSize(new Dimension(140, 25));
            aNumSubGenreField = new JTextField(String.valueOf(aGenre.getNumberOfSubGenres()));
            aNumSubGenrePanel.add(aNumSubGenreLabel, BorderLayout.WEST);
            aNumSubGenrePanel.add(aNumSubGenreField, BorderLayout.CENTER);

            aURLPanel = new JPanel();
            aURLPanel.setLayout(new BorderLayout());
            aURLLabel = new JLabel("URL : ");
            aURLLabel.setPreferredSize(new Dimension(140, 25));
            aURLField = new JTextField(aGenre.getURL());
            aURLPanel.add(aURLLabel, BorderLayout.WEST);
            aURLPanel.add(aURLField, BorderLayout.CENTER);

            aModifyPanel.add(aTitlePanel);
            aModifyPanel.add(aNumSubGenrePanel);
            aModifyPanel.add(aURLPanel);

            aSavePanel = new JPanel();
            aSaveButton = new JButton("Save");
            aSavePanel.add(aSaveButton);

            aModifyFrame.add(aModifyPanel, BorderLayout.CENTER);
            aModifyFrame.add(aSavePanel, BorderLayout.SOUTH);

            aModifyFrame.setVisible(true);
            return aModifyFrame;
        }
        public JFrame getModifyFrame(){
            return aModifyFrame;
        }
        public Genre getModifyFrameGenre() {
            return this.aGenre;
        }
        public JButton getSaveButton() {
            return aSaveButton;
        }
        public void setModifiedGenre(){
            aGenre.setTitle(aTitleField.getText());
            aGenre.setURL(aURLField.getText());
            try {
                int numSubGenres = Integer.parseInt(aNumSubGenreField.getText());
                aGenre.setNumberOfSubGenres(numSubGenres);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(aModifyFrame, "No. of sub-genres must be a number.", "Invalid value", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
