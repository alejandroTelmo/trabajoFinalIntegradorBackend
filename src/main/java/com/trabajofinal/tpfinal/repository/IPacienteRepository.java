package com.trabajofinal.tpfinal.repository;

import com.trabajofinal.tpfinal.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente,Long> {
}
