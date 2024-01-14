package code;

import Conexion.PoolConexiones;

import java.sql.*;

public class MetodosInsertarModificar {
    public static void insertarContacto(String nombre, Array telefonos) {
        String query = "INSERT INTO AGENDA (NOMBRE, TELEF) VALUES (?, ?)";
        try (Connection connection = PoolConexiones.conectar();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, nombre);
            pstmt.setArray(2, telefonos);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modificarTelefono(String nombre, Array nuevosTelefonos) {
        String query = "UPDATE AGENDA SET TELEF = ? WHERE NOMBRE = ?";
        try (Connection connection = PoolConexiones.conectar();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setArray(1, nuevosTelefonos);
            pstmt.setString(2, nombre);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
