package code;

import Conexion.PoolConexiones;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.sql.*;

public class MetodosInsertarModificar {
    /**Insertar un nuevo contacto en la agenda**/
    public static void insertarContacto(EntityManager em, String nombre, Array telefonos) {
        em.getTransaction().begin();
        Query q = em.createNativeQuery("INSERT INTO AGENDA (NOMBRE, TELEF) VALUES (?, ?)");
        q.setParameter(1, nombre);
        q.setParameter(2, telefonos);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    /**Modificar el teléfono de un contacto existente**/
    public static void modificarTelefono(EntityManager em, String nombre, Array telefonos) {
        em.getTransaction().begin();
        Query q = em.createNativeQuery("UPDATE AGENDA SET TELEF = ? WHERE NOMBRE = ?");
        q.setParameter(1, telefonos);
        q.setParameter(2, nombre);
        q.executeUpdate();
        em.getTransaction().commit();
    }

}
