// Clase de Tipos Abstractos de Datos (TAD) de Pila

class Pila(val n: Int){
    // init: Bloque de codigo que se ejecuta al crear un objeto de la clase
    init{
        // Verificamos que el maximo de la pila sea mayor a 0
        if(n <= 0){
            throw IllegalArgumentException("El maximo de la pila debe ser mayor a 0")
        }
        println("Se creo una pila con maximo $n")
    }
    
    // Declaramos las variables que vamos a usar en la clase
    // n: Int -> El maximo de elementos que puede tener la pila
    // size: Int -> El numero de elementos que tiene la pila
    private var size = 0
    // fun getSize(): Int -> Funcion que devuelve el numero de elementos que tiene la pila
    fun getSize() : Int{
        return size
    }

    // head: Nodo? -> El nodo que esta en la cabeza de la pila
    // tail: Nodo? -> El nodo que esta en la cola de la pila
    private var head: Nodo? = null
    private var tail: Nodo? = null

    // fun estaVacia(): Boolean -> Funcion que devuelve si la pila esta vacia
    fun estaVacia() : Boolean{
        return size == 0
    }

    // fun empilar(dato: Int): Unit -> Funcion que agrega un elemento a la pila
    fun empilar(dato: Int){
        // Verificamos que la pila no este llena
        if(size == n){
            throw IllegalStateException("La pila esta llena")
        }
        // Creamos un nuevo nodo con el dato que nos pasan
        val nuevoNodo = Nodo(dato, null)
        // Verificamos si la pila esta vacia
        if(estaVacia()){
            // Si esta vacia, el nuevo nodo es la cabeza y la cola
            head = nuevoNodo
            tail = nuevoNodo
        }
        // Si no esta vacia, el nuevo nodo es la cola y el nodo anterior a la cola es el nodo que era la cola
        else{
            nuevoNodo.cambiarPrev(tail!!)
            tail!!.cambiarNext(nuevoNodo)
            tail = nuevoNodo
        }
        // Aumentamos el numero de elementos de la pila
        size++
    }

    // fun desempilar(): Unit -> Funcion que elimina un elemento de la pila
    fun desempilar(){
        // Verificamos que la pila no este vacia
        if(estaVacia()){
            throw IllegalStateException("La pila esta vacia")
        }
        // Si tiene al menos un elemento, el nodo anterior a la cola es la nueva cola
        tail = tail!!.prev
        // Reducimos el numero de elementos de la pila
        size--
    }

    // fun tope(): Int -> Funcion que devuelve el elemento que esta en la cabeza de la pila
    fun tope(): Int{
        // Verificamos que la pila no este vacia
        if(estaVacia()){
            throw IllegalStateException("La pila esta vacia")
        }
        // Devolvemos el dato del nodo que esta en la cabeza
        return tail!!.dato!!
    }

    // fun toString(): String -> Funcion que devuelve una representacion en String de la pila
    override fun toString(): String{
        var nodoActual = head
        var string = "["
        for(i in 0 until n){
            if(nodoActual == null){
                string += " "
            }else{
                string += nodoActual.toString()
                nodoActual = nodoActual.next
            }
            if(i < n - 1){
                string += ", "
            }
        }
        string += "]"
        return string
    }
}