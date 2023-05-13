fun estaEnOrdenAscendente(secuencia: Array<Int>): Boolean {
    val n = secuencia.size
    for (i in 0 until n-1) {
	if (secuencia [i] > secuencia [i+1]) {
	    return false
	} 
    }
    return true
}

fun secuenciaRandom(n: Int): Array<Int> {
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
    secuencia[i] =  (0..n).random()
    }
    return secuencia
}

fun secuenciaOrdenada(n: Int): Array<Int> {
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
    secuencia[i] =  i
    }
    return secuencia
}

fun secuenciaInvertida(n: Int): Array<Int> {
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
    secuencia[i] =  n-i
    }
    return secuencia
}

fun secuenciaCerosYUnos(n: Int): Array<Int> {
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
    secuencia[i] =  (0..1).random()
    }
    return secuencia
}

fun secuenciaMedia(n: Int): Array<Int> {
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n/2) {
    secuencia[i] =  i
    }
    for (i in n/2+1 until n) {
    secuencia[i] =  n-i
    }
    return secuencia
}

fun main(args: Array<String>) {
    // Realizar
}
