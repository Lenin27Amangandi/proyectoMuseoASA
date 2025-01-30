package DataAcces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SQLiteDataHelper {

    private static String DBPathConnection = "jdbc:sqlite:DataBase/prjDataBase.sqlite";

    private static Connection conn = null;
    protected static synchronized Connection openConnection() throws Exception {
        try {
            if (conn == null) {
                conn = DriverManager.getConnection(DBPathConnection);
            }
        } catch (SQLException e) {
            throw e;
        }
        return conn;
    }
    protected static void closeConnection() throws Exception {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }


    public static void testConnection() {
        try {
            Connection connection = openConnection();
            System.out.println("\n Conexión exitosa a la base de datos. \n");
            // closeConnection();
        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
