package org.library_manager.view;

import org.library_manager.model.Book;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BookFrame {

    private JFrame aBookFrame;
    private Book aBook;
    private JPanel aBookPanel;
    private JTextArea aBookTextAreaTitle;
    private JTextArea aBookTextAreaAuthor;
    private JTextArea aBookTextAreaGenre;
    private JTextArea aBookTextAreaSubGenre;
    private JTextArea aBookTextAreaType;
    private JTextArea aBookTextAreaPrice;
    private JTextArea aBookTextAreaRating;
    private JTextArea aBookTextAreaNoOfPeopleRated;
    private JTextArea aBookTextAreaUrls;
    private JPanel aImagePanel;
    private ImageIcon aImage;
    private JLabel aImageLabel;
    private JButton aModifyBookButton;
    private JButton aDeleteButton;
    private JPanel aButtonPanel;

    public BookFrame(Book pBook) {
        this.aBook = pBook;
    }

    public void createBookDetailsPanel(){

        aBookFrame = new JFrame();
        aBookFrame.setTitle("Book Details");
        ImageIcon vIcon = new ImageIcon(getClass().getResource("/book_img.jpg"));
        aBookFrame.setIconImage(vIcon.getImage());
        aBookFrame.setSize(700, 500);
        aBookFrame.setVisible(true);
        aBookFrame.setLayout(new BorderLayout());
        aBookFrame.setResizable(false);
        aBookFrame.setLocationRelativeTo(null);


        aDeleteButton = new JButton("Delete book");
        aModifyBookButton = new JButton("Modify book");

        aBookPanel = new JPanel();
        aBookPanel.setLayout(new BoxLayout(aBookPanel, BoxLayout.Y_AXIS));
        aBookPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        Font vPolice = new Font("Arial", Font.BOLD, 12);
        aBookTextAreaTitle = new JTextArea("Title: "+aBook.getTitle());

        aBookTextAreaTitle.setFont(vPolice);
        aBookTextAreaTitle.setLineWrap(true);
        aBookTextAreaTitle.setWrapStyleWord(true);

        aBookTextAreaTitle.setEditable(false);
        aBookPanel.add(aBookTextAreaTitle);

        aBookTextAreaAuthor = new JTextArea("Author: "+aBook.getAuthor());

        aBookTextAreaAuthor.setFont(vPolice);
        aBookTextAreaAuthor.setLineWrap(true);
        aBookTextAreaAuthor.setWrapStyleWord(true);

        aBookTextAreaAuthor.setEditable(false);
        aBookPanel.add(aBookTextAreaAuthor);
        aBookTextAreaAuthor.setFont(vPolice);

        aBookTextAreaGenre = new JTextArea("Main Genre: "+aBook.getMainGenre());

        aBookTextAreaGenre.setFont(vPolice);
        aBookTextAreaGenre.setLineWrap(true);
        aBookTextAreaGenre.setWrapStyleWord(true);

        aBookTextAreaGenre.setEditable(false);
        aBookPanel.add(aBookTextAreaGenre);

        aBookTextAreaSubGenre = new JTextArea("Sub Genre: "+aBook.getSubGenre());

        aBookTextAreaSubGenre.setFont(vPolice);
        aBookTextAreaSubGenre.setLineWrap(true);
        aBookTextAreaSubGenre.setWrapStyleWord(true);

        aBookTextAreaSubGenre.setEditable(false);
        aBookPanel.add(aBookTextAreaSubGenre);

        aBookTextAreaType = new JTextArea("Type: "+aBook.getType());

        aBookTextAreaType.setFont(vPolice);
        aBookTextAreaType.setLineWrap(true);
        aBookTextAreaType.setWrapStyleWord(true);

        aBookTextAreaType.setEditable(false);
        aBookPanel.add(aBookTextAreaType);

        aBookTextAreaPrice = new JTextArea("Price: "+aBook.getPrice());

        aBookTextAreaPrice.setFont(vPolice);
        aBookTextAreaPrice.setLineWrap(true);
        aBookTextAreaPrice.setWrapStyleWord(true);

        aBookTextAreaPrice.setEditable(false);
        aBookPanel.add(aBookTextAreaPrice);

        aBookTextAreaRating = new JTextArea("Rate: "+aBook.getRating());

        aBookTextAreaRating.setFont(vPolice);
        aBookTextAreaRating.setLineWrap(true);
        aBookTextAreaRating.setWrapStyleWord(true);

        aBookTextAreaRating.setEditable(false);
        aBookPanel.add(aBookTextAreaRating);

        aBookTextAreaNoOfPeopleRated = new JTextArea("No. of People Rated: "+aBook.getNoPeopleRated());

        aBookTextAreaNoOfPeopleRated.setFont(vPolice);
        aBookTextAreaNoOfPeopleRated.setLineWrap(true);
        aBookTextAreaNoOfPeopleRated.setWrapStyleWord(true);

        aBookTextAreaNoOfPeopleRated.setEditable(false);
        aBookPanel.add(aBookTextAreaNoOfPeopleRated);

        aBookTextAreaUrls = new JTextArea("URL: "+aBook.getURL());

        aBookTextAreaUrls.setFont(vPolice);
        aBookTextAreaUrls.setLineWrap(true);
        aBookTextAreaUrls.setWrapStyleWord(true);

        aBookTextAreaUrls.setEditable(false);
        aBookPanel.add(aBookTextAreaUrls);

        aImage = new ImageIcon(getClass().getResource("/book_img.jpg"));
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
        aModifyBookButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        aDeleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        aButtonPanel.add(aModifyBookButton);
        aButtonPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        aButtonPanel.add(aDeleteButton);
        aImagePanel.add(aButtonPanel, BorderLayout.SOUTH);

        aBookFrame.add(aBookPanel, BorderLayout.CENTER);
        aBookFrame.add(aImagePanel, BorderLayout.WEST);


    }
    public JButton getModifyButton(){
        return aModifyBookButton;
    }
    public JButton getDeleteButton() {
        return aDeleteButton;
    }
    public JFrame getBookFrame(){
        return aBookFrame;
    }

}
