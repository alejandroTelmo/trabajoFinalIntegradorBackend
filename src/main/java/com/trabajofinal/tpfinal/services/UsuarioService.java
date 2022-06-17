package com.trabajofinal.tpfinal.services;



import com.trabajofinal.tpfinal.model.Usuario;
import com.trabajofinal.tpfinal.repository.IDao;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService implements IDao<Usuario> {
    private IDao<Usuario> usuarioIDao;

    public UsuarioService(IDao<Usuario> usuarioIDao) {
        this.usuarioIDao = usuarioIDao;
    }

    public void setUsuarioIDao(IDao<Usuario> usuarioIDao) {
        this.usuarioIDao = usuarioIDao;
    }

    @Override
    public Usuario guardar(Usuario usuario) throws SQLException {
        return usuarioIDao.guardar(usuario);
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        usuarioIDao.eliminar(id);
    }

    @Override
    public Usuario buscar(Long id) {
        return usuarioIDao.buscar(id);
    }

    public Usuario actualizar(Usuario usuario) throws SQLException {
        return usuarioIDao.actualizar(usuario);
    }
    @Override
    public List<Usuario> buscarTodos() {
        return usuarioIDao.buscarTodos();
    }
}
