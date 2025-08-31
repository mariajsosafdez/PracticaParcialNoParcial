package biblioteca;

import java.util.ArrayList;

public class Autor {
    private static long contador = 0;
    private long id;
    private String nombre;
    private String pais;
    private ArrayList<Libro> libros = new ArrayList<>();

    public Autor() {
        this.id = ++contador;
    }

    public Autor(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
        this.id = ++contador;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void addLibro(Libro libro){
        this.libros.add(libro);
    }
    public ArrayList<Libro> getLibros(){
        return this.libros;
    }

    //cosa
}
