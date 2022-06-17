package com.trabajofinal.tpfinal.repository;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
    public T guardar(T t) throws SQLException;
    public void eliminar(Long id) throws SQLException;
    public  T buscar(Long id);
    public List<T> buscarTodos();

    public T actualizar(T t) throws SQLException;

}
