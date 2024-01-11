package Conexion;

import oracle.jdbc.pool.OracleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConexiones {

    private static String SERVER = "jdbc:oracle:thin:@//localhost:1523";
    private static String BD = "objerel";
    private static String USER = "root";
    private static String PASSWORD = "admin";

    static DataSource poolConexiones() {
        String URL = SERVER + "/" + BD;

        //en este caso usamos un OracleDataSource, pero tambien seria posible usar un BasicDataSource por ejemplo de Apache
        OracleDataSource oracleDataSource = null;
        try {
            oracleDataSource = new OracleDataSource();
            oracleDataSource.setURL(URL);
            oracleDataSource.setUser(USER);
            oracleDataSource.setPassword(PASSWORD);
            oracleDataSource.setConnectionProperty("maxpoolsize", "10");
        } catch (SQLException e) {
            System.out.println("Error en la conexión");
        }
        //pasamos el dato source a quien pide la coenxion
        return oracleDataSource;
    }

    public static Connection conectar() throws SQLException {
        //cogemos una conexion del DataSource que nos devuelve el pool
        return poolConexiones().getConnection();
    }
}
