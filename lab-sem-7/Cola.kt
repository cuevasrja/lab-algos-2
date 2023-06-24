// Clase del TAD de Cola

class Cola(val n: Int){
    init{
        if(n <= 0){
            throw IllegalArgumentException("El maximo de la cola debe ser mayor a 0")
        }
        println("Se creo una cola con maximo $n")
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

    fun encolar(dato: Int){
        if(size == n){
            throw IllegalStateException("La cola esta llena")
        }
        val nuevoNodo = Nodo(dato, null)
        if(estaVacia()){
            head = nuevoNodo
            tail = nuevoNodo
        }else{
            nuevoNodo.cambiarNext(head!!)
            head!!.cambiarPrev(nuevoNodo)
            head = nuevoNodo
        }
        size++
    }
    
    fun desencolar(){
        if(estaVacia()){
            throw IllegalStateException("La cola esta vacia")
        }
        head = head!!.next
        size--
    }

    fun primero() : Int{
        if(estaVacia()){
            throw IllegalStateException("La cola esta vacia")
        }
        return head!!.dato!!
    }

    fun ultimo() : Int{
        if(estaVacia()){
            throw IllegalStateException("La cola esta vacia")
        }
        return tail!!.dato!!
    }

    override fun toString() : String{
        var nodoActual = tail
        var string = "["
        for(i in 0 until n){
            if(nodoActual == null){
                string += " "
            }else{
                string += nodoActual.toString()
                nodoActual = nodoActual.prev
            }
            if(i != n-1){
                string += ", "
            }
        }
        string += "]"
        return string
    }
}