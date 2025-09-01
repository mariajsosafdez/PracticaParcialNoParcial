package biblioteca;

import java.util.ArrayList;

public class Libro {
    private long id;
    private String titulo;
    private String genero;
    private int year;
    private boolean disponible;
    private Autor autor;
    public ArrayList<LibroUsuario> prestamo = new ArrayList<>();
    public int vecesPrestado = 0;

    public Libro(long id, String titulo, String genero, int year, boolean disponible, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.year = year;
        this.disponible = disponible;
        this.autor = autor;
        this.autor.addLibro(this);
    }

    public Libro() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getVecesPrestado() {
        return vecesPrestado;
    }

    public void setVecesPrestado() {
        this.vecesPrestado++;
    }

    public Autor getAutor() {
        return this.autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public ArrayList<LibroUsuario> getPrestamo() {
        return prestamo;
    }
//cosa
}
