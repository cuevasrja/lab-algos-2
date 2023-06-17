/**
* Nombre del archivo: DCLSTSP.kt
* Descripcion: Implementacion del algoritmo "Divide, Conquer, Local Search" para el problema del agente viajero
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

fun obtenerPuntoDeCorteMitad(rectangulo: Array<Pair<Double, Double>>, ejeDeCorte: Char): Pair<Double, Double> {
    val xMin = obtenerCoordMinX(rectangulo)
    val yMin = obtenerCoordMinY(rectangulo)
    if (ejeDeCorte == 'X') {
        return Pair(xMin + (obtenerCoordMaxX(rectangulo) - xMin)/2, yMin)
    }
    else {
        return Pair(xMin, yMin + (obtenerCoordMaxY(rectangulo) - yMin)/2)
    }
}

fun aplicarCorte(ejeDeCorte: Char, puntoDeCorte: Pair<Double, Double>, rectangulo: Array<Pair<Double, Double>>): Array<Array<Pair<Double, Double>>>{
    val rectanguloIzq = Array<Pair<Double, Double>>(4, {Pair(0.0, 0.0)})
    val rectanguloDer = Array<Pair<Double, Double>>(4, {Pair(0.0, 0.0)})
    val xMin = obtenerCoordMinX(rectangulo)
    val yMin = obtenerCoordMinY(rectangulo)
    val xMax = obtenerCoordMaxX(rectangulo)
    val yMax = obtenerCoordMaxY(rectangulo)
    if (ejeDeCorte == 'X') {
        rectanguloIzq[0] = Pair(xMin, yMin)
        rectanguloIzq[1] = Pair(puntoDeCorte.first, yMin)
        rectanguloIzq[2] = Pair(puntoDeCorte.first, yMax)
        rectanguloIzq[3] = Pair(xMin, yMax)
        rectanguloDer[0] = Pair(puntoDeCorte.first, yMin)
        rectanguloDer[1] = Pair(xMax, yMin)
        rectanguloDer[2] = Pair(xMax, yMax)
        rectanguloDer[3] = Pair(puntoDeCorte.first, yMax)
    }
    else {
        rectanguloIzq[0] = Pair(xMin, yMin)
        rectanguloIzq[1] = Pair(xMax, yMin)
        rectanguloIzq[2] = Pair(xMax, puntoDeCorte.second)
        rectanguloIzq[3] = Pair(xMin, puntoDeCorte.second)
        rectanguloDer[0] = Pair(xMin, puntoDeCorte.second)
        rectanguloDer[1] = Pair(xMax, puntoDeCorte.second)
        rectanguloDer[2] = Pair(xMax, yMax)
        rectanguloDer[3] = Pair(xMin, yMax)
    }
    val rectangulos = Array<Array<Pair<Double, Double>>>(2, {Array<Pair<Double, Double>>(4, {Pair(0.0, 0.0)})})
    rectangulos[0] = rectanguloIzq
    rectangulos[1] = rectanguloDer
    return rectangulos
}

fun obtenerCoordMaxX(P: Array<Pair<Double, Double>>): Double{
    // Buscamos el punto con mayor coordenada X en el arreglo P
    return P.maxBy { it.first }!!.first
}

fun obtenerCoordMaxY(P: Array<Pair<Double, Double>>): Double{
    // Buscamos el punto con mayor coordenada Y en el arreglo P
    return P.maxBy { it.second }!!.second
}

fun obtenerCoordMinX(P: Array<Pair<Double, Double>>): Double{
    // Buscamos el punto con menor coordenada X en el arreglo P
    return P.minBy { it.first }!!.first
}

fun obtenerCoordMinY(P: Array<Pair<Double, Double>>): Double{
    // Buscamos el punto con menor coordenada Y en el arreglo P
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
    // Rectangulo = [Punto inferior izquierdo, Punto superior derecho, Punto inferior derecho, Punto superior izquierdo]
    // Filtrar puntos que esten dentro del rectangulo, buscando pares <= Punto superior derecho y >= Punto inferior izquierdo
    return P.filter { it.first >= rectangulo[0].first && it.first <= rectangulo[1].first && it.second >= rectangulo[0].second && it.second <= rectangulo[1].second }.toTypedArray()
}

fun distancia2D(p1: Pair<Double, Double>, p2: Pair<Double, Double>): Double{
    val x = p1.first - p2.first
    val y = p1.second - p2.second
    return Math.sqrt((x*x + y*y).toDouble())
}

fun obtenerParticiones(P: Array<Pair<Double, Double>>): Pair<Array<Double>, Array<Double>>{
    val rectangulo = crearRectangulo(P)
    val xDim = obtenerCoordMaxX(rectangulo) - obtenerCoordMinX(rectangulo)
    val yDim = obtenerCoordMaxY(rectangulo) - obtenerCoordMinY(rectangulo)
    if (xDim > yDim){
        // TODO: Particionar en X
    }
    else {
        // TODO: Particionar en Y
    }
    // TODO: Obtener punto de corte
}

fun distanciaGanada(n1: Double, n2: Double, o1: Double, o2: Double): Double{
    return (n1 + n2) - (o1 + o2)
}

fun combinarCiclos(c1: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>, c2: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>): Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>{
    if (c1.size == 0){
        // Si c1 es vacio, retornamos c2
        return c2
    }
    else if (c2.size == 0){
        // Si c2 es vacio, retornamos c1
        return c1
    }
    // Tomamos los el tamaño de los ciclos
    val n = c1.size
    val m = c2.size
    // Definimos minDist como el valor maximo de Double
    var minDist = Double.MAX_VALUE
    // Agregamos los puntos de c1 y c2 a un arreglo de puntos
    var aggC1: Pair<Pair<Double, Double>, Pair<Double, Double>> = Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))
    var aggC2: Pair<Pair<Double, Double>, Pair<Double, Double>> = Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))
    // Eliminamos los puntos de c1 y c2 que ya estan en aggC1 y aggC2
    var delC1: Pair<Pair<Double, Double>, Pair<Double, Double>> = Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))
    var delC2: Pair<Pair<Double, Double>, Pair<Double, Double>> = Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))
    
    // Iteramos sobre los ciclos
    // Sea el par de coordenadas (a, b) en c1. Siendo a y b puntos en el plano
    for (i in 0 until n){
        // Calculamos la distancia entre a y b
        val dOld1 = distancia2D(c1[i].first, c1[i].second)
        // Sea el par de coordenadas (c, d) en c2. Siendo c y d puntos en el plano
        for (j in 0 until m){
            val dOld2 = distancia2D(c2[j].first, c2[j].second) // Calculamos la distancia entre c y d
            val dNew1 = distancia2D(c1[i].first, c2[j].first) // Calculamos la distancia entre a y c
            val dNew2 = distancia2D(c1[i].second, c2[j].second) // Calculamos la distancia entre b y d
            val dNew3 = distancia2D(c1[i].first, c2[j].second) // Calculamos la distancia entre a y d
            val dNew4 = distancia2D(c1[i].second, c2[j].first) // Calculamos la distancia entre b y c
            val g1 = distanciaGanada(dNew1, dNew2, dOld1, dOld2) // Calculamos la distancia ganada entre (a, c) y (b, d)
            val g2 = distanciaGanada(dNew3, dNew4, dOld1, dOld2) // Calculamos la distancia ganada entre (a, d) y (b, c)
            val dist = Math.min(g1, g2) // Tomamos la distancia minima entre g1 y g2

            // Si la distancia es menor a minDist, actualizamos minDist
            if (dist < minDist){
                minDist = dist
                // Si g1 es menor a g2, agregamos (a, c) y (b, d) a aggC1 y aggC2 respectivamente
                if (g1 < g2){
                    aggC1 = Pair(c1[i].first, c2[j].first)
                    aggC2 = Pair(c1[i].second, c2[j].second)
                }
                // En caso contrario, agregamos (a, d) y (b, c) a aggC1 y aggC2 respectivamente
                else {
                    aggC1 = Pair(c1[i].first, c2[j].second)
                    aggC2 = Pair(c1[i].second, c2[j].first)
                }
                // Eliminamos los puntos de (a, b) y (c, d) de c1 y c2 respectivamente
                delC1 = c1[i]
                delC2 = c2[j]
            }
        }
    }
    // Creamos un nuevo ciclo con los puntos de c1 y c2
    val ciclo = Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>(n+m-2){Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))}
    var index = 0
    // Iteramos sobre c1
    for (i in 0 until n){
        // Si el punto no es el punto a eliminar, lo agregamos al nuevo ciclo
        if (c1[i] != delC1){
            ciclo[index] = c1[i]
            index++
        }
    }
    // Iteramos sobre c2
    for (i in 0 until m){
        // Si el punto no es el punto a eliminar, lo agregamos al nuevo ciclo
        if (c2[i] != delC2){
            ciclo[index] = c2[i]
            index++
        }
    }
    // Agregamos los puntos de aggC1 y aggC2 al nuevo ciclo
    ciclo[index] = aggC1
    ciclo[index+1] = aggC2
    return ciclo
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
        // Cuando n = 0, no hay ciclos
        return arrayOf()
    }
    else if (n == 1){
        // Cuando n = 1, se tiene un solo ciclo
        return arrayOf(Pair(P[0], P[0]))
    }
    else if (n == 2){
        // Cuando n = 2, se tiene un solo ciclo
        return arrayOf(Pair(P[0], P[1]), Pair(P[1], P[0]))
    }
    else if (n == 3){
        // Cuando n = 3, se tienen 3 posibles ciclos, todos con la misma distancia
        return arrayOf(Pair(P[0], P[1]), Pair(P[1], P[2]), Pair(P[2], P[0]))
    }
    else{
        // Cuando n > 3, se tiene que dividir el conjunto de puntos en dos
        // Particionar el conjunto de puntos en dos
        val (pright, pleft) = obtenerParticiones(P)
        // Resolver el problema para cada conjunto de puntos
        val c1 = divideAndConquerTSP(pright)
        val c2 = divideAndConquerTSP(pleft)
        // Combinar las soluciones
        return combinarCiclos(c1, c2)
    }
}

fun divideAndConquerAndLocalSearchTSP(P: Array<Pair<Double, Double>>): Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>{
    val c = divideAndConquerTSP(P)
    return // TODO: Implementar busqueda local
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
        // Las coordenadas de las ciudades vienen en el archivo de entrada con un espacio de separación
        // por lo que debemos eliminar los espacios en blanco al inicio y al final de cada coordenada
        // para poder convertirlas a reales
        val x = ciudad[1].trim().toDouble()
        val y = ciudad[2].trim().toDouble()
        ciudades[i] = Pair(x, y)
    }
    reader.close()

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
    }

    // Escribimos el EOF que indica el final del archivo
    writer.newLine("EOF")
    writer.close()
}
