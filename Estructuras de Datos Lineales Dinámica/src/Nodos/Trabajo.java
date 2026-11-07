/*
Salcedo Alvarez Hugo Emmanuel
Morales Escobar Juan Adrian
 */
package Nodos;

/**
 * Clase base de la jerarquía de la Biblioteca.
 * Representa cualquier trabajo bibliográfico (Libro, Revista, Articulo).
 */
public class Trabajo {

    private String titulo;
    private String autor;
    private int anioPublicacion;

    public Trabajo(String titulo, String autor, int anioPublicacion) {
        this.titulo = validarTexto(titulo, "El título");
        this.autor = validarTexto(autor, "El autor");
        this.anioPublicacion = validarEnteroPositivo(anioPublicacion, "El año de publicación");
    }

    /** Valida que un texto no sea nulo ni vacío y lo devuelve sin espacios sobrantes. */
    protected static String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío.");
        }
        return valor.trim();
    }

    /** Valida que un entero sea mayor a cero. */
    protected static int validarEnteroPositivo(int valor, String campo) {
        if (valor <= 0) {
            throw new IllegalArgumentException(campo + " debe ser un entero mayor a cero.");
        }
        return valor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = validarTexto(titulo, "El título");
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = validarTexto(autor, "El autor");
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = validarEnteroPositivo(anioPublicacion, "El año de publicación");
    }

    @Override
    public String toString() {
        return "Título: " + titulo + " | Autor: " + autor + " | Año: " + anioPublicacion;
    }
}
