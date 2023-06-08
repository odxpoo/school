package odx.lotto.database;

import java.sql.*;

public class Database {
    private Statement statement;
    private Connection connection;
    public Database() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/odx/lotto/db/lotto.db");
        statement = connection.createStatement();
    }
    public void executeUpdate(String query) {
        try {
            statement.executeUpdate(query);
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
    public ResultSet executeQuery(String query) {
        try{
            return statement.executeQuery(query);
        }
        catch(SQLException e) {
            System.out.println(e);
            return null;
        }

    }
    public void closeConnection() {
        try{
            connection.close();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
    public ResultSet getTables() {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            return metaData.getTables(null, null, null, new String[]{"TABLE"});
        }
        catch(SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
