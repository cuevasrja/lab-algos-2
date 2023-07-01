// Laboratorio de la semana 8 de Algoritmos y Estructuras de Datos II (CI-2692).
// Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

class CuckooHashTableEntry(val key: Int, var value: Int){
    // prev: CuckooHashTableEntry? -> El nodo anterior al nodo actual
    // next: CuckooHashTableEntry? -> El nodo siguiente al nodo actual
    var prev: CuckooHashTableEntry? = null
    var next: CuckooHashTableEntry? = null

    // Metodos de la clase CuckooHashTableEntry
    // cambiarNext(nodo: CuckooHashTableEntry?): Unit -> Cambia el nodo siguiente al nodo actual
    fun cambiarNext(nodo: CuckooHashTableEntry?): Unit {
        this.next = nodo
    }

    // cambiarPrev(nodo: CuckooHashTableEntry?): Unit -> Cambia el nodo anterior al nodo actual
    fun cambiarPrev(nodo: CuckooHashTableEntry?): Unit {
        this.prev = nodo
    }

    // obtenerClave(): Int? -> Funcion que devuelve la clave que contiene el nodo
    fun obtenerClave(): Int? {
        return this.key
    }

    // obtenerValor(): Int? -> Funcion que devuelve el valor que contiene el nodo
    fun obtenerValor(): Int? {
        return this.value
    }

    // override significa que estamos sobreescribiendo el método toString de la clase Any
    // Así, cuando usemos la función println() con la clase CuckooHashTableEntry, se imprimirá la clave y el valor
    override fun toString(): String {
        return "${this.key} -> ${this.value}"
    }
}