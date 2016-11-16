package nl.cwi.monetdb.embedded.tables;

import nl.cwi.monetdb.embedded.mapping.AbstractRowSet;
import nl.cwi.monetdb.embedded.mapping.MonetDBRow;

/**
 * The iterator class for a MonetDB table. It's possible to inspect the current currentColumns in the row as well
 * their mappings.
 *
 * @author <a href="mailto:pedro.ferreira@monetdbsolutions.com">Pedro Ferreira</a>
 */
public class RowIterator extends AbstractRowSet {

    /**
     * The original table of this iterator.
     */
    protected final MonetDBTable table;

    /**
     * The current table row number on the fetched set.
     */
    protected int currentIterationNumber;

    /**
     * The first row in the table to iterate.
     */
    protected final int firstIndex;

    /**
     * The last row in the table to iterate.
     */
    protected final int lastIndex;

    protected RowIterator(MonetDBTable table, Object[][] rows, int firstIndex, int lastIndex) {
        super(table.getMappings(), rows);
        this.table = table;
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
        this.currentIterationNumber = -1;
    }

    /**
     * Gets the original table of this iterator.
     *
     * @return The original table of this iterator
     */
    public MonetDBTable getTable() { return table; }

    /**
     * Gets the first index used on this iteration.
     *
     * @return The first index used on this iteration
     */
    public int getFirstIndex() { return firstIndex; }

    /**
     * Gets the last index used on this iteration.
     *
     * @return The last index used on this iteration
     */
    public int getLastIndex() { return lastIndex; }

    /**
     * Gets the current iteration number.
     *
     * @return The current iteration number
     */
    public int getCurrentIterationNumber() { return currentIterationNumber; }

    /**
     * Gets the current row number of the table in the iteration.
     *
     * @return The current row number of the table in the iteration
     */
    public int getCurrentTableRowNumber() { return this.firstIndex + this.currentIterationNumber; }

    /**
     * Gets the current row currentColumns values as Java objects.
     *
     * @return The current row currentColumns values as Java objects
     */
    public MonetDBRow getCurrentRow() { return this.rows[this.currentIterationNumber]; }

    /**
     * Checks if there are more rows to iterate after the current one.
     *
     * @return There are more rows to iterate
     */
    public boolean hasMore() { return this.currentIterationNumber + this.firstIndex < this.lastIndex; }

    /**
     * Gets a column value as a Java class.
     *
     * @param <T> A Java class mapped to a MonetDB data type
     * @param index The index of the column
     * @param javaClass The Java class
     * @return The column value as a Java class
     */
    public <T> T getColumnByIndex(int index, Class<T> javaClass) {
        return javaClass.cast(this.getCurrentRow().getColumnByIndex(index, javaClass));
    }

    /**
     * Gets a column value as a Java class using the default mapping.
     *
     * @param <T> A Java class mapped to a MonetDB data type
     * @param index The index of the column
     * @return The column value as a Java class
     */
    public <T> T getColumnByIndex(int index) {
        Class<T> javaClass = this.mappings[index].getJavaClass();
        return javaClass.cast(this.getCurrentRow().getColumnByIndex(index));
    }

    /**
     * Gets a column value as a Java class.
     *
     * @param <T> A Java class mapped to a MonetDB data type
     * @param name The name of the column
     * @param javaClass The Java class
     * @return The column value as a Java class
     */
    public <T> T getColumnByName(String name, Class<T> javaClass) {
        String[] colNames = this.getTable().getColumnNames();
        int index = 0;
        for (String colName : colNames) {
            if (name.equals(colName)) {
                return this.getColumnByIndex(index, javaClass);
            }
            index++;
        }
        throw new ArrayIndexOutOfBoundsException("The column is not present in the result set!");
    }

    /**
     * Gets a column value as a Java class using the default mapping.
     *
     * @param <T> A Java class mapped to a MonetDB data type
     * @param name The name of the column
     * @return The column value as a Java class
     */
    public <T> T getColumnByName(String name) {
        String[] colNames = this.getTable().getColumnNames();
        int index = 0;
        for (String colName : colNames) {
            if (name.equals(colName)) {
                return this.getColumnByIndex(index);
            }
            index++;
        }
        throw new ArrayIndexOutOfBoundsException("The column is not present in the result set!");
    }

    /**
     * Get the next row in the table if there are more.
     *
     * @return A boolean indicating if there are more rows to fetch
     */
    protected boolean tryContinueIteration() {
        if(this.hasMore()) {
            this.currentIterationNumber++;
            return true;
        }
        return false;
    }
}
