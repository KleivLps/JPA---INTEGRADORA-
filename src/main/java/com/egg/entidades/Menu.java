package com.egg.entidades;

import com.egg.servicios.AutorServicio;
import com.egg.servicios.EditorialServicio;
import com.egg.servicios.LibroServicio;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Menu {

    private final AutorServicio autorServicio;
    private final EditorialServicio editorialServicio;
    private final LibroServicio libroServicio;
    private final Scanner scanner;

    public Menu() {
        this.autorServicio = new AutorServicio();
        this.editorialServicio = new EditorialServicio();
        this.libroServicio = new LibroServicio();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = 0;

        while (opcion != 6) {
            System.out.println("\n--- Menu de Libreria ---");
            System.out.println("1. Crear Autor");
            System.out.println("2. Crear Editorial");
            System.out.println("3. Crear Libro");
            System.out.println("4. Buscar Libro por ISBN");
            System.out.println("5. Buscar Autor por nombre");
            System.out.println("6. Salir");
            System.out.println("Seleccione una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre del autor: ");
                    String nombreAutor = scanner.nextLine();
                    autorServicio.crearAutor(nombreAutor);
                    break;
                case 2:
                    System.out.println("Ingrese el nombre de la editorial: ");
                    String nombreEditorial = scanner.nextLine();
                    editorialServicio.crearEditorial(nombreEditorial);
                    break;
                case 3:
                    System.out.println("Ingrese el ISBN del libro:");
                    Long isbn = Long.parseLong(scanner.nextLine());
                    System.out.println("Ingrese el titulo del libro: ");
                    String titulo = scanner.nextLine();
                    System.out.println("Ingrese el año de publicacion: ");
                    int año = Integer.parseInt(scanner.nextLine());
                    System.out.println("Ingrese el numero de ejempalres: ");
                    int ejemplares = Integer.parseInt(scanner.nextLine());
                    break;
                case 4:
                    System.out.println("Ingrese el ISBN del libro: ");
                    Long buscarIsbn = Long.parseLong(scanner.nextLine());
                    Libro libro = libroServicio.buscarLibroPorIsbn(buscarIsbn);
                    if (libro != null) {
                        System.out.println(libro);
                    } else {
                        System.out.println("Libro no encontrado.");
                    }
                    break;
                case 5:
                    System.out.println("Ingrese el nombre del autor: ");
                    String buscarNombreAutor = scanner.nextLine();
                    Autor autor = autorServicio.buscarAutorPorNombre(buscarNombreAutor);
                    if (autor != null) {
                        System.out.println(autor);
                    } else {
                        System.out.println("Autor no encontrado. ");

                    }
                    break;
                case 6:
                    System.out.println("¡Gracias por usar la aplicacion de Librerias!");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
}
