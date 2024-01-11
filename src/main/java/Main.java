import Conexion.PoolConexiones;
import code.Insertar;
import code.Listar;
import libs.Leer;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        boolean salir = false;
        int opcion;
        Connection conexion = null;

        do {
            System.out.println("0. Salir");
            System.out.println("1. Conectar a la base de datos");
            System.out.println("2. Insertar alumno");
            System.out.println("3. Listar nota media alumnos");

            opcion = Leer.pedirEntero("Introduce una opción: ");

            switch (opcion) {
                case 0:
                    salir = true;
                    break;
                case 1:
                    //String nombreBD = Leer.pedirCadena("Introduce el nombre de la base de datos: ");
                    try {
                        PoolConexiones.conectar();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    Insertar.insertarAlumno();
                case 3:
                    Listar.notasMediasAlumnos();
                default:
                    System.out.println("La opción seleccionada no existe");
            }

        } while (!salir);
    }
}
