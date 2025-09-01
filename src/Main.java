import biblioteca.Autor;
import biblioteca.Libro;
import biblioteca.LibroUsuario;
import biblioteca.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static void lanzarMenu() {
        System.out.println("----Ingrese una opción----");
        System.out.println("1. Ingresar autor");
        System.out.println("2. Ingresar libro");
        System.out.println("3. Ingresar usuario");
        System.out.println("4. Ingresar préstamo");
        System.out.println("5. Devolver libro");
        System.out.println("6. Listar usuarios");
        System.out.println("7. Listar libros disponibles");
        System.out.println("8. Listar autores");
        System.out.println("9. Mostrar los libros de cada autor");
        System.out.println("10. Autor con mas libros publicados");
        System.out.println("11. Autor más leído o con más usuarios");
        System.out.println("12. Libro más leído");
        System.out.println("13. Usuario con más préstamos");
        System.out.println("14. mostrar los usuarios en orden alfabético");
        System.out.println("15. Salir 👋");
    }


    public static void main(String[] args) {
        ArrayList<Autor> autores = new ArrayList<>();
        ArrayList<Libro> libros = new ArrayList<>();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<LibroUsuario> prestamos = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        System.out.println("----------------Biblioteca corre y no mueras--------------");
        lanzarMenu();
        int opcion = Integer.parseInt(sc.nextLine());
        //boolean salir = false;
        while (true) {
            switch (opcion) {
                case 1: //ingresar autor
                    System.out.println("Ingrese el nombre del autor");
                    String nombre = sc.nextLine();
                    System.out.println("Ingrese el nombre del país");
                    String pais = sc.nextLine();
                    Autor au = new Autor(nombre, pais);
                    autores.add(au);
                    break;
                case 2: //ingresar libro (autor debe existir)
                    System.out.println("Ingrese el código del autor");
                    long codigo = Long.parseLong(sc.nextLine());
                    Autor w = null;
                    for (int i = 0; i < autores.size(); i++) {
                        if (autores.get(i).getId() == codigo) {
                            w = autores.get(i);
                            break;
                        }
                    }
                    if (w == null) {
                        System.out.println("El codigo del autor no existe");
                        System.out.println("Busque bien bobote");
                    } else {
                        System.out.println("Ingrese el codigo del libro:");
                        long idLibro = Long.parseLong(sc.nextLine());
                        System.out.println("Ingrese el nombre del libro:");
                        String nombreLibro = sc.nextLine();
                        System.out.println("Ingrese el genero del libro:");
                        String genero = sc.nextLine();
                        System.out.println("Ingrese el año de publicación:");
                        int year = Integer.parseInt(sc.nextLine());
                        boolean disponible = true;
                        Libro lb = new Libro(idLibro, nombreLibro, genero, year, disponible, w);
                        libros.add(lb);
                    }
                    break;
                case 3: //ingresar usuario
                    System.out.println("Ingrese el id del usuario");
                    long idUsuario = Long.parseLong(sc.nextLine());
                    System.out.println("Ingrese el nombre del usuario:");
                    String nombreUsuario = sc.nextLine();
                    System.out.println("Ingrese el correo del usuario");
                    String correo = sc.nextLine();
                    System.out.println("Ingrese el teléfono");
                    String telefono = sc.nextLine();
                    Usuario us = new Usuario(idUsuario, nombreUsuario, correo, telefono);
                    usuarios.add(us);
                    break;
                case 4: //hacer un préstamo (libro y usuario deben existir)
                    System.out.println("Ingrese el id del libro");
                    long idLibro = Long.parseLong(sc.nextLine());
                    System.out.println("Ingrese la cedula del usuario");
                    long idUs = Long.parseLong(sc.nextLine());

                    Libro l = null;
                    Usuario u = null;
                    for (Libro libro : libros) { //para cada libro en libros[], la variable libro sirve como índice para recorrer el arreglo, cabe aclarar que no permite modificaciones
                        if (libro.getId() == idLibro) {
                            l = libro;
                            break;
                        }
                    }
                    for (int i = 0; i < usuarios.size(); i++) { //este for es el mismo de arriba, lo dejo para hacer la comparación
                        if (usuarios.get(i).getId() == idUs) {
                            u = usuarios.get(i);
                            break;
                        }
                    }
                    if (u == null || l == null) {
                        System.out.println("El libro o usuario no existen");
                        break;
                    }
                    System.out.println("Ingrese el codigo del préstamo");
                    long idPrestamo = Long.parseLong(sc.nextLine());
                    String fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now()); //toDo preguntar como se ponía esto
                    LibroUsuario prs = new LibroUsuario(idPrestamo, fecha, l, u);
                    prestamos.add(prs);
                    l.setVecesPrestado();

                    break;
                case 5: //devolver libro
                    System.out.println("Ingrese el codigo del prestamo que desea desprestar 🤗");
                    long idDesPrestamo = Long.parseLong(sc.nextLine());
                    LibroUsuario p = null;
                    for (LibroUsuario prestamo : prestamos) {
                        if (prestamo.getCodigo() == idDesPrestamo) {
                            p = prestamo;
                        }
                    }
                    if (p == null) {
                        System.out.println("Prestamo no existente, pendejo");
                        break;
                    }
                    for (Libro libro : libros) {
                        if (libro.getId() == p.getLibro().getId()) {
                            prestamos.remove(p);
                            libro.setDisponible(true);
                            //toDo PREGUNTAR SOBRE LA ELIMINACIÓN DE LOS PRESTAMOS QUE ESTÁN DENTRO DE LAS CLASES
                            p.getUsuario().getPrestamo().remove(p);
                            p.getLibro().getPrestamo().remove(p);
                        }
                    }
                    break;
                case 6: //listar usuarios

                    for (Usuario usuario : usuarios) {
                        System.out.println("Identificación: " + usuario.getId());
                        System.out.println("Nombre: " + usuario.getNombre());
                        System.out.println("Correo: " + usuario.getCorreo());
                        System.out.println(" ");
                    }

                    break;
                case 7: //listar libros disponibles
                    for (Libro value : libros) {
                        if (value.isDisponible()) {
                            System.out.println(value.getTitulo());
                            System.out.println(value.getAutor().getNombre());
                            System.out.println(" ");
                        }
                    }
                    break;
                case 8: //listar autores
                    for (Autor autor : autores) {
                        System.out.println("Codigo: " + autor.getId());
                        System.out.println("Autor: " + autor.getNombre());
                        System.out.println(" ");
                    }
                    break;
                case 9: //libros de cada autor
                    for (Autor autor : autores) {
                        System.out.println("Nombre: " + autor.getNombre());
                        for (Libro libro : autor.getLibros()) {
                            System.out.println("Libro: " + libro.getTitulo());
                        }
                    }
                    break;
                case 10: // autor con más libros publicados
                    int cantidadLibros;
                    int mayorCantidadLibros = autores.getFirst().getLibros().size();
                    Autor autorMasLibros = autores.getFirst();
                    for (Autor autor : autores) {
                        cantidadLibros = autor.getLibros().size();
                        if (cantidadLibros > mayorCantidadLibros) {
                            mayorCantidadLibros = cantidadLibros;
                            autorMasLibros = autor;
                        }
                    }
                    System.out.println("Autor : " + autorMasLibros.getNombre());
                    System.out.println("Cantidad libros: " + mayorCantidadLibros);

                    break;
                case 11: // autor más leído o con más usuarios (autor con más libros prestados)
                    Autor autorMasLeido = autores.getFirst(); //toDo este weak warning que chucha ¿
                    int mayorLibrosPrestados = 0;
                    for (Autor autor : autores) {
                        int librosPrestados = 0;
                        for (Libro libro : libros) {
                            if (!libro.isDisponible()) {
                                librosPrestados++;
                            }
                        }
                        if (librosPrestados > mayorLibrosPrestados) {
                            mayorLibrosPrestados = librosPrestados;
                            autorMasLeido = autor;
                        }
                    }
                    System.out.println("El autor más leído fue :" + autorMasLeido.getNombre());
                    break;
                case 12: //libro más leído (con más préstamos)
                    Libro libroMasLeido = libros.getFirst();
                    for (Libro libro : libros) {
                        if (libro.getVecesPrestado() > libroMasLeido.getVecesPrestado()) {
                            libroMasLeido = libro;
                        }
                    }
                    System.out.println("El libro más leído fue :" + libroMasLeido.getTitulo() + " con " + libroMasLeido.getVecesPrestado() + " préstamos");
                    break;
                case 13://Usuario con más préstamos
                    Usuario usuarioMasPrestamos = usuarios.getFirst();
                    for (Usuario usuario : usuarios) {
                        if (usuario.getPrestamo().size() > usuarioMasPrestamos.getPrestamo().size()) {
                            usuarioMasPrestamos = usuario;
                        }
                    }
                    System.out.println("El usuario con más préstamos fue: " + usuarioMasPrestamos.getNombre() + " con " + usuarioMasPrestamos.getPrestamo().size() + " préstamos");
                    break;
                case 14: // mostrar los usuarios en orden alfabético
                    ArrayList<Usuario> usuOrd = new ArrayList<>(usuarios);
                    usuOrd.sort(Comparator.comparing(Usuario::getNombre));

                    System.out.println("Usuarios en orden alfabético");
                    for (Usuario usuario :usuOrd){
                        System.out.println(usuario);
                    }
                    break;
                case 15: //chao con adios
                    System.out.println("Chao 🗿🤙");
                    System.exit(0);
                default:
                    System.out.println("Opción no valida");
                    System.out.println("Deje de ser tonto");
                    break;
            }
            System.out.println("--------------------------");
            lanzarMenu();
            opcion = Integer.parseInt(sc.nextLine());
        }

        //cosa
    }
}
