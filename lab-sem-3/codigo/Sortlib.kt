/*
Laboratorio de la semana 2 de Algoritmos y Estructuras de Datos II (CI-2692).
Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
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

/**
* uso: merge(A, B, C)
* Precondición: A y B son arreglos de enteros ordenados de menor a mayor, C es un
* arreglo de enteros de tamaño A.size + B.size
* Postcondición: C es un arreglo de enteros ordenados de menor a mayor que
* contiene todos los elementos de A y B
* Descripción: combina los elementos de A y B en C de manera ordenada
*/
fun merge(A: Array<Int>, B: Array<Int>, C: Array<Int>): Unit {
    var i: Int = 0
    var j: Int = 0
    for (k in 0 until C.size) {
        if (i == A.size) {
            C[k] = B[j]
            j += 1
        } else if (j == B.size) {
            C[k] = A[i]
            i += 1
        } else if (A[i] < B[j]) {
            C[k] = A[i]
            i += 1
        } else {
            C[k] = B[j]
            j += 1
        }
    }
}

/**
* uso: mergesort(A)
* Precondición: A es un arreglo de enteros
* Postcondición: ordena los elementos de A de menor a mayor
* Descripción: divide el arreglo en dos mitades, ordena cada mitad recursivamente
* y luego combina las dos mitades ordenadas
*/
fun mergesortInsertion(A: Array<Int>): Unit {
    if (A.size <= 30) {
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