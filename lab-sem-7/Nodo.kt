/**
* Creacion de la clase Nodo a usar en la creacion de Tipos abstractos de datos tanto de cola como de pila.
* Ademas, se va a usar para crear listas circulares
*/

class Nodo(val dato: Int?, var next: Nodo?){
    var prev: Nodo? = null

    fun cambiarNext(nodo: Nodo){
        this.next = nodo
    }

    fun cambiarPrev(nodo: Nodo){
        this.prev = nodo
    }

    override fun toString() : String {
        return "${this.dato}"
    }
}