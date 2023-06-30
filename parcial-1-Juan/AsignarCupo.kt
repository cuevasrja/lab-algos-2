fun swapDual(A: Array<Int>, B: Array<Int>, i: Int, j: Int): Unit {
    val aux: Int = A[i]
    A[i] = A[j]
    A[j] = aux
    val temp: Int = B[i]
    B[i] = B[j]
    B[j] = temp
}

fun compararEstudiantes(A: Array<Int>, B: Array<Int>, i: Int, j: Int): Boolean{
    return A[i] < A[j] || (A[i] == A[j] && B[i] <= B[j])
}

fun parent(i: Int): Int {
    return i / 2
}

fun left(i: Int): Int {
    return 2 * i
}

fun right(i: Int): Int {
    return 2 * i + 1
}

fun maxHeapify(A: Array<Int>, B: Array<Int>, i: Int, heapSize: Int): Unit {
    val l: Int = left(i)
    val r: Int = right(i)
    var largest: Int = i
    if (l <= heapSize && compararEstudiantes(A, B, i, l)) {
        largest = l
    }
    if (r <= heapSize && compararEstudiantes(A, B, largest, r)) {
        largest = r
    }
    if (largest != i) {
        swapDual(A, B, i, largest)
        maxHeapify(A, B, largest, heapSize)
    }
}

fun buildMaxHeap(A: Array<Int>, B: Array<Int>, heapSize: Int): Unit {
    for (i in heapSize / 2 downTo 0) {
        maxHeapify(A, B, i, heapSize)
    }
}

fun heapSort(A: Array<Int>, B: Array<Int>): Unit {
    val heapSize: Int = A.size - 1
    buildMaxHeap(A, B, heapSize)
    for (i in heapSize downTo 1) {
        swapDual(A, B, 0, i)
        maxHeapify(A, B, 0, i - 1)
    }
}

fun main(args: Array<String>) {
    val cupos = args[0].toInt()
    if (cupos < 0){
        throw Exception("El número de cupos debe ser positivo")
    }
    else if (cupos == 0){
        println("NO HAY CUPOS")
        return
    }
    if ((args.size)%2 != 1){
        throw Exception("Falta un estudiante por NFC o carnet")
    }
    val numeroEstudiantes = (args.size - 1)/2
    if (cupos >= numeroEstudiantes){
        println("TODOS FUERON ADMITIDOS")
        return
    }
    val carnets: Array<Int> = Array(numeroEstudiantes){0}
    val NCFs: Array<Int> = Array(numeroEstudiantes){0}
    for (i in 0 until numeroEstudiantes){
        carnets[i] = args[i*2 + 1].toInt()
        NCFs[i] = args[i*2 + 2].toInt()
    }
    heapSort(NCFs, carnets)
    for (i in 0 until cupos){
        println(carnets[i])
    }
}