/*
Salcedo Alvarez Hugo Emmanuel
Morales Escobar Juan Adrian
 */
package Nodos;

/** Trabajo de tipo Libro: agrega el número de páginas. */
public class Libro extends Trabajo {

    private int numeroPaginas;

    public Libro(String titulo, String autor, int anioPublicacion, int numeroPaginas) {
        super(titulo, autor, anioPublicacion);
        this.numeroPaginas = validarEnteroPositivo(numeroPaginas, "El número de páginas");
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = validarEnteroPositivo(numeroPaginas, "El número de páginas");
    }

    @Override
    public String toString() {
        return "Libro -> " + super.toString() + " | Páginas: " + numeroPaginas;
    }
}
