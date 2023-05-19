/* 
* Laboratorio de la semana 2 de Algoritmos y Estructuras de Datos II (CI-2692).
* Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
*/

/**
* uso: crearMatrizCuadrada(n)
* Precondición: n es un entero positivo
* Postcondición: devuelve una matriz cuadrada de enteros de tamaño n
*/
fun crearMatrizCuadrada(n: Int): Array<IntArray> {
    // Crear una matriz de ceros de tamaño n
    val matriz = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        for (j in 0 until n) {
            // Llenar la matriz con enteros aleatorios entre 0 y 9
            matriz[i][j] = (0..9).random()
        }
    }
    return matriz
}

/**
* uso: compararMatrices(A, B)
* Precondición: A y B son matrices cuadradas de enteros
* Postcondición: devuelve true si A y B son iguales, false en caso contrario
*/
fun compararMatrices(A: Array<IntArray>, B: Array<IntArray>): Boolean {
    // Chequear que las matrices sean cuadradas
    if (A.size != B.size || A[0].size != B[0].size) {
        // Retornar false si no lo son
        return false
    }
    // Comparar las matrices
    for (i in A.indices) {
        for (j in A[0].indices) {
            // Retornar false si algún elemento es diferente
            if (A[i][j] != B[i][j]) {
                return false
            }
        }
    }
    // Retornar true si todos los elementos son iguales
    return true
}

/**
* uso: chekearInputValido(args)
* Precondición: args es un arreglo de strings
* Postcondición: devuelve true si args es un input válido, false en caso contrario
*/
fun chekearInputValido(args: Array<String>): Boolean {
    // Chequear que la cantidad de argumentos sea la correcta
    if (args.size != 4) {
        // Imprimir un mensaje de error si no lo es
        println("Cantidad de argumentos invalida")
        println("Uso: ./runMultMatrices.sh -n <tamaño de las matrices> -t <cantidad de veces a multiplicar>")
        return false
    }
    // Chequear que los argumentos sean válidos
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
    // Retornar true si los argumentos son válidos
    return true
}

fun main(args: Array<String>) {
    // Crear un mapa con los argumentos
    val argumentos = mapOf(args[0] to args[1], args[2] to args[3])
    // Chequear que los argumentos sean válidos
    if (!chekearInputValido(args)) {
        return
    }
    // Obtener el tamaño de las matrices y la cantidad de veces a multiplicar
    val n = argumentos["-n"]!!.toInt()
    val intentos = argumentos["-t"]!!.toInt()

    // Crear dos matrices cuadradas de tamaño n
    val A = crearMatrizCuadrada(n)
    val B = crearMatrizCuadrada(n)

    // Crear arreglos para guardar los tiempos de ejecución
    val tiempoMultiplicacionSimple = Array(intentos) { 0L }
    val tiempoMultiplicacionStrassen = Array(intentos) { 0L }
    // Multiplicar las matrices y guardar los tiempos de ejecución para todos los intentos
    for (i in 0 until intentos) {
        // Guardar el tiempo de ejecución de la multiplicación simple
        val tiempoInicial = System.currentTimeMillis()
        val C = multiplicacionSimpleDeMatrices(A, B)
        val tiempoFinal = System.currentTimeMillis()
        tiempoMultiplicacionSimple[i] = tiempoFinal - tiempoInicial

        // Guardar el tiempo de ejecución de la multiplicación de Strassen
        val tiempoInicial2 = System.currentTimeMillis()
        val D = multiplicacionStrassen(A, B)
        val tiempoFinal2 = System.currentTimeMillis()
        tiempoMultiplicacionStrassen[i] = tiempoFinal2 - tiempoInicial2

        // Chequear que las matrices sean iguales
        if (!compararMatrices(C, D)) {
            // Imprimir un mensaje de error si no lo son
            println("Las matrices no son iguales")
            return
        }
    }

    // Imprimir los tiempos de ejecución
    if(intentos == 1) {
        println("Matrices de tamaño $n multiplicadas")
        println("Tiempo multiplicacion simple: ${tiempoMultiplicacionSimple[0]} milisegundos")
        println("Tiempo multiplicacion strassen: ${tiempoMultiplicacionStrassen[0]} milisegundos")
        return
    }
    // Imprimir los tiempos promedio de ejecución en caso de que se haya realizado más de un intento
    println("Multiplicacion de matrices de tamaño $n realizada $intentos veces")
    println("Tiempo promedio multiplicacion simple: ${tiempoMultiplicacionSimple.average()} milisegundos")
    println("Tiempo promedio multiplicacion strassen: ${tiempoMultiplicacionStrassen.average()} milisegundos")
}