package projects.functions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PSQLSupportFunctions {
    private static PSQLSupportFunctions psqlConn;
    private Connection connect;
    private PreparedStatement st;

    public PSQLSupportFunctions(String IP, String database, String username, String password) throws SQLException {
        connectToDatabase(IP, username, password, database);
    }

    public static PSQLSupportFunctions instance(String IP, String database, String username, String password) throws SQLException {
        if (psqlConn == null) psqlConn = new PSQLSupportFunctions(IP, database, username, password);

        return psqlConn;
    }

    public static PSQLSupportFunctions instance(String dbName) throws SQLException {
        String IP = "", database = "", username = "postgres", password = "";
        switch (dbName.toUpperCase().trim()) {
            case "CREDIT":
                IP = "10.60.7.122";
                database = "credit";
                break;
            case "BANK_TRANSFER":
            case "BANK TRANSFER":
                IP = "10.60.3.3";
                database = "bank_transfer";
                break;
            case "PROMOTION":
                IP = "10.60.3.3";
                database = "promotion";
                break;
        }
            psqlConn = new PSQLSupportFunctions(IP, database, username, password);

        return psqlConn;
    }
    //Instance function
//    private static PSQLSupportFunctions psqlConnObj;
//    public static synchronized PSQLSupportFunctions instance() {
//        if (psqlConnObj == null) {
//            psqlConnObj = new PSQLSupportFunctions();
//        }
//        return psqlConnObj;
//    }

    //Functions
    private Connection connectToDatabase(String IP, String username, String password, String database) {
        String URL = "jdbc:postgresql://" + IP + "/" + database;
        try {
            Properties props = new Properties();
            props.setProperty("user", username);
            props.setProperty("password", password);
            connect = DriverManager.getConnection(URL, props);
        } catch (SQLException throwables) {
            System.out.println("CAN'T CONNECT TO PSQL: " + throwables.toString() + "\n" + URL);
        }
        return connect;
    }

    /**
     * @param colIndex Get value of column
     * @return only 1 datarow
     * @author VUHOANG
     */
    public Object queryGetTopValue(String querySQL, int colIndex) {
        try (Statement stmt = connect.createStatement();
             ResultSet result = stmt.executeQuery(querySQL)) {
            if (result.next()) {
                return result.getString(colIndex);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public int queryGetTopValueInt(String querySQL, int colIndex) {
        try (Statement stmt = connect.createStatement();
             ResultSet result = stmt.executeQuery(querySQL)) {
            if (result.next()) {
                return result.getInt(colIndex);
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    /**
     * @param colIndex Get all values of a specified column
     * @return a datatable
     * @author VUHOANG
     */
    public List<Object> queryGetColumnValues(String querySQL, int colIndex) {
        List<Object> dataCol = new ArrayList<>();
        try (Statement stmt = connect.createStatement();
             ResultSet result = stmt.executeQuery(querySQL)) {
            if (result.next()) {
                while (result.next()) {
                    dataCol.add(result.getString(colIndex));
                }
                return dataCol;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public int insertToDB(String query) {
        try {
            st = connect.prepareStatement(query);
            return st.executeUpdate();
        } catch (SQLException throwables) {
            return -1;
        }
    }

    public int updateToDB(String query) {
        return insertToDB(query);
    }

    /*================== SUPPORT FOR CHOTOT =====================
    PSQLSupportFunctions("10.60.7.35", "sticky_ads", "postgres", "")
    PSQLSupportFunctions("10.60.3.3", "profiler", "postgres", "")
     */

}
