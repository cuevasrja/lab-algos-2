fun busquedaLineal(secuencia: Array<Int>, x: Int): Int {
    for ((index, value) in secuencia.withIndex()) {
	if (value >= x) {
	    return index 
	} 
    }
    return secuencia.size
}

fun busquedaBinaria(secuencia: Array<Int>, x: Int): Int {
    if (secuencia[secuencia.size-1] < x) {
	return secuencia.size
    }
    return busquedaBinariaRec(secuencia, 0, secuencia.size-1, x)
}

fun busquedaBinariaRec(T: Array<Int>, i: Int, j: Int, x: Int): Int {
    if (i == j) {
	return i
    }
    val k = (i+j).div(2)
    if (x <= T[k]) {
	return busquedaBinariaRec(T, i, k, x)
    } else {
	return busquedaBinariaRec(T, k+1, j, x)
    }
}
