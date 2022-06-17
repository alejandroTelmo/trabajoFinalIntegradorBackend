package com.trabajofinal.tpfinal.repository.impl;

import com.trabajofinal.tpfinal.model.Usuario;
import com.trabajofinal.tpfinal.repository.IDao;
import com.trabajofinal.tpfinal.util.H2Gestor;


import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoH2 implements IDao<Usuario> {
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private H2Gestor utilDb=new H2Gestor();


    public void crearTablaUsuario() throws SQLException {


        Connection conn = utilDb.getConnection();

        String sql_create_table ="DROP TABLE IF EXISTS usuarios ; CREATE TABLE usuarios "
                + "("
                + "ID BIGINT PRIMARY KEY,"
                + "USERNAME VARCHAR(100) NOT NULL,"
                + "PASSWORD VARCHAR(100) NOT NULL"
                + ")";

        utilDb.executeSQL(conn, sql_create_table);
    }

    @Override
    public Usuario guardar(Usuario usuario) throws SQLException {
        Connection connection = null;

        PreparedStatement preparedStatement = null;



        try {

            //1 Levantar el driver y Conectarnos

            Class.forName(DB_JDBC_DRIVER);

            connection = utilDb.getConnection();



            //2 Crear una sentencia

            preparedStatement = connection.prepareStatement("INSERT INTO usuarios VALUES(?,?,?)");

            preparedStatement.setLong(1,usuario.getId());

            preparedStatement.setString(2, usuario.getUsername());

            preparedStatement.setString(3, usuario.getPassword());


            //3 Ejecutar una sentencia SQL

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {

            throwables.printStackTrace();

        }

        return usuario;
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        Connection conn=null;
        PreparedStatement ps=null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            conn=utilDb.getConnection();
            ps= conn.prepareStatement("DELETE FROM usuarios WHERE id=?");
            ps.setLong(1,id);
            ps.executeUpdate();
            ps.close();


        }catch (SQLException | ClassNotFoundException throwables){

            throwables.printStackTrace();
        }

    }

    @Override
    public Usuario buscar(Long id) {
        Connection conn=null;
        PreparedStatement ps=null;
        Usuario usuario=null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            conn=utilDb.getConnection();
            ps=conn.prepareStatement("SELECT * FROM usuarios WHERE id=?");
            ps.setLong(1,id);
           ResultSet rs= ps.executeQuery();
           while (rs.next()){
               String user=rs.getString("username");
               String pass="xxx";
               Long idUsuario=rs.getLong("id");
                usuario=new Usuario(idUsuario,user,pass);
           }

            ps.close();
        } catch (SQLException  | ClassNotFoundException throwables) {

        }
        return usuario;
    }

    @Override
    public List<Usuario> buscarTodos() {
        Connection conn=null;
        PreparedStatement ps=null;
        List<Usuario> usuarios=new ArrayList<>();
        try {
            Class.forName(DB_JDBC_DRIVER);
            conn=utilDb.getConnection();
            ps=conn.prepareStatement("SELECT id,username FROM usuarios");
           ResultSet rs= ps.executeQuery();
           while (rs.next()){
               Long id=rs.getLong("id");
               String user=rs.getString("username");
               String pass="xxx";
               usuarios.add(new Usuario(id,user,pass));

           }

        } catch (SQLException | ClassNotFoundException throwables) {

        }
        return usuarios;
    }
    public Usuario actualizar(Usuario usuario) throws SQLException {

        Connection connection = null;

        PreparedStatement preparedStatement = null;



        try {

            //1 Levantar el driver y Conectarnos

            Class.forName(DB_JDBC_DRIVER);

            connection = utilDb.getConnection();



            //2 Crear una sentencia

            preparedStatement = connection.prepareStatement("UPDATE usuarios SET username = ?, password = ? where id = ? ");

            preparedStatement.setString(1, usuario.getUsername());

            preparedStatement.setString(2, usuario.getPassword());

            preparedStatement.setLong(3,usuario.getId());

            //3 Ejecutar una sentencia SQL

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {

            throwables.printStackTrace();

        }

        return usuario;
    }



}
