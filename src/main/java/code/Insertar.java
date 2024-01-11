package code;

import Conexion.PoolConexiones;

import java.sql.*;

public class Insertar {
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
