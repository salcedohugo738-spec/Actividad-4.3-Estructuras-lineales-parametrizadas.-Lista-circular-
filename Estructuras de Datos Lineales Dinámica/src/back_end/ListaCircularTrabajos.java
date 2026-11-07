/*
Salcedo Alvarez Hugo Emmanuel
Morales Escobar Juan Adrian
 */
package back_end;

import Nodos.NodoCircular;
import Nodos.Trabajo;

/**
 * Lista circular doblemente enlazada de trabajos de biblioteca.
 * El último nodo apunta al primero (siguiente) y el primero al último
 * (anterior), por lo que se puede recorrer indefinidamente en ambos sentidos.
 *
 * Las posiciones son base 0: la cabeza es la posición 0.
 *
 * @param <T> cualquier subclase de Trabajo (Libro, Revista, Articulo)
 */
public class ListaCircularTrabajos<T extends Trabajo> {

    private NodoCircular<T> cabeza;   // nodo inicio; cabeza.anterior es el último nodo
    private NodoCircular<T> actual;   // cursor de navegación
    private int tamanio;

    public ListaCircularTrabajos() {
        vaciarLista();
    }

    // ------------------------------------------------------------ inserción

    public void insertarInicio(T trabajo) {
        NodoCircular<T> nuevo = crearNodo(trabajo);
        enlazarAntesDe(nuevo, cabeza);
        cabeza = nuevo;
        actual = nuevo;
    }

    public void insertarFinal(T trabajo) {
        NodoCircular<T> nuevo = crearNodo(trabajo);
        enlazarAntesDe(nuevo, cabeza);   // antes de la cabeza = al final del ciclo
        actual = nuevo;
    }

    public void insertarPosicion(T trabajo, int posicion) {
        if (posicion < 0 || posicion > tamanio) {
            throw new IllegalArgumentException("Posición fuera de rango: debe estar entre 0 y " + tamanio + ".");
        }
        if (posicion == 0) {
            insertarInicio(trabajo);
        } else if (posicion == tamanio) {
            insertarFinal(trabajo);
        } else {
            NodoCircular<T> nuevo = crearNodo(trabajo);
            enlazarAntesDe(nuevo, nodoEn(posicion));
            actual = nuevo;
        }
    }

    // ------------------------------------------------------------- eliminación

    public T eliminar(int posicion) {
        exigirNoVacia();
        if (posicion < 0 || posicion >= tamanio) {
            throw new IllegalArgumentException("Posición fuera de rango: debe estar entre 0 y " + (tamanio - 1) + ".");
        }
        return desenlazar(nodoEn(posicion));
    }

    public T eliminarPorTitulo(String titulo) {
        exigirNoVacia();
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título a eliminar no puede estar vacío.");
        }
        String buscado = titulo.trim();
        NodoCircular<T> nodo = cabeza;
        for (int i = 0; i < tamanio; i++) {   // una sola vuelta
            if (nodo.dato.getTitulo().equalsIgnoreCase(buscado)) {
                return desenlazar(nodo);
            }
            nodo = nodo.siguiente;
        }
        throw new IllegalArgumentException("No existe ningún trabajo con el título \"" + buscado + "\".");
    }

    // -------------------------------------------------------------- navegación

    public T siguiente() {
        exigirNoVacia();
        actual = actual.siguiente;
        return actual.dato;
    }

    public T anterior() {
        exigirNoVacia();
        actual = actual.anterior;
        return actual.dato;
    }

    public T consultarActual() {
        exigirNoVacia();
        return actual.dato;
    }

    /** Posición (base 0) del cursor actual dentro de la lista. */
    public int getPosicionActual() {
        exigirNoVacia();
        NodoCircular<T> nodo = cabeza;
        int pos = 0;
        while (nodo != actual) {
            nodo = nodo.siguiente;
            pos++;
        }
        return pos;
    }

    // ---------------------------------------------------------------- consulta

    /**
     * Recorre la lista exactamente una vuelta, desde la cabeza hasta regresar
     * a ella, y devuelve la secuencia marcando la cabeza y el elemento actual.
     */
    public String mostrarTodos() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tamaño: ").append(tamanio).append("   |   isEmpty(): ").append(isEmpty()).append("\n");
        sb.append("------------------------------------------------------------\n");
        if (isEmpty()) {
            sb.append("(lista circular vacía)\n");
            return sb.toString();
        }
        NodoCircular<T> nodo = cabeza;
        int pos = 0;
        do {
            String marcaCabeza = (nodo == cabeza) ? "[CABEZA]" : "";
            String marcaActual = (nodo == actual) ? "[ACTUAL]" : "";
            sb.append(String.format("[%d] %-8s %-8s %s%n", pos, marcaCabeza, marcaActual, nodo.dato));
            nodo = nodo.siguiente;
            pos++;
        } while (nodo != cabeza);
        sb.append("<-- el último nodo apunta de nuevo a [0] (ciclo cerrado)\n");
        return sb.toString();
    }

    public boolean isEmpty() {
        return cabeza == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void vaciarLista() {
        if (cabeza != null) {   // rompe los enlaces para liberar los nodos
            NodoCircular<T> nodo = cabeza;
            for (int i = 0; i < tamanio; i++) {
                NodoCircular<T> sig = nodo.siguiente;
                nodo.siguiente = null;
                nodo.anterior = null;
                nodo = sig;
            }
        }
        cabeza = null;
        actual = null;
        tamanio = 0;
    }

    // ---------------------------------------------------------------- privados

    private NodoCircular<T> crearNodo(T trabajo) {
        if (trabajo == null) {
            throw new IllegalArgumentException("El trabajo a insertar no puede ser nulo.");
        }
        return new NodoCircular<>(trabajo);
    }

    private void exigirNoVacia() {
        if (isEmpty()) {
            throw new IllegalStateException("La lista circular está vacía.");
        }
    }

    /** Enlaza "nuevo" justo antes de "referencia" (si la lista está vacía, nuevo queda solo). */
    private void enlazarAntesDe(NodoCircular<T> nuevo, NodoCircular<T> referencia) {
        if (referencia == null) {
            cabeza = nuevo;   // primer nodo: se apunta a sí mismo
        } else {
            NodoCircular<T> previo = referencia.anterior;
            nuevo.siguiente = referencia;
            nuevo.anterior = previo;
            previo.siguiente = nuevo;
            referencia.anterior = nuevo;
        }
        tamanio++;
    }

    /** Devuelve el nodo en la posición indicada, recorriendo por el lado más corto. */
    private NodoCircular<T> nodoEn(int posicion) {
        NodoCircular<T> nodo = cabeza;
        if (posicion <= tamanio / 2) {
            for (int i = 0; i < posicion; i++) {
                nodo = nodo.siguiente;
            }
        } else {
            for (int i = 0; i < tamanio - posicion; i++) {
                nodo = nodo.anterior;
            }
        }
        return nodo;
    }

    /** Quita el nodo ajustando los enlaces de su anterior y su siguiente. */
    private T desenlazar(NodoCircular<T> nodo) {
        T dato = nodo.dato;
        if (tamanio == 1) {
            cabeza = null;
            actual = null;
        } else {
            nodo.anterior.siguiente = nodo.siguiente;
            nodo.siguiente.anterior = nodo.anterior;
            if (nodo == cabeza) {
                cabeza = nodo.siguiente;
            }
            if (nodo == actual) {
                actual = nodo.siguiente;
            }
        }
        nodo.siguiente = null;
        nodo.anterior = null;
        tamanio--;
        return dato;
    }
}
