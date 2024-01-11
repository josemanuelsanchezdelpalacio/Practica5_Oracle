package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionOracle {

    private static final String URL = "jdbc:oracle:thin:@//localhost:1523";
    private static final String USUARIO = "root";
    private static final String CLAVE = "admin";

    public static Connection conectar(String nombreBD) {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL + "/" + nombreBD, USUARIO, CLAVE);
            System.out.println("Conexión OK.");
        } catch (SQLException e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }
        return conexion;
    }
}



