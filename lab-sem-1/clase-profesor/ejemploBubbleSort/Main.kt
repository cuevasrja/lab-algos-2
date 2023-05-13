fun estaEnOrdenAscendente(secuencia: Array<Int>): Boolean {
    val n = secuencia.size
    for (i in 0 until n-1) {
	if (secuencia [i] > secuencia [i+1]) {
	    return false
	} 
    }
    return true
}

fun main(args: Array<String>) {
    val n: Int = args[0].toInt()
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
	secuencia[i] =  (0..n).random()
    }
    bubbleSort(secuencia)
    if (!estaEnOrdenAscendente(secuencia)) {
	println("Error, la secuencia no esta ordenada")
	return
    }
    println("Ordenamiento exitoso!")
}
