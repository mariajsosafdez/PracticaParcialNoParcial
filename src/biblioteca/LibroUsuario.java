package biblioteca;

public class LibroUsuario { //se puede llamar préstamos
    private long codigo;
    private String date;
    private Libro libro;
    private Usuario usuario;
    public LibroUsuario(){}
    public LibroUsuario(long codigo, String date, Libro libro, Usuario usuario){
        this.codigo = codigo;
        this.date = date;
        this.libro = libro;
        this.usuario = usuario;
        this.libro.prestamo.add(this);
        this.libro.setDisponible(false);
        this.usuario.prestamo.add(this);

    }
}
