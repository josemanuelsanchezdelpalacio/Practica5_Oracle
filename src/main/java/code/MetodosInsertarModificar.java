package code;

import Conexion.ConexionOracle;
import Conexion.ConexionSGBD;
import Conexion.PoolConexiones;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import libs.Leer;

import java.sql.*;

public class MetodosInsertarModificar {
    /**Insertar un nuevo contacto en la agenda**/
    public static void insertarContacto() {
        try(Connection con = ConexionOracle.conectar("objerel")){
            String nombre = Leer.pedirCadena("Introduce nuevo nombre: ");
            int telefono = Leer.pedirEntero("Introduce nuevo telefono: ");
            PreparedStatement ps = con.prepareStatement("INSERT INTO AGENDA VALUES(?, TELEFONO(?))");
            ps.setString(1, nombre);
            ps.setInt(2, telefono);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Modificar el teléfono de un contacto existente**/
    public static void modificarTelefono() {
        try (Connection con = ConexionOracle.conectar("objerel")) {
            String nombre = Leer.pedirCadena("Introduce el nombre a modificar: ");
            String nuevoNombre = Leer.pedirCadena("Introduce el nuevo nombre: ");
            int nuevoTelefono = Leer.pedirEntero("Introduce el nuevo teléfono: ");

            PreparedStatement ps = con.prepareStatement("UPDATE AGENDA SET NOMBRE = ?, TELEFONO = ? WHERE NOMBRE = ?");
            ps.setString(1, nuevoNombre);
            ps.setInt(2, nuevoTelefono);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Registro modificado correctamente.");
            } else {
                System.out.println("No se encontró el registro con el nombre proporcionado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
