// estaEnOrdenAscendente: Array<Int> -> Boolean
// Uso: estaEnOrdenAscendente(A)
// Precondición: A.size > 0
// Poscondición: Devuelve true si A está ordenado en forma ascendente 
fun estaEnOrdenAscendente(secuencia: Array<Int>): Boolean {
    val n = secuencia.size
    for (i in 0 until n-1) {
        // Itera sobre los elementos de la secuencia desde el primero hasta el penúltimo
	    if (secuencia [i] > secuencia [i+1]) {
            // Si encuentra un par de elementos consecutivos que no están ordenados en forma ascendente
	        return false
	    } 
    }
    // Si no encontró ningún par de elementos consecutivos que no estén ordenados en forma ascendente
    return true
}

// secuenciaRandom: Int -> Array<Int>
// Uso: secuenciaRandom(n)
// Precondición: n > 0
// Poscondición: Devuelve una secuencia de n elementos con valores aleatorios
fun secuenciaRandom(n: Int): Array<Int> {
    // Crea una secuencia de n elementos cuyos valores iniciales son todos 0
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
        // Itera sobre los elementos de la secuencia y les asigna un valor aleatorio
        secuencia[i] =  (0..n).random()
    }
    return secuencia
}

// secuenciaOrdenada: Int -> Array<Int>
// Uso: secuenciaOrdenada(n)
// Precondición: n > 0
// Poscondición: Devuelve una secuencia de n elementos ordenados en forma ascendente
fun secuenciaOrdenada(n: Int): Array<Int> {
    // Crea una secuencia de n elementos cuyos valores iniciales son todos 0
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
        // Itera sobre los elementos de la secuencia y les asigna un valor entre 1 y n
        secuencia[i] =  i+1
    }
    return secuencia
}

// secuenciaInvertida: Int -> Array<Int>
// Uso: secuenciaInvertida(n)
// Precondición: n > 0
// Poscondición: Devuelve una secuencia de n elementos ordenados en forma descendente
fun secuenciaInvertida(n: Int): Array<Int> {
    // Crea una secuencia de n elementos cuyos valores iniciales son todos 0
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
        // Itera sobre los elementos de la secuencia y les asigna un valor entre 1 y n en forma descendente
        secuencia[i] =  n-i
    }
    return secuencia
}

// secuenciaCerosYUnos: Int -> Array<Int>
// Uso: secuenciaCerosYUnos(n)
// Precondición: n > 0
// Poscondición: Devuelve una secuencia de n elementos con valores 0 o 1
fun secuenciaCerosYUnos(n: Int): Array<Int> {
    // Crea una secuencia de n elementos cuyos valores iniciales son todos 0
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
        // Itera sobre los elementos de la secuencia y les asigna un valor aleatorio entre 0 y 1
        secuencia[i] =  (0..1).random()
    }
    return secuencia
}

// secuenciaMedia: Int -> Array<Int>
// Uso: secuenciaMedia(n)
// Precondición: n > 0
// Poscondición: Devuelve una secuencia de n elementos con valores en
fun secuenciaMedia(n: Int): Array<Int> {
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n/2) {
        // Itera sobre los elementos de la secuencia entre el primero y el elemento n/2 - 1
        secuencia[i] =  i+1
    }
    for (i in n/2 until n) {
        // Itera sobre los elementos de la secuencia entre el elemento n/2 y el último
        secuencia[i] =  n-i
    }
    return secuencia
}

fun main(args: Array<String>) {
    // ! Realizar
}
