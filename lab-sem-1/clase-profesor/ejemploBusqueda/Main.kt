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
    val x = args[0].toInt()
    println("Elemento a buscar: $x")
    val entrada: Array<String> = args.sliceArray(1 until args.size)
    val secuencia: Array<Int> = entrada.map{ it.toInt() }.toTypedArray()
    if (!estaEnOrdenAscendente(secuencia)) {
	println("Error, la secuencia no esta ordenada")
	return
    }
    var k = busquedaLineal(secuencia, x)
    println("Posicion busqueda lineal: $k")
    k = busquedaBinaria(secuencia, x)
    println("Posicion busqueda binaria: $k")
}
