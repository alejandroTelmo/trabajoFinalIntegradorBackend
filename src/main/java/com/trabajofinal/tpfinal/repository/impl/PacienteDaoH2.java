package com.trabajofinal.tpfinal.repository.impl;

import com.trabajofinal.tpfinal.model.Paciente;
import com.trabajofinal.tpfinal.repository.IDao;
import com.trabajofinal.tpfinal.util.H2Gestor;


import org.apache.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDaoH2 implements IDao<Paciente> {

    private final static String DB_JDBC_DRIVER ="org.h2.Driver";

    private final static String DB_URL = "jdbc:h2:~/test";

    private final static String DB_USER ="sa";

    private final static String DB_PASSWORD = "";

    private H2Gestor utilDb=new H2Gestor();
    private static Logger log=Logger.getLogger(PacienteDaoH2.class);
    public PacienteDaoH2() {
        super();
    }

    public void crearTabla() throws SQLException {
        log.debug("Se crea la tabla de pacientes.");

        Connection conn = utilDb.getConnection();

        String sql_create_table ="DROP TABLE IF EXISTS pacientes ; CREATE TABLE pacientes "
                + "("
                + "ID BIGINT PRIMARY KEY,"
                + "NOMBRE VARCHAR(100) NOT NULL,"
                + "APELLIDO VARCHAR(100) NOT NULL,"
                + "DOMICILIO VARCHAR(100) NOT NULL,"
                + "FECHA_DE_ALTA VARCHAR(20) NOT NULL"

                + ")";

        utilDb.executeSQL(conn, sql_create_table);
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        log.info("comienza metodo guardar");
        Connection connection = null;

        PreparedStatement preparedStatement = null;



        try {

            //1 Levantar el driver y Conectarnos

            Class.forName(DB_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);



            //2 Crear una sentencia

            preparedStatement = connection.prepareStatement("INSERT INTO pacientes VALUES(?,?,?,?,?)");

            preparedStatement.setLong(1,paciente.getId());

            preparedStatement.setString(2, paciente.getNombre());

            preparedStatement.setString(3, paciente.getApellido());

            preparedStatement.setString(4, paciente.getDomicilio());

            preparedStatement.setString(5,paciente.getFechaDeAlta());



            //3 Ejecutar una sentencia SQL

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            log.error("Hubo un error al intentar guardar el nuevo paciente.");
            throwables.printStackTrace();

        }
        log.info("Se guardo correctamente el nuevo paciente.");
        return paciente;
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

            preparedStatement = connection.prepareStatement("DELETE FROM pacientes where id = ?");

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
    public Paciente buscar(Long id) {
        Connection connection = null;

        PreparedStatement preparedStatement = null;

        Paciente paciente = null;

        try {

            //1 Levantar el driver y Conectarnos

            Class.forName(DB_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);



            //2 Crear una sentencia

            preparedStatement = connection.prepareStatement("SELECT id,nombre,apellido,domicilio,fecha_de_alta FROM pacientes where id = ?");

            preparedStatement.setLong(1,id);



            //3 Ejecutar una sentencia SQL

            ResultSet result = preparedStatement.executeQuery();



            //4 Obtener resultados

            while (result.next()) {

                Long idPaciente = result.getLong("id");

                String nombre = result.getString("nombre");

                String apellido = result.getString("apellido");

                String domicilio = result.getString("domicilio");

                String fecha=result.getString("fecha_de_alta");

                paciente = new Paciente(nombre, apellido,idPaciente,domicilio,fecha);

            }


            log.info("Se encontro el odontologo buscado por id");
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {

            throwables.printStackTrace();
            log.error("Hubo un error al buscar un odontologo por  id");
        }



        return paciente;
    }

    @Override
    public List<Paciente> buscarTodos() {
        Connection connection = null;

        PreparedStatement preparedStatement = null;

        List<Paciente> pacientes = new ArrayList<Paciente>();

        try {

            //1 Levantar el driver y Conectarnos

            Class.forName(DB_JDBC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);



            //2 Crear una sentencia

            preparedStatement = connection.prepareStatement("SELECT *  FROM pacientes");



            //3 Ejecutar una sentencia SQL

            ResultSet result = preparedStatement.executeQuery();



            //4 Obtener resultados

            while (result.next()) {
                Long idPaciente=result.getLong("id");
                String domicilio=result.getString("domicilio");

                String nombre = result.getString("nombre");

                String apellido = result.getString("apellido");
                String fecha=result.getString("fecha_de_alta");
                pacientes.add(new Paciente( nombre, apellido,idPaciente,domicilio,fecha));



            }


            log.info("Se implemento el metodo buscarTodos los odontologos correctamente");
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {

            throwables.printStackTrace();
            log.error("Hubo un error al intentar listar todos los odontologos");
        }



        return pacientes;
    }
}
