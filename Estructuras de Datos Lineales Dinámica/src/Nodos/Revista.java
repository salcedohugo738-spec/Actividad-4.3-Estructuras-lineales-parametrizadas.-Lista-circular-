/*
Salcedo Alvarez Hugo Emmanuel
Morales Escobar Juan Adrian
 */
package Nodos;

/** Trabajo de tipo Revista: agrega el número de edición. */
public class Revista extends Trabajo {

    private int numeroEdicion;

    public Revista(String titulo, String autor, int anioPublicacion, int numeroEdicion) {
        super(titulo, autor, anioPublicacion);
        this.numeroEdicion = validarEnteroPositivo(numeroEdicion, "El número de edición");
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(int numeroEdicion) {
        this.numeroEdicion = validarEnteroPositivo(numeroEdicion, "El número de edición");
    }

    @Override
    public String toString() {
        return "Revista -> " + super.toString() + " | Edición: " + numeroEdicion;
    }
}
