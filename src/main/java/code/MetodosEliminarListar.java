package code;

import Conexion.PoolConexiones;
import entities.AgendaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.sql.*;
import java.util.List;

public class MetodosEliminarListar {

    /**Eliminar un contacto de la agenda**/
    public static void eliminarContacto(EntityManager em, String nombre) {
        em.getTransaction().begin();
        TypedQuery<AgendaEntity> q = em.createQuery("DELETE FROM AgendaEntity WHERE nombre = :nombre", AgendaEntity.class);
        q.setParameter("nombre", nombre);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    /**Acceder al primer teléfono de un contacto haciendo uso de la función almacenada creada en el ejercicio anterior.**/
    public static List<String> obtenerPrimerTelefono(EntityManager em, String nombre) {
        Query q = em.createNativeQuery("{ ? = call OBTENER_PRIMER_TELEFONO(?) }");
        q.setParameter(1, nombre);
        return (List<String>) q.getResultList();
    }
}
