package code;

import Conexion.PoolConexiones;
import libs.Leer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Modificar {

    /** Modificar el nombre y/o teléfono de un contacto existente **/
    public static void modificarContacto() {
        try (Connection con = PoolConexiones.conectar()) {
            String nombre = Leer.pedirCadena("Introduce el nombre del contacto que quieres modificar: ");

            // Verificar si el contacto existe antes de continuar
            if (existeContacto(con, nombre)) {
                String nuevoTelefono = Leer.pedirCadena("Introduce el nuevo número de teléfono (o deja en blanco para mantener el actual): ");

                // Modificar el nombre y/o teléfono en la base de datos
                String sql = "UPDATE AGENDA SET TELEF = TELEFONO(?) WHERE NOMBRE = ?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, nuevoTelefono);
                    ps.setString(2, nombre);
                    ps.executeUpdate();
                }
                System.out.println("Contacto modificado");
            } else {
                System.out.println("El contacto no existe en la agenda");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static boolean existeContacto(Connection con, String nombre) throws SQLException {
        String sql = "SELECT COUNT(*) FROM AGENDA WHERE NOMBRE = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            return ps.executeQuery().next();
        }
    }
}