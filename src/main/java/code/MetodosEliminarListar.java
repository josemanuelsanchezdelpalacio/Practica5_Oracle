package code;

import Conexion.PoolConexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetodosEliminarListar {

    public static void eliminarContacto(String nombre) {
        try (Connection miCon = PoolConexiones.conectar()) {
            String deleteQuery = "DELETE FROM AGENDA WHERE NOMBRE = ?";
            try (PreparedStatement ps = miCon.prepareStatement(deleteQuery)) {
                ps.setString(1, nombre);
                ps.executeUpdate();
                System.out.println("Contacto " + nombre + " eliminado.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String obtenerPrimerTelefono(String nombre) {
        String telefono = null;
        String selectQuery = "SELECT TELEF FROM AGENDA WHERE NOMBRE = ? AND ROWNUM = 1";
        try (Connection miCon = PoolConexiones.conectar();
             PreparedStatement ps = miCon.prepareStatement(selectQuery)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    telefono = rs.getString("TELEF");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return telefono;
    }

}
