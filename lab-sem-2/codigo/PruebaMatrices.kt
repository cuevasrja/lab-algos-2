/* 
* Laboratorio de la semana 2 de Algoritmos y Estructuras de Datos II (CI-2692).
* Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
*/

fun crearMatrizCuadrada(n: Int): Array<IntArray> {
    val matriz = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        for (j in 0 until n) {
            matriz[i][j] = (0..9).random()
        }
    }
    return matriz
}

fun compararMatrices(A: Array<IntArray>, B: Array<IntArray>): Boolean {
    if (A.size != B.size || A[0].size != B[0].size) {
        return false
    }
    for (i in A.indices) {
        for (j in A[0].indices) {
            if (A[i][j] != B[i][j]) {
                return false
            }
        }
    }
    return true
}

fun chekearInputValido(args: Array<String>): Boolean {
    if (args.size != 4) {
        println("Cantidad de argumentos invalida")
        println("Uso: ./runMultMatrices.sh -n <tamaño de las matrices> -t <cantidad de veces a multiplicar>")
        return false
    }
    if (args[0] != "-t" && args[2] != "-t") {
        println("Argumento invalido")
        println("Uso: ./runMultMatrices.sh -n <tamaño de las matrices> -t <cantidad de veces a multiplicar>")
        return false
    }
    if (args[0] != "-n" && args[2] != "-n") {
        println("Argumento invalido")
        println("Uso: ./runMultMatrices.sh -n <tamaño de las matrices> -t <cantidad de veces a multiplicar>")
        return false
    }
    return true
}

fun main(args: Array<String>) {
    val argumentos = mapOf(args[0] to args[1], args[2] to args[3])
    if (!chekearInputValido(args)) {
        return
    }
    val n = argumentos["-n"]!!.toInt()
    val intentos = argumentos["-t"]!!.toInt()

    val A = crearMatrizCuadrada(n)
    val B = crearMatrizCuadrada(n)

    val tiempoMultiplicacionSimple = Array(intentos) { 0L }
    val tiempoMultiplicacionStrassen = Array(intentos) { 0L }
    for (i in 0 until intentos) {
        val tiempoInicial = System.currentTimeMillis()
        val C = multiplicacionSimpleDeMatrices(A, B)
        val tiempoFinal = System.currentTimeMillis()
        tiempoMultiplicacionSimple[i] = tiempoFinal - tiempoInicial

        val tiempoInicial2 = System.currentTimeMillis()
        val D = multiplicacionStrassen(A, B)
        val tiempoFinal2 = System.currentTimeMillis()
        tiempoMultiplicacionStrassen[i] = tiempoFinal2 - tiempoInicial2

        if (!compararMatrices(C, D)) {
            println("Las matrices no son iguales")
            return
        }
    }
    if(intentos == 1) {
        println("Matrices de tamaño $n multiplicadas")
        println("Tiempo multiplicacion simple: ${tiempoMultiplicacionSimple[0]} milisegundos")
        println("Tiempo multiplicacion strassen: ${tiempoMultiplicacionStrassen[0]} milisegundos")
        return
    }
    println("Multiplicacion de matrices de tamaño $n realizada $intentos veces")
    println("Tiempo promedio multiplicacion simple: ${tiempoMultiplicacionSimple.average()} milisegundos")
    println("Tiempo promedio multiplicacion strassen: ${tiempoMultiplicacionStrassen.average()} milisegundos")
}