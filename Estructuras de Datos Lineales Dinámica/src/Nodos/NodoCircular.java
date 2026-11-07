/*
Salcedo Alvarez Hugo Emmanuel
Morales Escobar Juan Adrian
 */
package Nodos;

/**
 * Nodo de la lista circular doblemente enlazada.
 * Guarda un trabajo (cualquier subclase de Trabajo) y las referencias al
 * nodo siguiente y al nodo anterior.
 */
public class NodoCircular<T extends Trabajo> {

    public T dato;
    public NodoCircular<T> siguiente;
    public NodoCircular<T> anterior;

    public NodoCircular(T dato) {
        this.dato = dato;
        this.siguiente = this;   // un nodo solo se apunta a sí mismo
        this.anterior = this;
    }
}
