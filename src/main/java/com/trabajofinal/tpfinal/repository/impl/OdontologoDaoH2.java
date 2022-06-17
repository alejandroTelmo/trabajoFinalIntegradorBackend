package com.trabajofinal.tpfinal.repository.impl;

import com.trabajofinal.tpfinal.model.Odontologo;
import com.trabajofinal.tpfinal.repository.IDao;


import com.trabajofinal.tpfinal.util.H2Gestor;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private final static String DB_JDBC_DRIVER ="org.h2.Driver";

    private final static String DB_URL = "jdbc:h2:~/test";



    private final static String DB_USER ="sa";

    private final static String DB_PASSWORD = "";

    private H2Gestor utilDb;
private static Logger log=Logger.getLogger(OdontologoDaoH2.class);
    public OdontologoDaoH2() {
        super();
    }

    public void crearTabla() throws SQLException {
        log.debug("Se crea la tabla de odontologos.");

        Connection conn = utilDb.getConnection();

        String sql_create_table ="DROP TABLE IF EXISTS odontologos; CREATE TABLE odontologos"
                + "("
                + "ID BIGINT PRIMARY KEY,"
                + "NOMBRE VARCHAR(100) NOT NULL,"
                + "APELLIDO VARCHAR(100) NOT NULL,"
                + "NUMERO_MATRICULA INT NOT NULL"

                + ")";

        utilDb.executeSQL(conn, sql_create_table);
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        log.info("comienza metodo guardar");
        Connection connection = null;

        PreparedStatement preparedStatement = null;



        try {

            //1 Levantar el driver y Conectarnos

            Class.forName(DB_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);



            //2 Crear una sentencia

            preparedStatement = connection.prepareStatement("INSERT INTO odontologos VALUES(?,?,?,?)");

            preparedStatement.setLong(1,odontologo.getId());

            preparedStatement.setString(2, odontologo.getNombre());

            preparedStatement.setString(3, odontologo.getApellido());

            preparedStatement.setInt(4, odontologo.getNumeroMatricula());



            //3 Ejecutar una sentencia SQL

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            log.error("Hubo un error al intentar guardar el nuevo odontologo.");
            throwables.printStackTrace();

        }
        log.info("Se guardo correctamente el nuevo odontologo.");
        return odontologo;
    }
    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        log.info("comienza metodo actualizar");
        Connection connection = null;

        PreparedStatement preparedStatement = null;



        try {

            //1 Levantar el driver y Conectarnos

            Class.forName(DB_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);



            //2 Crear una sentencia

            preparedStatement = connection.prepareStatement("UPDATE odontologos SET nombre=? ,apellido=?,numero_matricula=? where id=?");



            preparedStatement.setString(1, odontologo.getNombre());

            preparedStatement.setString(2, odontologo.getApellido());

            preparedStatement.setInt(3, odontologo.getNumeroMatricula());

            preparedStatement.setLong(4,odontologo.getId());


            //3 Ejecutar una sentencia SQL

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            log.error("Hubo un error al intentar actualizar el  odontologo.");
            throwables.printStackTrace();

        }
        log.info("Se actualizo correctamente el nuevo odontologo.");
        return odontologo;
    }
    @Override
    public void eliminar(Long id) {
        Connection connection = null;

        PreparedStatement preparedStatement = null;

        try {

            //1 Levantar el driver y Conectarnos

            Class.forName(DB_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);



            //2 Crear una sentencia

            preparedStatement = connection.prepareStatement("DELETE FROM odontologos where id = ?");

            preparedStatement.setLong(1,id);



            //3 Ejecutar una sentencia SQL

            preparedStatement.executeUpdate();
            log.info("Se elimino correctamente.");
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            log.error("Hubo un error al intentar eliminar..");
            throwables.printStackTrace();

        }




    }

    @Override
    public Odontologo buscar(Long id) {
        Connection connection = null;

        PreparedStatement preparedStatement = null;

        Odontologo odontologo = null;

        try {

            //1 Levantar el driver y Conectarnos

            Class.forName(DB_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);



            //2 Crear una sentencia

            preparedStatement = connection.prepareStatement("SELECT id,nombre,apellido,numero_matricula FROM odontologos where id = ?");

            preparedStatement.setLong(1,id);



            //3 Ejecutar una sentencia SQL

            ResultSet result = preparedStatement.executeQuery();



            //4 Obtener resultados

            while (result.next()) {

                Long idOdontologo = result.getLong("id");

                String nombre = result.getString("nombre");

                String apellido = result.getString("apellido");

               Integer numeroMatricula = result.getInt("numero_matricula");
                odontologo = new Odontologo(nombre, apellido,numeroMatricula,idOdontologo);

            }


            log.info("Se encontro el odontologo buscado por id");
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {

            throwables.printStackTrace();
            log.error("Hubo un error al buscar un odontologo por  id");
        }



        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection = null;

        PreparedStatement preparedStatement = null;

        List<Odontologo> odontologos = new ArrayList<Odontologo>();

        try {

            //1 Levantar el driver y Conectarnos

            Class.forName(DB_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);



            //2 Crear una sentencia

            preparedStatement = connection.prepareStatement("SELECT *  FROM odontologos");



            //3 Ejecutar una sentencia SQL

            ResultSet result = preparedStatement.executeQuery();



            //4 Obtener resultados

            while (result.next()) {
                Long idOdontologo=result.getLong("id");
                Integer numeroMatricula=result.getInt("numero_matricula");

                String nombre = result.getString("nombre");

                String apellido = result.getString("apellido");

              odontologos.add(new Odontologo( nombre, apellido,numeroMatricula,idOdontologo));



            }


            log.info("Se implemento el metodo buscarTodos los odontologos correctamente");
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {

            throwables.printStackTrace();
            log.error("Hubo un error al intentar listar todos los odontologos");
        }



        return odontologos;
    }
}
