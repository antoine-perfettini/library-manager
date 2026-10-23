package org.library_manager.view;

import org.library_manager.model.SubGenre;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

    public class SubGenreFrame {

        private JFrame aSubGenreFrame;
        private SubGenre aSubGenre;
        private JButton aDeleteButton;
        private JButton aModifySubGenreButton;
        private JPanel aSubGenrePanel;
        private JTextArea aSubGenreTextAreaTitle;
        private JTextArea aSubGenreTextAreaMainGenre;
        private JTextArea aSubGenreTextAreaNumberOfBooks;
        private JTextArea aSubGenreTextAreaUrl;
        private ImageIcon aImage;
        private JLabel aImageLabel;
        private JPanel aImagePanel;
        private JPanel aButtonPanel;

        public SubGenreFrame(SubGenre pSubGenre){
            aSubGenre = pSubGenre;
        }

        public void createSubGenreDetailsPanel(){

            aSubGenreFrame = new JFrame();
            aSubGenreFrame.setTitle("SubGenre Details");
            ImageIcon vIcon = new ImageIcon(getClass().getResource("/sub_genre_img.png"));
            aSubGenreFrame.setIconImage(vIcon.getImage());
            aSubGenreFrame.setSize(580, 340);
            aSubGenreFrame.setLayout(new BorderLayout());
            aSubGenreFrame.setResizable(false);
            aSubGenreFrame.setLocationRelativeTo(null);

            aDeleteButton = new JButton("Delete SubGenre");
            aModifySubGenreButton = new JButton("Modify SubGenre");

            aSubGenrePanel = new JPanel();
            aSubGenrePanel.setLayout(new BoxLayout(aSubGenrePanel, BoxLayout.Y_AXIS));
            aSubGenrePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            Font vPolice = new Font("Arial", Font.BOLD, 12);

            aSubGenreTextAreaTitle = new JTextArea(" Title: " + aSubGenre.getTitle());
            aSubGenreTextAreaTitle.setFont(vPolice);
            aSubGenreTextAreaTitle.setLineWrap(true);
            aSubGenreTextAreaTitle.setWrapStyleWord(true);
            aSubGenreTextAreaTitle.setEditable(false);
            aSubGenrePanel.add(aSubGenreTextAreaTitle);

            aSubGenreTextAreaMainGenre = new JTextArea(" Main Genre: " + aSubGenre.getMainGenre());
            aSubGenreTextAreaMainGenre.setFont(vPolice);
            aSubGenreTextAreaMainGenre.setLineWrap(true);
            aSubGenreTextAreaMainGenre.setWrapStyleWord(true);
            aSubGenreTextAreaMainGenre.setEditable(false);
            aSubGenrePanel.add(aSubGenreTextAreaMainGenre);

            aSubGenreTextAreaNumberOfBooks = new JTextArea(" Number of Books: " + aSubGenre.getNumberOfBooks());
            aSubGenreTextAreaNumberOfBooks.setFont(vPolice);
            aSubGenreTextAreaNumberOfBooks.setLineWrap(true);
            aSubGenreTextAreaNumberOfBooks.setWrapStyleWord(true);
            aSubGenreTextAreaNumberOfBooks.setEditable(false);
            aSubGenrePanel.add(aSubGenreTextAreaNumberOfBooks);

            aSubGenreTextAreaUrl = new JTextArea(" Url: " + aSubGenre.getURL());
            aSubGenreTextAreaUrl.setFont(vPolice);
            aSubGenreTextAreaUrl.setLineWrap(true);
            aSubGenreTextAreaUrl.setWrapStyleWord(true);
            aSubGenreTextAreaUrl.setEditable(false);
            aSubGenrePanel.add(aSubGenreTextAreaUrl);

            aImage = new ImageIcon(getClass().getResource("/sub_genre_img.png"));
            Image vImage = aImage.getImage();
            Image imageRedimensionnee = vImage.getScaledInstance(150, 235, Image.SCALE_SMOOTH);
            aImage = new ImageIcon(imageRedimensionnee);
            aImageLabel = new JLabel(aImage);
            aImagePanel = new JPanel(new BorderLayout());
            int vPixels = 10;
            EmptyBorder vPadding = (EmptyBorder) BorderFactory.createEmptyBorder(vPixels, vPixels, vPixels, vPixels);
            aImagePanel.setBorder(vPadding);
            aImagePanel.add(aImageLabel, BorderLayout.CENTER);

            aButtonPanel = new JPanel();
            aButtonPanel.setLayout(new BoxLayout(aButtonPanel, BoxLayout.Y_AXIS));
            aModifySubGenreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            aDeleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            aButtonPanel.add(aModifySubGenreButton);
            aButtonPanel.add(Box.createRigidArea(new Dimension(0, 8)));
            aButtonPanel.add(aDeleteButton);
            aImagePanel.add(aButtonPanel, BorderLayout.SOUTH);

            aSubGenreFrame.add(aSubGenrePanel, BorderLayout.CENTER);
            aSubGenreFrame.add(aImagePanel, BorderLayout.WEST);

            aSubGenreFrame.setVisible(true);
        }
        public JButton getModifySubGenreButton(){
            return this.aModifySubGenreButton;
        }
        public JButton getDeleteButton(){
            return this.aDeleteButton;
        }
        public JFrame getSubGenreFrame(){
            return this.aSubGenreFrame;
        }
    }
