package com.egg.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Alta")
    private boolean alta;

    public Autor(int id, String nombre, boolean alta) {
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
    }

    public Autor(boolean alta, String nombre) {
        this.alta = alta;
        this.nombre = nombre;
    }

    public Autor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    @Override
    public String toString() {
        return " \nAutor:" +
                "\nid = " + id +
                " \nnombre ='" + nombre + "'";
}
    }
