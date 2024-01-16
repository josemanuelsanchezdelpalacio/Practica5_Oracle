package code;

import Conexion.ConexionOracle;
import libs.Leer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MetodosEliminarListar {

    /**
     * Eliminar un contacto de la agenda
     **/
    public static void eliminarContacto() {
        try (Connection con = ConexionOracle.conectar("objerel")) {
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

    /**
     * Acceder al primer teléfono de un contacto haciendo uso de la función almacenada creada en el ejercicio anterior
     **/
    public static List<String> obtenerPrimerTelefono() {
        try (Connection con = ConexionOracle.conectar("objerel")) {
            String nombre = Leer.pedirCadena("Introduce nuevo nombre: ");
            int telefono = Leer.pedirEntero("Introduce nuevo telefono: ");
            PreparedStatement ps = con.prepareStatement("INSERT INTO AGENDA VALUES(?, TELEFONO(?))");
            ps.setString(1, nombre);
            ps.setInt(2, telefono);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}


