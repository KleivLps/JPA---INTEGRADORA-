package com.egg.servicios;

import com.egg.entidades.Editorial;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class EditorialServicio {

    private EntityManagerFactory entityManagerFactory;
    public EditorialServicio() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("libreria-PU");
    }

    public Editorial crearEditorial(String nombre) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la editorial es obligatoria.");
        }

        if (buscarEditorialPorNombre(nombre) != null) {
            throw new IllegalArgumentException("Ya existe una editorial  con este nombre.");
        }

        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(editorial);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return editorial;
    }

    public Editorial buscarEditorialPorNombre(String nombre) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Editorial editorial = null;
        try {
            editorial = entityManager.createQuery("SELECT e FROM Editorial e WHERE e.nombre = :nombre", Editorial.class).setParameter("nombre", nombre).getSingleResult();
        } catch (NoResultException e) {

        } finally {
            entityManager.close();
        }
        return editorial;
    }

    public void darBajaEditorial(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Editorial editorial = entityManager.find(Editorial.class, id);
            if (editorial != null){
                editorial.setAlta(false);
                entityManager.getTransaction().commit();
            }
        } finally {
            entityManager.close();
        }
    }
    public void modificarEditorial(Long id, String nuevoNombre) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Editorial editorial = entityManager.find(Editorial.class, id);
            if (editorial != null && nuevoNombre != null && !nuevoNombre.isEmpty()) {
                editorial.setNombre(nuevoNombre);
                entityManager.getTransaction().commit();
            }
        } finally {
            entityManager.close();
        }
    }

    public void eliminarEditorial(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Editorial editorial = entityManager.find(Editorial.class, id);
            if (editorial != null) {
                entityManager.remove(editorial);
                entityManager.getTransaction().commit();
            }
        } finally {
            entityManager.close();
        }
    }
}
