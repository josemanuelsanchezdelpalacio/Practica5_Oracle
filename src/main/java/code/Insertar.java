package code;

import Conexion.PoolConexiones;
import libs.Leer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Insertar {

    static List<String> listaTelefonos = new ArrayList<>();

    /**Insertar un nuevo contacto en la agenda**/
    public static void insertarContacto() {
        try (Connection con = PoolConexiones.conectar()) {
            String nombre = Leer.pedirCadena("Introduce el nombre del nuevo contacto: ");

            boolean agregarTelefono = true;
            while (agregarTelefono) {
                String telefono = Leer.pedirCadena("Introduce un numero de telefono (fin para salir): ");
                if (telefono.equalsIgnoreCase("fin")) {
                    agregarTelefono = false;
                } else {
                    listaTelefonos.add(telefono);
                }
            }

            //creo la cadena de telefonos para introducirlos
            String telefonosString = String.join("', '", listaTelefonos);

            String sql = "INSERT INTO AGENDA (NOMBRE, TELEF) VALUES (?, TELEFONO('" + telefonosString + "'))";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, nombre);
                int filas = ps.executeUpdate();
                if (filas > 0) {
                    System.out.println("Nuevo contacto agregado");
                } else {
                    System.out.println("Error al agregar el contacto");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
