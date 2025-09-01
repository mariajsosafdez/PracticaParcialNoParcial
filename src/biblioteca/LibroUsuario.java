package biblioteca;

public class LibroUsuario {
    private long codigo;
    private String date;
    private Libro libro;
    private Usuario usuario;

    public LibroUsuario() {
    }

    public LibroUsuario(long codigo, String date, Libro libro, Usuario usuario) {
        this.codigo = codigo;
        this.date = date;
        this.libro = libro;
        this.usuario = usuario;
        this.libro.prestamo.add(this);
        this.libro.setDisponible(false);
        this.usuario.prestamo.add(this);

    }

    public long getCodigo() {
        return codigo;
    }

    public String getDate() {
        return date;
    }

    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
