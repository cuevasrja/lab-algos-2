/*
Laboratorio de la semana 4 de Algoritmos y Estructuras de Datos II (CI-2692).
Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
*/

/**
* uso: swap(A, i, j)
* Precondición: A es un arreglo de enteros, i y j son índices válidos en A
* Postcondición: intercambia los elementos en las posiciones i y j del arreglo A
*/
fun swap(A: Array<Int>, i: Int, j: Int): Unit {
    val aux: Int = A[i]
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
        var j: Int = i
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
        var min: Int = i
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
/**
* uso: even(n)
* Precondición: n es un entero
* Postcondición: devuelve true si n es par, false en caso contrario
 */
fun even(n: Int): Boolean {
    return n % 2 == 0
}

/**
* uso: down1(vars)
* Precondición: vars es un arreglo de enteros de tamaño 8
* Postcondición: realiza un downheap en el arreglo vars
*/
fun down1(vars: Array<Int>): Unit {
    // * vars: [p, b, r, q, c, r1, c1, b1]
    val temp: Int = vars[6]
    vars[6] = vars[7] - vars[6] - 1
    vars[7] = temp
}

/**
* uso: up1(vars)
* Precondición: vars es un arreglo de enteros de tamaño 8
* Postcondición: realiza un upheap en el arreglo vars
*/
fun up1(vars: Array<Int>): Unit {
    // * vars: [p, b, r, q, c, r1, c1, b1]
    val temp: Int = vars[7]
    vars[7] += vars[6] + 1
    vars[6] = temp
}

/**
* uso: sift(A, vars)
* Precondición: A es un arreglo de enteros, vars es un arreglo de enteros de tamaño 8
* Postcondición: realiza un sift en el arreglo A
* Descripción: asume que el arreglo A es un heap, pero que el elemento en la posición
* vars[7] puede ser mayor que sus hijos, intercambia A[vars[7]] con su hijo mayor
*/
fun sift(A: Array<Int>, vars: Array<Int>): Unit {
    // * vars: [p, b, r, q, c, r1, c1, b1]
    // Mientras que el nodo no sea una hoja y no sea mayor que su hijo mayor
    // (es decir, no esté en la posición correcta)
    while (vars[7] >= 3) {
        var r2: Int = vars[5] - vars[7] + vars[6]
        // Si el nodo tiene un hijo derecho
        if (A[r2] <= A[vars[5] - 1]) {
            r2 = vars[5] - 1
            // Moverse hacia abajo
            down1(vars)
        }
        // Si el hijo derecho es mayor o igual que el hijo izquierdo
        if (A[vars[5]] >= A[r2]) {
            vars[7] = 1
        }
        // Si el hijo izquierdo es mayor que el hijo derecho
        else {
            // Intercambiar el nodo con el hijo izquierdo
            swap(A, vars[5], r2)
            vars[5] = r2
            // Moverse hacia abajo
            down1(vars)
        }
    }
}

/**
* uso: trinkle(A, vars)
* Precondición: A es un arreglo de enteros, vars es un arreglo de enteros de tamaño 8
* Postcondición: realiza un trinkle en el arreglo A
* Descripción: trinkle es una versión modificada de sift que se utiliza cuando el nodo
* que se está moviendo hacia abajo tiene un hijo izquierdo que es menor que el nodo
* y un hijo derecho que es mayor que el nodo. En este caso, se intercambia el nodo con
* el hijo derecho y se mueve hacia abajo. Si el nodo tiene un hijo derecho, se intercambia
* con el hijo derecho y se mueve hacia abajo. Si el nodo tiene un hijo izquierdo, se intercambia
* con el hijo izquierdo y se mueve hacia abajo. Si el nodo no tiene hijos, se detiene.
*/
fun trinkle(A: Array<Int>, vars: Array<Int>): Unit {
    // * vars: [p, b, r, q, c, r1, c1, b1]
    var p1: Int = vars[0]
    vars[7] = vars[1]
    vars[6] = vars[4]
    // Mientras que el nodo no sea una hoja
    while (p1 > 0) {
        // Mientras el nodo tenga un hijo derecho
        while (even(p1)) {
            p1 /= 2
            // Moverse hacia arriba
            up1(vars)
        }
        var r3: Int = vars[5] - vars[7]
        // Si el nodo tiene un hijo izquierdo
        if (p1 == 1 || A[r3] <= A[vars[5]]) {
            // Convertir el nodo en una hoja
            p1 = 0
        }
        // Si el hijo izquierdo es mayor que el hijo derecho
        else if (p1 > 1 && A[r3] > A[vars[5]]) {
            p1--
            // Si el nodo tiene un hijo derecho
            if (vars[7] == 1) {
                // Intercambiar el nodo con el hijo derecho
                swap(A, vars[5], r3)
                vars[5] = r3
            }
            // Si el nodo tiene un hijo izquierdo
            else if (vars[7] >= 3) {
                var r2: Int = vars[5] - vars[7] + vars[6]
                // Si el nodo tiene un hijo derecho
                if (A[r2] <= A[vars[5]-1]) {
                    r2 = vars[5] - 1
                    down1(vars)
                    p1 *= 2
                }
                // Si el hijo derecho es mayor o igual que el hijo izquierdo
                if (A[r3] >= A[r2]) {
                    swap(A, vars[5], r3)
                    vars[5] = r3
                }
                // Si el hijo izquierdo es mayor que el hijo derecho
                else {
                    swap(A, vars[5], r2)
                    vars[5] = r2
                    down1(vars)
                    p1 = 0
                }
            }
        }
    }
    // Si el nodo es una hoja
    sift(A, vars)
}

/**
* uso: semitrinkle(A, vars)
* Precondición: A es un arreglo de enteros, vars es un arreglo de enteros de tamaño 8
* Postcondición: realiza un semitrinkle en el arreglo A
* Descripción: semitrinkle es una versión modificada de trinkle que se utiliza cuando el nodo
* que se está moviendo hacia abajo tiene un hijo izquierdo que es menor que el nodo
* y un hijo derecho que es mayor que el nodo. En este caso, se intercambia el nodo con
* el hijo derecho y se mueve hacia abajo. Si el nodo tiene un hijo derecho, se intercambia
* con el hijo derecho y se mueve hacia abajo. Si el nodo tiene un hijo izquierdo, se intercambia
* con el hijo izquierdo y se mueve hacia abajo. Si el nodo no tiene hijos, se detiene.
*/
fun semitrinkle(A: Array<Int>, vars: Array<Int>): Unit {
    // * vars: [p, b, r, q, c, r1, c1, b1]
    vars[5] = vars[2] - vars[4]
    if (A[vars[5]] > A[vars[2]]) {
        swap(A, vars[5], vars[2])
        trinkle(A, vars)
    }
}

/**
* uso: up(vars)
* Precondición: vars es un arreglo de enteros de tamaño 8
* Postcondición: realiza un up en el arreglo vars
* Descripción: up es una versión modificada de siftup que se utiliza cuando el nodo
* que se está moviendo hacia arriba tiene un hijo izquierdo que es menor que el nodo
* y un hijo derecho que es mayor que el nodo.
*/
fun up(vars: Array<Int>): Unit {
    // * vars: [p, b, r, q, c, r1, c1, b1]
    val temp: Int = vars[1]
    vars[1] += vars[4] + 1
    vars[4] = temp
}

/**
* uso: down(vars)
* Precondición: vars es un arreglo de enteros de tamaño 8
* Postcondición: realiza un down en el arreglo vars
* Descripción: down es una versión modificada de siftdown que se utiliza cuando el nodo
* que se está moviendo hacia abajo tiene un hijo izquierdo que es menor que el nodo
* y un hijo derecho que es mayor que el nodo.
*/
fun down(vars: Array<Int>): Unit {
    // * vars: [p, b, r, q, c, r1, c1, b1]
    val temp: Int = vars[4]
    vars[4] = vars[1] - vars[4] - 1
    vars[1] = temp
}

/**
* uso: smoothSort(A)
* Precondición: A es un arreglo de enteros
* Postcondición: realiza un smoothSort en el arreglo A
* Descripción: smoothSort es un algoritmo de ordenamiento que utiliza un heap de Fibonacci
* para ordenar un arreglo de enteros. El algoritmo se basa en el algoritmo de ordenamiento
* por mezcla natural, pero utiliza un heap de Fibonacci para realizar las mezclas.
*/
fun smoothSort(A: Array<Int>): Unit {
    // Guardamos el tamaño del arreglo
    val n: Int = A.size

    // Creamos nuestras variables iniciales
    var p: Int = 1
    var b: Int = 1
    var r: Int = 0
    var q: Int = 0
    var c: Int = 1

    // Creamos nuestro conjunto de variables en el orden
    // vars: [p, b, r, q, c, r1, c1, b1]
    val vars: Array<Int> = Array<Int>(8, {0})
    vars[0] = p
    vars[1] = b
    vars[2] = r
    vars[3] = q
    vars[4] = c

    // Mientras no hayamos llegado al final del arreglo
    while (vars[3] != n) {
        vars[5] = vars[2]
        // Si el nodo tiene un hijo izquierdo
        if (vars[0]%8 == 3) {
            vars[7] = vars[1]
            vars[6] = vars[4]
            // Hacer un sift
            sift(A, vars)
            // Reducir el número de nodos
            vars[0] = (vars[0] + 1) / 4
            // Aumentar el tamaño del heap
            up(vars)
            up(vars)
        }
        // Si el nodo tiene un hijo derecho
        else if (vars[0]%4 == 1) {
            // Si la suma del hijo derecho y el hijo izquierdo es menor que el tamaño del arreglo
            if (vars[3] + vars[4] < n) {
                vars[7] = vars[1]
                vars[6] = vars[4]
                sift(A, vars)
            }
            else {
                trinkle(A, vars)
            }
            // Reducir el tamaño del heap
            down(vars)
            vars[0] *= 2
            // Mientras el número de nodos sea mayor que 1
            while (vars[1] != 1) {
                down(vars)
                vars[0] *= 2
            }
            vars[0]++
        }
        vars[3]++
        vars[2]++
    }
    // Mientras el número de nodos sea mayor que 1
    while (vars[3] != 1) {
        vars[3]--
        // Si el nodo tiene un hijo izquierdo
        if (vars[1] == 1) {
            vars[2]--
            vars[0]--
            // Mientras el número de nodos sea par
            while (even(vars[0])) {
                vars[0] /= 2
                up(vars)
            }
        }
        // Si el nodo tiene un hijo derecho y dos hijos izquierdos
        else if (vars[1] >= 3) {
            vars[0] -= 1
            vars[2] = vars[2] - vars[1] + vars[4]
            // Si el nodo no es una hoja
            if (vars[0] > 0) {
                semitrinkle(A, vars)
            }
            down(vars)
            vars[0] = 2*vars[0] + 1
            vars[2] += vars[4]
            semitrinkle(A, vars)
            down(vars)
            vars[0] = 2*vars[0] + 1
        }
    }
}

/**
* uso: partition(A, p, r)
* Precondición: A es un arreglo de enteros, p y r son enteros
* Postcondición: realiza un partition en el arreglo A
*/
fun partition(A: Array<Int>, p: Int, r: Int): Int {
    // El pivote
    val x: Int = A[r]
    // Indice más alto de la parte izquierda
    var i: Int = p - 1
    // Procesa todos los elementos excepto el pivote
    for (j in p..r - 1) {
        // Si el elemento actual es menor que el pivote
        if (A[j] <= x) {
            // Incrementa el indice más alto en la parte izquierda
            i++
            // Intercambia el elemento actual con el índice más alto en la parte izquierda
            swap(A, i, j)
        }
    }
    // Intercambia el pivote con el primer elemento mayor que el pivote
    swap(A, i + 1, r)
    // Regresa el nuevo índice del pivote
    return i + 1
}

/**
* uso: quicksort(A, p, r)
* Precondición: A es un arreglo de enteros, p y r son enteros
* Postcondición: realiza un quicksort en el arreglo A
* Descrición: quicksort crea particiones en el arreglo A y las ordena recursivamente
*/
fun quicksort(A: Array<Int>, p: Int, r: Int): Unit {
    if (p < r) {
        // Crea una partición que termina en A[q]
        val q: Int = partition(A, p, r)
        // Ordena recursivamente el lado izquierdo de la partición
        quicksort(A, p, q - 1)
        // Ordena recursivamente el lado derecho de la partición
        quicksort(A, q + 1, r)
    }
}

/**
* uso: quicksortClasico(A)
* Precondición: A es un arreglo de enteros
* Postcondición: realiza un quicksort en el arreglo A
* Descrición: quicksortClasico crea particiones en el arreglo A y las ordena recursivamente
*/
fun quicksortClasico(A: Array<Int>): Unit {
    quicksort(A, 0, A.size - 1)
}

/**
* uso: quickThreeWay(A, p, r)
* Precondición: A es un arreglo de enteros, p y r son enteros
* Postcondición: realiza un quicksort con 3 particiones en el arreglo A
* Descrición: quickThreeWay crea particiones en el arreglo A y las ordena recursivamente
*/
fun quickThreeWay(A: Array<Int>, left: Int, right: Int) {
    if (right <= left) return
    var i: Int = left - 1
    var j: Int = right
    var p: Int = left - 1
    var q: Int = right
    val v: Int = A[right]
    while (true) {
        while (A[++i] < v);
        while (v < A[--j]) if (j == left) break
        if (i >= j) break
        swap(A, i, j)
        if (A[i] == v) {
            p++
            swap(A, p, i)
        }
        if (v == A[j]) {
            q--
            swap(A, q, j)
        }
    }
    swap(A, i, right)
    j = i - 1
    i++
    for (k in left..p) {
        swap(A, k, j)
        j--
    }
    for (k in right - 1 downTo q + 1) {
        swap(A, i, k)
        i++
    }
    quickThreeWay(A, left, j)
    quickThreeWay(A, i, right)
}

/**
* uso: quicksortThreeWay(A)
* Precondición: A es un arreglo de enteros
* Postcondición: realiza un quicksort con 3 particiones en el arreglo A
* Descrición: quicksortThreeWay crea particiones en el arreglo A y las ordena recursivamente
*/
fun quicksortThreeWay(A: Array<Int>): Unit {
    quickThreeWay(A, 0, A.size - 1)
}

/**
* uso: dualPivotQuicksort(A, left, right)
* Precondición: A es un arreglo de enteros, left y right son enteros
* Postcondición: realiza un dualPivotQuicksort en el arreglo A
* Descrición: dualPivotQuicksort crea particiones en el arreglo A y las ordena recursivamente
*/
fun dualPivotPartition(A: Array<Int>, left: Int, right: Int): Unit {
    if (left < right) {
        if (A[left] > A[right]) {
            swap(A, left, right)
        }
        val p: Int = A[left]
        val q: Int = A[right]
        var l: Int = left + 1
        var g: Int = right - 1
        var k: Int = l
        while (k <= g) {
            if (A[k] < p) {
                swap(A, k, l)
                l++
            }
            else if (A[k] >= q) {
                while (A[g] > q && k < g) {
                    g--
                }
                swap(A, k, g)
                g--
                if (A[k] < p) {
                    swap(A, k, l)
                    l++
                }
            }
            k++
        }
        l--
        g++
        swap(A, left, l)
        swap(A, right, g)
        dualPivotPartition(A, left, l - 1)
        dualPivotPartition(A, l + 1, g - 1)
        dualPivotPartition(A, g + 1, right)
    }
}

/**
* uso: quicksortDualPivot(A)
* Precondición: A es un arreglo de enteros
* Postcondición: realiza un quicksortDualPivot en el arreglo A
* Descrición: quicksortDualPivot crea particiones en el arreglo A y las ordena recursivamente
*/
fun quicksortDualPivot(A: Array<Int>): Unit {
    val n: Int = A.size
    dualPivotPartition(A, 0, n - 1)
}

fun counting(A: Array<Int>, k: Int): Unit {
    val n: Int = A.size
    val B: Array<Int> = Array<Int>(n, {0})
    val C: Array<Int> = Array<Int>(k+1, {0})
    for (i in 0 until n){
        C[A[i]]++
    }
    for (i in 1 until C.size) {
        C[i] += C[i-1]
    }
    for (i in n-1 downTo 0) {
        B[C[A[i]]-1] = A[i]
        C[A[i]]--
    }
    for (i in 0 until n) {
        A[i] = B[i]
    }
}

fun getMax(A: Array<Int>): Int {
    var max: Int = 0
    for (i in 0 until A.size) {
        if (A[i] > max) max = A[i]
    }
    return max
}

fun countingSort(A: Array<Int>): Unit {
    val max: Int = getMax(A)
    counting(A, max)
}

fun getAmountOfDigits(n: Int): Int {
    var amountOfDigits: Int = 0
    var number: Int = n
    while (number > 0) {
        number /= 10
        amountOfDigits++
    }
    return amountOfDigits
}

fun radixSort(A: Array<Int>): Unit {
    val maxElement: Int = getMax(A)
    val maxAmountOfDigits: Int = getAmountOfDigits(maxElement)
    for(numberOfDigit in 1..maxAmountOfDigits) {
        val B: Array<Int> = Array<Int>(A.size, {0})
        val C: Array<Int> = Array<Int>(10, {0})
        for (i in 0 until A.size) {
            val digit: Int = (A[i] / Math.pow(10.0, (numberOfDigit - 1).toDouble())).toInt() % 10
            C[digit]++
        }
        for (i in 1 until C.size) {
            C[i] += C[i-1]
        }
        for (i in A.size-1 downTo 0) {
            val digit: Int = (A[i] / Math.pow(10.0, (numberOfDigit - 1).toDouble())).toInt() % 10
            B[C[digit]-1] = A[i]
            C[digit]--
        }
        for (i in 0 until A.size) {
            A[i] = B[i]
        }
    }
}