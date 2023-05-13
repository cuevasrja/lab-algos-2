fun bubbleSort(A: Array<Int>) {
    for (i in 0 until A.size - 1) {
        for (j in (A.size - 1) downTo (i + 1)) {
            if (A[j] < A[j - 1]) {
                val aux = A[j]
                A[j] = A[j - 1]
                A[j - 1] = aux
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
        val aux = A[i]
        A[i] = A[min]
        A[min] = aux
    }
}

fun shellSort(A: Array<Int>) {
 // Realizar!
}
