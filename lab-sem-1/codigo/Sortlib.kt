fun swap(A: Array<Int>, i: Int, j: Int) {
    val aux = A[i]
    A[i] = A[j]
    A[j] = aux
}

fun bubbleSort(A: Array<Int>) {
    for (i in 0 until A.size - 1) {
        for (j in (A.size - 1) downTo (i + 1)) {
            if (A[j] < A[j - 1]) {
                swap(A, j, j - 1)
            }
        }
    }
}

fun insertionSort(A: Array<Int>) {
    // Realizar!
}

fun selectionSort(A: Array<Int>) {
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

fun shellSort(A: Array<Int>) {
 // Realizar!
}
