package code;

import Conexion.PoolConexiones;
import libs.Leer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class ObtenerTelefono {

    /** Acceder al primer teléfono de un contacto **/
    public static void obtenerPrimerTelefono() {
        try (Connection con = PoolConexiones.conectar()) {
            //llamo a la funcion almacenada OBTENER_PRIMER_TELEFONO
            try (CallableStatement cs = con.prepareCall("{ ? = call OBTENER_PRIMER_TELEFONO(?) }")) {
                //configuro los parametros de entrada y salida y ejecuto la funcion
                cs.registerOutParameter(1, Types.ARRAY, "TELEFONO");
                String nombre = Leer.pedirCadena("Introduce el nombre del nuevo contacto: ");
                cs.setString(2, nombre);
                cs.execute();

                //obtengo el resultado
                Object result = cs.getArray(1).getArray();

                //muestro el resultado
                if (result != null) {
                    String[] telefonos = (String[]) result;
                    if (telefonos.length > 0) {
                        System.out.println("El primer telefono de " + nombre + " es " + telefonos[0]);
                    } else {
                        System.out.println("No se encontro ningun telefono de " + nombre);
                    }
                } else {
                    System.out.println("No se encontro ningún telefono de " + nombre);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al intentar acceder al primer telefono: " + e.getMessage());
        }
    }
}
