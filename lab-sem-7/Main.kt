fun main(args: Array<String>) {
    val prueba = listOf(5, 1, 7, 2, 8)

    val lista = ListaCircular()
    for (i in prueba) {
        lista.agregar(i)
    }
    println("Lista original: $lista")

    val pila = Pila(prueba.size)
    for (i in prueba) {
        pila.empilar(i)
    }
    println("Pila original: $pila")

    val cola = Cola(prueba.size)
    for (i in prueba) {
        cola.encolar(i)
    }
    println("Cola original: $cola")
}