package com.trabajofinal.tpfinal;

import com.trabajofinal.tpfinal.model.Odontologo;
import com.trabajofinal.tpfinal.model.Paciente;
import com.trabajofinal.tpfinal.model.Usuario;
import com.trabajofinal.tpfinal.repository.impl.OdontologoDaoH2;
import com.trabajofinal.tpfinal.repository.impl.PacienteDaoH2;
import com.trabajofinal.tpfinal.repository.impl.UsuarioDaoH2;
import com.trabajofinal.tpfinal.services.OdontologoService;
import com.trabajofinal.tpfinal.services.PacienteService;
import com.trabajofinal.tpfinal.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
class TpfinalApplicationTests {
	Usuario usuario=new Usuario(15L,"OHNO","ZZZ");
	UsuarioDaoH2 usuarioDaoH2=new UsuarioDaoH2();
	UsuarioService usuarioService=new UsuarioService(usuarioDaoH2);

	Paciente paciente=new Paciente("kevin","hoy comandas la clase de backend",541L,"COLOMBIA","20/22/2022");
	PacienteDaoH2 pacienteDaoH2=new PacienteDaoH2();
	PacienteService pacienteService=new PacienteService(pacienteDaoH2);

	Odontologo odontologo=new Odontologo("saca muela","y dientes",123,9955L);
	OdontologoDaoH2 odontologoDaoH2=new OdontologoDaoH2();
	OdontologoService odontologoService=new OdontologoService(odontologoDaoH2);

	@Test
	void contextLoads() {

	}
	@Test
	public void agregarUsuario() throws SQLException {
		usuarioService.guardar(usuario);
	}
	@Test
	public void agregarPaciente() throws SQLException {
		pacienteService.guardar(paciente);
	}
	@Test
	public void agregarOdontologo() throws SQLException {

		odontologoService.guardar(odontologo);
	}
}
