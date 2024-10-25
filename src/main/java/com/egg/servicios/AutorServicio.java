package com.egg.servicios;

import com.egg.entidades.Autor;
import jakarta.persistence.*;

public class AutorServicio {
    private EntityManagerFactory entityManagerFactory;

    public AutorServicio(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("libreria-PU");
    }

    public Autor crearAutor(String nombre) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del autor es obligatorio.");
        }

        if (buscarAutorPorNombre(nombre) != null) {
            throw new IllegalArgumentException("Ya existe un autor con ese nombre.");
        }

        Autor autor = new Autor();
        autor.setNombre(nombre);

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(autor);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return autor;
    }

    public Autor buscarAutorPorNombre(String nombre) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Autor autor = null;
        try {
            Query query = entityManager.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre");
            query.setParameter("nombre", nombre);
            autor = (Autor) query.getSingleResult();
        } catch (NoResultException e) {

        } finally {
            entityManager.close();
        }
        return autor;
    }

    public void darBajaAutor(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Autor autor = entityManager.find(Autor.class, id);
            if (autor != null) {
                autor.setAlta(false);
                entityManager.getTransaction().commit();
            }
        } finally {
            entityManager.close();
        }
    }

    public void modificarAutor(Long id, String nuevoNombre) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Autor autor = entityManager.find(Autor.class, id);
            if (autor != null && nuevoNombre != null && !nuevoNombre.isEmpty()) {
                autor.setNombre(nuevoNombre);
                entityManager.getTransaction().commit();
            }
        } finally {
            entityManager.close();
        }
    }

    public void eliminarAutor(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Autor autor = entityManager.find(Autor.class, id);
            if (autor != null) {
                entityManager.remove(autor);
                entityManager.getTransaction().commit();
            }
        } finally {
            entityManager.close();
        }
    }
}
