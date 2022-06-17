package com.trabajofinal.tpfinal.services;

import com.trabajofinal.tpfinal.model.Odontologo;
import com.trabajofinal.tpfinal.repository.IDao;
import com.trabajofinal.tpfinal.repository.impl.OdontologoDaoH2;

import java.sql.SQLException;
import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;


public OdontologoService(){

}
    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao=odontologoIDao;
    }







    public void setOdontologoDao( IDao<Odontologo> odontologoIDao) {

        this.odontologoIDao = odontologoIDao;

    }



    public Odontologo guardar(Odontologo odontologo) throws SQLException {

        odontologoIDao.guardar(odontologo);

        return odontologo;

    }



    public void eliminar(Long id) throws SQLException {

        odontologoIDao.eliminar(id);

    }

    public Odontologo buscar(Long id){

        return odontologoIDao.buscar(id);

    }



    public List<Odontologo> buscarTodos(){

        return odontologoIDao.buscarTodos();

    }
}
