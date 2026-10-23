package org.library_manager.view;

import org.library_manager.model.SubGenre;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SubGenreTableModele extends AbstractTableModel {

    private List<SubGenre> aSubGenreList;
    private String[] aColumnNames = {"Title", "Main_Genre", "Number_of_Books", "URLs"};

    public SubGenreTableModele(List<SubGenre> pSubGenreList) {
        this.aSubGenreList = pSubGenreList;
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
        return  aSubGenreList.size();
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
        return 4;
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
                return aSubGenreList.get(rowIndex).getTitle();
            case 1:
                return aSubGenreList.get(rowIndex).getMainGenre();
            case 2:
                return aSubGenreList.get(rowIndex).getNumberOfBooks();
            case 3:
                return aSubGenreList.get(rowIndex).getURL();
            default: return null;
        }
    }
    public SubGenre getSubGenreAt(int rowIndex) {
        if(rowIndex >= 0 && rowIndex < aSubGenreList.size()){
            return aSubGenreList.get(rowIndex);
        }
        return null;
    }
    public void setSubGenreTableModele(List<SubGenre> pSubGenreList) {
        this.aSubGenreList = pSubGenreList;
        this.fireTableDataChanged();
    }
}
