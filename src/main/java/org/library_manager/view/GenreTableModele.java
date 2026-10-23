package org.library_manager.view;

import org.library_manager.model.Genre;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class GenreTableModele extends AbstractTableModel {

    private List<Genre> aGenreList;
    private String[] aColumnNames = {"Title", "Number_of_Sub_Genre", "URLs"};

    public GenreTableModele(List<Genre> pGenreList) {
        this.aGenreList = pGenreList;
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
        return aGenreList.size();
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
        return 3;
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
                return aGenreList.get(rowIndex).getTitle();
            case 1:
                return aGenreList.get(rowIndex).getNumberOfSubGenres();
            case 2:
                return aGenreList.get(rowIndex).getURL();
            default: return null;
        }
    }
    public Genre getGenreAt(int rowIndex) {
        if(rowIndex >= 0 && rowIndex < aGenreList.size()){
            return aGenreList.get(rowIndex);
        }
        return null;
    }
    public void setGenreTableModele(List<Genre> pGenreList) {
        this.aGenreList = pGenreList;
        this.fireTableDataChanged();
    }
}
