package com.trabajofinal.tpfinal.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Odontologo {
    private String nombre;
    private String apellido;
    private Integer numeroMatricula;
    private Long id;

    public Odontologo(String nombre, String apellido, Integer numeroMatricula, Long id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroMatricula = numeroMatricula;
        this.id = id;
    }


}
