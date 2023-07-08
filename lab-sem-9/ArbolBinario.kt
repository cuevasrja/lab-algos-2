/*
 * Laboratorio semana 9 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 *
 * ArbolBinario.kt -> Este archivo contiene el arbol binario de busqueda del alfabeto en morse.
 */

class ArbolBinario() {
    var raiz = Nodo(' ', "")

    /**
    * agregar(nuevo: Nodo) -> Agrega un nuevo nodo al arbol binario de busqueda.
    * @param nuevo: Nodo que se desea agregar al arbol binario de busqueda.
    * Precondicion: El nodo que se desea agregar no existe en el arbol binario de busqueda.
    * Postcondicion: El nodo se agrega al arbol binario de busqueda.
    */
    // ! Tiene un problema con los caracteres que no existen en el alfabeto en morse. Los convierte a uno de los caracteres que si existen en el alfabeto en morse.
    // TODO: Resolver el problema con los caracteres que no existen en el alfabeto en morse.
    fun agregar(nuevo: Nodo) {
        // Se recorre el arbol binario de busqueda hasta encontrar el lugar donde se debe agregar el nuevo nodo.
        var x: Nodo? = raiz
        var y: Nodo? = null
        var i = 0
        val codigoNuevo = nuevo.codigo
        while (x != null && i < codigoNuevo.length) {
            // Se guarda el nodo padre del nodo que se desea agregar.
            y = x
            // Si el codigo del nodo que se desea agregar es un punto, se recorre el arbol binario de busqueda por la rama izquierda.
            if (codigoNuevo[i] == '.') {
                x = x.izq
            }
            // En caso contrario, se recorre el arbol binario de busqueda por la rama derecha. 
            else {
                x = x.der
            }
            i++
        }
        // Si el nodo padre del nodo que se desea agregar es la raiz o el nodo que se desea agregar es nulo, entonces el nodo que se desea agregar es la raiz.
        if (y == null) {
            raiz = nuevo
        } 
        // En caso contrario, se agrega el nodo que se desea agregar al arbol binario de busqueda.
        else {
            // Se guarda el nodo padre del nodo que se desea agregar.
            nuevo.padre = y
            // Si el codigo del nodo que se desea agregar es un punto, entonces el nodo que se desea agregar es el hijo izquierdo del nodo padre.
            if (codigoNuevo[i - 1] == '.') {
                y.izq = nuevo
            } 
            // En caso contrario, el nodo que se desea agregar es el hijo derecho del nodo padre.
            else {
                y.der = nuevo
            }
        }
    }

    /**
    * buscar(codigo: String) -> Busca un nodo en el arbol binario de busqueda.
    * @param codigo: Codigo del nodo que se desea buscar en el arbol binario de busqueda.
    * @return El valor del nodo que se desea buscar en el arbol binario de busqueda.
    * Precondicion: El codigo del nodo que se desea buscar en el arbol binario de busqueda es valido.
    * Postcondicion: Se retorna el valor del nodo que se desea buscar en el arbol binario de busqueda.
    */
    fun buscar(codigo: String): Char? {
        var x: Nodo? = raiz
        var y: Nodo? = null
        var i = 0
        while (x != null && i < codigo.length) {
            y = x
            if (codigo[i] == '.') {
                x = x.izq
            } else {
                x = x.der
            }
            i++
        }
        if (x != null) y = x
        return if (y == raiz || y == null) {
            null
        } else {
            y.valor
        }
    }

    /**
    * toString() -> Retorna una representacion en cadena del arbol binario de busqueda.
    * @return Una representacion en cadena del arbol binario de busqueda.
    * Precondicion: El arbol binario de busqueda no es nulo.
    * Postcondicion: Se retorna una representacion en cadena del arbol binario de busqueda.
    */
    override fun toString(): String {
        var str = "ArbolBinario(raiz"
        if (raiz.izq != null) {
            str += ", ${raiz.izq.toString()}"
        }
        if (raiz.der != null) {
            str += ", ${raiz.der.toString()}"
        }
        return str + ")"
    }
}

/**
* Clase Nodo -> Representa un nodo del arbol binario de busqueda.
* @param valor: Valor del nodo.
* @param codigo: Codigo del nodo.
* @param izq: Hijo izquierdo del nodo.
* @param der: Hijo derecho del nodo.
* @param padre: Padre del nodo.
*/
class Nodo(val valor: Char, val codigo: String) {
    var izq: Nodo? = null
    var der: Nodo? = null
    var padre: Nodo? = null

    /**
    * toString() -> Retorna una representacion en cadena del nodo.
    * @return Una representacion en cadena del nodo.
    * Precondicion: El nodo no es nulo.
    * Postcondicion: Se retorna una representacion en cadena del nodo.
    */
    override fun toString(): String {
        var str = "Nodo($valor"
        if (izq != null) {
            str += ", ${izq.toString()}"
        }
        if (der != null) {
            str += ", ${der.toString()}"
        }
        return str + ")"
    }
}
