/*
 * Laboratorio de la semana 8 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 */

/**
 * Clase CucoHashTableEntry, que representa un nodo de la tabla de hash con resolución de colisiones por el método de Cuckoo Hashing
 * @property clave: Int? -> Clave que contiene el nodo
 * @property valor: String? -> Valor que contiene el nodo
 */
class CuckooHashTableEntry(var clave: Int?, var valor: String?) {
    // Metodos de la clase CuckooHashTableEntry

    // obtenerClave(): Int? -> Funcion que devuelve la clave que contiene el nodo
    fun obtenerClave(): Int? {
        return this.clave
    }

    // obtenerValor(): Int? -> Funcion que devuelve el valor que contiene el nodo
    fun obtenerValor(): String? {
        return this.valor
    }

    // cambiarClave(clave: Int?): Unit -> Funcion que cambia la clave que contiene el nodo
    fun cambiarClave(clave: Int?) {
        this.clave = clave
    }

    // cambiarValor(valor: String?): Unit -> Funcion que cambia el valor que contiene el nodo
    fun cambiarValor(valor: String?) {
        this.valor = valor
    }

    // esVacio(): Boolean -> Funcion que devuelve true si el nodo no contiene ni clave ni valor
    fun esVacio(): Boolean {
        return this.clave == null && this.valor == null
    }

    // override significa que estamos sobreescribiendo el método toString de la clase Any
    // Así, cuando usemos la función println() con la clase CuckooHashTableEntry, se imprimirá la clave y el valor
    override fun toString(): String {
        if (this.esVacio()) {
            return "[]"
        } else {
            return "[${this.clave} -> ${this.valor}]"
        }
    }
}
