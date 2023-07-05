/*
 * Proyecto 2 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 *
 * ConjuntoPalabras.kt -> Este archivo contiene la implementación del TAD Conjunto como
 * una tabla de hash que resuelve colisiones con encadenamiento. Además se implementa
 * la clase AlfabetHashTable, que representa una tabla de hash para buscar si los caracteres
 * de una posible palabra están o no en el alfabeto.
 *
 * Nota: Para poder implementar las tablas de hash se agregaron al archivo las clases
 * Nodo y CircularList, que se usan en la implementación de las tablas de hash.
 */

class AlfabetHashTable() {
    private val alfabeto: Array<CircularList> = Array(27) { CircularList() }
}

/**
 * Clase Nodo, que representa un nodo de una lista circular doblemente enlazada.
 * @param dato: String? -> El dato que va a contener el nodo.
 * @property next: Nodo? -> El nodo siguiente al nodo actual.
 * @property prev: Nodo? -> El nodo anterior al nodo actual.
 */
class Nodo(val dato: String?) {
    // Atributos de la clase Nodo

    // next: Nodo? -> El nodo siguiente al nodo actual.
    var next: Nodo? = null

    // prev: Nodo? -> El nodo anterior al nodo actual.
    var prev: Nodo? = null

    // Metodos de la clase Nodo

    /**
     * cambiarNext(nodo: Nodo?): Unit
     * Método que cambia el nodo siguiente al nodo actual.
     * @param nodo: Nodo? -> El nuevo nodo siguiente al nodo actual.
     * Precondición: true.
     * Postcondición: El nodo siguiente al nodo actual es el nodo dado.
     */
    fun cambiarNext(nodo: Nodo?) {
        this.next = nodo
    }

    /**
     * cambiarPrev(nodo: Nodo?): Unit
     * Método que cambia el nodo anterior al nodo actual.
     * @param nodo: Nodo? -> El nuevo nodo anterior al nodo actual.
     * Precondición: true.
     * Postcondición: El nodo anterior al nodo actual es el nodo dado.
     */
    fun cambiarPrev(nodo: Nodo?) {
        this.prev = nodo
    }

    /**
     * cambiarDato(dato: String?): Unit
     * Método que cambia el dato que contiene el nodo.
     * @param dato: String? -> El nuevo dato que va a contener el nodo.
     * Precondición: true.
     * Postcondición: El dato que contiene el nodo es el dato dado.
     */
    fun cambiarDato(dato: String?) {
        this.dato = dato
    }

    /**
     * obtenerDato(): String?
     * Método que devuelve el dato que contiene el nodo.
     * Precondición: true.
     * Postcondición: El dato que contiene el nodo es el dato dado.
     */
    fun obtenerDato(): String? {
        return this.dato
    }

    /**
     * toString(): String
     * Método que imprime una representación en String del nodo.
     * Precondición: true.
     * Postcondición: Se devuelve una representación en String del nodo.
     */
    override fun toString(): String {
        return "${this.obtenerDato()}"
    }
}

/**
 * Clase CircularList, que representa una lista circular doblemente enlazada
 * Los nodos de la lista son de la clase Nodo
 * @property sentinel: Nodo -> El nodo sentinela de la lista
 * @property size: Int -> El tamaño de la lista
 */
class CircularList() {
    // Atributos de la clase CircularList

    // sentinel: Nodo -> El nodo sentinela de la lista
    var sentinel: Nodo = Nodo(null)

    // size: Int -> El tamaño de la lista
    private var size: Int = 0

    // Metodos de la clase CircularList

    /**
     * init
     * Método constructor que se ejecuta al crear un objeto de la clase CircularList.
     * Precondición: true.
     * Postcondición: Se crea un objeto de la clase CircularList.
     */
    init {
        // Cambiamos el nodo siguiente y anterior del nodo sentinela para que apunte a si mismo
        sentinel.cambiarNext(sentinel)
        sentinel.cambiarPrev(sentinel)
    }

    /**
     * getSize(): Int
     * Método que devuelve el tamaño de la lista.
     * Precondición: true.
     * Postcondición: Se devuelve el tamaño de la lista.
     */
    fun getSize(): Int {
        return size
    }

    /**
     * estaVacia(): Boolean
     * Método que devuelve true si la lista está vacía, false en caso contrario.
     * Precondición: true.
     * Postcondición: Se devuelve true si la lista está vacía, false en caso contrario.
     */
    fun estaVacia(): Boolean {
        return size == 0
    }

    /**
     * agregarAlFrente(dato: String): Unit
     * Método que agrega un nodo con el dato dado al frente de la lista.
     * @param dato: String -> El dato que va a contener el nodo.
     * Precondición: true.
     * Postcondición: Se agrega un nodo con el dato dado al frente de la lista.
     */
    fun agregarAlFrente(dato: String) {
        // Creamos el nuevo nodo
        val nuevoNodo = Nodo(dato)

        // Insertamos el nuevo nodo al frente de la lista
        nuevoNodo.cambiarNext(sentinel.next!!)
        nuevoNodo.cambiarPrev(sentinel)

        // Actualizamos los nodos next y prev de los nodos que estaban al frente de la lista
        sentinel.next!!.cambiarPrev(nuevoNodo)
        sentinel.cambiarNext(nuevoNodo)

        // Aumentamos el tamaño de la lista
        size++
    }

    /**
     * agregarAlFinal(dato: String): Unit
     * Método que agrega un nodo con el dato dado al final de la lista.
     * @param dato: String -> El dato que va a contener el nodo.
     * Precondición: true.
     * Postcondición: Se agrega un nodo con el dato dado al final de la lista.
     */
    fun agregarAlFinal(dato: String) {
        // Creamos el nuevo nodo
        val nuevoNodo = Nodo(dato)

        // Insertamos el nuevo nodo al final de la lista
        nuevoNodo.cambiarNext(sentinel)
        nuevoNodo.cambiarPrev(sentinel.prev!!)

        // Actualizamos los nodos next y prev de los nodos que estaban al final de la lista
        sentinel.prev!!.cambiarNext(nuevoNodo)
        sentinel.cambiarPrev(nuevoNodo)

        // Aumentamos el tamaño de la lista
        size++
    }

    /**
     * eliminar(dato: String): Unit
     * Método que elimina el nodo con el dato dado de la lista.
     * @param dato: String -> El dato del nodo que se va a eliminar.
     * Precondición: true.
     * Postcondición: Se elimina el nodo con el dato dado de la lista.
     */
    fun eliminar(dato: String) {
        // Si la lista esta vacia, no hacemos nada
        if (estaVacia()) return

        // Obtenemos el primer nodo de la lista
        var nodoActual = sentinel.next

        // Recorremos la lista
        while (nodoActual != sentinel) {
            // Si el dato del nodo actual es el dato dado, eliminamos el nodo actual
            if (nodoActual?.obtenerDato() == dato) {
                // Juntamos el nodo anterior y el nodo siguiente del nodo actual
                nodoActual.prev?.cambiarNext(nodoActual.next!!)
                nodoActual.next?.cambiarPrev(nodoActual.prev!!)

                // Reducimos el tamaño de la lista
                size--
                return
            }
            // Si no, pasamos al siguiente nodo
            nodoActual = nodoActual?.next
        }
    }

    /**
     * existe(dato: String): Boolean
     * Método que devuelve true si existe un nodo con el dato dado, false en caso contrario.
     * @param dato: String -> El dato del nodo que se va a buscar.
     * Precondición: true.
     * Postcondición: Se devuelve true si existe un nodo con el dato dado, false en caso contrario.
     */
    fun existe(dato: String): Boolean {
        // Si la lista esta vacia, retornamos false
        if (estaVacia()) return false

        // Obtenemos el primer nodo de la lista
        var nodoActual = sentinel.next

        // Recorremos la lista
        while (nodoActual != sentinel) {
            // Si el dato del nodo actual es el dato dado, retornamos true
            if (nodoActual!!.obtenerDatos() == dato) {
                return true
            }
            // Si no, cambiamos el nodo actual al nodo siguiente al nodo actual
            nodoActual = nodoActual.next
        }

        // Si se recorrio toda la lista y no se encontro el dato, retornamos false
        return false
    }

    /**
     * toString(): String
     * Método que devuelve una representación en String de la lista.
     * Precondición: true.
     * Postcondición: Se devuelve una representación en String de la lista.
     */
    override fun toString(): String {
        // Si la lista esta vacia, retornamos "[]"
        if (estaVacia()) return "[]"

        var string = "["

        // Obtenemos el primer elemento de la lista
        var nodoActual = sentinel.next

        // Recorremos cada uno de los elementos de la lista
        while (nodoActual != sentinel.prev) {
            string += "${nodoActual!!}, "
            nodoActual = nodoActual.next
        }

        // El último elemento de la lista
        string += "${nodoActual!!}]"

        return string
    }
}
