package code;

import Conexion.PoolConexiones;

import java.sql.*;

public class MetodosInsertarModificar {
    public static void insertarContacto(String nombre, String telefono) {
        try (Connection miCon = PoolConexiones.conectar()) {
            String insertQuery = "INSERT INTO AGENDA VALUES (?, ?)";
            try (PreparedStatement ps = miCon.prepareStatement(insertQuery)) {
                ps.setString(1, nombre);
                ps.setString(2, telefono);
                ps.executeUpdate();
                System.out.println("Nuevo contacto insertado.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modificarTelefono(String nombre, String nuevoTelefono) {
        try (Connection miCon = PoolConexiones.conectar()) {
            String updateQuery = "UPDATE AGENDA SET TELEF = ? WHERE NOMBRE = ?";
            try (PreparedStatement ps = miCon.prepareStatement(updateQuery)) {
                ps.setString(1, nuevoTelefono);
                ps.setString(2, nombre);
                ps.executeUpdate();
                System.out.println("Teléfono de " + nombre + " actualizado.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
