import Conexion.PoolConexiones;
import code.MetodosEliminarListar;
import code.MetodosInsertarModificar;
import libs.Leer;

import java.sql.Array;
import java.sql.SQLException;

import static code.MetodosEliminarListar.eliminarContacto;
import static code.MetodosEliminarListar.obtenerPrimerTelefono;
import static code.MetodosInsertarModificar.insertarContacto;
import static code.MetodosInsertarModificar.modificarTelefono;

public class Main {
    public static void main(String[] args) throws SQLException {
        boolean salir = false;
        int opcion;

        do {
            System.out.println("0. Salir");
            System.out.println("1. Conectar a la base de datos");
            System.out.println("2. Insertar contacto");
            System.out.println("3. Modificar teléfono de contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Mostrar primer teléfono de contacto");

            opcion = Leer.pedirEntero("Introduce una opción: ");

            switch (opcion) {
                case 0:
                    salir = true;
                    break;
                case 1:
                    try {
                        PoolConexiones.conectar();
                        System.out.println("Conexión establecida correctamente.");
                    } catch (SQLException e) {
                        System.out.println("Error al conectar a la base de datos.");
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    String nombre = Leer.pedirCadena("Introduce el nombre del contacto: ");
                    String telefono = Leer.pedirCadena("Introduce el teléfono del contacto: ");
                    Array telefonosArray = PoolConexiones.conectar().createArrayOf("VARCHAR", new String[]{telefono});
                    MetodosInsertarModificar.insertarContacto(nombre, telefonosArray);
                    System.out.println("Contacto insertado correctamente.");
                    break;
                case 3:
                    nombre = Leer.pedirCadena("Introduce el nombre del contacto: ");
                    String nuevoTelefono = Leer.pedirCadena("Introduce el nuevo teléfono del contacto: ");
                    Array nuevosTelefonosArray = PoolConexiones.conectar().createArrayOf("VARCHAR", new String[]{nuevoTelefono});
                    MetodosInsertarModificar.modificarTelefono(nombre, nuevosTelefonosArray);
                    System.out.println("Teléfono modificado correctamente.");
                    break;
                case 4:
                    nombre = Leer.pedirCadena("Introduce el nombre del contacto: ");
                    MetodosEliminarListar.eliminarContacto(nombre);
                    System.out.println("Contacto eliminado correctamente.");
                    break;
                case 5:
                    nombre = Leer.pedirCadena("Introduce el nombre del contacto: ");
                    Array primerTelefono = MetodosEliminarListar.obtenerPrimerTelefono(nombre);
                    if (primerTelefono != null) {
                        System.out.println("El primer teléfono de " + nombre + " es " + primerTelefono.toString());
                    } else {
                        System.out.println("No se encontró ningún teléfono para " + nombre);
                    }
                    break;
                default:
                    System.out.println("La opción seleccionada no existe");
            }

        } while (!salir);
    }

}
