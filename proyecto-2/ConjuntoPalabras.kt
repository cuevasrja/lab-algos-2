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

import kotlin.math.abs

/**
 * Clase ConjuntoPalabras, que representa una tabla de hash que resuelve colisiones
 * con encadenamiento, que almacena las palabras que se agregan a la estructura.
 * @property size: Int -> El tamaño de la tabla de hash.
 * @property palabras: Array<CircularList> -> El conjunto de palabras representado como una tabla de hash.
 */
class ConjuntoPalabras() {
    // Atributos de la clase ConjuntoPalabras

    // size: Int -> El tamaño de la tabla de hash.
    private var size = 7

    // numPalabras: Int -> El número de palabras que hay en la tabla de hash.
    private var numPalabras = 0

    // palabras: Array<CircularList> -> El conjunto de palabras representado como una tabla de hash.
    private var palabras: Array<CircularList> = Array(size) { CircularList() }

    // Métodos de la clase ConjuntoPalabras

    /**
     * hash(palabra: String): Int
     * Método que devuelve el índice en el que debería estar insertada la palabra en la tabla de hash.
     * @param palabra: String -> La palabra que se va a insertar en la tabla de hash.
     * Precondición: true.
     * Postcondición: Se devuelve el índice en el que debería estar insertada la palabra en la tabla de hash.
     */
    private fun hash(palabra: String): Int {
        return abs(palabra.hashCode() % this.getSize())
    }

    /**
     * obtenerFactorCarga(): Double
     * Método que devuelve el factor de carga de la tabla de hash.
     * Precondición: true.
     * Postcondición: Se devuelve el factor de carga de la tabla de hash.
     */
    private fun obtenerFactorCarga(): Double {
        return (this.getNumPalabras().toDouble() / this.getSize().toDouble())
    }

    /**
     * rehash(): Unit
     * Método que aumenta el tamaño de la tabla de hash y reinserta las palabras en la nueva tabla de hash.
     * Precondición: true.
     * Postcondición: Se aumenta el tamaño de la tabla de hash y se reinsertan las palabras en la nueva tabla de hash.
     */
    private fun rehash() {
        // Se crea un registro de las palabras que hay en la tabla de hash.
        val anterioresPalabras = this.palabras.copyOf()

        // Se duplica el tamaño de la tabla de hash.
        this.size *= 2

        // Se reinicia el número de palabras en la tabla de hash.
        this.numPalabras = 0

        // Se crea una nueva tabla de hash con el doble de tamaño.
        this.palabras = Array(size) { CircularList() }

        // Se reinsertan las palabras en la nueva tabla de hash.
        for (i in 0 until anterioresPalabras.size) {
            // Si la lista enlazada está vacía, se pasa a la siguiente.
            if (anterioresPalabras[i].estaVacia()) continue

            // Si no, se obtiene el primer nodo de la lista enlazada.
            var nodo = anterioresPalabras[i].obtenerPrimero()

            // Y se recorre la lista enlazada insertando las palabras en la nueva tabla de hash.
            while (nodo != anterioresPalabras[i].sentinel) {
                this.agregar(nodo?.obtenerDato()!!)
                nodo = nodo.next!!
            }
        }
    }

    /**
     * agregar(palabra: String): Unit
     * Método que agrega una palabra a la tabla de hash.
     * @param palabra: String -> La palabra que se va a insertar en la tabla de hash.
     * Precondición: La palabra es válida y no está en la tabla de hash.
     * Postcondición: Se inserta la palabra en la tabla de hash.
     */
    fun agregar(palabra: String) {
        // Se verifica si la palabra ya está en la tabla de hash.
        if (this.pertenece(palabra)) return

        // Se verifica si se debe aumentar el tamaño de la tabla de hash.
        if (this.obtenerFactorCarga() >= 0.7) this.rehash()

        // Se obtiene el índice en el que se debe insertar la palabra.
        val indice = this.hash(palabra)

        // Se inserta la palabra en la lista enlazada correspondiente.
        palabras[indice].agregarAlFinal(palabra)

        // Se actualiza el número de palabras en la tabla de hash.
        this.numPalabras++
    }

    /**
     * eliminar(palabra: String): Unit
     * Método que elimina una palabra de la tabla de hash.
     * @param palabra: String -> La palabra que se va a eliminar de la tabla de hash.
     * Precondición: La palabra es válida.
     * Postcondición: Se elimina la palabra de la tabla de hash.
     */
    fun eliminar(palabra: String) {
        // Se verifica si la palabra está en la tabla de hash.
        if (!this.pertenece(palabra)) return

        // Se obtiene el índice en el que se debe eliminar la palabra.
        val indice = this.hash(palabra)

        // Se elimina la palabra de la lista enlazada correspondiente.
        palabras[indice].eliminar(palabra)

        // Se actualiza el número de palabras en la tabla de hash.
        this.numPalabras--
    }

    /**
     * pertenece(palabra: String): Boolean
     * Método que devuelve si una palabra pertenece o no al conjunto de palabras.
     * @param palabra: String -> La palabra que se va a buscar en la tabla de hash.
     * Precondición: true.
     * Postcondición: Se devuelve si la palabra pertenece o no al conjunto de palabras.
     */
    fun pertenece(palabra: String): Boolean {
        val indice = this.hash(palabra)
        return palabras[indice].existe(palabra)
    }

    /**
     * getSize(): Int
     * Método que devuelve el número de palabras que hay en el conjunto de palabras.
     * Precondición: true.
     * Postcondición: Se devuelve el número de palabras que hay en el conjunto de palabras.
     */
    fun getSize(): Int {
        return this.size
    }

    /**
     * getNumPalabras(): Int
     * Método que devuelve el número de palabras que hay en el conjunto de palabras.
     * Precondición: true.
     * Postcondición: Se devuelve el número de palabras que hay en el conjunto de palabras.
     */
    fun getNumPalabras(): Int {
        return this.numPalabras
    }

    /**
     * crearArregloPalabras(): Array<String>
     * Método que crea un arreglo con las palabras que hay en el conjunto de palabras.
     * Precondición: true.
     * Postcondición: Se devuelve un arreglo con las palabras que hay en el conjunto de palabras.
     */
    fun crearArregloPalabras(): Array<String> {
        var arregloPalabras = Array(this.numPalabras) { "" }
        var indice = 0
        for (i in 0 until this.getSize()) {
            if (palabras[i].estaVacia()) continue
            var nodo = palabras[i].obtenerPrimero()
            while (nodo != palabras[i].sentinel) {
                arregloPalabras[indice] = nodo?.obtenerDato()!!
                nodo = nodo.next!!
                indice++
            }
        }
        return arregloPalabras
    }

    /**
     * toString(): String
     * Método que devuelve una representación en String del conjunto de palabras.
     * Precondición: true.
     * Postcondición: Se devuelve una representación en String del conjunto de palabras.
     */
    override fun toString(): String {
        if (this.getNumPalabras() == 0) return "[]\n"

        // Se crea un String con la representación del conjunto de palabras.
        var str = "[\n"

        // Se crea un arreglo con las palabras que hay en el conjunto de palabras.
        val palabras = this.crearArregloPalabras()

        // Se ordena lexicográficamente el arreglo de palabras.
        quicksortLexicografico(palabras)

        // Se añaden las palabras al String.
        for (i in 0 until palabras.size - 1) {
            // Se quiere imprimir solo 5 palabras por línea.
            if (i % 5 == 0 && i != 0) {
                str += palabras[i] + ",\n"
            } else {
                str += palabras[i] + ", "
            }
        }

        // Se añade la última palabra al String.
        str += palabras[palabras.size - 1] + "\n]\n"

        return str
    }
}

/**
 * Clase AlfabetHashTable, que representa una tabla de hash para buscar si los caracteres
 * de una posible palabra están o no en el alfabeto español.
 * @property size: Int -> El tamaño de la tabla de hash.
 * @property alfabeto: Array<CircularList> -> El alfabeto representado como una tabla de hash.
 */
class AlfabetHashTable() {
    // Atributos de la clase AlfabetHashTable

    // size: Int -> El tamaño de la tabla de hash.
    private val size = 26

    // alfabeto: Array<CircularList> -> El alfabeto representado como una tabla de hash.
    private val alfabeto: Array<CircularList> = Array(size) { CircularList() }

    // Metodos de la clase AlfabetHashTable

    /**
     * hash(dato: Char): Int
     * Método que devuelve el índice en el que debería estar insertando el dato en la tabla de hash.
     * @param dato: Char -> El dato que se va a insertar en la tabla de hash.
     * Precondición: true.
     * Postcondición: Se devuelve el índice en el que se debería estar insertando el dato en la tabla de hash.
     */
    private fun hash(dato: Char): Int {
        return String.format("%c", dato).hashCode() % size
    }

    /**
     * insertar(dato: Char): Unit
     * Método que inserta un dato en la tabla de hash.
     * @param dato: Char -> El dato que se va a insertar en la tabla de hash.
     * Precondición: true.
     * Postcondición: Se inserta el dato en la tabla de hash.
     */
    fun insertar(dato: Char) {
        val indice = this.hash(dato)
        alfabeto[indice].agregarAlFinal(String.format("%c", dato))
    }

    /**
     * init
     * Método constructor que se ejecuta al crear un objeto de la clase AlfabetHashTable.
     * Precondición: true.
     * Postcondición: Se inicializa la tabla de hash con el alfabeto español.
     */
    init {
        for (i in 'a'..'z') insertar(i)
        insertar('ñ')
    }

    /**
     * perteneceAlAlfabeto(dato: Char): Boolean
     * Método que devuelve si un dato pertenece o no al alfabeto.
     * @param dato: Char -> El dato que se va a buscar en la tabla de hash.
     * Precondición: true.
     * Postcondición: Se devuelve si el dato pertenece o no al alfabeto.
     */
    fun perteneceAlAlfabeto(dato: Char): Boolean {
        val indice = this.hash(dato)
        return alfabeto[indice].existe(String.format("%c", dato))
    }

    /**
     * toString(): String
     * Método que imprime una representación en String de la tabla de hash.
     * Precondición: true.
     * Postcondición: Se devuelve una representación en String de la tabla de hash.
     */
    override fun toString(): String {
        var str = ""
        for (i in 0 until size) {
            str += "${alfabeto[i]}\n"
        }
        return str
    }
}

/**
 * Clase Nodo, que representa un nodo de una lista circular doblemente enlazada.
 * @param dato: String? -> El dato que va a contener el nodo.
 * @property next: Nodo? -> El nodo siguiente al nodo actual.
 * @property prev: Nodo? -> El nodo anterior al nodo actual.
 */
class Nodo(var dato: String?) {
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
        if (this.estaVacia()) return

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
        if (this.estaVacia()) return false

        // Obtenemos el primer nodo de la lista
        var nodoActual = sentinel.next

        // Recorremos la lista
        while (nodoActual != sentinel) {
            // Si el dato del nodo actual es el dato dado, retornamos true
            if (nodoActual!!.obtenerDato() == dato) {
                return true
            }
            // Si no, cambiamos el nodo actual al nodo siguiente al nodo actual
            nodoActual = nodoActual.next
        }

        // Si se recorrio toda la lista y no se encontro el dato, retornamos false
        return false
    }

    /**
     * obtenerPrimero(): Nodo?
     * Método que devuelve el primer nodo de la lista.
     * Precondición: true.
     * Postcondición: Si la lista esta vacia, se devuelve null. Si no, se devuelve el primer nodo de la lista.
     */
    fun obtenerPrimero(): Nodo? {
        if (this.estaVacia()) return null
        return sentinel.next
    }

    /**
     * toString(): String
     * Método que devuelve una representación en String de la lista.
     * Precondición: true.
     * Postcondición: Se devuelve una representación en String de la lista.
     */
    override fun toString(): String {
        // Si la lista esta vacia, retornamos "[]"
        if (this.estaVacia()) return "[]"
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

/**
* swap(arregloPalabras: Array<String>, i: Int, j: Int): Unit
* Método que intercambia dos palabras en un arreglo de palabras.
* @param arregloPalabras: Array<String> -> El arreglo de palabras en el que se van a intercambiar las palabras.
* @param i: Int -> El índice de la primera palabra.
* @param j: Int -> El índice de la segunda palabra.
* Precondición: Los índices son válidos.
* Postcondición: Se intercambian las palabras en el arreglo de palabras.
*/
fun swap(arregloPalabras: Array<String>, i: Int, j: Int) {
    var temp = arregloPalabras[i]
    arregloPalabras[i] = arregloPalabras[j]
    arregloPalabras[j] = temp
}

/**
* partitionLex(arregloPalabras: Array<String>, low: Int, high: Int): Int
* Método que crea una partición en un arreglo de palabras.
* @param arregloPalabras: Array<String> -> El arreglo de palabras que se va a particionar.
* @param low: Int -> El índice más bajo de la sección del arreglo que se va a particionar.
* @param high: Int -> El índice más alto de la sección del arreglo que se va a particionar.
* Precondición: Los índices son válidos.
* Postcondición: Se devuelve el nuevo índice del pivote.
*/
fun partitionLex(arregloPalabras: Array<String>, low: Int, high: Int): Int {
    var pivot = arregloPalabras[high]
    var i = low - 1
    for (j in low until high) {
        if (arregloPalabras[j].compareTo(pivot) <= 0) {
            i++
            swap(arregloPalabras, i, j)
        }
    }
    swap(arregloPalabras, i + 1, high)
    return i + 1
}

/**
* quicksortLex(arregloPalabras: Array<String>, low: Int, high: Int): Unit
* Método que ordena lexicográficamente un arreglo de palabras desde low hasta high usando el algoritmo quicksort.
* @param arregloPalabras: Array<String> -> El arreglo de palabras que se va a ordenar.
* @param low: Int -> El índice más bajo de la sección del arreglo que se va a ordenar.
* @param high: Int -> El índice más alto de la sección del arreglo que se va a ordenar.
* Precondición: Los índices son válidos.
* Postcondición: Se ordena lexicográficamente la sección del arreglo de palabras.
*/
fun quicksortLex(arregloPalabras: Array<String>, low: Int, high: Int) {
    if (low < high) {
        var p = partitionLex(arregloPalabras, low, high)
        quicksortLex(arregloPalabras, low, p - 1)
        quicksortLex(arregloPalabras, p + 1, high)
    }
}

/**
* quicksortLexicografico(arregloPalabras: Array<String>): Unit
* Método que ordena lexicográficamente un arreglo de palabras usando el algoritmo quicksort.
* @param arregloPalabras: Array<String> -> El arreglo de palabras que se va a ordenar.
* Precondición: true.
* Postcondición: Se ordena lexicográficamente el arreglo de palabras.
*/
fun quicksortLexicografico(arregloPalabras: Array<String>) {
    quicksortLex(arregloPalabras, 0, arregloPalabras.size - 1)
}
