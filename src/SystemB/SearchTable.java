package SystemB;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class SearchTable extends DefaultTableModel{
	
    public SearchTable(Vector<Vector<Object>> data, Vector<String> columnNames) {
        super(data,columnNames);
    }       
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}