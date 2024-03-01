package code;

import Conexion.PoolConexiones;
import libs.Leer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Modificar {

    /**
     * Modificar el nombre y/o teléfono de un contacto existente
     **/
    public static void modificarContacto() {
        try (Connection con = PoolConexiones.conectar()) {
            String nombre = Leer.pedirCadena("Introduce el nombre del contacto que quieres modificar: ");

            //comprueba si el contacto existe antes de continuar
            if (existeContacto(con, nombre)) {
                //obtengo los numeros actuales del contacto
                List<String> telefonosActuales = obtenerTelefonos(con, nombre);

                //muestro los numeros de telefono
                System.out.println("Números de teléfono actuales:");
                for (String telefono : telefonosActuales) {
                    System.out.println("- " + telefono);
                }

                //pido el numero de telefonos que quiera modificar
                List<String> nuevosTelefonos = new ArrayList<>();
                int cantidadTelefonos = Leer.pedirEntero("¿Cuántos números de teléfono deseas modificar?");

                for (int i = 1; i <= cantidadTelefonos; i++) {
                    String nuevoTelefono = Leer.pedirCadena("Introduce el nuevo número de teléfono " + i + ": ");
                    nuevosTelefonos.add(nuevoTelefono);
                }

                //modifica los numeros de telefonos
                String sql = "UPDATE AGENDA SET TELEF = TELEFONO(?) WHERE NOMBRE = ?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    for (String telefono : nuevosTelefonos) {
                        ps.setString(1, telefono);
                        ps.setString(2, nombre);
                        ps.executeUpdate();
                    }
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

    private static List<String> obtenerTelefonos(Connection con, String nombre) throws SQLException {
        List<String> telefonos = new ArrayList<>();
        String sql = "SELECT TELEF FROM AGENDA WHERE NOMBRE = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                telefonos.add(rs.getString("TELEF"));
            }
        }
        return telefonos;
    }
}