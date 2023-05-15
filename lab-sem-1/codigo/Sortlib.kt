fun swap(A: Array<Int>, i: Int, j: Int): Unit {
    val aux = A[i]
    A[i] = A[j]
    A[j] = aux
}

fun bubbleSort(A: Array<Int>): Unit {
    for (i in 0 until A.size - 1) {
        for (j in (A.size - 1) downTo (i + 1)) {
            if (A[j] < A[j - 1]) {
                swap(A, j, j - 1)
            }
        }
    }
}

fun insertionSort(A: Array<Int>): Unit {
    for (i in 1 until A.size) {
        var j = i
        while (j > 0 && A[j] < A[j - 1]) {
            swap(A, j, j - 1)
            j -= 1
        }
    }
}

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
