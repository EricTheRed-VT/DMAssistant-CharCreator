import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Eric the Red on 4/20/2016.
 */

//Sets-up database access and handles interaction
public class SQLController {

    //Connects to our database
    private static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(               //FIXME: should pull execution path at runtime
                    "jdbc:sqlite:C:/Users/Eric the Red/IdeaProjects/DM Assistant/CharacterCreator/CHARCREATORDB.db");
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return connection;
    }

    //Gets statement object for given connection
    private static Statement getStatement(Connection connection){
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return stmt;
    }

    //overloaded for simplistic use
    private static ResultSet getResultSet(String sql) {
        return getResultSet(getStatement(getConnection()), sql);
    }

    //generic method returns results from query using a given statement object
    private static ResultSet getResultSet(Statement stmt, String sql) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return rs;
    }



    //Pulls a specified record from our database. recName is the entry to select for in the specified column
    //If multiple records are returned from the SQL, this method only returns the first.
    //Returns null and error message if unable to pull a record
    public static Object[] getRecord(String tableName, String colName, String recName) {

        Object[] record = null;

        try(ResultSet result =
                    getResultSet("SELECT * FROM " + tableName + " WHERE " + colName + " = '" + recName + "';")
        ){
            ResultSetMetaData meta = result.getMetaData();
            int numColumns = meta.getColumnCount();
            record = new Object[numColumns];

            result.next();
            for (int c = 1; c <= numColumns; c++){
                record[c-1] = result.getObject(c);
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        if (record == null){System.out.println("Record not found.");}
        return record;
    }


    //Runs any String input as SQL in our database and returns records as an array-list of Object arrays
    //Returns null and error message if unable to return any records
    public static ArrayList<Object[]> runSQL(String sql){
        ArrayList<Object[]> output = null;

        try(ResultSet result = getResultSet(sql)) {

            ResultSetMetaData meta = result.getMetaData();
            int numColumns = meta.getColumnCount();

            output = new ArrayList<Object[]>();


            //start list with arrays of column names and types
            Object[] colNames = new Object[numColumns];
            Object[] colTypes = new Object[numColumns];

            for (int c = 1; c <= numColumns; c++) {
                colNames[c - 1] = meta.getColumnName(c);
                colTypes[c - 1] = meta.getColumnTypeName(c);
            }
            output.add(colNames);
            output.add(colTypes);


            //read each record and add to list
            while (result.next()){
                Object[] record = new Object[numColumns];
                for (int c = 1; c <= numColumns; c++){
                    record[c-1] = result.getObject(c);
                }
                output.add(record);
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        if (output == null){System.out.println("No records found.");}
        return output;
    }


}
