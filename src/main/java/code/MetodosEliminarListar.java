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
    public static List<String> obtenerPrimerTelefono() {
        List<String> telefonos = new ArrayList<>();

        try (Connection con = ConexionOracle.conectar("objerel")) {
            String v_name = Leer.pedirCadena("Introduce nuevo nombre: "); // Consider removing this line if not needed

            try (CallableStatement cs = con.prepareCall("{ CALL ? := OBTENER_PRIMER_TELEFONO(?) }")) {
                cs.registerOutParameter(1, Types.ARRAY, "TELEFONO_ARRAY");
                cs.setString(2, v_name);

                cs.execute();

                // Retrieve the result from the OUT parameter
                Array phoneArray = cs.getArray(1);
                if (phoneArray != null) {
                    Object[] phoneData = (Object[]) phoneArray.getArray();
                    for (Object phone : phoneData) {
                        telefonos.add(phone.toString());
                    }

                    if (!telefonos.isEmpty()) {
                        System.out.println("El primer teléfono de " + v_name + " es " + telefonos.get(0));
                    } else {
                        System.out.println("No se encontró ningún teléfono para " + v_name);
                    }
                } else {
                    System.out.println("No se encontró ningún teléfono para " + v_name);
                }
            } catch (SQLException e) {
                if (e.getErrorCode() == 1403) {
                    System.out.println("No se encontró ningún teléfono para " + v_name);
                } else if (e.getErrorCode() == 100 && e.getMessage().contains("TOO_MANY_ROWS")) {
                    System.out.println("Se encontraron demasiados registros para " + v_name);
                } else {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return telefonos;
    }

}


