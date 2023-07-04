/*
 * Laboratorio de la semana 8 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 */

/**
 * Creacion de la clase HashTableEntry a usar en la implementacion de la tabla de hash
 * @param clave: Int -> La clave del elemento a insertar en la tabla de hash
 * @param valor: String -> El valor del elemento a insertar en la tabla de hash
 * @property prev: HashTableEntry? -> El nodo anterior al nodo actual
 * @property next: HashTableEntry? -> El nodo siguiente al nodo actual
 */
class HashTableEntry(val clave: Int?, val valor: String?) {
    // prev: HashTableEntry? -> El nodo anterior al nodo actual
    // next: HashTableEntry? -> El nodo siguiente al nodo actual
    var prev: HashTableEntry? = null
    var next: HashTableEntry? = null

    // Metodos de la clase HashTableEntry
    // cambiarNext(nodo: HashTableEntry?): Unit -> Cambia el nodo siguiente al nodo actual
    fun cambiarNext(nodo: HashTableEntry?) {
        this.next = nodo
    }

    // cambiarPrev(nodo: HashTableEntry?): Unit -> Cambia el nodo anterior al nodo actual
    fun cambiarPrev(nodo: HashTableEntry?) {
        this.prev = nodo
    }

    // obtenerClave(): Int? -> Funcion que devuelve la clave que contiene el nodo
    fun obtenerClave(): Int? {
        return this.clave
    }

    // obtenerValor(): String? -> Funcion que devuelve el valor que contiene el nodo
    fun obtenerValor(): String? {
        return this.valor
    }

    // override significa que estamos sobreescribiendo el método toString de la clase Any
    // Así, cuando usemos la función println() con la clase HashTableEntry, se imprimirá la clave y el valor
    override fun toString(): String {
        return "${this.clave} -> ${this.valor}"
    }
}
