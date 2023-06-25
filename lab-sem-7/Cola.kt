// Laboratorio de la semana 7 de Algoritmos y Estructuras de Datos II (CI-2692).
// Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

/**
* Clase del TAD de Cola
* @param n: Int -> El maximo de elementos que puede tener la cola
* @property size: Int -> El numero de elementos que tiene la cola
* @property head: Nodo? -> El nodo que esta en la cabeza de la cola
* @property tail: Nodo? -> El nodo que esta en la cola de la cola
*/
class Cola(val n: Int) {
    // init: Bloque de codigo que se ejecuta al crear un objeto de la clase
    init {
        // Verificamos que el maximo de la cola sea mayor a 0
        if (n <= 0) {
            throw IllegalArgumentException("El maximo de la cola debe ser mayor a 0")
        }
        println("Se creo una cola con maximo $n")
    }

    // Declaramos las variables que vamos a usar en la clase
    // n: Int -> El maximo de elementos que puede tener la cola
    // size: Int -> El numero de elementos que tiene la cola
    private var size = 0
    // getSize(): Int -> Función que devuelve el numero de elementos que tiene la cola
    fun getSize(): Int {
        return size
    }

    // head: Nodo? -> El nodo que esta en la cabeza de la cola
    // tail: Nodo? -> El nodo que esta en la cola de la cola
    private var head: Nodo? = null
    private var tail: Nodo? = null

    // estaVacia(): Boolean -> Función que devuelve si la cola esta vacia
    fun estaVacia(): Boolean {
        return size == 0
    }

    // encolar(dato: Int): Unit -> Función que agrega un elemento a la cola
    fun encolar(dato: Int): Unit {
        // Verificamos que la cola no este llena
        if (size == n) {
            throw IllegalStateException("La cola esta llena")
        }
        // Creamos un nuevo nodo con el dato que nos pasan
        val nuevoNodo = Nodo(dato, null)
        // Verificamos si la cola esta vacia
        if (estaVacia()) {
            head = nuevoNodo
            tail = nuevoNodo
        }
        // Si no esta vacia, el nuevo nodo es la cabeza y el nodo siguiente a la cabeza es el nodo que era la cabeza
        else {
            nuevoNodo.cambiarNext(head!!)
            head!!.cambiarPrev(nuevoNodo)
            head = nuevoNodo
        }
        // Aumentamos el numero de elementos de la cola
        size++
    }

    // desencolar(): Unit -> Función que elimina un elemento de la cola
    fun desencolar(): Unit {
        // Verificamos que la cola no este vacia
        if (estaVacia()) {
            throw IllegalStateException("La cola esta vacia")
        }
        // Si la cola tiene al menos un elemento, el nodo anterior a la cola es la nueva cola
        head = head!!.next
        // Reducimos el numero de elementos de la cola
        size--
    }

    // primero(): Int -> Función que devuelve el primer elemento de la cola
    fun primero(): Int {
        // Verificamos que la cola no este vacia
        if (estaVacia()) {
            throw IllegalStateException("La cola esta vacia")
        }
        // Devolvemos el dato del nodo que esta en la cola
        return head!!.dato!!
    }

    // ultimo(): Int -> Función que devuelve el ultimo elemento de la cola
    fun ultimo(): Int {
        // Verificamos que la cola no este vacia
        if (estaVacia()) {
            throw IllegalStateException("La cola esta vacia")
        }
        // Devolvemos el dato del nodo que esta en la cola
        return tail!!.dato!!
    }

    // toString(): String -> Función que devuelve una representacion en String de la cola
    // override significa que estamos sobreescribiendo el método toString de la clase Any
    // Así, cuando usemos la función println() con una cola, se imprimirá la representacion en String de la cola
    override fun toString(): String {
        var nodoActual = tail
        var string = "["
        for (i in 0 until n) {
            if (nodoActual == null) {
                string += " "
            }
            else {
                string += nodoActual.toString()
                nodoActual = nodoActual.prev
            }
            if (i != n-1) {
                string += ", "
            }
        }
        string += "]"
        return string
    }
}
