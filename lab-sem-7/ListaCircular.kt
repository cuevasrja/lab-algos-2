// Laboratorio de la semana 7 de Algoritmos y Estructuras de Datos II (CI-2692).
// Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

/**
* Clase de lista circular, escrita como una secuencia con sentinela
* @property sentinel: Nodo -> El nodo sentinela de la lista
* @property size: Int -> El tamaño de la lista
*/
class ListaCircular() {
    // Declaramos las variables que vamos a usar en la clase
    // sentinel: Nodo -> El nodo sentinela de la lista
    var sentinel = Nodo(null, null)

    // size: Int -> El tamaño de la lista
    private var size: Int = 0

    // Metodos de la clase ListaCircular
    // getSize(): Int -> Devuelve el tamaño de la lista
    fun getSize(): Int {
        return size
    }

    // Metodo init, que se ejecuta al crear un objeto de la clase ListaCircular
    init {
        // Cambiamos el nodo siguiente y anterior del nodo sentinela para que apunte a si mismo
        sentinel.cambiarNext(sentinel)
        sentinel.cambiarPrev(sentinel)
    }

    // estaVacia(): Boolean -> Devuelve true si la lista esta vacia, false en caso contrario
    fun estaVacia(): Boolean {
        return size == 0
    }

    // agregarAlFrente(dato: Int): Unit -> Agrega un nodo con el dato dado al principio de la lista
    fun agregarAlFrente(dato: Int): Unit {
        // Creamos el nuevo nodo
        val nuevoNodo = Nodo(dato, sentinel.next)
        nuevoNodo.cambiarPrev(sentinel) // Cambiamos el nodo anterior del nuevo nodo para que apunte al nodo sentinela
        sentinel.next!!.cambiarPrev(nuevoNodo) // Cambiamos el nodo anterior del nodo siguiente al nuevo nodo para que apunte al nuevo nodo
        sentinel.cambiarNext(nuevoNodo) // Cambiamos el nodo siguiente del nodo sentinela para que apunte al nuevo nodo
        size++ // Aumentamos el tamaño de la lista
    }

    // agregarAlFinal(dato: Int): Unit -> Agrega un nodo con el dato dado al final de la lista
    fun agregarAlFinal(dato: Int): Unit {
        // Creamos el nuevo nodo
        val nuevoNodo = Nodo(dato, sentinel)
        nuevoNodo.cambiarPrev(sentinel.prev!!) // Cambiamos el nodo anterior del nuevo nodo para que apunte al nodo anterior al nodo sentinela
        sentinel.prev!!.cambiarNext(nuevoNodo) // Cambiamos el nodo siguiente del nodo anterior al nodo sentinela para que apunte al nuevo nodo
        sentinel.cambiarPrev(nuevoNodo) // Cambiamos el nodo anterior del nodo sentinela para que apunte al nuevo nodo
        size++ // Aumentamos el tamaño de la lista
    }

    // eliminar(dato: Int): Unit -> Elimina el primer nodo con el dato dado
    fun eliminar(dato: Int): Unit {
        // Si la lista esta vacia, no hacemos nada
        if (estaVacia()) {
            println("La lista esta vacia")
            return
        }
        // Declaramos el nodo actual como el nodo siguiente al nodo sentinela
        var nodoActual = sentinel.next
        // Recorremos la lista hasta que el nodo actual sea el nodo sentinela
        while (nodoActual != sentinel) {
            // Si el dato del nodo actual es igual al dato que queremos eliminar, cambiamos el nodo siguiente y anterior del nodo anterior y siguiente al nodo actual
            if (nodoActual!!.dato == dato) {
                nodoActual.prev!!.cambiarNext(nodoActual.next!!)
                nodoActual.next!!.cambiarPrev(nodoActual.prev!!)
                size--
                return
            }
            // Si no, cambiamos el nodo actual al nodo siguiente al nodo actual
            nodoActual = nodoActual.next
        }
    }

    // eliminarPrimero(): Unit -> Elimina el primer nodo de la lista
    fun eliminarPrimero(): Unit {
        // Si la lista esta vacia, no hacemos nada
        if (estaVacia()) {
            println("La lista esta vacia")
            return
        }
        // Cambiamos el nodo siguiente y anterior del nodo siguiente al nodo sentinela
        sentinel.next!!.next!!.cambiarPrev(sentinel)
        sentinel.cambiarNext(sentinel.next!!.next!!)
        size--
    }

    // eliminarUltimo(): Unit -> Elimina el ultimo nodo de la lista
    fun eliminarUltimo(): Unit {
        // Si la lista esta vacia, no hacemos nada
        if (estaVacia()) {
            println("La lista esta vacia")
            return
        }
        // Cambiamos el nodo siguiente y anterior del nodo anterior al nodo sentinela
        sentinel.prev!!.prev!!.cambiarNext(sentinel)
        sentinel.cambiarPrev(sentinel.prev!!.prev!!)
        size--
    }

    // buscar(dato: Int): Nodo? -> Devuelve el nodo con el dato dado, o si el dato no se encuentra en la lista, devuelve null
    fun buscar(dato: Int): Nodo? {
        if (estaVacia()) {
            println("La lista esta vacia")
            return null
        }
        var nodoActual = sentinel.next
        while (nodoActual != sentinel) {
            if (nodoActual!!.dato == dato) {
                return nodoActual
            }
            nodoActual = nodoActual.next
        }
        println("El dato no se encuentra en la lista")
        return null
    }

    // buscarPorIndice(indice: Int): Nodo? -> Devuelve el nodo con el indice dado, o si el indice esta fuera de rango, devuelve null
    fun buscarPorIndice(indice: Int): Nodo? {
        // Si la lista esta vacia, o el indice esta fuera de rango, devolvemos un nodo nulo
        if (estaVacia()) {
            println("La lista esta vacia")
            return null
        }
        // Si el indice es mayor o igual al tamaño de la lista, devolvemos un nodo nulo
        if (indice >= size) {
            println("El indice esta fuera de rango")
            return null
        }
        // Declaramos el nodo actual como el nodo siguiente al nodo sentinela
        var nodoActual: Nodo? = sentinel.next
        // Declaramos el indice actual como 0
        var indiceActual = 0
        // Recorremos la lista hasta que el nodo actual sea el nodo sentinela
        while (nodoActual != sentinel) {
            // Si el indice actual es igual al indice dado, devolvemos el nodo actual
            if (indiceActual == indice) {
                return nodoActual
            }
            // Si no, cambiamos el nodo actual al nodo siguiente al nodo actual, y aumentamos el indice actual en 1
            nodoActual = nodoActual!!.next
            indiceActual++
        }
        // Si no se encontro el nodo, devolvemos un nodo nulo
        println("El nodo no se encuentra en la lista")
        return null
    }

    // primeraAparicion(dato: Int): Int -> Devuelve el indice del primer nodo con el dato dado
    fun primeraAparicion(dato: Int): Int {
        // Si la lista esta vacia, devolvemos -1
        if (estaVacia()) {
            println("La lista esta vacia")
            return -1
        }
        // Declaramos el nodo actual como el nodo siguiente al nodo sentinela
        var nodoActual = sentinel.next
        // Declaramos el indice como 0
        var indice = 0
        // Recorremos la lista hasta que el nodo actual sea el nodo sentinela
        while (nodoActual != sentinel) {
            // Si el dato del nodo actual es igual al dato dado, devolvemos el indice
            if (nodoActual!!.dato == dato) {
                return indice
            }
            // Si no, cambiamos el nodo actual al nodo siguiente al nodo actual, y aumentamos el indice en 1
            nodoActual = nodoActual.next
            indice++
        }
        // Si no se encontro el dato, devolvemos -1
        return -1
    }

    // primerElemento(): Int -> Devuelve el primer elemento de la lista
    fun primerElemento(): Int {
        return sentinel.next!!.dato!!
    }

    // ultimoElemento(): Int -> Devuelve el ultimo elemento de la lista
    fun ultimoElemento(): Int {
        return sentinel.prev!!.dato!!
    }

    // todosMenosPrimero(): ListaCircular -> Devuelve una nueva lista con todos los elementos menos el primero
    fun todosMenosPrimero(): ListaCircular {
        // Si la lista esta vacia, devolvemos una lista vacia
        if (estaVacia()) {
            println("La lista esta vacia")
            return ListaCircular()
        }
        // Si la lista solo tiene un elemento, devolvemos una lista vacia
        if (size == 1) {
            println("La lista solo tiene un elemento")
            return ListaCircular()
        }
        // Declaramos una nueva lista
        val nuevaLista = ListaCircular()
        // Declaramos el nodo actual como el segundo nodo de la lista
        var nodoActual = sentinel.next!!.next
        // Recorremos la lista hasta que el nodo actual sea el nodo anterior al nodo sentinela
        while (nodoActual != sentinel) {
            // Agregamos el dato del nodo actual a la nueva lista, y cambiamos el nodo actual al nodo siguiente al nodo actual
            nuevaLista.agregarAlFrente(nodoActual!!.dato!!)
            nodoActual = nodoActual.next
        }
        // Devolvemos la nueva lista
        return nuevaLista
    }

    // todosMenosUltimo(): ListaCircular -> Devuelve una nueva lista con todos los elementos menos el ultimo
    fun todosMenosUltimo(): ListaCircular {
        // Si la lista esta vacia, devolvemos una lista vacia
        if (estaVacia()) {
            println("La lista esta vacia")
            return ListaCircular()
        }
        // Si la lista solo tiene un elemento, devolvemos una lista vacia
        if (size == 1) {
            println("La lista solo tiene un elemento")
            return ListaCircular()
        }
        // Declaramos una nueva lista
        val nuevaLista = ListaCircular()
        // Declaramos el nodo actual como el nodo siguiente al nodo sentinela
        var nodoActual = sentinel.next
        // Recorremos la lista hasta que el nodo actual sea el nodo anterior al nodo sentinela
        while (nodoActual != sentinel.prev) {
            // Agregamos el dato del nodo actual a la nueva lista, y cambiamos el nodo actual al nodo siguiente al nodo actual
            nuevaLista.agregarAlFrente(nodoActual!!.dato!!)
            nodoActual = nodoActual.next
        }
        // Devolvemos la nueva lista
        return nuevaLista
    }

    // concatenarListas(lista: ListaCircular): Unit -> Concatena la lista dada con la lista actual
    fun concatenarListas(lista: ListaCircular): Unit {
        // Si la lista dada esta vacia, no hacemos nada
        if (lista.estaVacia()) {
            println("La lista dada esta vacia")
            return
        }
        // Si la lista actual esta vacia, la igualamos a la lista dada
        if (estaVacia()) {
            this.sentinel = lista.sentinel
            this.size = lista.size
            return
        }
        // Si no, declaramos el nodo actual como el nodo siguiente al nodo sentinela
        var nodoActual = lista.sentinel.next
        // Recorremos la lista dada hasta que el nodo actual sea el nodo sentinela
        while (nodoActual != lista.sentinel) {
            // Agregamos el dato del nodo actual a la lista actual, y cambiamos el nodo actual al nodo siguiente al nodo actual
            agregarAlFrente(nodoActual!!.dato!!)
            nodoActual = nodoActual.next
        }
    }

    // copiarLista(): ListaCircular -> Devuelve una copia de la lista actual
    fun copiarLista() : ListaCircular {
        // Si la lista esta vacia, devolvemos una lista vacia
        if (estaVacia()) {
            println("La lista esta vacia")
            return ListaCircular()
        }
        // Declaramos una nueva lista
        val nuevaLista = ListaCircular()
        // Declaramos el nodo actual como el nodo siguiente al nodo sentinela
        var nodoActual = sentinel.next
        // Recorremos la lista hasta que el nodo actual sea el nodo sentinela
        while (nodoActual != sentinel) {
            // Agregamos el dato del nodo actual a la nueva lista, y cambiamos el nodo actual al nodo siguiente al nodo actual
            nuevaLista.agregarAlFrente(nodoActual!!.dato!!)
            nodoActual = nodoActual.next
        }
        // Devolvemos la nueva lista
        return nuevaLista
    }

    // toString(): String -> Devuelve una representacion en String de la lista
    // override quiere decir que estamos sobreescribiendo el metodo toString de la clase Any
    // Así, cuando llamemos al metodo toString de una lista, se ejecutara este metodo en vez del metodo toString de la clase Any
    override fun toString(): String {
        if (estaVacia()) {
            return "La lista esta vacia"
        }
        var nodoActual = sentinel.next
        var lista = ""
        while (nodoActual != sentinel) {
            lista += "${nodoActual} "
            nodoActual = nodoActual!!.next
        }
        return lista
    }
}

// crearListaCircular(): ListaCircular -> Devuelve una nueva lista circular vacia
fun crearListaCircular(): ListaCircular {
    return ListaCircular()
}
