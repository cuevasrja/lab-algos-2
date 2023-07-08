/*
 * Laboratorio semana 9 de Algoritmos y Estructuras de Datos II (CI-2692).
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 *
 * ArbolDeDecision.kt -> Este archivo contiene la clase que representa un arbol de decision binario para la decodificacion de codigo morse.
 */

/**
 * Clase ArbolDeDecision -> Representa un arbol de decision binario para la decodificacion de codigo morse.
 * @property raiz: Nodo que representa la raiz del arbol de decision binario.
 */
public class ArbolDeDecision() {
    // Atributos de la clase ArbolDeDecision.

    // raiz: Nodo -> Nodo que representa la raiz del arbol de decision binario.
    var raiz = Nodo(' ', "")

    // Métodos de la clase ArbolDeDecision.

    /**
     * agregar(nuevo: Nodo)
     * Método que agrega un nodo al arbol de decision binario.
     * @param nuevo: Nodo que se desea agregar al arbol de decision binario.
     * Precondicion: El nodo que se desea agregar no existe en el arbol de decision binario.
     * Postcondicion: El nodo se agrega al arbol de decision binario.
     */
    fun agregar(nuevo: Nodo) {
        // Se recorre el arbol de decision hasta encontrar el lugar donde se desea agregar el nodo.
        var x: Nodo? = raiz
        var y: Nodo? = null
        var i = 0
        val codigoNuevo = nuevo.codigo
        while (x != null && i < codigoNuevo.length) {
            // Se guarda el nodo padre del nodo que se desea agregar.
            y = x
            // Si el codigo del nodo que se desea agregar es un punto, se recorre el arbol de decision por la rama izquierda.
            if (codigoNuevo[i] == '.') {
                x = x.izq
            }
            // En caso contrario, se recorre el arbol de decision por la rama derecha.
            else {
                x = x.der
            }
            i++
        }
        // Si el nodo padre del nodo que se desea agregar es la raiz o el nodo que se desea agregar es nulo, entonces el nodo que se desea agregar es la raiz.
        if (y == null) {
            raiz = nuevo
        }
        // En caso contrario, se agrega el nodo al arbol de decision.
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
     * buscar(codigo: String) -> Busca un nodo en el arbol de decision binario.
     * @param codigo: Codigo morse del nodo que se desea buscar en el arbol de decision binario.
     * @return El valor del nodo que se desea buscar en el arbol de decision binario.
     * Precondicion: El codigo morse del nodo que se desea buscar en el arbol de decision binario no es nulo.
     * Postcondicion: Se retorna el valor del nodo que se desea buscar en el arbol de decision binario.
     */
    fun buscar(codigo: String): Char? {
        var x: Nodo? = this.raiz
        var y: Nodo?
        var i = 0

        // Se recorre el arbol de decision hasta encontrar el nodo que se desea buscar.
        while (x != null && i < codigo.length) {
            // Si el siguiente caracter del codigo es un punto, se recorre el arbol de decision por la rama izquierda.
            // En caso contrario, se recorre el arbol de decision por la rama derecha.
            if (codigo[i] == '.') x = x.izq else x = x.der
            i++
        }

        // Se guarda el resultado de la busqueda.
        y = x

        // Se retorna el valor del nodo que se desea buscar en el arbol de decision.
        if (y == this.raiz || y == null || codigo.length != y.codigo.length) {
            // Se retorna null si el nodo que se desea buscar no existe en el arbol de decision.
            return null
        } else {
            // Se retorna el valor del nodo que se desea buscar en el arbol de decision.
            return y.valor
        }
    }

    /**
     * toString() -> Retorna una representacion en String del arbol de decision binario.
     * @return Una representacion en String del arbol de decision binario.
     * Precondicion: El arbol de decision binario no es nulo.
     * Postcondicion: Se retorna una representacion en String del arbol de decision binario.
     */
    override fun toString(): String {
        var str = "ArbolDeDecision(raiz"
        if (raiz.izq != null) {
            str += ", ${raiz.izq}"
        }
        if (raiz.der != null) {
            str += ", ${raiz.der}"
        }
        return str + ")"
    }
}

/**
* Clase Nodo -> Representa un nodo del arbol de decision binario para la decodificacion de codigo morse.
* @param valor: Char -> Valor del nodo.
* @param codigo: String -> Codigo del nodo.
* @property izq: Nodo? -> Hijo izquierdo del nodo.
* @property der: Nodo? -> Hijo derecho del nodo.
* @property padre: Nodo? -> Padre del nodo.
*/
public class Nodo(val valor: Char, val codigo: String) {
    // Atributos de la clase Nodo.

    // izq: Nodo? -> Hijo izquierdo del nodo.
    var izq: Nodo? = null

    // der: Nodo? -> Hijo derecho del nodo.
    var der: Nodo? = null

    // padre: Nodo? -> Padre del nodo.
    var padre: Nodo? = null

    // Métodos de la clase Nodo.

    /**
     * toString() -> Retorna una representacion en String del Nodo.
     * @return Una representacion en String del Nodo.
     * Precondicion: El nodo no es nulo.
     * Postcondicion: Se retorna una representacion en String del Nodo.
     */
    override fun toString(): String {
        var str = "Nodo($valor"
        if (izq != null) {
            str += ", $izq"
        }
        if (der != null) {
            str += ", $der"
        }
        return str + ")"
    }
}
