/*
    Bryan Dingman
    Test 2
*/

package bryan.dingmantest2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DatabaseConnector
{
    private static final String DB_NAME = "students.db";
    private static final int DB_VERSION = 1;
    private final String TABLE_NAME = "students";

    private DatabaseOpenHelper dbHelper;
    private SQLiteDatabase myDB;

    // Database connector for current context
    public DatabaseConnector(Context c)
    {
        dbHelper = new DatabaseOpenHelper(c, DB_NAME, null, DB_VERSION);
    }

    /**
     * Opens the database
     * @throws SQLException
     */
    private void open() throws SQLException
    {
        myDB = dbHelper.getWritableDatabase();
    }

    /**
     * close
     * Closes the database connection
     */
    public void close()
    {
        if (myDB != null)
            myDB.close();
    }

    /**
     * Update a record
     * @param _id
     * @param _params
     */
    public void updateRecord(String _id, ArrayList<String[]> _params)
    {
        ContentValues data = new ContentValues();

        // Take the params and add it to the contentvalue
        for (String[] arr : _params)
        {
            data.put(arr[0], arr[1]);
        }

        myDB.update(TABLE_NAME, data, "id = " + _id, null);
    }

    /**
     * Insert a record into our database
     * @param _name
     * @param _email
     * @param _major
     * @param _gpa
     */
    public void insertRecord(String _name, String _email, String _major, String _gpa)
    {
        ContentValues cv = new ContentValues();

        // First argument to put method is table column name, and the second is value to be inserted
        cv.put("name", _name);
        cv.put("email", _email);
        cv.put("major", _major);
        cv.put("gpa", _gpa);

        // Open database
        this.open();

        // Insert record
        myDB.insert(TABLE_NAME, null, cv);
    }

    /**
     * Get a particular record from the database where name = ?
     * @param _name
     * @return
     */
    public ArrayList<ArrayList<String>> getRecord(String _name)
    {
        ArrayList<ArrayList<String>> studentList = new ArrayList<>();

        String query = "SELECT id, name, email, major, gpa FROM " + TABLE_NAME + " WHERE name LIKE '%" + _name + "%';";

        // Open Database
        this.open();

        Cursor selectCursor = myDB.rawQuery(query, null);

        // Interate over cursor to fetch faculty records into array list
        for (selectCursor.moveToFirst(); !selectCursor.isAfterLast(); selectCursor.moveToNext())
        {
            ArrayList<String> studentRecord = new ArrayList<>();

            studentRecord.add(selectCursor.getString(0));
            studentRecord.add(selectCursor.getString(1));
            studentRecord.add(selectCursor.getString(2));
            studentRecord.add(selectCursor.getString(3));
            studentRecord.add(selectCursor.getString(4));

            studentList.add(studentRecord);
        }

        // Return
        return studentList;
    }

    /**
     * Delete a record based on it's id
     * @param _id
     * @return
     */
    public boolean deleteRecord(String _id)
    {
        return myDB.delete(TABLE_NAME, "id = " + _id, null) > 0;
    }

    /**
     * Get all of the records from our student table
     * @return
     */
    public ArrayList<String> getStudentRecords()
    {
        ArrayList<String> studentList = new ArrayList<>();

        // Create a query to select all student records
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        // Open Database
        this.open();

        Cursor selectCursor = myDB.rawQuery(selectQuery, null);

        // Interate over cursor to fetch faculty records into array list
        for (selectCursor.moveToFirst(); !selectCursor.isAfterLast(); selectCursor.moveToNext())
        {
            // Display as NAME  |  EMAIL  |  MAJOR  |  GPA
            String studentRecord = selectCursor.getString(1) + "  |  " + selectCursor.getString(2) + "  |  " + selectCursor.getString(3) + "  |  " + selectCursor.getString(4) + "\n";
            studentList.add(studentRecord);
        }

        // Return
        return studentList;
    }

    /**
     * Get all records from the student table as an array list
     * @return
     */
    public ArrayList<ArrayList<String>> getStudentRecordsAsList()
    {
        ArrayList<ArrayList<String>> studentList = new ArrayList<>();

        // Create a query to select all student records
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        // Open Database
        this.open();

        Cursor selectCursor = myDB.rawQuery(selectQuery, null);

        // Interate over cursor to fetch faculty records into array list
        for (selectCursor.moveToFirst(); !selectCursor.isAfterLast(); selectCursor.moveToNext())
        {
            ArrayList<String> studentRecord = new ArrayList<>();

            studentRecord.add(selectCursor.getString(0));
            studentRecord.add(selectCursor.getString(1));
            studentRecord.add(selectCursor.getString(2));
            studentRecord.add(selectCursor.getString(3));
            studentRecord.add(selectCursor.getString(4));

            studentList.add(studentRecord);
        }

        // Return
        return studentList;
    }

    /**
     * Database helper
     */
    private class DatabaseOpenHelper extends SQLiteOpenHelper
    {
        public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        {
            super (context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            // Create Student table
            String createTable = "CREATE TABLE " +
                    TABLE_NAME +
                    " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT UNIQUE NOT NULL, " +
                    "email TEXT UNIQUE NOT NULL, " +
                    "major TEXT NOT NULL, gpa real);";

            // Add the initial values
            String values = "INSERT INTO " +
                    TABLE_NAME +
                    " (name, email, major, gpa) VALUES " +
                    "('Adam Armstrong', 'armstrong@example.edu', 'Computer Science', 4.0)," +
                    "('Becky Bird', 'bird@example.edu', 'Physics', 3.8)," +
                    "('Chris Cannon', 'cannon@example.edu', 'Computer Science', 3.2)," +
                    "('Daniel Dodd', 'dodd@example.edu', 'History', 3.9)," +
                    "('Erica England', 'england@example.edu', 'Physics', 2.6)," +
                    "('Frank Foster', 'foster@example.edu', 'History', 3.4)," +
                    "('George Gates', 'gates@example.edu', 'Computer Science', 3.2)," +
                    "('Hannah Harris', 'harris@example.edu', 'Computer Science', 3.7)," +
                    "('Ivan Inkster', 'inkster@example.edu', 'Physics', 4.0)," +
                    "('Julia Jones', 'jones@example.edu', 'Computer Science', 2.8);";

            // Execute query
            db.execSQL(createTable);
            db.execSQL(values);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    }
}
