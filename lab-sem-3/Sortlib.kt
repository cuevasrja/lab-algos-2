/*
Laboratorio de la semana 3 de Algoritmos y Estructuras de Datos II (CI-2692).
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

/**
* uso: parent(i)
* Precondición: i es un entero que representa el índice de un elemento en un arreglo
* Postcondición: devuelve el índice del padre de i
*/
fun parent(i: Int): Int {
    return i / 2
}

/**
* uso: left(i)
* Precondición: i es un entero que representa el índice de un elemento en un arreglo
* Postcondición: devuelve el índice del hijo izquierdo de i
*/
fun left(i: Int): Int {
    return 2 * i
}

/**
* uso: right(i)
* Precondición: i es un entero que representa el índice de un elemento en un arreglo
* Postcondición: devuelve el índice del hijo derecho de i
*/
fun right(i: Int): Int {
    return 2 * i + 1
}

/**
* uso: maxHeapify(A, i, heapSize)
* Precondición: A es un arreglo de enteros, i es un entero que representa el índice
* de un elemento en A, heapSize es un entero que representa el tamaño del heap
* Postcondición: A es un max-heap
* Descripción: asume que los árboles con raíces izquierda y derecha son max-heaps,
* pero que A[i] puede ser menor que sus hijos, intercambia A[i] con su hijo mayor
* y llama a maxHeapify recursivamente en el hijo mayor
*/
fun maxHeapify(A: Array<Int>, i: Int, heapSize: Int): Unit {
    val l: Int = left(i)
    val r: Int = right(i)
    var largest: Int = i
    if (l <= heapSize && A[l] > A[i]) {
        largest = l
    }
    if (r <= heapSize && A[r] > A[largest]) {
        largest = r
    }
    if (largest != i) {
        swap(A, i, largest)
        maxHeapify(A, largest, heapSize)
    }
}

/**
* uso: buildMaxHeap(A, heapSize)
* Precondición: A es un arreglo de enteros, heapSize es un entero que representa
* el tamaño del heap
* Postcondición: A es un max-heap
* Descripción: convierte A en un max-heap. Solo es necesario evaluar los nodos
* desde la mitad del arreglo hasta el principio, ya que los nodos restantes son
* hojas y por lo tanto ya son max-heaps
*/
fun buildMaxHeap(A: Array<Int>, heapSize: Int): Unit {
    for (i in heapSize / 2 downTo 0) {
        maxHeapify(A, i, heapSize)
    }
}

/**
* uso: heapSort(A)
* Precondición: A es un arreglo de enteros
* Postcondición: ordena los elementos de A de menor a mayor
* Descripción: convierte A en un max-heap, luego intercambia el primer elemento
* con el último, reduce el tamaño del heap en 1 y llama a maxHeapify en el primer
* elemento. Repite este proceso hasta que el heap tenga tamaño 1
*/
fun heapSort(A: Array<Int>): Unit {
    val heapSize: Int = A.size - 1
    buildMaxHeap(A, heapSize)
    for (i in heapSize downTo 1) {
        swap(A, 0, i)
        maxHeapify(A, 0, i - 1)
    }
}

fun even(n: Int): Boolean {
    return n % 2 == 0
}

fun down1(vars: Array<Int>){
    // * vars: [p, b, r, q, c, r1, c1, b1]
    val temp = vars[6]
    vars[6] = vars[7] - vars[6] - 1
    vars[7] = temp
}

fun up1(vars: Array<Int>){
    // * vars: [p, b, r, q, c, r1, c1, b1]
    val temp = vars[7]
    vars[7] += vars[6] + 1
    vars[6] = temp
}

fun sift(A: Array<Int>, vars: Array<Int>){
    // * vars: [p, b, r, q, c, r1, c1, b1]
    while (vars[7] >= 3){
        var r2 = vars[5] - vars[7] + vars[6]
        if (A[r2] <= A[vars[5] - 1]){
            r2 = vars[5] - 1
            down1(vars)
        }
        if (A[vars[5]] >= A[r2]){
            vars[7] = 1
        }
        else {
            swap(A, vars[5], r2)
            vars[5] = r2
            down1(vars)
        }
        println("sift: ${A.toList()}")
    }
    println("sift: ${A.toList()}")
}

fun trinkle(A: Array<Int>, vars: Array<Int>){
    // * vars: [p, b, r, q, c, r1, c1, b1]
    var p1 = vars[0]
    vars[7] = vars[1]
    vars[6] = vars[4]
    while (p1 > 0){
        while (even(p1)){
            p1 /= 2
            up1(vars)
        }
        var r3 = vars[5] - vars[7]
        if (p1 == 1 || A[r3] <= A[vars[5]]){
            p1 = 0
        }
        else if (p1 > 1 && A[r3] > A[vars[5]]){
            p1--
            if (vars[7] == 1){
                swap(A, vars[5], r3)
                vars[5] = r3
            }
            else if (vars[7] >= 3){
                var r2 = vars[5] - vars[7] + vars[6]
                if (A[r2] <= A[vars[5]-1]){
                    r2 = vars[5] - 1
                    down1(vars)
                    p1 *= 2
                }
                if (A[r3] >= A[r2]){
                    swap(A, vars[5], r3)
                    vars[5] = r3
                }
                else{
                    swap(A, vars[5], r2)
                    vars[5] = r2
                    down1(vars)
                    p1 = 0
                }
            }
        }
        println("trinkle while: ${A.toList()}")
    }
    println("trinkle: ${A.toList()}")
}

fun semitrinkle(A: Array<Int>, vars: Array<Int>){
    // * vars: [p, b, r, q, c, r1, c1, b1]
    vars[5] = vars[2] - vars[4]
    if (A[vars[5]] > A[vars[2]]){
        swap(A, vars[5], vars[2])
        trinkle(A, vars)
    }
    println("semitrinkle: ${A.toList()}")
}

fun up(vars: Array<Int>){
    // * vars: [p, b, r, q, c, r1, c1, b1]
    val temp = vars[1]
    vars[1] += vars[4] + 1
    vars[4] = temp
}

fun down(vars: Array<Int>){
    // * vars: [p, b, r, q, c, r1, c1, b1]
    val temp = vars[4]
    vars[4] = vars[1] - vars[4] - 1
    vars[1] = temp
}

fun smoothSort(A: Array<Int>){
    val n = A.size

    var p = 1
    var b = 1
    var r = 0
    var q = 1
    var c = 1
    
    // Creamos nuestro conjunto de variables en el orden
    // vars: [p, b, r, q, c, r1, c1, b1]
    val vars = Array<Int>(8, {0})
    vars[0] = p
    vars[1] = b
    vars[2] = r
    vars[3] = q
    vars[4] = c

    while (vars[3] != n){
        vars[5] = vars[2]
        if (vars[0]%8 == 3){
            vars[7] = vars[1]
            vars[6] = vars[4]
            sift(A, vars)
            vars[0] = (vars[0] + 1)/4
            up(vars)
            up(vars)
            println("smoothSort if(p%8 == 3): ${A.toList()}")
        }
        else if (vars[0]%4 == 1){
            if (vars[3] + vars[4] < n){
                vars[7] = vars[1]
                vars[6] = vars[4]
                sift(A, vars)
                println("smoothSort if(p%4 == 1) && (q + c < n): ${A.toList()}")
            }
            else {
                trinkle(A, vars)
                println("smoothSort if(p%4 == 1) && (q + c >= n): ${A.toList()}")
            }
            down(vars)
            vars[0] *= 2
            while (vars[1] != 1) {
                down(vars)
                vars[0] *= 2
            }
            vars[0]++
        }
        vars[3]++
        vars[2]++
        println("smoothSort while(q != n): ${A.toList()})")
    }
    while (vars[3] != 1){
        vars[3]--
        if (vars[1] == 1){
            vars[2]--
            vars[0]--
            while (even(vars[0])){
                vars[0] /= 2
                up(vars)
            }
        }
        else if (vars[1] >= 3){
            vars[0] -= 1
            vars[2] = vars[2] - vars[1] + vars[4]
            if (vars[0] > 0){
                semitrinkle(A, vars)
                println("smoothSort while(q != 1) if(p >= 3) && p > 0: ${A.toList()}")
            }
            down(vars)
            vars[0] = 2*vars[0] + 1
            vars[2] += vars[4]
            semitrinkle(A, vars)
            down(vars)
            vars[0] = 2*vars[0] + 1
            println("smoothSort while(q != 1) if(p >= 3): ${A.toList()}")
        }
        println("smoothSort while(q != 1): ${A.toList()}")
    }
}