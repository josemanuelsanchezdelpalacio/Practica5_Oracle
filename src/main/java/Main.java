import Conexion.PoolConexiones;
import libs.Leer;

import java.sql.SQLException;

import static code.MetodosEliminarListar.eliminarContacto;
import static code.MetodosEliminarListar.obtenerPrimerTelefono;
import static code.MetodosInsertarModificar.insertarContacto;
import static code.MetodosInsertarModificar.modificarTelefono;

public class Main {

    public static void main(String[] args) {
        boolean salir = false;
        int opcion;

        do {
            System.out.println("0. Salir");
            System.out.println("1. Conectar a la base de datos");
            System.out.println("2. Insertar contacto");
            System.out.println("3. Modificar teléfono de contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Mostrar primer contacto");

            opcion = Leer.pedirEntero("Introduce una opción: ");

            switch (opcion) {
                case 0:
                    salir = true;
                    break;
                case 1:
                    try {
                        PoolConexiones.conectar();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    String nombre = Leer.pedirCadena("Introduce el nombre del contacto: ");
                    String telefono = Leer.pedirCadena("Introduce el teléfono del contacto: ");
                    insertarContacto(nombre, telefono);
                    break;
                case 3:
                    nombre = Leer.pedirCadena("Introduce el nombre del contacto: ");
                    String nuevoTelefono = Leer.pedirCadena("Introduce el nuevo teléfono del contacto: ");
                    modificarTelefono(nombre, nuevoTelefono);
                    break;
                case 4:
                    nombre = Leer.pedirCadena("Introduce el nombre del contacto: ");
                    eliminarContacto(nombre);
                    break;
                case 5:
                    nombre = Leer.pedirCadena("Introduce el nombre del contacto: ");
                    String telefonoBuscado = obtenerPrimerTelefono(nombre);
                    if (telefonoBuscado != null) {
                        System.out.println("El primer teléfono de " + nombre + " es " + telefonoBuscado);
                    } else {
                        System.out.println("No se encontró ningún teléfono para " + nombre);
                    }
                default:
                    System.out.println("La opción seleccionada no existe");
            }

        } while (!salir);
    }

}
