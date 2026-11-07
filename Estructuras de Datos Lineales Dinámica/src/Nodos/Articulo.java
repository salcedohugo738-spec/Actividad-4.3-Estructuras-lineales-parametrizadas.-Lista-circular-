/*
Salcedo Alvarez Hugo Emmanuel
Morales Escobar Juan Adrian
 */
package Nodos;

/** Trabajo de tipo Articulo: agrega el nombre de la conferencia. */
public class Articulo extends Trabajo {

    private String nombreConferencia;

    public Articulo(String titulo, String autor, int anioPublicacion, String nombreConferencia) {
        super(titulo, autor, anioPublicacion);
        this.nombreConferencia = validarTexto(nombreConferencia, "El nombre de la conferencia");
    }

    public String getNombreConferencia() {
        return nombreConferencia;
    }

    public void setNombreConferencia(String nombreConferencia) {
        this.nombreConferencia = validarTexto(nombreConferencia, "El nombre de la conferencia");
    }

    @Override
    public String toString() {
        return "Articulo -> " + super.toString() + " | Conferencia: " + nombreConferencia;
    }
}
