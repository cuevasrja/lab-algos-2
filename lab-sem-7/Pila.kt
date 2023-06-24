// Clase de Tipos Abstractos de Datos (TAD) de Pila

class Pila(val n: Int){
    init{
        if(n <= 0){
            throw IllegalArgumentException("El maximo de la pila debe ser mayor a 0")
        }
        println("Se creo una pila con maximo $n")
    }
    
    private var size = 0
    fun getSize() : Int{
        return size
    }

    private var head: Nodo? = null
    private var tail: Nodo? = null

    fun estaVacia() : Boolean{
        return size == 0
    }

    fun empilar(dato: Int){
        if(size == n){
            throw IllegalStateException("La pila esta llena")
        }
        val nuevoNodo = Nodo(dato, null)
        if(estaVacia()){
            head = nuevoNodo
            tail = nuevoNodo
        }else{
            nuevoNodo.cambiarPrev(tail!!)
            tail!!.cambiarNext(nuevoNodo)
            tail = nuevoNodo
        }
        size++
    }

    fun desempilar(){
        if(estaVacia()){
            throw IllegalStateException("La pila esta vacia")
        }
        tail = tail!!.prev
        size--
    }

    fun tope(): Int{
        if(estaVacia()){
            throw IllegalStateException("La pila esta vacia")
        }
        return tail!!.dato!!
    }

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