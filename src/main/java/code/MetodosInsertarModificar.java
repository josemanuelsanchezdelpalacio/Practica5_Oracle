package code;

import Conexion.ConexionOracle;
import Conexion.ConexionSGBD;
import Conexion.PoolConexiones;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import libs.Leer;

import java.sql.*;

public class MetodosInsertarModificar {
    /**Insertar un nuevo contacto en la agenda**/
    public static void insertarContacto() {
        try(Connection con = ConexionOracle.conectar("objerel")){
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

    /**Modificar el teléfono de un contacto existente**/
    public static void modificarTelefono() {
        try(Connection con = ConexionOracle.conectar("objerel")){
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

}
