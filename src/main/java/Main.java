
import code.*;
import libs.Leer;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        boolean salir = false;
        int opcion;

        do {
            System.out.println("0. Salir");
            System.out.println("1. Insertar contacto");
            System.out.println("2. Modificar teléfono de contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Mostrar primer teléfono de contacto");

            opcion = Leer.pedirEntero("Introduce una opción: ");

            switch (opcion) {
                case 0:
                    salir = true;
                    break;
                case 1:
                    Insertar.insertarContacto();
                    break;
                case 2:
                    Modificar.modificarContacto();
                    break;
                case 3:
                    Eliminar.eliminarContacto();
                    break;
                case 4:
                    ObtenerTelefono.obtenerPrimerTelefono();
                default:
                    System.out.println("La opción seleccionada no existe");
            }

        } while (!salir);
    }
}
