package com.egg;


import com.egg.entidades.Menu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        /*EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("integradora-JPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();*/
        Menu menu = new Menu();
        menu.mostrarMenu();
    }

}
