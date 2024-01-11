package code;

import Conexion.PoolConexiones;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Insertar {

    public static void insertarAlumno(){
        try(Connection miCon = PoolConexiones.conectar()){
            System.out.println("ok");
            //preparamos la consulta
            String consulta = "INSERT INTO ALUMNOS2 (ALUM) VALUES(PERSONA(2, 'Maria Perez', DIRECCION ('C/Mayor 12', 'Utebo',50180), '18/12/1999'))";
            Statement stmt = miCon.createStatement();
            int registros = stmt.executeUpdate(consulta);
            System.out.println("Registros cambiados: " + registros);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
