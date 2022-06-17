package com.trabajofinal.tpfinal.model;



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



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(String fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }
}
