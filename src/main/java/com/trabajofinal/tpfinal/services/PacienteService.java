package com.trabajofinal.tpfinal.services;

import com.trabajofinal.tpfinal.model.Paciente;
import com.trabajofinal.tpfinal.repository.IDao;

import java.sql.SQLException;
import java.util.List;

public class PacienteService implements IDao<Paciente> {
    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }
    public PacienteService(){

    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        return null;
    }

    public void setPacienteIDao(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    @Override
    public Paciente guardar(Paciente paciente) throws SQLException {
        return pacienteIDao.guardar(paciente);
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        pacienteIDao.eliminar(id);
    }

    @Override
    public Paciente buscar(Long id) {
        return pacienteIDao.buscar(id);
    }

    @Override
    public List<Paciente> buscarTodos() {
        return pacienteIDao.buscarTodos();
    }
}
