package com.trabajofinal.tpfinal.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Paciente extends Persona {
    private Long id;
    private String domicilio;
    private String fechaDeAlta;

    public Paciente(String nombre, String apellido, Long id, String domicilio, String fechaDeAlta) {
        super(nombre, apellido);
        this.id = id;
        this.domicilio = domicilio;
        this.fechaDeAlta = fechaDeAlta;
    }



}
