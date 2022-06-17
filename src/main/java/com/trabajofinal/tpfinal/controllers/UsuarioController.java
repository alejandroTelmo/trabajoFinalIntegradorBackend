package com.trabajofinal.tpfinal.controllers;

import com.trabajofinal.tpfinal.model.Usuario;
import com.trabajofinal.tpfinal.repository.impl.UsuarioDaoH2;
import com.trabajofinal.tpfinal.services.UsuarioService;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private Logger log=Logger.getLogger(UsuarioController.class);
    private UsuarioService usuarioService=new UsuarioService(new UsuarioDaoH2());
    @ResponseBody
    @PostMapping()
    public ResponseEntity<Usuario> registrarPaciente(@RequestBody Usuario usuario) throws SQLException {

        return ResponseEntity.ok(usuarioService.guardar(usuario));

    }
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscar(id);

        return ResponseEntity.ok(usuario);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) throws SQLException {
            usuarioService.eliminar(id);
    }

    @GetMapping
    public List<Usuario> buscarTodos(){
        log.info("Listando todo");

        return usuarioService.buscarTodos();
    }
    @PutMapping("/{id}")
    public Usuario  actualizar(@RequestBody Usuario usuario) throws SQLException {
        log.info("actualizando");

        return usuarioService.actualizar(usuario);
    }
}
