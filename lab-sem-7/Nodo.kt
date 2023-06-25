// Laboratorio de la semana 7 de Algoritmos y Estructuras de Datos II (CI-2692).
// Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

/**
* Creacion de la clase Nodo a usar en la implementacion de los TADs cola y pila
* Además, se usa en la implementacion de la lista circular
* @param dato: Int? -> El dato que va a contener el nodo
* @param next: Nodo? -> El nodo siguiente al nodo actual
*/
class Nodo(val dato: Int?, var next: Nodo?) {
    // Declaramos las variables que vamos a usar en la clase
    // dato: Int? -> El dato que va a contener el nodo
    // next: Nodo? -> El nodo siguiente al nodo actual
    // prev: Nodo? -> El nodo anterior al nodo actual
    var prev: Nodo? = null

    // Metodos de la clase Nodo
    // cambiarNext(nodo: Nodo): Unit -> Cambia el nodo siguiente al nodo actual
    fun cambiarNext(nodo: Nodo): Unit {
        this.next = nodo
    }

    // cambiarPrev(nodo: Nodo): Unit -> Cambia el nodo anterior al nodo actual
    fun cambiarPrev(nodo: Nodo): Unit {
        this.prev = nodo
    }

    // toString(): String -> Funcion que devuelve el dato que contiene el nodo
    // override significa que estamos sobreescribiendo el método toString de la clase Any
    // Así, cuando usemos la función println() con un nodo, se imprimirá el dato que contiene el nodo
    override fun toString(): String {
        return "${this.dato}"
    }
}
