/**
* Nombre del archivo: DCLSTSP.kt
* Descripcion: Implementacion del algoritmo "Divide, Conquer, Local Search" para el problema del vendedor viajero
* Autores: Luis Miguel Isea 19-10175, Juan Cuevas 19-10056
*/

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.BufferedWriter

fun swap(P: Array<Pair<Double, Double>>, i: Int, j: Int): Unit {
    val temp = P[i]
    P[i] = P[j]
    P[j] = temp
}

fun partitionX(P: Array<Pair<Double, Double>>, l: Int, r: Int): Int {
    val pivot = P[r].first
    var i = l - 1
    for (j in l until r) {
        if (P[j].first <= pivot) {
            i++
            swap(P, i, j)
        }
    }
    swap(P, i + 1, r)
    return i + 1
}

fun quickSortX(P: Array<Pair<Double, Double>>, l: Int, r: Int): Unit {
    if (l < r) {
        val p = partitionX(P, l, r)
        quickSortX(P, l, p - 1)
        quickSortX(P, p + 1, r)
    }
}

fun partitionY(P: Array<Pair<Double, Double>>, l: Int, r: Int): Int {
    val pivot = P[r].second
    var i = l - 1
    for (j in l until r) {
        if (P[j].second <= pivot) {
            i++
            swap(P, i, j)
        }
    }
    swap(P, i + 1, r)
    return i + 1
}

fun quickSortY(P: Array<Pair<Double, Double>>, l: Int, r: Int): Unit {
    if (l < r) {
        val p = partitionY(P, l, r)
        quickSortY(P, l, p - 1)
        quickSortY(P, p + 1, r)
    }
}

fun ordenarCoordXIguales(P: Array<Pair<Double, Double>>): Unit {
    val n = P.size
    for (i in 0 until n - 1) {
        if (P[i].first == P[i + 1].first && obtenerCoordMayor(P[i], P[i + 1], 'X') == P[i]) {
            swap(P, i, i + 1)
        }
    }
}

fun ordenarCoordYIguales(P: Array<Pair<Double, Double>>): Unit {
    val n = P.size
    for (i in 0 until n - 1) {
        if (P[i].second == P[i + 1].second && obtenerCoordMayor(P[i], P[i + 1], 'Y') == P[i]) {
            swap(P, i, i + 1)
        }
    }
}

fun obtenerCoordMayor(p1: Pair<Double, Double>, p2: Pair<Double, Double>, ejeDeCorte: Char): Pair<Double, Double> {
    if (ejeDeCorte == 'X') {
        if (p1.second > p2.second) {
            return p1
        }
        else {
            return p2
        }
    }
    else {
        if (p1.first > p2.first) {
            return p1
        }
        else {
            return p2
        }
    }
}

fun obtenerPuntoDeCorte(P: Array<Pair<Double, Double>>, ejeDeCorte: Char): Pair<Double, Double> {
    val n = P.size
    val pos = Math.ceil(n/2.0).toInt() - 1
    if (ejeDeCorte == 'X') {
        quickSortX(P, 0, n - 1)
        ordenarCoordXIguales(P)
    }
    else {
        quickSortY(P, 0, n - 1)
        ordenarCoordYIguales(P)
    }
    return P[pos]
}

fun obtenerPuntoDeCorteMitad(rectangulo: Array<Pair<Double, Double>>, eje: Char): Pair<Double, Double> {
    val xMin = obtenerCoordMinX(rectangulo)
    val yMin = obtenerCoordMinY(rectangulo)
    if (eje == 'X') {
        return Pair(xMin + (obtenerCoordMaxX(rectangulo) - xMin)/2, yMin)
    }
    else {
        return Pair(xMin, yMin + (obtenerCoordMaxY(rectangulo) - yMin)/2)
    }
}

fun aplicarCorte(eje: Int, puntoDeCorte: Pair<Double, Double>, rectangulo: Array<Pair<Double, Double>>): Pair<Double, Double>{

}

fun obtenerParticiones(P: Array<Pair<Double, Double>>): Pair<Array<Double>, Array<Double>>{

}

fun obtenerCoordMaxX(P: Array<Pair<Double, Double>>): Double{
    return P.maxBy { it.first }!!.first
}

fun obtenerCoordMaxY(P: Array<Pair<Double, Double>>): Double{
    return P.maxBy { it.second }!!.second
}

fun obtenerCoordMinX(P: Array<Pair<Double, Double>>): Double{
    return P.minBy { it.first }!!.first
}

fun obtenerCoordMinY(P: Array<Pair<Double, Double>>): Double{
    return P.minBy { it.second }!!.second
}

fun crearRectangulo(P: Array<Pair<Double, Double>>): Array<Pair<Double, Double>>{
    val maxX = obtenerCoordMaxX(P)
    val maxY = obtenerCoordMaxY(P)
    val minX = obtenerCoordMinX(P)
    val minY = obtenerCoordMinY(P)
    return arrayOf(Pair(minX, minY), Pair(maxX, maxY), Pair(maxX, minY), Pair(minX, maxY))
}

fun obtenterPuntosRectangulo(P: Array<Pair<Double, Double>>, rectangulo: Array<Pair<Double, Double>>): Array<Pair<Double, Double>>{

}

fun distancia2D(p1: Pair<Double, Double>, p2: Pair<Double, Double>): Double{
    val x = p1.first - p2.first
    val y = p1.second - p2.second
    return Math.sqrt((x*x + y*y).toDouble())
}

fun distancia3D(p1: Triple<Double, Double, Double>, p2: Triple<Double, Double, Double>): Double{
    val x = p1.first - p2.first
    val y = p1.second - p2.second
    val z = p1.third - p2.third
    return Math.sqrt((x*x + y*y + z*z).toDouble())
}

fun distanciaGanada(n1: Double, n2: Double, o1: Double, o2: Double): Double{
    return (n1 + n2) - (o1 + o2)
}

fun divideAndConquerAndLocalSearchTSP(P: Array<Pair<Double, Double>>): Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>{
    return
}

fun combinarCiclos(c1: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>, c2: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>): Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>{
    return
}

fun distanciaTotal(ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>): Double{
    var acc = 0
    for (i in 0 until ciclo.size){
        acc += distancia2D(ciclo[i].first, ciclo[i].second)
    }
}

fun divideAndConquerTSP (P: Array<Pair<Double, Double>>): Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>{
    val n = P.size
    if (n == 0){
        return arrayOf()
    }
    else if (n == 1){
        return arrayOf(Pair(P[0], P[0]))
    }
    else if (n == 2){
        return arrayOf(Pair(P[0], P[1]), Pair(P[1], P[0]))
    }
    else if (n == 3){
        return arrayOf(Pair(P[0], P[1]), Pair(P[1], P[2]), Pair(P[2], P[0]))
    }
    else{
        val (pright, pleft) = obtenerParticiones(P)
        val c1 = divideAndConquerTSP(pright)
        val c2 = divideAndConquerTSP(pleft)
        return combinarCiclos(c1, c2)
    }
}

fun main(args: Array<String>) {
    val archivoEntrada = File(args[0])
    val reader = BufferedReader(FileReader(archivoEntrada, Charsets.UTF_8))

    val nombre = reader.readLine().split(":")[1].trim()
    reader.readLine() // Tipo de archivo que no nos interesa
    reader.readLine() // Comentario que no nos interesa
    val numeroCiudades = reader.readLine().split(":")[1].trim().toInt()
    reader.readLine() // Tipo de coordenadas que no nos interesa
    reader.readLine() // Comentario que no nos interesa

    // Creamos un arreglo de pares de enteros para almacenar las coordenadas de las ciudades
    val ciudades = Array<Pair<Double, Double>>(numeroCiudades, { Pair(0.0, 0.0) })
    for (i in 0 until numeroCiudades) {
        val ciudad = reader.readLine().split(" ")
        val x = ciudad[1].trim().toDouble()
        val y = ciudad[2].trim().toDouble()
        ciudades[i] = Pair(x, y)
    }

    // Aplicamos el algoritmo de divide and conquer para obtener la solución
    val solucion = divideAndConquerTSP(ciudades)
    val distanciaRuta = distanciaTotal(solucion)

    // Escribimos la solución en un archivo de salida
    val archivoSalida = archivoSalida(args[1])
    val writer = BufferedWriter(FileWriter(archivoSalida))

    writer.write("NAME : ${nombre}")
    writer.newLine("COMMENT : Length ${distanciaRuta}")
    writer.newLine("TYPE : TOUR")
    writer.newLine("DIMENSION : ${numeroCiudades}")
    writer.newLine("TOUR_SECTION")

    for (i in 0 until numeroCiudades) {
        val x = solucion[i].first.first
        val y = solucion[i].first.second
        writer.newLine("${i+1} ${x} ${y}")
        if (i == numeroCiudades - 1) {
            val xf = solucion[i].second.first
            val yf = solucion[i].second.second
            writer.newLine("${i+2} $xf $yf")
        }
        
        writer.newLine()
    }

    writer.newLine("EOF")
}
