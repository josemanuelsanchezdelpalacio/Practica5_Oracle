package code;

import Conexion.PoolConexiones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Listar {

    public static void notasMediasAlumnos(){
        try(Connection miCon = PoolConexiones.conectar()){
            System.out.println("ok");
            //preparamos la consulta
            String consulta = "select A.ALUM.NOMBRE, A.MEDIA() from ALUMNOS2 A";
            Statement stmt = miCon.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            while(rs.next()){
                String alumRS = rs.getString("ALUM.NOMBRE");
                Float alumMedia = rs.getFloat("A.MEDIA()");
                System.out.println(alumRS + ": Nota media: " + alumMedia);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
