package org.library_manager.controller;

import org.library_manager.model.Book;
import org.library_manager.model.Genre;
import org.library_manager.model.SubGenre;
import org.library_manager.view.*;
import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.library_manager.utilitary.PDFExporter.exportTableToPDF;

public class Controller implements ActionListener {

    GUI aGui;

    public Controller(GUI pGUI) {
        aGui = pGUI;
        aGui.getNavBarButtonGenres().addActionListener(this);
        aGui.getNavBarButtonSubGenres().addActionListener(this);
        aGui.getNavBarButtonAllBooks().addActionListener(this);
        aGui.getControlBarDetailsButton().addActionListener(this);
        aGui.getSortingByComboBox().addActionListener(this);
        aGui.getSortingOrderComboBox().addActionListener(this);
        aGui.getControlBarAddBookButton().addActionListener(this);
        aGui.getControlBarDetailsGenreButton().addActionListener(this);
        aGui.getAddGenreButton().addActionListener(this);
        aGui.getExportBookButton().addActionListener(this);
        aGui.getExportGenreButton().addActionListener(this);
        aGui.getControlBarDetailsSubGenreButton().addActionListener(this);
        aGui.getAddSubGenreButton().addActionListener(this);
        aGui.getExportSubGenreButton().addActionListener(this);
        aGui.getInfoGenreButton().addActionListener(this);
        aGui.getInfoSubGenreButton().addActionListener(this);
        aGui.getApplyMultiFilterButton().addActionListener(this);
        aGui.getApplySubGenreFilterButton().addActionListener(this);
        aGui.getReloadButton().addActionListener(this);
        this.bookSearchFilter();
        this.genreSearchFilter();
        this.subGenreSearchFilter();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == aGui.getNavBarButtonGenres()) {
            aGui.getMultiPagesLayout().show(aGui.getMultiPagesPanel(),"Genres");
        }
        if (e.getSource() == aGui.getNavBarButtonSubGenres()) {
            aGui.getMultiPagesLayout().show(aGui.getMultiPagesPanel(),"SubGenres");
        }
        if (e.getSource() == aGui.getNavBarButtonAllBooks()) {
            aGui.getMultiPagesLayout().show(aGui.getMultiPagesPanel(),"All Books");
        }
        if (e.getSource() == aGui.getExportBookButton()) {
            JFileChooser vFileChooser = new JFileChooser();
            vFileChooser.setDialogTitle("Save to PDF");
            int vUserSelection = vFileChooser.showSaveDialog(aGui.getMainWindow());
            if (vUserSelection == JFileChooser.APPROVE_OPTION) {
                String vFilePath = vFileChooser.getSelectedFile().getAbsolutePath();
                if (!vFilePath.toLowerCase().endsWith(".pdf")) {
                    vFilePath += ".pdf";
                }
                JTable vTableToExport = aGui.getAllBooksTable();
                exportTableToPDF(vTableToExport, vFilePath);
            }
        }
        if (e.getSource() == aGui.getExportGenreButton()) {
            JFileChooser vFileChooser = new JFileChooser();
            vFileChooser.setDialogTitle("Save to PDF");
            int vUserSelection = vFileChooser.showSaveDialog(aGui.getMainWindow());
            if (vUserSelection == JFileChooser.APPROVE_OPTION) {
                String vFilePath = vFileChooser.getSelectedFile().getAbsolutePath();
                if (!vFilePath.toLowerCase().endsWith(".pdf")) {
                    vFilePath += ".pdf";
                }
                JTable vTableToExport = aGui.getAllGenresTable();
                exportTableToPDF(vTableToExport, vFilePath);
            }
        }
        if (e.getSource() == aGui.getExportSubGenreButton()){
            JFileChooser vFileChooser = new JFileChooser();
            vFileChooser.setDialogTitle("Save to PDF");
            int vUserSelection = vFileChooser.showSaveDialog(aGui.getMainWindow());
            if (vUserSelection == JFileChooser.APPROVE_OPTION) {
                String vFilePath = vFileChooser.getSelectedFile().getAbsolutePath();
                if (!vFilePath.toLowerCase().endsWith(".pdf")) {
                    vFilePath += ".pdf";
                }
                JTable vTableToExport = aGui.getAllSubGenresTable();
                exportTableToPDF(vTableToExport, vFilePath);
            }
        }
        if (e.getSource() == aGui.getControlBarAddBookButton()) {
            BookTableModele vModel = (BookTableModele)aGui.getAllBooksTable().getModel();
            Book vBook = new Book();
            BookModifyFrame vForms = new BookModifyFrame(vBook, aGui.getGenreService());
            vForms.showModifyFrame();
            vForms.getSaveButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent eventSave) {
                    vForms.setModifiedBook();
                    try {
                        aGui.getBookService().insertBookService(vForms.getModifyFrameBook());
                        vModel.setBookTableModele(aGui.getBookService().getAllBooksService());
                        vForms.getModifyFrame().dispose();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });

        }
        if (e.getSource() == aGui.getControlBarDetailsButton()) {
            BookTableModele vModel = (BookTableModele)aGui.getAllBooksTable().getModel();
            int vSelectedRow = aGui.getAllBooksTable().getSelectedRow();
            if(vSelectedRow!=-1) {
                // convert the visible row to the model row (needed when a search filter is active)
                int vModelRow = aGui.getAllBooksTable().convertRowIndexToModel(vSelectedRow);
                BookFrame vBookFrame = new BookFrame(vModel.getBookAt(vModelRow));
                vBookFrame.createBookDetailsPanel();
                vBookFrame.getModifyButton().addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent eventModifyFrame) {
                           BookModifyFrame vModifyFrame = new BookModifyFrame(vModel.getBookAt(vModelRow), aGui.getGenreService());
                           vModifyFrame.showModifyFrame();
                           vModifyFrame.getSaveButton().addActionListener(new ActionListener() {
                               @Override
                               public void actionPerformed(ActionEvent eventSave) {
                                   vModifyFrame.setModifiedBook();
                                   try {
                                       aGui.getBookService().updateBookService(vModifyFrame.getModifyFrameBook());
                                       vModel.setBookTableModele(aGui.getBookService().getAllBooksService());
                                       vModifyFrame.getModifyFrame().dispose();
                                       vBookFrame.getBookFrame().dispose();
                                   } catch (SQLException ex) {
                                       throw new RuntimeException(ex);
                                   } catch (ClassNotFoundException ex) {
                                       throw new RuntimeException(ex);
                                   }

                               }
                           });

                       }
                });
                vBookFrame.getDeleteButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent eventDelete) {
                        int vConfirm = JOptionPane.showConfirmDialog(aGui.getMainWindow(), "Delete this book ?", "Confirm delete", JOptionPane.YES_NO_OPTION);
                        if (vConfirm != JOptionPane.YES_OPTION) {
                            return;
                        }
                        try {
                            aGui.getBookService().deleteBookService(vModel.getBookAt(vModelRow));
                            vModel.setBookTableModele(aGui.getBookService().getAllBooksService());
                            vBookFrame.getBookFrame().dispose();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
            }
            else{
                JOptionPane.showMessageDialog(null,"Please select a row","",JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == aGui.getSortingByComboBox() || e.getSource() == aGui.getSortingOrderComboBox()) {

            if(e.getSource()==aGui.getSortingOrderComboBox()){
                if(aGui.getSortingByComboBox().getSelectedItem()==null||aGui.getSortingByComboBox().getSelectedItem().equals("-- Sort by --")) {
                    JOptionPane.showMessageDialog(null, "Please select a order of sorting","",JOptionPane.WARNING_MESSAGE);
                    aGui.getSortingOrderComboBox().setSelectedIndex(0);
                    return;
                }
            }
            if(aGui.getSortingByComboBox().getSelectedItem()!=null&&aGui.getSortingOrderComboBox().getSelectedItem()!=null) {
                BookTableModele vModel = (BookTableModele)aGui.getAllBooksTable().getModel();
                String vSortedBy = (String)aGui.getSortingByComboBox().getSelectedItem();
                String vSortOrder = (String)aGui.getSortingOrderComboBox().getSelectedItem();
                try {
                    vModel.setBookTableModele(aGui.getBookService().getAllBooksSortedService(vSortedBy, vSortOrder));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
        if (e.getSource() == aGui.getAddGenreButton()) {
            GenreTableModele vModel = (GenreTableModele)aGui.getAllGenresTable().getModel();
            Genre vGenre = new Genre();
            GenreModifyFrame vForms = new GenreModifyFrame(vGenre);
            vForms.showModifyFrame();
            vForms.getSaveButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent eventSave) {
                    vForms.setModifiedGenre();
                    try {
                        aGui.getGenreService().insertGenreService(vForms.getModifyFrameGenre());
                        vModel.setGenreTableModele(aGui.getGenreService().getAllGenresService());
                        vForms.getModifyFrame().dispose();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });

        }
        if (e.getSource() == aGui.getControlBarDetailsGenreButton()) {
            GenreTableModele vModel = (GenreTableModele) aGui.getAllGenresTable().getModel();
            int vSelectedRow = aGui.getAllGenresTable().getSelectedRow();
            if (vSelectedRow != -1) {
                // convert the visible row to the model row (needed when a search filter is active)
                int vModelRow = aGui.getAllGenresTable().convertRowIndexToModel(vSelectedRow);
                GenreFrame vGenreFrame = new GenreFrame(vModel.getGenreAt(vModelRow));
                vGenreFrame.createGenreDetailsPanel();
                vGenreFrame.getModifyGenreButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent eventModifyFrame) {
                        GenreModifyFrame vGenreModifyFrame = new GenreModifyFrame(vModel.getGenreAt(vModelRow));
                        vGenreModifyFrame.showModifyFrame();

                        vGenreModifyFrame.getSaveButton().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent eventSave) {
                                vGenreModifyFrame.setModifiedGenre();
                                try {
                                    aGui.getGenreService().updateGenreService(vGenreModifyFrame.getModifyFrameGenre());
                                    vModel.setGenreTableModele(aGui.getGenreService().getAllGenresService());
                                    vGenreModifyFrame.getModifyFrame().dispose();
                                    vGenreFrame.getGenreFrame().dispose();
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                } catch (ClassNotFoundException ex) {
                                    throw new RuntimeException(ex);
                                }

                            }
                        });

                    }
                });
                vGenreFrame.getDeleteButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent eventDelete) {
                        int vConfirm = JOptionPane.showConfirmDialog(aGui.getMainWindow(), "Delete this genre ?", "Confirm delete", JOptionPane.YES_NO_OPTION);
                        if (vConfirm != JOptionPane.YES_OPTION) {
                            return;
                        }
                        try {
                            aGui.getGenreService().deleteGenreService(vModel.getGenreAt(vModelRow));
                            vModel.setGenreTableModele(aGui.getGenreService().getAllGenresService());
                            vGenreFrame.getGenreFrame().dispose();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
            }
            else {
                JOptionPane.showMessageDialog(null,"Please select a row","",JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == aGui.getAddSubGenreButton()) {
            SubGenreTableModele vModel = (SubGenreTableModele)aGui.getAllSubGenresTable().getModel();
            SubGenre vSubGenre = new SubGenre();
            SubGenreModifyFrame vForms = new SubGenreModifyFrame(vSubGenre, aGui.getGenreService());
            vForms.showModifyFrame();
            vForms.getSaveButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent eventSave) {
                    vForms.setModifiedSubGenre();
                    try {
                        aGui.getSubGenreService().insertSubGenreService(vForms.getModifyFrameSubGenre());
                        vModel.setSubGenreTableModele(aGui.getSubGenreService().getAllSubGenresService());
                        vForms.getModifyFrame().dispose();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });

        }
        if (e.getSource() == aGui.getControlBarDetailsSubGenreButton()) {
            SubGenreTableModele vModel = (SubGenreTableModele) aGui.getAllSubGenresTable().getModel();
            int vSelectedRow = aGui.getAllSubGenresTable().getSelectedRow();
            if (vSelectedRow != -1) {
                // convert the visible row to the model row (needed when a search filter is active)
                int vModelRow = aGui.getAllSubGenresTable().convertRowIndexToModel(vSelectedRow);
                SubGenreFrame vSubGenreFrame = new SubGenreFrame(vModel.getSubGenreAt(vModelRow));
                vSubGenreFrame.createSubGenreDetailsPanel();
                vSubGenreFrame.getModifySubGenreButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent eventModifyFrame) {
                        SubGenreModifyFrame vSubGenreModifyFrame = new SubGenreModifyFrame(vModel.getSubGenreAt(vModelRow), aGui.getGenreService());
                        vSubGenreModifyFrame.showModifyFrame();

                        vSubGenreModifyFrame.getSaveButton().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent eventSave) {
                                vSubGenreModifyFrame.setModifiedSubGenre();
                                try {
                                    aGui.getSubGenreService().updateSubGenreService(vSubGenreModifyFrame.getModifyFrameSubGenre());
                                    vModel.setSubGenreTableModele(aGui.getSubGenreService().getAllSubGenresService());
                                    vSubGenreModifyFrame.getModifyFrame().dispose();
                                    vSubGenreFrame.getSubGenreFrame().dispose();
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                } catch (ClassNotFoundException ex) {
                                    throw new RuntimeException(ex);
                                }

                            }
                        });

                    }
                });
                vSubGenreFrame.getDeleteButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent eventDelete) {
                        int vConfirm = JOptionPane.showConfirmDialog(aGui.getMainWindow(), "Delete this sub genre ?", "Confirm delete", JOptionPane.YES_NO_OPTION);
                        if (vConfirm != JOptionPane.YES_OPTION) {
                            return;
                        }
                        try {
                            aGui.getSubGenreService().deleteSubGenreService(vModel.getSubGenreAt(vModelRow));
                            vModel.setSubGenreTableModele(aGui.getSubGenreService().getAllSubGenresService());
                            vSubGenreFrame.getSubGenreFrame().dispose();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
            }
            else {
                JOptionPane.showMessageDialog(null,"Please select a row","",JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == aGui.getInfoGenreButton()){
            try {
                JTextArea vTextArea = new JTextArea(aGui.getBookService().getGenreStatisticsReportService());
                JScrollPane vScrollPane = new JScrollPane(vTextArea);
                vScrollPane.setPreferredSize(new Dimension(400, 600));
                JOptionPane.showMessageDialog(null, vScrollPane);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
        if (e.getSource() == aGui.getInfoSubGenreButton()){
            try {
                JTextArea vTextArea = new JTextArea(aGui.getBookService().getSubGenreStatisticsReportService());
                JScrollPane vScrollPane = new JScrollPane(vTextArea);
                vScrollPane.setPreferredSize(new Dimension(400, 600));
                JOptionPane.showMessageDialog(null, vScrollPane);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
        if (e.getSource() == aGui.getApplyMultiFilterButton()){
                    try {
                        JPanel vPopupPanel = new JPanel();
                        vPopupPanel.setLayout(new BoxLayout(vPopupPanel, BoxLayout.Y_AXIS));

                        List<JCheckBox> vTempCheckBoxes = new ArrayList<>();
                        List<Genre> allGenres = aGui.getGenreService().getAllGenresService();

                        for (Genre g : allGenres) {
                            JCheckBox box = new JCheckBox(g.getTitle());
                            vPopupPanel.add(box);
                            vTempCheckBoxes.add(box);
                        }

                        JScrollPane scrollPane = new JScrollPane(vPopupPanel);
                        scrollPane.setPreferredSize(new Dimension(300, 400));
                        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

                        int result = JOptionPane.showConfirmDialog(
                                aGui.getMainWindow(),
                                scrollPane,
                                "Select Genre(s)",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE
                        );

                        if (result == JOptionPane.OK_OPTION) {
                            List<String> selectedGenres = new ArrayList<>();

                            for (JCheckBox box : vTempCheckBoxes) {
                                if (box.isSelected()) {
                                    selectedGenres.add(box.getText());
                                }
                            }
                            if (selectedGenres.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Please select at least one genre");
                            } else {
                                List<Book> filteredBooks = aGui.getBookService().getBooksByMultipleGenresService(selectedGenres);
                                BookTableModele model = (BookTableModele) aGui.getAllBooksTable().getModel();
                                model.setBookTableModele(filteredBooks);

                                // Reset scroll
                                if (aGui.getAllBooksTable().getRowCount() > 0) {
                                    aGui.getAllBooksTable().scrollRectToVisible(new Rectangle(0, 0, 1, 1));
                                }
                            }
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
        }
        if (e.getSource() == aGui.getApplySubGenreFilterButton()){

            try {
                JPanel vPopupPanel = new JPanel();
                vPopupPanel.setLayout(new BoxLayout(vPopupPanel, BoxLayout.Y_AXIS));

                List<JCheckBox> vTempCheckBoxes = new ArrayList<>();

                List<SubGenre> allSubGenres = aGui.getSubGenreService().getAllSubGenresService();

                for (SubGenre sub : allSubGenres) {
                    JCheckBox box = new JCheckBox(sub.getTitle());
                    vPopupPanel.add(box);
                    vTempCheckBoxes.add(box);
                }

                JScrollPane scrollPane = new JScrollPane(vPopupPanel);
                scrollPane.setPreferredSize(new Dimension(300, 400));
                scrollPane.getVerticalScrollBar().setUnitIncrement(16);

                int result = JOptionPane.showConfirmDialog(
                        aGui.getMainWindow(),
                        scrollPane,
                        "Select Sub Genre(s)",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );
                if (result == JOptionPane.OK_OPTION) {
                    List<String> selectedSubGenres = new ArrayList<>();

                    for (JCheckBox box : vTempCheckBoxes) {
                        if (box.isSelected()) {
                            selectedSubGenres.add(box.getText());
                        }
                    }

                    if (selectedSubGenres.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please select at least one sub genre.");

                    }
                    else {

                        List<Book> filteredBooks = aGui.getBookService().getBooksByMultipleSubGenresService(selectedSubGenres);
                        BookTableModele model = (BookTableModele) aGui.getAllBooksTable().getModel();
                        model.setBookTableModele(filteredBooks);
                    }

                        // Reset Scroll
                        if (aGui.getAllBooksTable().getRowCount() > 0) {
                            aGui.getAllBooksTable().scrollRectToVisible(new Rectangle(0, 0, 1, 1));
                        }
                    }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == aGui.getReloadButton()) {
            List<Book> allBooks = null;
            try {
                allBooks = aGui.getBookService().getAllBooksService();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            BookTableModele model = (BookTableModele) aGui.getAllBooksTable().getModel();
            model.setBookTableModele(allBooks);

            if (aGui.getAllBooksTable().getRowCount() > 0) {
                aGui.getAllBooksTable().scrollRectToVisible(new Rectangle(0, 0, 1, 1));
            }

            aGui.getBookSearchBar().setText("");

        }
    }
    private void bookSearchFilter() {
        BookTableModele vModel = (BookTableModele) aGui.getAllBooksTable().getModel();
        JTable vTable = aGui.getAllBooksTable();

        TableRowSorter<BookTableModele> vSorter = new TableRowSorter<>(vModel);

        vTable.setRowSorter(vSorter);

        aGui.getBookSearchBar().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filter();
            }

            private void filter() {

                String text = aGui.getBookSearchBar().getText();
                if (text.trim().length() == 0) {
                    vSorter.setRowFilter(null);
                } else {
                    try {
                        vSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0, 1));
                    } catch (java.util.regex.PatternSyntaxException e) {
                        return;
                    }
                }
            }
        });
    }
    private void genreSearchFilter() {
        GenreTableModele vModel = (GenreTableModele) aGui.getAllGenresTable().getModel();
        JTable vTable = aGui.getAllGenresTable();
        TableRowSorter<GenreTableModele> vSorter = new TableRowSorter<>(vModel);
        vTable.setRowSorter(vSorter);

        aGui.getGenreSearchBar().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filter();
            }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filter();
            }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filter();
            }

            private void filter() {
                String text = aGui.getGenreSearchBar().getText();

                if (text.trim().length() == 0) {

                    vSorter.setRowFilter(null);
                } else {
                    try {
                        vSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0, 1));
                    } catch (java.util.regex.PatternSyntaxException e) {
                        return;
                    }
                }
            }
        });
    }
    private void subGenreSearchFilter() {
        SubGenreTableModele vModel = (SubGenreTableModele) aGui.getAllSubGenresTable().getModel();
        JTable vTable = aGui.getAllSubGenresTable();
        TableRowSorter<SubGenreTableModele> vSorter = new TableRowSorter<>(vModel);
        vTable.setRowSorter(vSorter);
        aGui.getSubGenreSearchBar().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filter();
            }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filter();
            }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filter();
            }
            private void filter() {
                String text = aGui.getSubGenreSearchBar().getText();

                if (text.trim().length() == 0) {
                    vSorter.setRowFilter(null);
                } else {
                    try {
                        vSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0, 1));
                    } catch (java.util.regex.PatternSyntaxException e) {
                        return;
                    }
                }
            }
        });
    }
}

