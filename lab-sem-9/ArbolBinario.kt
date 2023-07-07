/*
 * Laboratorio semana 9 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 *
 * ArbolBinario.kt -> Este archivo contiene el arbol binario de busqueda del alfabeto en morse.
 */

class ArbolBinario() {
    var raiz: Nodo? = null

    fun agregar(nuevo: Nodo) {
        if (raiz == null) {
            raiz = nuevo
        } else {
            agregar(nuevo, raiz!!)
        }
    }

    fun agregar(nuevo: Nodo, nodo: Nodo) {
        val nuevoValor = codigoToInt(nuevo.getCodigo())
        val nodoValor = codigoToInt(nodo.getCodigo())
        if (nuevoValor < nodoValor) {
            if (nodo.izq == null) {
                nodo.izq = nuevo
            } else {
                agregar(nuevo, nodo.izq!!)
            }
        } else {
            if (nodo.der == null) {
                nodo.der = nuevo
            } else {
                agregar(nuevo, nodo.der!!)
            }
        }
    }

    fun buscarCodigo(valor: Char): String {
        return busquedaCodigo(valor, raiz!!)
    }

    private fun busquedaCodigo(valor: Char, nodo: Nodo): String {
        if (nodo.valor == valor) {
            return nodo.codigo
        } else {
            if (valor < nodo.valor) {
                return busquedaCodigo(valor, nodo.izq!!)
            } else {
                return busquedaCodigo(valor, nodo.der!!)
            }
        }
    }

    fun buscar(codigo: String): Char {
        return busqueda(codigo, raiz!!)
    }

    private fun busqueda(codigo: String, nodo: Nodo): Char {
        val codigoNodo = nodo.getCodigo()
        val codigoNodoInt = codigoToInt(codigoNodo)
        val codigoInt = codigoToInt(codigo)
        if (codigoNodoInt == codigoInt) {
            return nodo.getValor()
        } else {
            if (codigoInt < codigoNodoInt) {
                return busqueda(codigo, nodo.izq!!)
            } else {
                return busqueda(codigo, nodo.der!!)
            }
        }
    }

    private fun codigoToInt(codigo: String): Int {
        return codigo.replace(".", "0").replace("-", "1").toInt(2)
    }
}

class Nodo(val valor: Char, val codigo: String) {
    var izq: Nodo? = null
    var der: Nodo? = null
    var padre: Nodo? = null

    fun esHoja(): Boolean {
        return izq == null && der == null
    }

    fun esIzquierdo(): Boolean {
        return padre != null && padre!!.izq == this
    }

    fun esDerecho(): Boolean {
        return padre != null && padre!!.der == this
    }

    fun esRaiz(): Boolean {
        return padre == null
    }

    fun esHijo(): Boolean {
        return esIzquierdo() || esDerecho()
    }

    fun esPadre(): Boolean {
        return izq != null || der != null
    }

    fun getCodigo(): String {
        return codigo
    }

    fun getValor(): Char {
        return valor
    }
}
