package org.library_manager.view;

import org.library_manager.model.Book;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BookTableModele extends AbstractTableModel {

    private List<Book> aBookList;
    private String[] aColumnNames = {"Title", "Author", "Main_Genre", "Sub_Genre", "Type", "Price", "Rating", "No_of_People_rated", "URLs"};

    public BookTableModele(List<Book> pBookList) {
        this.aBookList = pBookList;
    }

    public String getColumnName(int pColumn) {
        return aColumnNames[pColumn];
    }
    /**
     * Returns the number of rows in the model. A
     * <code>JTable</code> uses this method to determine how many rows it
     * should display.  This method should be quick, as it
     * is called frequently during rendering.
     *
     * @return the number of rows in the model
     * @see #getColumnCount
     */
    @Override
    public int getRowCount() {
        return aBookList.size();
    }
    /**
     * Returns the number of columns in the model. A
     * <code>JTable</code> uses this method to determine how many columns it
     * should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     */
    @Override
    public int getColumnCount() {
        return 9;
    }
    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param rowIndex    the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     * @return the value Object at the specified cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return aBookList.get(rowIndex).getTitle();
            case 1:
                return aBookList.get(rowIndex).getAuthor();
            case 2:
                return aBookList.get(rowIndex).getMainGenre();
            case 3:
                return aBookList.get(rowIndex).getSubGenre();
            case 4:
                return aBookList.get(rowIndex).getType();
            case 5:
                return aBookList.get(rowIndex).getPrice();
            case 6:
                return aBookList.get(rowIndex).getRating();
            case 7:
                return aBookList.get(rowIndex).getNoPeopleRated();
            case 8:
                return aBookList.get(rowIndex).getURL();
            default: return null;
        }
    }
    public Book getBookAt(int rowIndex) {
        if(rowIndex >= 0 && rowIndex < aBookList.size()){
            return aBookList.get(rowIndex);
        }
        return null;
    }
    public void setBookTableModele(List<Book> pBookList) {
        this.aBookList = pBookList;
        this.fireTableDataChanged();
    }

}


