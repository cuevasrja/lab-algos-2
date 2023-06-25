// Laboratorio de la semana 7 de Algoritmos y Estructuras de Datos II (CI-2692).
// Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

/**
* Clase de Tipos Abstractos de Datos (TAD) de Pila
* @param n: Int -> El número inicial de elementos que puede tener la pila
* @property size: Int -> El numero de elementos que tiene la pila
* @property head: Nodo? -> El nodo que esta en la cabeza de la pila
* @property tail: Nodo? -> El nodo que esta en la cola de la pila
*/
class Pila(val n: Int) {
    // init: Bloque de codigo que se ejecuta al crear un objeto de la clase
    init {
        // Verificamos que el número inicial de elementos que puede tener la pila sea mayor a 0
        if (n <= 0) {
            throw IllegalArgumentException("El número inicial de elementos que puede tener la pila debe ser mayor a 0.")
        }
        println("Se creó una pila con $n elementos iniciales.")
    }

    // Declaramos las variables que vamos a usar en la clase
    // size: Int -> El numero de elementos que tiene la pila
    private var size: Int = 0
    // getSize(): Int -> Funcion que devuelve el numero de elementos que tiene la pila
    fun getSize(): Int {
        return size
    }

    // head: Nodo? -> El nodo que esta en la cabeza de la pila
    // tail: Nodo? -> El nodo que esta en la cola de la pila
    private var head: Nodo? = null
    private var tail: Nodo? = null

    // estaVacia(): Boolean -> Funcion que devuelve si la pila esta vacia
    fun estaVacia() : Boolean {
        return size == 0
    }

    // empilar(dato: Int): Unit -> Funcion que agrega un elemento a la pila
    fun empilar(dato: Int): Unit {
        // Creamos un nuevo nodo con el dato que nos pasan
        val nuevoNodo = Nodo(dato, null)
        // Verificamos si la pila esta vacia
        if (estaVacia()) {
            // Si esta vacia, el nuevo nodo es la cabeza y la cola
            head = nuevoNodo
            tail = nuevoNodo
        }
        // Si no esta vacia, el nuevo nodo es la cola y el nodo anterior a la cola es el nodo que era la cola
        else {
            nuevoNodo.cambiarPrev(tail!!)
            tail!!.cambiarNext(nuevoNodo)
            tail = nuevoNodo
        }
        // Aumentamos el numero de elementos de la pila
        size++
    }

    // desempilar(): Unit -> Funcion que elimina un elemento de la pila
    fun desempilar(): Unit {
        // Verificamos que la pila no este vacia
        if (estaVacia()) {
            throw IllegalStateException("La pila esta vacia")
        }
        // Si tiene al menos un elemento, el nodo anterior a la cola es la nueva cola
        tail = tail!!.prev
        // El nodo siguiente a la nueva cola es null
        tail!!.cambiarNext(null)
        // Reducimos el numero de elementos de la pila
        size--
    }

    // tope(): Int -> Funcion que devuelve el elemento que esta en la cabeza de la pila
    fun tope(): Int {
        // Verificamos que la pila no este vacia
        if (estaVacia()) {
            throw IllegalStateException("La pila esta vacia")
        }
        // Devolvemos el dato del nodo que esta en la cabeza
        return tail!!.dato!!
    }

    // toString(): String -> Funcion que devuelve una representacion en String de la pila
    // override significa que estamos sobreescribiendo el método toString de la clase Any
    // Así, cuando usemos la función println() con una pila, se imprimirá la representacion en String de la pila
    override fun toString(): String {
        var nodoActual = head
        var string = "["
        var sizePila: Int = getSize()
        for (i in 0 until sizePila) {
            if (nodoActual == null) {
                string += " "
            }
            else {
                string += nodoActual.toString()
                nodoActual = nodoActual.next
            }
            if (i < sizePila - 1) {
                string += ", "
            }
        }
        string += "]"
        return string
    }
}

// crearPila(n: Int): Pila -> Funcion que crea una pila con n elementos iniciales
fun crearPila(n: Int): Pila {
    return Pila(n)
}
