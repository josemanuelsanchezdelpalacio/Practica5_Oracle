package code;

import Conexion.ConexionOracle;
import libs.Leer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetodosEliminarListar {

    /**
     * Eliminar un contacto de la agenda
     **/
    public static void eliminarContacto() {
        try (Connection con = ConexionOracle.conectar("objerel")) {
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
            throw new RuntimeException(e);
        }
    }

    /**
     * Acceder al primer teléfono de un contacto haciendo uso de la función almacenada creada en el ejercicio anterior
     **/
    public static void accederPrimerTelefono() {
        try (Connection con = ConexionOracle.conectar("objerel")) {
            String nombre = Leer.pedirCadena("Introduce el nombre del contacto: ");

            CallableStatement cs = con.prepareCall("{? = call OBTENER_PRIMER_TELEFONO(?)}");
            cs.registerOutParameter(1, Types.ARRAY, "TELEFONO");
            cs.setString(2, nombre);
            cs.execute();

            Array telefonoArray = cs.getArray(1);
            int[] telefono = (int[]) telefonoArray.getArray();

            if (telefono.length > 0) {
                System.out.println("El primer teléfono de " + nombre + " es " + telefono[0]);
            } else {
                System.out.println("No se encontró ningún teléfono para " + nombre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

