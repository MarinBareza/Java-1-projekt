/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.bareza.models;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bareza
 */
public class MovieTableModel extends AbstractTableModel{

    private static final String[] COLUMN_NAMES = {"Id", "OriginalTitle", "PublishedDate", "Duration"};
    
    private List<Movie> movies;

    public MovieTableModel(List<Movie> movies) {
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
    
    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0:
                return movies.get(rowIndex).getId();
            case 1:
                return movies.get(rowIndex).getOriginalTitle();
            case 2:
                return movies.get(rowIndex).getPublishedDate();
            case 3:
                return movies.get(rowIndex).getDuration();
            default:
                throw new RuntimeException("No such column!");
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0 || columnIndex == 3) {
            return Integer.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
    
}
