package code;

import Conexion.PoolConexiones;
import libs.Leer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Eliminar {

    /**Eliminar un contacto de la agenda**/
    public static void eliminarContacto() {
        try (Connection con = PoolConexiones.conectar()) {
            String nombre = Leer.pedirCadena("Introduce el nombre a eliminar: ");
            PreparedStatement ps = con.prepareStatement("DELETE FROM AGENDA WHERE NOMBRE = ?");
            ps.setString(1, nombre);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Registro eliminado correctamente.");
            } else {
                System.out.println("No se encontró el registro con el nombre proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al intentar eliminar el contacto: " + e.getMessage());
        }
    }
}