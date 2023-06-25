/**
* Creacion de la clase Nodo a usar en la creacion de Tipos abstractos de datos tanto de cola como de pila.
* Ademas, se va a usar para crear listas circulares
*/

class Nodo(val dato: Int?, var next: Nodo?){
    // Declaramos las variables que vamos a usar en la clase
    // dato: Int? -> El dato que va a contener el nodo
    // next: Nodo? -> El nodo siguiente al nodo actual
    // prev: Nodo? -> El nodo anterior al nodo actual
    var prev: Nodo? = null

    // Metodos de la clase Nodo
    // Cambiar el nodo siguiente al nodo actual
    fun cambiarNext(nodo: Nodo){
        this.next = nodo
    }

    // Cambiar el nodo anterior al nodo actual
    fun cambiarPrev(nodo: Nodo){
        this.prev = nodo
    }

    // Sobreescribimos el metodo toString para que nos devuelva el dato del nodo
    override fun toString() : String {
        return "${this.dato}"
    }
}