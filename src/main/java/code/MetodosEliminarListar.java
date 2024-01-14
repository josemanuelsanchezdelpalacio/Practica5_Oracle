package code;

import Conexion.PoolConexiones;

import java.sql.*;

public class MetodosEliminarListar {

    public static void eliminarContacto(String nombre) {
        String query = "DELETE FROM AGENDA WHERE NOMBRE = ?";
        try (Connection connection = PoolConexiones.conectar();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Array obtenerPrimerTelefono(String nombre) {
        Array primerTelefono = null;
        String query = "{? = call OBTENER_PRIMER_TELEFONO(?)}";
        try (Connection connection = PoolConexiones.conectar();
             CallableStatement cstmt = connection.prepareCall(query)) {
            cstmt.registerOutParameter(1, Types.ARRAY);
            cstmt.setString(2, nombre);
            cstmt.execute();
            primerTelefono = cstmt.getArray(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return primerTelefono;
    }


}
