package com.example.sisa.POO;

import java.time.LocalDate;

public class Jugadores {
    private String nombre;
    private String apellido;
    private Posiciones posiciones;
    private LocalDate fechaDisponible;

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public Jugadores(String nombre, String apellido, Posiciones posiciones, LocalDate fechaDisponible) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.posiciones = posiciones;
        this.fechaDisponible = fechaDisponible;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Posiciones getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(Posiciones posiciones) {
        this.posiciones = posiciones;
    }

    public LocalDate getFechaDisponible() {
        return fechaDisponible;
    }

    public void setFechaDisponible(LocalDate fechaDisponible) {
        this.fechaDisponible = fechaDisponible;
    }

    @Override
    public String toString() {
        return "Jugadores{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", posiciones=" + posiciones +
                ", fechaDisponible=" + fechaDisponible +
                '}';
    }

}
