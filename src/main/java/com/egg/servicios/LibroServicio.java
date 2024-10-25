package com.egg.servicios;

import com.egg.entidades.Autor;
import com.egg.entidades.Editorial;
import com.egg.entidades.Libro;
import com.egg.persistencias.LibroDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.util.List;

public class LibroServicio {
    private EntityManagerFactory entityManagerFactory;

    public LibroServicio() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("libreria-PU");
    }

    public Libro crearLibro(Long isbn, String titulo, int año, int ejemplares, Autor autor, Editorial editorial) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        if (isbn == null || titulo == null || titulo.isEmpty() || año <= 0 || ejemplares <= 0) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }

        if (buscarLibroPorIsbn(isbn) != null) {
            throw new IllegalArgumentException("Ya existe un libro con este ISBN.");
        }

        Libro libro = new Libro(isbn,titulo,año,ejemplares,true,autor,editorial);

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(libro);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return libro;
    }

    public Libro buscarLibroPorIsbn(Long isbn) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Libro.class, isbn);
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Libro libro = null;

        try {
            libro = entityManager.createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo", Libro.class).setParameter("titulo", titulo).getSingleResult();
        } catch (NoResultException e) {

        } finally {
            entityManager.close();
        }
        return libro;
    }

    public List<Libro> buscarLibrosPorAutor(String nombreAutor) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Libro> libros = null;
        try {
            libros = entityManager.createQuery("SELECT l FROM Libro l WHERE l.autor.nombre = :nombreAutor", Libro.class).setParameter("nombreAutor", nombreAutor).getResultList();
        } finally {
            entityManager.close();
        }
        return libros;
    }

    public List<Libro> buscarLibroPorEditorial(String nombreEditorial) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Libro> libros = null;
        try {
            libros = entityManager.createQuery("SELECT l FROM Libro l WHERE l.editorial.nombre = :nombreEditorial", Libro.class).setParameter("nombreEditorial", nombreEditorial).getResultList();
        } finally {
            entityManager.close();
        }
        return libros;
    }

    public void darBajaLibro(Long isbn) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Libro libro = entityManager.find(Libro.class, isbn);
            if (libro != null) {
                libro.setAlta(false);
                entityManager.getTransaction().commit();
            }
        } finally {
            entityManager.close();
        }
    }
    public void modificarLibro(Long isbn, String nuevoTitulo, int nuevoAño, int nuevosEjemplares) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Libro libro = entityManager.find(Libro.class, isbn);
            if (libro != null) {
                if (nuevoTitulo != null && !nuevoTitulo.isEmpty()) libro.setTitulo(nuevoTitulo);
                if (nuevoAño > 0) libro.setAño(nuevoAño);
                if (nuevosEjemplares > 0) libro.setEjemplares(nuevosEjemplares);
                entityManager.getTransaction().commit();
            }
        } finally {
            entityManager.close();
        }
    }

    public void eliminarLibro(Long isbn) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Libro libro = entityManager.find(Libro.class, isbn);
            if (libro != null) {
                entityManager.remove(libro);
                entityManager.getTransaction().commit();
            }
        } finally {
            entityManager.close();
        }
    }
}
