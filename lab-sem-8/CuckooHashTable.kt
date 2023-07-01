// Laboratorio de la semana 8 de Algoritmos y Estructuras de Datos II (CI-2692).
// Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

class CuckooHashTable() {
    // conocidas: Array<Int> -> Arreglo que contiene las claves de los elementos que ya fueron insertados en la tabla de hash
    var conocidas: Array<Int> = Array(7) { 0 }
    // tabla1: Array<CircularList> -> Arreglo que contiene las listas enlazadas que representan la tabla de hash
    var tabla1: Array<CircularList> = Array(7) { CircularList() }
    // tabla2: Array<CircularList> -> Arreglo que contiene las listas enlazadas que representan la tabla de hash
    var tabla2: Array<CircularList> = Array(7) { CircularList() }

    // numElementos: Int -> Número de elementos que hay en la tabla de hash
    var numElementos: Int = 0

    private val A: Double = 0.6180339887

    // Métodos de la clase CuckooHashTable

    // fun hashFunction1(clave: Int): Int {
    //     return clave % this.tabla1.size
    // }

    // fun hashFunction2(clave: Int): Int {
    //     return clave % this.tabla2.size
    // }

    // fun hashingDouble(clave: Int): Int {
    //     return (this.hashFunction1(clave) + this.hashFunction2(clave)*this.A) % this.tabla1.size
    // }
    

}