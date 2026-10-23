package org.library_manager.view;

import org.library_manager.model.Genre;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GenreFrame{

    private JFrame aGenreFrame;
    private Genre aGenre;
    private JButton aDeleteButton;
    private JButton aModifyGenreButton;
    private JPanel aGenrePanel;
    private JTextArea aGenreTextAreaTitle;
    private JTextArea aGenreTextAreaNumberOfSubGenres;
    private JTextArea aGenreTextAreaUrl;
    private ImageIcon aImage;
    private JLabel aImageLabel;
    private JPanel aImagePanel;
    private JPanel aButtonPanel;

    public GenreFrame(Genre pGenre){
        aGenre = pGenre;
    }

    public void createGenreDetailsPanel(){

        aGenreFrame = new JFrame();
        aGenreFrame.setTitle("Genre Details");
        ImageIcon vIcon = new ImageIcon(getClass().getResource("/genre_img.png"));
        aGenreFrame.setIconImage(vIcon.getImage());
        aGenreFrame.setSize(550, 320);
        aGenreFrame.setLayout(new BorderLayout());
        aGenreFrame.setResizable(false);
        aGenreFrame.setLocationRelativeTo(null);

        aDeleteButton = new JButton("Delete Genre");
        aModifyGenreButton = new JButton("Modify Genre");

        aGenrePanel = new JPanel();
        aGenrePanel.setLayout(new BoxLayout(aGenrePanel, BoxLayout.Y_AXIS));
        aGenrePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        Font vPolice = new Font("Arial", Font.BOLD, 12);
        aGenreTextAreaTitle = new JTextArea(" Title: "+aGenre.getTitle());

        aGenreTextAreaTitle.setFont(vPolice);
        aGenreTextAreaTitle.setLineWrap(true);
        aGenreTextAreaTitle.setWrapStyleWord(true);

        aGenreTextAreaTitle.setEditable(false);
        aGenrePanel.add(aGenreTextAreaTitle);

        aGenreTextAreaNumberOfSubGenres = new JTextArea(" Number of SubGenre: "+aGenre.getNumberOfSubGenres());

        aGenreTextAreaNumberOfSubGenres.setFont(vPolice);
        aGenreTextAreaNumberOfSubGenres.setLineWrap(true);
        aGenreTextAreaNumberOfSubGenres.setWrapStyleWord(true);

        aGenreTextAreaNumberOfSubGenres.setEditable(false);
        aGenrePanel.add(aGenreTextAreaNumberOfSubGenres);
        aGenreTextAreaNumberOfSubGenres.setFont(vPolice);

        aGenreTextAreaUrl = new JTextArea(" Url: "+aGenre.getURL());

        aGenreTextAreaUrl.setFont(vPolice);
        aGenreTextAreaUrl.setLineWrap(true);
        aGenreTextAreaUrl.setWrapStyleWord(true);

        aGenreTextAreaUrl.setEditable(false);
        aGenrePanel.add(aGenreTextAreaUrl);

        aImage = new ImageIcon(getClass().getResource("/genre_img.png"));
        aImageLabel = new JLabel(aImage);
        aImagePanel = new JPanel(new BorderLayout());
        int vPixels = 10;
        EmptyBorder vPadding = (EmptyBorder) BorderFactory.createEmptyBorder(vPixels, vPixels, vPixels, vPixels);
        aImagePanel.setBorder(vPadding);
        Image vImage = aImage.getImage();
        Image imageRedimensionnee = vImage.getScaledInstance(150,235,Image.SCALE_SMOOTH);
        aImage = new ImageIcon(imageRedimensionnee);
        aImageLabel = new JLabel(aImage);
        aImagePanel.add(aImageLabel, BorderLayout.CENTER);

        aButtonPanel = new JPanel();
        aButtonPanel.setLayout(new BoxLayout(aButtonPanel, BoxLayout.Y_AXIS));
        aModifyGenreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        aDeleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        aButtonPanel.add(aModifyGenreButton);
        aButtonPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        aButtonPanel.add(aDeleteButton);
        aImagePanel.add(aButtonPanel, BorderLayout.SOUTH);

        aGenreFrame.add(aGenrePanel, BorderLayout.CENTER);
        aGenreFrame.add(aImagePanel, BorderLayout.WEST);
        aGenreFrame.setVisible(true);
    }
    public JButton getModifyGenreButton(){
        return this.aModifyGenreButton;
    }
    public JButton getDeleteButton(){
        return this.aDeleteButton;
    }
    public JFrame getGenreFrame(){
        return this.aGenreFrame;
    }
}

