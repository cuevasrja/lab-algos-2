// Clase de lista circular, escrita como una secuencia con sentinela
class ListaCircular(){
    val sentinel = Nodo(null, null)
    private var size = 0
    fun getSize() : Int{
        return size
    }

    init{
        sentinel.cambiarNext(sentinel)
        sentinel.cambiarPrev(sentinel)
    }

    fun estaVacia() : Boolean{
        return size == 0
    }

    fun agregar(dato: Int){
        val nuevoNodo = Nodo(dato, sentinel.next)
        nuevoNodo.cambiarPrev(sentinel)
        sentinel.next!!.cambiarPrev(nuevoNodo)
        sentinel.cambiarNext(nuevoNodo)
        size++
    }

    fun eliminar(dato: Int){
        var nodoActual = sentinel.next
        while(nodoActual != sentinel){
            if(nodoActual!!.dato == dato){
                nodoActual.prev!!.cambiarNext(nodoActual.next!!)
                nodoActual.next!!.cambiarPrev(nodoActual.prev!!)
                size--
                return
            }
            nodoActual = nodoActual.next
        }
    }

    fun eliminarPrimero(){
        if(estaVacia()){
            println("La lista esta vacia")
            return
        }
        sentinel.next!!.next!!.cambiarPrev(sentinel)
        sentinel.cambiarNext(sentinel.next!!.next!!)
        size--
    }

    fun eliminarUltimo(){
        if(estaVacia()){
            println("La lista esta vacia")
            return
        }
        sentinel.prev!!.prev!!.cambiarNext(sentinel)
        sentinel.cambiarPrev(sentinel.prev!!.prev!!)
        size--
    }

    fun buscar(dato: Int) : Nodo{
        if(estaVacia()){
            println("La lista esta vacia")
            return Nodo(null, null)
        }
        var nodoActual = sentinel.next
        while(nodoActual != sentinel){
            if(nodoActual!!.dato == dato){
                return nodoActual
            }
            nodoActual = nodoActual.next
        }
        println("El dato no se encuentra en la lista")
        return Nodo(null, null)
    }

    fun buscarPorIndice(indice: Int) : Nodo?{
        if(estaVacia()){
            println("La lista esta vacia")
            return Nodo(null, null)
        }
        if(indice >= size){
            println("El indice esta fuera de rango")
            return Nodo(null, null)
        }
        var nodoActual: Nodo? = sentinel.next
        var indiceActual = 0
        while(nodoActual != sentinel){
            if(indiceActual == indice){
                return nodoActual
            }
            nodoActual = nodoActual!!.next
            indiceActual++
        }
        return Nodo(null, null)
    }

    fun primeraAparicion(dato: Int) : Int{
        if(estaVacia()){
            println("La lista esta vacia")
            return -1
        }
        var nodoActual = sentinel.next
        var indice = 0
        while(nodoActual != sentinel){
            if(nodoActual!!.dato == dato){
                return indice
            }
            nodoActual = nodoActual.next
            indice++
        }
        return -1
    }

    fun primerElemento() : Int{
        return sentinel.next!!.dato!!
    }

    fun ultimoElemento() : Int{
        return sentinel.prev!!.dato!!
    }

    fun todosMenosPrimero() : ListaCircular{
        if(estaVacia()){
            println("La lista esta vacia")
            return ListaCircular()
        }
        if(size == 1){
            println("La lista solo tiene un elemento")
            return ListaCircular()
        }
        val nuevaLista = ListaCircular()
        var nodoActual = sentinel.next!!.next
        while(nodoActual != sentinel){
            nuevaLista.agregar(nodoActual!!.dato!!)
            nodoActual = nodoActual.next
        }
        return nuevaLista
    }

    fun todosMenosUltimo() : ListaCircular{
        if(estaVacia()){
            println("La lista esta vacia")
            return ListaCircular()
        }
        if(size == 1){
            println("La lista solo tiene un elemento")
            return ListaCircular()
        }
        val nuevaLista = ListaCircular()
        var nodoActual = sentinel.next
        while(nodoActual != sentinel.prev){
            nuevaLista.agregar(nodoActual!!.dato!!)
            nodoActual = nodoActual.next
        }
        return nuevaLista
    }

    fun concatenarListas(lista: ListaCircular){
        var nodoActual = lista.sentinel.next
        while(nodoActual != lista.sentinel){
            agregar(nodoActual!!.dato!!)
            nodoActual = nodoActual.next
        }
    }

    fun copiarLista() : ListaCircular{
        val nuevaLista = ListaCircular()
        var nodoActual = sentinel.next
        while(nodoActual != sentinel){
            nuevaLista.agregar(nodoActual!!.dato!!)
            nodoActual = nodoActual.next
        }
        return nuevaLista
    }

    override fun toString() : String{
        if(estaVacia()){
            return "La lista esta vacia"
        }
        var nodoActual = sentinel.prev
        var lista = ""
        while(nodoActual != sentinel){
            lista += "${nodoActual} "
            nodoActual = nodoActual!!.prev
        }
        return lista
    }
}