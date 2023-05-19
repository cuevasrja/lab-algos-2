/*
Laboratorio de la semana 2 de Algoritmos y Estructuras de Datos II (CI-2692).
Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
*/

/**
* uso: mergesortInsertionPrueba(A, n)
* Precondición: A es un arreglo de enteros y n es un entero positivo
* Postcondición: A está ordenado de menor a mayor
* Descripción: divide el arreglo en dos mitades, ordena cada mitad recursivamente
* y luego combina las dos mitades ordenadas. Si el tamaño del arreglo es menor o igual
* a n, se usa insertionSort.
*/
fun mergesortInsertionPrueba(A: Array<Int>, n: Int): Unit {
    if (A.size <= n) {
        insertionSort(A)
    } else {
        val B: Array<Int> = Array(A.size / 2, {0})
        val C: Array<Int> = Array(A.size - B.size, {0})
        for (i in 0 until B.size) {
            B[i] = A[i]
        }
        for (i in 0 until C.size) {
            C[i] = A[i + B.size]
        }
        mergesortInsertion(B)
        mergesortInsertion(C)
        merge(B, C, A)
    }
}