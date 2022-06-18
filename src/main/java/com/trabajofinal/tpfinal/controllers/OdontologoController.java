package com.trabajofinal.tpfinal.controllers;

import com.trabajofinal.tpfinal.model.Odontologo;
import com.trabajofinal.tpfinal.repository.impl.OdontologoDaoH2;
import com.trabajofinal.tpfinal.services.OdontologoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RequestMapping("/odontologos")
@RestController
public class OdontologoController {

    private  OdontologoService odontologoService=new OdontologoService(new OdontologoDaoH2());
    
    @ResponseBody
    @PostMapping
    public Odontologo crearOdontologo(@RequestBody Odontologo odontologo) throws SQLException {
        return odontologoService.guardar(odontologo);
    }
    
    @ResponseBody
    @GetMapping("/{id}")
    public Odontologo buscarOdontologo(@PathVariable Long id){

       return odontologoService.buscar(id);
    }

    @ResponseBody
    @GetMapping
    public List<Odontologo> listarOdontologos(){

        return odontologoService.buscarTodos();
    }
    @ResponseBody
    @PutMapping
    public Odontologo actualizarOdontologo(@RequestBody Odontologo odontologo) throws SQLException {
        return odontologoService.actualizar(odontologo);
    }
}
