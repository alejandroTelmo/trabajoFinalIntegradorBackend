package com.trabajofinal.tpfinal.controllers;

import com.trabajofinal.tpfinal.model.Paciente;
import com.trabajofinal.tpfinal.repository.impl.PacienteDaoH2;
import com.trabajofinal.tpfinal.services.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController{

   private PacienteService pacienteService=new PacienteService(new PacienteDaoH2());

   @ResponseBody
    @GetMapping("/{id}")
    public Paciente buscarPaciente(@PathVariable Long id){
       return pacienteService.buscar(id);
   }
    @ResponseBody
    @GetMapping
    public List<Paciente> listarPacientes(){
        return pacienteService.buscarTodos();
    }
    @DeleteMapping("/{id}")
    public void eliminarPaciente(@PathVariable Long id) throws SQLException {
       pacienteService.eliminar(id);
    }
    @ResponseBody
    @PutMapping
    public Paciente actualizarPaciente(@RequestBody Paciente paciente) throws SQLException {
       return pacienteService.actualizar(paciente);
    }
    @PostMapping
    public Paciente crearPaciente(@RequestBody  Paciente paciente) throws SQLException {
       return pacienteService.guardar(paciente);
    }



}
