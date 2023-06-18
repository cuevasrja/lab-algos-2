/**
* Verificador de soluciones para el problema del vendedor viajero
* Recibe como argumentos el archivo de instancia y el archivo de solucion
* Imprime si la solucion es correcta o no y muestra algunos errores si los hay
*/

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

/**
* Funcion: distancia2D(p1: Pair<Int, Int>, p2: Pair<Int, Int>)
* Entradas: p1 y p2, dos pares de enteros que representan las coordenadas de dos puntos
* Salidas: La distancia euclidiana entre los dos puntos
* Descripcion: Calcula la distancia euclidiana entre dos puntos en el plano
*/
fun distancia2D(p1: Pair<Double, Double>, p2: Pair<Double, Double>): Double {
    val x = p1.first - p2.first
    val y = p1.second - p2.second
    return Math.sqrt((x * x + y * y).toDouble())
}

/**
* Funcion: distanciaRuta(ciudades: Array<Pair<Int, Int>>)
* Entradas: ciudades, un arreglo de pares de enteros que representan las coordenadas de las ciudades
* Salidas: La distancia total de la ruta que recorre todas las ciudades en el orden dado
* Descripcion: Calcula la distancia total de la ruta que recorre todas las ciudades en el orden dado
*/
fun distanciaRuta(ciudades: Array<Pair<Double, Double>>): Int {
    var acc: Double = 0.0
    for (i in 0 until ciudades.size - 1) {
        acc += distancia2D(ciudades[i], ciudades[i + 1])
    }
    return acc.toInt()
}

/**
* Funcion: checkSolution(ciudadesInstancia: Array<Pair<Int, Int>>, ciudadesSolucion: Array<Pair<Int, Int>>)
* Entradas: ciudadesInstancia, un arreglo de pares de enteros que representan las coordenadas de las ciudades de la instancia
*           ciudadesSolucion, un arreglo de pares de enteros que representan las coordenadas de las ciudades de la solucion
* Salidas: true si la solucion es correcta, false en otro caso
* Descripcion: Verifica que la solucion dada sea correcta
*/
fun checkSolution(ciudades: Array<Pair<Double, Double>>, indicesInstancia: Array<Int>, indicesSolucion: Array<Int>): Boolean {
    // Verificar que la solucion tenga todas las ciudades de la instancia
    for (i in 0 until indicesInstancia.size) {
        if (!indicesInstancia.contains(indicesSolucion[i])) {
            println("La solución no contiene la ciudad ${indicesSolucion[i] + 1}")
            return false
        }
    }
    // Verificar que la solucion no tenga ciudades repetidas
    for (i in 0 until indicesSolucion.size) {
        for (j in i + 1 until indicesSolucion.size) {
            if (indicesSolucion[i] == indicesSolucion[j] && (i != 0 && j != indicesSolucion.size - 1)) {
                println("La solución tiene la ciudad ${indicesSolucion[i] + 1} repetida")
                return false
            }
        }
    }
    // Verificar si la ruta es correcta
    for (i in 0 until indicesSolucion.size) {
        val instancia = ciudades[indicesInstancia[i]]
        val solucion = ciudades[indicesSolucion[i]]
        if (instancia != solucion) {
            println("La ciudad ${indicesSolucion[i] + 1} está en la posición incorrecta")
            return false
        }
    }
    return true
}

fun main(args: Array<String>) {
    // args[0] es el archivo de instancia
    // args[1] es el archivo de solucion

    val archivoInstancia = File(args[0])
    val archivoSolucion = File(args[1])

    // Leer las ciudades de la instancia
    val lectorInstancia = BufferedReader(FileReader(archivoInstancia, Charsets.UTF_8))
    lectorInstancia.readLine() // Ignorar primera linea. Es el nombre del archivo
    lectorInstancia.readLine() // Ignorar segunda linea. Es el comentario
    lectorInstancia.readLine() // Ignorar tercera linea. Es el tipo de archivo
    val numeroCiudadesInstancia = lectorInstancia.readLine().split(":")[1].trim().toInt()
    lectorInstancia.readLine() // Ignorar linea. Es el comentario
    val indicesInstancia = Array<Int>(numeroCiudadesInstancia, { 0 })
    val ciudades = Array<Pair<Double, Double>>(numeroCiudadesInstancia, { Pair(0.0, 0.0) })
    for (i in 0 until numeroCiudadesInstancia) {
        val ciudad = lectorInstancia.readLine().trim().split(" ")
        val indice = ciudad[0].trim().toInt()
        val x = ciudad[1].trim().toDouble()
        val y = ciudad[2].trim().toDouble()
        indicesInstancia[i] = indice - 1
        ciudades[i] = Pair(x, y)
    }
    lectorInstancia.close()

    // Leer las ciudades de la solucion
    val lectorSolucion = BufferedReader(FileReader(archivoSolucion, Charsets.UTF_8))
    lectorSolucion.readLine() // Ignorar primera linea. Es el nombre del archivo
    lectorSolucion.readLine() // Ignorar segunda linea. Es el comentario
    val numeroCiudadesSolucion = lectorSolucion.readLine().split(":")[1].trim().toInt()
    lectorSolucion.readLine() // Ignorar linea. Es el comentario
    val indicesSolucion = Array<Int>(numeroCiudadesSolucion, { 0 })
    for (i in 0 until numeroCiudadesSolucion) {
        val indice = lectorSolucion.readLine().trim().toInt()
        indicesSolucion[i] = indice-1
    }
    lectorSolucion.close()

    if (!checkSolution(ciudades, indicesInstancia, indicesSolucion)) {
        // Si la solucion no es correcta, terminar el programa
        println("Solución incorrecta")
        return
    }

    println("Solución correcta")
}