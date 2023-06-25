// Laboratorio de la semana 7 de Algoritmos y Estructuras de Datos II (CI-2692).
// Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

fun main() {
    val prueba = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    val lista: ListaCircular = crearListaCircular()
    for (i in prueba) {
        lista.agregarAlFinal(i)
    }
    println("Lista original: $lista")

    val pila: Pila = crearPila(prueba.size)
    for (i in prueba) {
        pila.empilar(i)
    }
    println("Pila original: $pila")

    val cola: Cola = crearCola(prueba.size)
    for (i in prueba) {
        cola.encolar(i)
    }
    println("Cola original: $cola")
}
