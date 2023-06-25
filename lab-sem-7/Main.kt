// Laboratorio de la semana 7 de Algoritmos y Estructuras de Datos II (CI-2692).
// Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

fun main() {
    // Creamos un arreglo con 10 elementos aleatorios entre 0 y 100
    println("\u001b[36mSe creó un arreglo con 10 elementos aleatorios entre 0 y 100.\u001b[0m")
    val prueba: Array<Int> = Array(4, {0})
    for (i in 0 until prueba.size) {
        prueba[i] = (0..100).random()
    }
    println("Arreglo original: ${prueba.contentToString()}")
    println()

    // Probamos la lista circular
    println("\u001b[36mPrueba de la lista circular:\u001b[0m")
    println("----------------------------------------------------------------")
    val lista: ListaCircular = crearListaCircular()
    println("Se agrega cada elemento del arreglo, usando agregarAlFinal().")
    for (i in prueba) {
        lista.agregarAlFinal(i)
    }
    println("Lista original: \u001b[33m$lista\u001b[0m")
    println("Imprimimos el primer elemento de la lista: \u001b[34m${lista.primerElemento()}\u001b[0m.")
    println("Imprimimos el ultimo elemento de la lista: \u001b[34m${lista.ultimoElemento()}\u001b[0m.")
    println("Imprimimos el elemento en la posicion \u001b[34m2\u001b[0m de la lista: \u001b[34m${lista.buscarPorIndice(2)}\u001b[0m.")
    println("Buscamos un elemento que no existe, como el elemento \u001b[34m-1\u001b[0m: \u001b[34m${lista.buscar(-1)}\u001b[0m.")
    println("Añadimos el elemento 200 al principio de la lista.")
    lista.agregarAlFrente(200)
    println("Lista modificada: \u001b[33m$lista\u001b[0m")
    println("Añadimos el elemento 100 al final de la lista.")
    lista.agregarAlFinal(100)
    println("Lista modificada: \u001b[33m$lista\u001b[0m")
    println("Buscamos la primera aparición del elemento \u001b[34m100\u001b[0m en la lista: \u001b[34m${lista.obtenerIndice(lista.buscar(100)!!.obtenerDato()!!)}\u001b[0m.")
    val elemAleatorio = lista.buscarPorIndice((0 until lista.getSize()).random())
    val posElemAleatorio = lista.obtenerIndice(elemAleatorio!!.obtenerDato()!!)
    println("Obtenemos un elemento aleatorio de la lista: \u001b[34m$elemAleatorio\u001b[0m, que está en la posicion \u001b[34m$posElemAleatorio\u001b[0m.")
    println("Eliminamos la primera aparición del elemento aleatorio de la lista.")
    lista.eliminar(elemAleatorio.obtenerDato()!!)
    println("Lista modificada: \u001b[33m$lista\u001b[0m")
    println("Eliminamos el primer elemento de la lista.")
    lista.eliminarPrimero()
    println("Lista modificada: \u001b[33m$lista\u001b[0m")
    println("Eliminamos el ultimo elemento de la lista.")
    lista.eliminarUltimo()
    println("Lista modificada: \u001b[33m$lista\u001b[0m")
    println()

    // Probamos la pila
    println("\u001b[36mPrueba de la pila:\u001b[0m")
    println("----------------------------------------------------------------")
    val pila: Pila = crearPila(prueba.size)
    println("Se apila cada elemento del arreglo, usando empilar().")
    for (i in prueba) {
        pila.empilar(i)
    }
    println("Pila original: \u001b[33m$pila\u001b[0m")
    println("Imprimimos el tope de la pila: \u001b[34m${pila.tope()}\u001b[0m.")
    println("Agremos el elemento 100 al tope de la pila.")
    pila.empilar(100)
    println("Pila modificada: \u001b[33m$pila\u001b[0m")
    println("Imprimimos el tope de la pila: \u001b[34m${pila.tope()}\u001b[0m.")
    for (i in 0 until prueba.size) {
        println("Desapilamos el tope de la pila.")
        pila.desempilar()
        println("Pila modificada: \u001b[33m$pila\u001b[0m")
        println("Imprimimos el tope de la pila: \u001b[34m${pila.tope()}\u001b[0m.")
    }
    println()

    // Probamos la cola
    println("\u001b[36mPrueba de la cola:\u001b[0m")
    println("----------------------------------------------------------------")
    val cola: Cola = crearCola(prueba.size)
    println("Se encola cada elemento del arreglo, usando encolar().")
    for (i in prueba) {
        cola.encolar(i)
    }
    println("Cola original: \u001b[33m$cola\u001b[0m")
    println("Imprimimos el primero de la cola: \u001b[34m${cola.primero()}\u001b[0m.")
    println("Agregamos el elemento 100 al final de la cola.")
    cola.encolar(100)
    println("Cola modificada: \u001b[33m$cola\u001b[0m")
    println("Imprimimos el primero de la cola: \u001b[34m${cola.primero()}\u001b[0m.")
    for (i in 0 until prueba.size) {
        println("Desencolamos el primero de la cola.")
        cola.desencolar()
        println("Cola modificada: \u001b[33m$cola\u001b[0m")
        println("Imprimimos el primero de la cola: \u001b[34m${cola.primero()}\u001b[0m.")
    }
}
