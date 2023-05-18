/*
Laboratorio de la semana 1 de Algoritmos y Estructuras de Datos II (CI-2692)
Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175)
*/

/**
* uso: swap(A, i, j)
* Precondición: A es un arreglo de enteros, i y j son índices válidos en A
* Postcondición: intercambia los elementos en las posiciones i y j del arreglo A
*/
fun swap(A: Array<Int>, i: Int, j: Int): Unit {
    val aux = A[i]
    A[i] = A[j]
    A[j] = aux
}

/**
* uso: bubbleSort(A)
* Precondición: A es un arreglo de enteros
+ Postcondición: ordena los elementos de A de menor a mayor
* Descripcióm: compara dos elementos adyacentes y los intercambia si están
* en el orden equivocado
*/
fun bubbleSort(A: Array<Int>): Unit {
    for (i in 0 until A.size - 1) {
        for (j in (A.size - 1) downTo (i + 1)) {
            if (A[j] < A[j - 1]) {
                swap(A, j, j - 1)
            }
        }
    }
}

/**
* uso: insertionSort(A)
* Precondición: A es un arreglo de enteros
* Postcondición: ordena los elementos de A de menor a mayor
* Descripción: inserta un elemento en la posición correcta de lo se haya ordenado,
* empujando los elementos mayores a la derecha
*/
fun insertionSort(A: Array<Int>): Unit {
    for (i in 1 until A.size) {
        var j = i
        while (j > 0 && A[j] < A[j - 1]) {
            swap(A, j, j - 1)
            j -= 1
        }
    }
}

/**
* uso: selectionSort(A)
* Precondición: A es un arreglo de enteros
* Postcondición: ordena los elementos de A de menor a mayor
* Descripción: busca el elemento más pequeño de la parte no ordenada y lo intercambia
* con el primer elemento de la parte no ordenada
*/
fun selectionSort(A: Array<Int>): Unit {
    for (i in 0 until A.size - 1) {
        var min = i
        for (j in (i + 1) until A.size) {
            if (A[j] < A[min]) {
                min = j
            }
        }
        swap(A, i, min)
    }
}

/**
* uso: shellSort(A)
* Precondición: A es un arreglo de enteros
* Postcondición: ordena los elementos de A de menor a mayor
* Descripción: primero ordena elementos que estén muy separados entre sí, y
* posteriormente reduce la separación entre los elementos a ordenar
*/
fun shellSort(A: Array<Int>): Unit {
    var incr: Int = A.size / 2
    while (incr > 0) {
        for (i in incr until A.size) {
            var j: Int = i - incr
            while (j > -1) {
                if (A[j] > A[j + incr]) {
                    swap(A, j, j + incr)
                    j -= incr
                } else {
                    j = -1
                }
            }
        }
        incr /= 2
    }
}
