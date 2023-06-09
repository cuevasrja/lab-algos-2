/*
Laboratorio de la semana 4 de Algoritmos y Estructuras de Datos II (CI-2692).
Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
*/

/**
* estaEnOrdenAscendente: Array<Int> -> Boolean
* Uso: estaEnOrdenAscendente(secuencia)
* Precondición: secuencia.size > 0
* Poscondición: Devuelve true si la secuencia está ordenado en forma ascendente
* y false en caso contrario.
*/
fun estaEnOrdenAscendente(secuencia: Array<Int>): Boolean {
    for (i in 0 until secuencia.size - 1) {
        // Itera sobre los elementos de la secuencia desde el primero hasta el penúltimo
	    if (secuencia[i] > secuencia[i + 1]) {
            // Si encuentra un par de elementos consecutivos que no están ordenados en forma ascendente
	        return false
	    }
    }
    // Si no encontró ningún par de elementos consecutivos que no estén ordenados en forma ascendente
    return true
}

/**
* secuenciaRandom: Int -> Array<Int>
* Uso: secuenciaRandom(n)
* Precondición: n > 0
* Poscondición: Devuelve una secuencia de n elementos de tipo entero, generados
* aleatoriamente en el intervalo [0,..., n].
*/
fun secuenciaRandom(n: Int): Array<Int> {
    // Crea una secuencia de n elementos cuyos valores iniciales son todos 0
    val secuencia: Array<Int> = Array(n, {0})
    for (i in 0 until n) {
        // Itera sobre los elementos de la secuencia y les asigna un valor aleatorio
        secuencia[i] = (0..n).random()
    }
    return secuencia
}

/**
* calcularMedia: Array<Long> -> Double
* Uso: calcularMedia(tiempo)
* Precondición: tiempo.size > 0
* Poscondición: Devuelve el promedio de los elementos del arreglo tiempo
*/
fun calcularMedia(tiempo: Array<Long>): Double {
    var suma: Double = 0.0
    for (i in 0 until tiempo.size) {
        suma += tiempo[i]
    }
    return suma / tiempo.size
}

/**
* calcularDesviacionEstandar: Array<Long>, Double -> Double
* Uso: calcularDesviacionEstandar(tiempo, media)
* Precondición: tiempo.size > 1
* Poscondición: Devuelve la desviación estándar de los elementos del arreglo tiempo
*/
fun calcularDesviacionEstandar(tiempo: Array<Long>, media: Double): Double {
    var suma: Double = 0.0
    for (i in 0 until tiempo.size) {
        suma += Math.pow((tiempo[i] - media), 2.0)
    }
    return Math.sqrt(suma / (tiempo.size))
}

/**
* Uso: mensajeOrdenamientoExitoso(tipoDeOrdenamiento, tiempo)
* Si el usuario especificó más de un intento, se calcula la media y la desviación estándar.
* Si el usuario especificó un solo intento, se imprime el tiempo de ejecución.
*/
fun mensajeOrdenamientoExitoso(tipoDeOrdenamiento: String, tiempo: Array<Long>): Unit {
    if (tiempo.size > 1) {
        val media: Double = calcularMedia(tiempo)
        val desviacionEstandar: Double = calcularDesviacionEstandar(tiempo, media)
        // Se divide entre 1000000000.0 para convertir el tiempo de nanosegundos a segundos
        println("\u001b[32mOrdenamiento exitoso con $tipoDeOrdenamiento en ${tiempo.size} intentos \u001b[0m")
        println("Tiempo promedio de ejecución de $tipoDeOrdenamiento: \u001b[33m${(media/1000000000.0).toDouble()} segundos \u001b[0m")
        println("Desviación estándar de $tipoDeOrdenamiento: \u001b[33m${(desviacionEstandar/1000000000.0).toDouble()} segundos \u001b[0m")
    } else {
        println("\u001b[32mOrdenamiento exitoso con $tipoDeOrdenamiento \u001b[0m")
        println("Tiempo de ejecución de $tipoDeOrdenamiento: \u001b[33m${(tiempo[0]/1000000000.0).toDouble()} segundos \u001b[0m")
    }
    println("----------------------------------------------------------------------------------------")
}

/**
* Uso: mensajeErrorEnElOrdenamiento(tipoDeOrdenamiento)
* Imprime un mensaje de error indicando que la secuencia no está ordenada con el tipo de
* ordenamiento especificado
*/
fun mensajeErrorEnElOrdenamiento(tipoDeOrdenamiento: String): Unit {
    println("\u001b[31mError:\u001b[0m La secuencia no está ordenada con $tipoDeOrdenamiento \u001b[0m")
    println("----------------------------------------------------------------------------------------")
}

// Declaración del método principal
fun main(): Unit {
    var pruebas: Array<Int> = arrayOf(10, 15, 30)
    var intentos: Int = 10

    val tiempoCoutingSort = Array<Long>(intentos, {0})
    val tiempoRadixSort = Array<Long>(intentos, {0})

    for (i in 0 until pruebas.size){
        val n = pruebas[i]
        println("\u001b[34mPrueba con secuencia de $n elementos \u001b[0m")
        println("----------------------------------------------------------------------------------------")
        for (j in 0 until intentos) {
            var error: Boolean = false
            val secuencia: Array<Int> = secuenciaRandom(n)
            val secuenciaCopia: Array<Int> = secuencia.copyOf()

            val tiempoInicialCoutingSort: Long = System.nanoTime()
            countingSort(secuencia)
            val tiempoFinalCoutingSort: Long = System.nanoTime()
            tiempoCoutingSort[j] = tiempoFinalCoutingSort - tiempoInicialCoutingSort

            val tiempoInicialRadixSort: Long = System.nanoTime()
            radixSort(secuenciaCopia)
            val tiempoFinalRadixSort: Long = System.nanoTime()
            tiempoRadixSort[j] = tiempoFinalRadixSort - tiempoInicialRadixSort

            if (!estaEnOrdenAscendente(secuencia)) {
                mensajeErrorEnElOrdenamiento("CoutingSort")
                error = true
            }
            if (!estaEnOrdenAscendente(secuenciaCopia)) {
                mensajeErrorEnElOrdenamiento("RadixSort")
                error = true
            }
            if (error) {
                return
            }
        }
        mensajeOrdenamientoExitoso("CoutingSort", tiempoCoutingSort)
        println("----------------------------------------------------------------------------------------")
    }
}
