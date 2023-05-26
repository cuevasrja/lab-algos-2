/*
Laboratorio de la semana 2 de Algoritmos y Estructuras de Datos II (CI-2692).
Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
*/

import kotlin.random.Random

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
* crearSecuenciaRandom: Int, Int, Int -> Array<Int>
* Uso: crearSecuenciaRandom(n, min, max)
* Precondición: n > 0, min <= max
* Poscondición: Devuelve una secuencia de n elementos de tipo entero, generados
* aleatoriamente en el intervalo [min,..., max] con la misma semilla siempre.
*/
fun crearSecuenciaRandom(n: Int, min: Int, max: Int): Array<Int> {
    // Crea una secuencia de n elementos cuyos valores iniciales son todos 0
    val secuencia: Array<Int> = Array(n, {0})
    val random = Random(99827818718)
    for (i in 0 until n) {
        // Itera sobre los elementos de la secuencia y les asigna un valor aleatorio
        // en el intervalo [min,..., max]
        secuencia[i] = (min..max).random(random)
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
* Uso: mensajeOrdenamientoExitoso(tipoDeOrdenamiento, tamañoN, tiempo)
* Si el usuario especificó más de un intento, se calcula la media y la desviación estándar.
* Si el usuario especificó un solo intento, se imprime el tiempo de ejecución.
*/
fun mensajeOrdenamientoExitoso(tamañoN: Int, tiempo: Array<Long>, media: Double): Unit {
    if (tiempo.size > 1) {
        val desviacionEstandar: Double = calcularDesviacionEstandar(tiempo, media)
        // Se divide entre 1000000000.0 para convertir el tiempo de nanosegundos a segundos
        println("\u001b[32mOrdenamiento exitoso con mergesortInsertion y n = $tamañoN en ${tiempo.size} intentos\u001b[0m")
        println("Tiempo promedio de ejecución de mergesortInsertion con n = $tamañoN:\u001b[33m ${(media/1000000000.0).toDouble()} segundos\u001b[0m")
        println("Desviación estándar de mergesortInsertion con n = $tamañoN:\u001b[33m ${(desviacionEstandar/1000000000.0).toDouble()} segundos\u001b[0m")
    } else {
        println("\u001b[32mOrdenamiento exitoso con mergesortInsertion y n = $tamañoN\u001b[0m")
        println("Tiempo de ejecución de mergesortInsertion con n = $tamañoN:\u001b[33m ${(tiempo[0]/1000000000.0).toDouble()} segundos \u001b[0m")
    }
    println("----------------------------------------------------------------------------------------")
}

/**
* Uso: mensajeErrorEnElOrdenamiento(tipoDeOrdenamiento)
* Imprime un mensaje de error indicando que la secuencia no está ordenada con el tipo de
* ordenamiento especificado
*/
fun mensajeErrorEnElOrdenamiento(tamañoN: Int): Unit {
    println("\u001b[31mError:\u001b[0m La secuencia no está ordenada con mergesortInsertion y n = $tamañoN\u001b[0m")
    println("----------------------------------------------------------------------------------------")
}

fun mensajeMejorN(mejorN: Int, mejorTiempo: Double): Unit {
    println("El mejor n es: \u001b[36m$mejorN\u001b[0m, con un tiempo de ejecución de: \u001b[32m${(mejorTiempo/1000000000.0).toDouble()} segundos\u001b[0m")
}

/**
* Uso: chekearInputValido(args)
* Precondición: args.size == 2
* Poscondición: Devuelve true si los argumentos son válidos, false en caso contrario
*/
fun chekearInputValido(args: Array<String>): Boolean {
    if (args.size != 2){
        // Si la cantidad de argumentos es distinta de 2, imprime un mensaje de error y termina la ejecución
        println("\u001b[31mError:\u001b[0m Cantidad de argumentos incorrecta")
        println("Uso: ./runPruebaMergesort.sh -t <numIntentos>")
        return false
    }
    if (args[0] != "-t") {
        // Si el primer argumento no es "-t", imprime un mensaje de error y termina la ejecución
        println("\u001b[31mError:\u001b[0m Argumento incorrecto")
        println("Uso: ./runPruebaMergesort.sh -t <numIntentos>")
        return false
    }
    return true
}

// Declaración del método principal
fun main(args: Array<String>): Unit {
    if (!chekearInputValido(args)) {
        return
    }

    // Obtiene el número de intentos de ordenamiento
    val numIntentos: Int = args[1].toInt()

    // Crea una secuencia aletoria de tamaño 1000000 con la misma semilla siempre
    val secuencia: Array<Int> = crearSecuenciaRandom(1000000, 0, 900000)
    println("\u001b[36mSecuencia aleatoria de tamaño 1000000 generada\u001b[0m")
    println("----------------------------------------------------------------------------------------")

    val tiempoMergesortPrueba = Array<Long>(numIntentos, {0})
    var tiempoInicial: Long
    var tiempoFinal: Long
    var mejorN: Int = 10
    var mejorTiempo: Double = Double.MAX_VALUE
    var mediaPrueba: Double
    // Se copia la secuencia original para no modificarla
    var secuenciaCopia: Array<Int> = secuencia.copyOf()

    for (i in 1 until 11) {
        // Se realizan los intentos de ordenamiento con n desde 10 hasta 100, aumentando de 10 en 10
        for (j in 0 until numIntentos) {
            // Si no es el primer intento, se copia la secuencia original
            if ((i == 1 && j != 0) || i != 1) {
                secuenciaCopia = secuencia.copyOf()
            }
            // Se ordena la secuencia copia y se mide el tiempo de ejecución
            tiempoInicial = System.nanoTime()
            mergesortInsertionPrueba(secuenciaCopia, i * 10)
            tiempoFinal = System.nanoTime()
            // Se verifica si la secuencia copia está ordenada
            if(!estaEnOrdenAscendente(secuenciaCopia)){
                // Si la secuencia copia no está ordenada, imprime un mensaje de error y termina la ejecución
                mensajeErrorEnElOrdenamiento(i * 10)
                return
            }
            // Se guarda el tiempo de ejecución
            tiempoMergesortPrueba[j] = (tiempoFinal - tiempoInicial)
        }
        // Se guarda el mejor tiempo de ejecución y el tamaño del n correspondiente
        mediaPrueba = calcularMedia(tiempoMergesortPrueba)
        if (mediaPrueba < mejorTiempo) {
            mejorTiempo = mediaPrueba
            mejorN = i * 10
        }
        // Si la secuencia está ordenada en todos los intentos, imprime un mensaje de éxito
        mensajeOrdenamientoExitoso(i * 10, tiempoMergesortPrueba, mediaPrueba)
    }
    mensajeMejorN(mejorN, mejorTiempo)
}
