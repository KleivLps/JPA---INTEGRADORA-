package com.egg.entidades;

import jakarta.persistence.*;

@Entity
@Table (name= "Libro")
public class Libro {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    @Column( name = "ISBN")
    private Long isbn;

    @Column(name = "Titulo")
    private String titulo;

    @Column(name = "Año")
    private int año;

    @Column(name = "Ejemplares")
    private int ejemplares;

    @Column(name = "Alta")
    private boolean alta;

    @ManyToOne
    @JoinColumn(name = "Autor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "Editorial")
    private Editorial editorial;

    public Libro(Long isbn, String titulo, int año, int ejemplares, boolean alta, Autor autor, Editorial editorial) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.año = año;
        this.ejemplares = ejemplares;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
    }

    public Libro(String titulo, int año, int ejemplares, boolean alta, Autor autor, Editorial editorial) {
        this.titulo = titulo;
        this.año = año;
        this.ejemplares = ejemplares;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
    }

    public Libro() {
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
}
