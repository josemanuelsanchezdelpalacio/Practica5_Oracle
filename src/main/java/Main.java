import Conexion.PoolConexiones;
import code.MetodosEliminarListar;
import code.MetodosInsertarModificar;
import database.EmfSingleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import libs.Leer;

import java.sql.Array;
import java.sql.SQLException;

import static code.MetodosEliminarListar.eliminarContacto;
import static code.MetodosEliminarListar.obtenerPrimerTelefono;
import static code.MetodosInsertarModificar.insertarContacto;
import static code.MetodosInsertarModificar.modificarTelefono;

public class Main {

    static EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
    public static EntityManager em = emf.createEntityManager();

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
                    String nombre = Leer.pedirCadena("Introduce el nombre del contacto: ");
                    String telefono = Leer.pedirCadena("Introduce el teléfono del contacto: ");
                    Array telefonosArray = PoolConexiones.conectar().createArrayOf("VARCHAR", new String[]{telefono});
                    MetodosInsertarModificar.insertarContacto(em, nombre, telefonosArray);
                    System.out.println("Contacto insertado correctamente.");
                    break;
                case 2:
                    nombre = Leer.pedirCadena("Introduce el nombre del contacto: ");
                    String nuevoTelefono = Leer.pedirCadena("Introduce el nuevo teléfono del contacto: ");
                    Array nuevosTelefonosArray = PoolConexiones.conectar().createArrayOf("VARCHAR", new String[]{nuevoTelefono});
                    MetodosInsertarModificar.modificarTelefono(em, nombre, nuevosTelefonosArray);
                    System.out.println("Teléfono modificado correctamente.");
                    break;
                case 3:
                    nombre = Leer.pedirCadena("Introduce el nombre del contacto: ");
                    MetodosEliminarListar.eliminarContacto(em, nombre);
                    System.out.println("Contacto eliminado correctamente.");
                    break;
                case 4:
                    nombre = Leer.pedirCadena("Introduce el nombre del contacto: ");
                    Array primerTelefono = (Array) MetodosEliminarListar.obtenerPrimerTelefono(em, nombre);
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
        desconectar();
    }

    private static void desconectar() {
        em.close();
        emf.close();
    }

}
