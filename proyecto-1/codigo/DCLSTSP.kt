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

/**
* Funcion: swap(P: Array<Pair<Int, Int>>, i: Int, j: Int)
* Entradas: P, un arreglo de pares de enteros que representan las coordenadas de las ciudades
*           i, un entero que representa la posicion de un elemento del arreglo
*           j, un entero que representa la posicion de un elemento del arreglo
* Salidas: Unit
* Descripcion: Intercambia los elementos en las posiciones i y j del arreglo P
*/
fun swap(P: Array<Pair<Double, Double>>, i: Int, j: Int): Unit {
    val temp = P[i]
    P[i] = P[j]
    P[j] = temp
}

/**
* Funcion: partitionX(P: Array<Pair<Double, Double>>, l: Int, r: Int)
* Entradas: P, un arreglo de pares de enteros que representan las coordenadas de las ciudades
*           l, un entero que representa la posicion del primer elemento del subarreglo a particionar
*           r, un entero que representa la posicion del ultimo elemento del subarreglo a particionar
* Salidas: Int
* Descripcion: Particiona el arreglo P en dos subarreglos en base a las coordenadas X de las ciudades, uno con los elementos menores o iguales al pivote y otro con los elementos mayores al pivote
*/
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

/**
* Funcion: quickSortX(P: Array<Pair<Double, Double>>, l: Int, r: Int)
* Entradas: P, un arreglo de pares de enteros que representan las coordenadas de las ciudades
*           l, un entero que representa la posicion del primer elemento del subarreglo a ordenar
*           r, un entero que representa la posicion del ultimo elemento del subarreglo a ordenar
* Salidas: Unit
* Descripcion: Ordena el arreglo P en base a las coordenadas X de las ciudades
*/
fun quickSortX(P: Array<Pair<Double, Double>>, l: Int, r: Int): Unit {
    if (l < r) {
        val p = partitionX(P, l, r)
        quickSortX(P, l, p - 1)
        quickSortX(P, p + 1, r)
    }
}

/**
* Funcion: partitionY(P: Array<Pair<Double, Double>>, l: Int, r: Int)
* Entradas: P, un arreglo de pares de enteros que representan las coordenadas de las ciudades
*           l, un entero que representa la posicion del primer elemento del subarreglo a particionar
*           r, un entero que representa la posicion del ultimo elemento del subarreglo a particionar
* Salidas: Int
* Descripcion: Particiona el arreglo P en dos subarreglos en base a las coordenadas Y de las ciudades, uno con los elementos menores o iguales al pivote y otro con los elementos mayores al pivote
*/
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

/**
* Funcion: quickSortY(P: Array<Pair<Double, Double>>, l: Int, r: Int)
* Entradas: P, un arreglo de pares de enteros que representan las coordenadas de las ciudades
*           l, un entero que representa la posicion del primer elemento del subarreglo a ordenar
*           r, un entero que representa la posicion del ultimo elemento del subarreglo a ordenar
* Salidas: Unit
* Descripcion: Ordena el arreglo P en base a las coordenadas Y de las ciudades
*/
fun quickSortY(P: Array<Pair<Double, Double>>, l: Int, r: Int): Unit {
    if (l < r) {
        val p = partitionY(P, l, r)
        quickSortY(P, l, p - 1)
        quickSortY(P, p + 1, r)
    }
}

/**
* Funcion: ordenarCoordXIguales(P: Array<Pair<Double, Double>>)
* Entradas: P, un arreglo de pares de enteros que representan las coordenadas de las ciudades
* Salidas: Unit
* Descripcion: Ordena los elementos del arreglo P cuyas coordenadas X son iguales en base a las coordenadas Y de las ciudades
*/
fun ordenarCoordXIguales(P: Array<Pair<Double, Double>>): Unit {
    val n = P.size
    for (i in 0 until n - 1) {
        if (P[i].first == P[i + 1].first && obtenerCoordMayor(P[i], P[i + 1], 'Y') == P[i]) {
            swap(P, i, i + 1)
        }
    }
}

/**
* Funcion: ordenarCoordYIguales(P: Array<Pair<Double, Double>>)
* Entradas: P, un arreglo de pares de enteros que representan las coordenadas de las ciudades
* Salidas: Unit
* Descripcion: Ordena los elementos del arreglo P cuyas coordenadas Y son iguales en base a las coordenadas X de las ciudades
*/
fun ordenarCoordYIguales(P: Array<Pair<Double, Double>>): Unit {
    val n = P.size
    for (i in 0 until n - 1) {
        if (P[i].second == P[i + 1].second && obtenerCoordMayor(P[i], P[i + 1], 'X') == P[i]) {
            swap(P, i, i + 1)
        }
    }
}

/**
* Funcion: obtenerCoordMayor(p1: Pair<Double, Double>, p2: Pair<Double, Double>, ejeDeCorte: Char)
* Entradas: p1, un par de enteros que representan las coordenadas de una ciudad
*           p2, un par de enteros que representan las coordenadas de una ciudad
*           ejeDeCorte, un caracter que representa el eje de corte actual
* Salidas: Pair<Double, Double>
* Descripcion: Obtiene el par de coordenadas mayor entre p1 y p2 en base al eje de corte actual
*/
fun obtenerCoordMayor(p1: Pair<Double, Double>, p2: Pair<Double, Double>, ejeDeCorte: Char): Pair<Double, Double> {
    if (ejeDeCorte == 'X') {
        if (p1.first > p2.first) {
            return p1
        }
        else {
            return p2
        }
    }
    else {
        if (p1.second > p2.second) {
            return p1
        }
        else {
            return p2
        }
    }
}

/**
* Funcion: obtenerPuntoDeCorte(P: Array<Pair<Double, Double>>, ejeDeCorte: Char)
* Entradas: P, un arreglo de pares de enteros que representan las coordenadas de las ciudades
*           ejeDeCorte, un caracter que representa el eje de corte especificado
* Salidas: Pair<Double, Double>
* Descripcion: Obtiene el punto de corte del arreglo P en base al eje de corte especificado
*/
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

/**
* Funcion: obtenerPuntoDeCorteMitad(rectangulo: Array<Pair<Double, Double>>, ejeDeCorte: Char)
* Entradas: rectangulo, un arreglo de pares de enteros que representan las coordenadas de un rectangulo que contiene a las ciudades
*           ejeDeCorte, un caracter que representa el eje de corte especificado
* Salidas: Pair<Double, Double>
* Descripcion: Obtiene el punto de corte del rectangulo en base a la mitad del eje de corte especificado
*/
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

/**
* Funcion: aplicarCorte(ejeDeCorte: Char, puntoDeCorte: Pair<Double, Double>, rectangulo: Array<Pair<Double, Double>>)
* Entradas: ejeDeCorte, un caracter que representa el eje de corte especificado
*           puntoDeCorte, un par de enteros que representan las coordenadas del punto de corte
*           rectangulo, un arreglo de pares de enteros que representan las coordenadas de un rectangulo que contiene a las ciudades
* Salidas: Pair<Array<Pair<Double, Double>>, Array<Pair<Double, Double>>>
* Descripcion: Aplica el corte al rectangulo en base al eje de corte y al punto de corte especificados
*/
fun aplicarCorte(ejeDeCorte: Char, puntoDeCorte: Pair<Double, Double>, rectangulo: Array<Pair<Double, Double>>): Pair<Array<Pair<Double, Double>>, Array<Pair<Double, Double>>> {
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
    return Pair(rectanguloIzq, rectanguloDer)
}

/**
* Funcion: obtenerCoordMaxX(P: Array<Pair<Double, Double>>)
* Entradas: P: Arreglo de puntos
* Salidas: Coordenada X del punto con mayor coordenada X en el arreglo P
*/
fun obtenerCoordMaxX(P: Array<Pair<Double, Double>>): Double{
    // Buscamos el punto con mayor coordenada X en el arreglo P
    return P.maxBy { it.first }.first
}

/**
* Funcion: obtenerCoordMaxY(P: Array<Pair<Double, Double>>)
* Entradas: P: Arreglo de puntos
* Salidas: Coordenada Y del punto con mayor coordenada Y en el arreglo P
*/
fun obtenerCoordMaxY(P: Array<Pair<Double, Double>>): Double{
    // Buscamos el punto con mayor coordenada Y en el arreglo P
    return P.maxBy { it.second }.second
}

/**
* Funcion: obtenerCoordMinX(P: Array<Pair<Double, Double>>)
* Entradas: P: Arreglo de puntos
* Salidas: Coordenada X del punto con menor coordenada X en el arreglo P
*/
fun obtenerCoordMinX(P: Array<Pair<Double, Double>>): Double{
    // Buscamos el punto con menor coordenada X en el arreglo P
    return P.minBy { it.first }.first
}

/**
* Funcion: obtenerCoordMinY(P: Array<Pair<Double, Double>>)
* Entradas: P: Arreglo de puntos
* Salidas: Coordenada Y del punto con menor coordenada Y en el arreglo P
*/
fun obtenerCoordMinY(P: Array<Pair<Double, Double>>): Double{
    // Buscamos el punto con menor coordenada Y en el arreglo P
    return P.minBy { it.second }.second
}

/**
* Funcion: crearRectangulo(P: Array<Pair<Double, Double>>)
* Entradas: P: Arreglo de puntos
* Salidas: Arreglo de puntos que representan un rectangulo que contiene a todos los puntos en P
*/
fun crearRectangulo(P: Array<Pair<Double, Double>>): Array<Pair<Double, Double>>{
    val maxX = obtenerCoordMaxX(P)
    val maxY = obtenerCoordMaxY(P)
    val minX = obtenerCoordMinX(P)
    val minY = obtenerCoordMinY(P)
    // Rectangulo = [Punto inferior izquierdo, Punto superior derecho, Punto inferior derecho, Punto superior izquierdo]
    return arrayOf(Pair(minX, minY), Pair(maxX, maxY), Pair(maxX, minY), Pair(minX, maxY))
}

/**
* Funcion: obtenterPuntosRectangulo(P: Array<Pair<Double, Double>>, rectangulo: Array<Pair<Double, Double>>)
* Entradas: P: Arreglo de puntos, rectangulo: Arreglo de puntos que representan un rectangulo
* Salidas: Arreglo de puntos que estan dentro del rectangulo
*/
fun obtenterPuntosRectangulo(P: Array<Pair<Double, Double>>, rectangulo: Array<Pair<Double, Double>>, puntoDeCorte: Pair<Double,Double>): Array<Pair<Double, Double>>{
    var numElementos = 0
    for (punto in P){
        if (punto.first >= rectangulo[0].first && punto.first <= rectangulo[1].first && punto.second >= rectangulo[0].second && punto.second <= rectangulo[3].second){
            if (numElementos == 0 && punto == puntoDeCorte) {
                continue
            }
            numElementos++
        }
    }
    val puntosRectangulo = Array<Pair<Double, Double>>(numElementos, {Pair(0.0, 0.0)})
    var i = 0
    for (punto in P){
        if (punto.first >= rectangulo[0].first && punto.first <= rectangulo[1].first && punto.second >= rectangulo[0].second && punto.second <= rectangulo[3].second){
            if (i == 0 && punto == puntoDeCorte) {
                continue
            }
            puntosRectangulo[i] = punto
            i++
        }
    }
    return puntosRectangulo
}

/**
* Funcion: distancia2D(p1: Pair<Double, Double>, p2: Pair<Double, Double>)
* Entradas: p1: Par de coordenadas, p2: Par de coordenadas
* Salidas: Distancia entre los puntos p1 y p2 como un Int
*/
fun distancia2D(p1: Pair<Double, Double>, p2: Pair<Double, Double>): Int{
    val x = p1.first - p2.first
    val y = p1.second - p2.second
    return Math.sqrt((x*x + y*y).toDouble()).toInt()
}

/**
* Funcion: obtenerParticiones(P: Array<Pair<Double, Double>>)
* Entradas: P: Arreglo de puntos
* Salidas: Par de arreglos de puntos que representan las particiones de P
*/
fun obtenerParticiones(P: Array<Pair<Double, Double>>): Pair<Array<Pair<Double, Double>>, Array<Pair<Double, Double>>>{
    val rectangulo = crearRectangulo(P)
    val xDim = obtenerCoordMaxX(rectangulo) - obtenerCoordMinX(rectangulo)
    val yDim = obtenerCoordMaxY(rectangulo) - obtenerCoordMinY(rectangulo)
    var ejeDeCorte: Char
    if (xDim > yDim) {
        ejeDeCorte = 'X'
    } else {
        ejeDeCorte = 'Y'
    }
    var puntoDeCorte = obtenerPuntoDeCorte(P, ejeDeCorte)
    var rectangulosInternos = aplicarCorte(ejeDeCorte, puntoDeCorte, rectangulo)
    var particionIzq = obtenterPuntosRectangulo(P, rectangulosInternos.first, puntoDeCorte)
    var particionDer = obtenterPuntosRectangulo(P, rectangulosInternos.second, puntoDeCorte)
    if ((particionIzq.size == 0 && particionDer.size > 3) || (particionDer.size == 0 && particionIzq.size > 3)){
        if (ejeDeCorte == 'X') {
            ejeDeCorte = 'Y'
        } else {
            ejeDeCorte = 'X'
        }
        puntoDeCorte = obtenerPuntoDeCorte(P, ejeDeCorte)
        rectangulosInternos = aplicarCorte(ejeDeCorte, puntoDeCorte, rectangulo)
        particionIzq = obtenterPuntosRectangulo(P, rectangulosInternos.first, puntoDeCorte)
        particionDer = obtenterPuntosRectangulo(P, rectangulosInternos.second, puntoDeCorte)
        if ((particionIzq.size == 0 && particionDer.size > 3) || (particionDer.size == 0 && particionIzq.size > 3)){
            if (ejeDeCorte == 'X') {
                ejeDeCorte = 'Y'
            } else {
                ejeDeCorte = 'X'
            }
            puntoDeCorte = obtenerPuntoDeCorteMitad(P, ejeDeCorte)
            rectangulosInternos = aplicarCorte(ejeDeCorte, puntoDeCorte, rectangulo)
            particionIzq = obtenterPuntosRectangulo(P, rectangulosInternos.first, puntoDeCorte)
            particionDer = obtenterPuntosRectangulo(P, rectangulosInternos.second, puntoDeCorte)
        }
    }
    return Pair(particionIzq, particionDer)
}

/**
* Funcion: distanciaGanada(n1: Int, n2: Int, o1: Int, o2: Int)
* Entradas: n1: Distancia del primer ciclo, n2: Distancia del segundo ciclo, o1: Distancia del primer ciclo original, o2: Distancia del segundo ciclo original
* Salidas: Distancia ganada al combinar los ciclos
*/
fun distanciaGanada(n1: Int, n2: Int, o1: Int, o2: Int): Int{
    return ((n1 + n2) - (o1 + o2)).toInt()
}

/**
* Funcion: combinarCiclos(c1: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>, c2: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>)
* Entradas: c1: Arreglo de ciclos, c2: Arreglo de ciclos
* Salidas: Arreglo de ciclos que resulta de combinar los ciclos c1 y c2
*/
fun combinarCiclos(c1: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>, c2: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>): Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>{
    // Variables
    var minG: Int
    var dOLD1: Int
    var dOLD2: Int
    var dNEW1: Int
    var dNEW2: Int
    var dNEW3: Int
    var dNEW4: Int
    var a: Pair<Double, Double>
    var b: Pair<Double, Double>
    var d: Pair<Double, Double>
    var c: Pair<Double, Double>
    var g1: Int
    var g2: Int
    var ladosAgregarC1: Pair<Pair<Double, Double>, Pair<Double, Double>>
    var ladosAgregarC2: Pair<Pair<Double, Double>, Pair<Double, Double>>
    var ladosEliminarC1: Pair<Pair<Double, Double>, Pair<Double, Double>>
    var ladosEliminarC2: Pair<Pair<Double, Double>, Pair<Double, Double>>
    var ganancia: Int
    
    // Si alguno de los ciclos es vacio, se retorna el otro
    if (c1.size == 0){
        return c2
    } else if (c2.size == 0){
        return c1
    }

    // inicializamos
    minG = Int.MAX_VALUE
    dOLD1 = 0
    dOLD2 = 0
    dNEW1 = 0
    dNEW2 = 0
    dNEW3 = 0
    dNEW4 = 0
    g1 = 0
    g2 = 0
    ladosAgregarC1 = Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))
    ladosAgregarC2 = Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))
    ladosEliminarC1 = Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))
    ladosEliminarC2 = Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))

    for (i in 0 until c1.size) {
        a = c1[i].first
        b = c1[i].second

        dOLD1 = distancia2D(a, b)
        for (j in 0 until c2.size) {
            c = c2[j].first
            d = c2[j].second

            dOLD2 = distancia2D(c, d)
            dNEW1 = distancia2D(a, c)
            dNEW2 = distancia2D(b, d)
            dNEW3 = distancia2D(a, d)
            dNEW4 = distancia2D(b, c)

            g1 = distanciaGanada(dOLD1, dOLD2, dNEW1, dNEW2)
            g2 = distanciaGanada(dOLD1, dOLD2, dNEW3, dNEW4)
            ganancia = Math.min(g1, g2)
            if (ganancia < minG) {
                minG = ganancia

                if (g1 < g2) {
                    ladosAgregarC1 = Pair(a, c)
                    ladosAgregarC2 = Pair(d, b)

                } else {
                    ladosAgregarC1 = Pair(a, d)
                    ladosAgregarC2 = Pair(c, b)
                }

                ladosEliminarC1 = Pair(a, b)
                ladosEliminarC2 = Pair(c, d)
            }
        }
    }
    for (i in 0 until c1.size) {
            if (c1[i] == ladosEliminarC1) {
                c1[i] = ladosAgregarC1 // sustituimos el lado a eliminar por el lado a agregar
                break // ya eliminamos y agregamos el lado
            }
        }
    // analogamente, hacemos lo mismo con c2
    for (i in 0 until c2.size) {
        if (c2[i] == ladosEliminarC2) {
            c2[i] = ladosAgregarC2
            break // ya eliminamos y agregamos el lado
        }
    }

    var nuevoCiclo = Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>(c1.size + c2.size){Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))}
    var j: Int

    j = 0
    
    // llenamos nuevoCiclo
    for (i in 0 until nuevoCiclo.size) {
        if (i < c1.size) {
            nuevoCiclo[i] = c1[i]
        } else {
            nuevoCiclo[i] = c2[j]
            j = j + 1
        }
    }

    return nuevoCiclo
}

/**
* Funcion: distanciaTotal(ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>)
* Entradas: ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>
* Salidas: Distancia total del ciclo (Double)
*/
fun distanciaTotal(ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>): Int{
    var acc: Double = 0.0
    for (i in 0 until ciclo.size){
        acc += distancia2D(ciclo[i].first, ciclo[i].second)
    }
    return acc.toInt()
}

/**
* Funcion: divideAndConquerTSP(P: Array<Pair<Double, Double>>)
* Entradas: P: Array<Pair<Double, Double>>
* Salidas: Ciclo de distancia minima (Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>)
*/
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
        val (particionIzq, particionDer) = obtenerParticiones(P)
        // Resolver el problema para cada conjunto de puntos
        val cicloIzq = divideAndConquerTSP(particionIzq)
        val cicloDer = divideAndConquerTSP(particionDer)
        // Combinar las soluciones
        return combinarCiclos(cicloIzq, cicloDer)
    }
}

/**
* Funcion: divideAndConquerAndLocalSearchTSP(P: Array<Pair<Double, Double>>)
* Entradas: P: Array<Pair<Double, Double>>
* Salidas: Ciclo de distancia minima (Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>)
*/
// fun divideAndConquerAndLocalSearchTSP(P: Array<Pair<Double, Double>>): Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>{
//     val c = divideAndConquerTSP(P)
//     return // TODO: Implementar busqueda local
// }

/**
* main(args: Array<String>)
* Funcion principal del programa
* Entradas: args[0] = Nombre del archivo de entrada, args[1] = Nombre del archivo de salida
* Salidas: Archivo de salida con el ciclo de distancia minima y la distancia total
*/
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
        val ciudad = reader.readLine().trim().split(" ")
        // Las coordenadas de las ciudades vienen en el archivo de entrada con un espacio de separación
        // por lo que debemos eliminar los espacios en blanco al inicio y al final de cada coordenada
        // para poder convertirlas a reales
        val x = ciudad[1].trim().toDouble()
        val y = ciudad[2].trim().toDouble()
        ciudades[i] = Pair(x, y)
    }
    reader.close()

    val ciudadesEntrada = ciudades.copyOf()

    // Aplicamos el algoritmo de divide and conquer para obtener la solución
    val solucion = divideAndConquerTSP(ciudades)
    val distanciaRuta = distanciaTotal(solucion)

    // Escribimos la solución en un archivo de salida
    val archivoSalida = File(args[1])

    archivoSalida.writeText("NAME : ${nombre}\n")
    archivoSalida.appendText("COMMENT : Length ${distanciaRuta}\n")
    archivoSalida.appendText("TYPE : TOUR\n")
    archivoSalida.appendText("DIMENSION : ${solucion.size}\n")
    archivoSalida.appendText("TOUR_SECTION\n")

    // Imprimimos la solución en el stdout
    println("NAME : ${nombre}")
    println("COMMENT : Length ${distanciaRuta}")
    println("TYPE : TOUR")
    println("TOUR_SECTION")
    println("DIMENSION : ${solucion.size}")

    // Imprimimos la ruta
    for (i in 0 until solucion.size) {
        println("${solucion[i].first} -> ${solucion[i].second}")
    }

    for (i in 0 until solucion.size) {
        val par = solucion[i].first
        val indice = ciudadesEntrada.indexOf(par)
        val x = solucion[i].first.first
        val y = solucion[i].first.second
        archivoSalida.appendText("${indice+1} ${x} ${y}\n")
        if (i == solucion.size - 1) {
            val parf = solucion[i].second
            val indicef = ciudadesEntrada.indexOf(parf)
            val xf = solucion[i].second.first
            val yf = solucion[i].second.second
            archivoSalida.appendText("${indicef + 1} $xf $yf\n")
        }
    }
    println("La solucion es de tamaño ${solucion.size}")

    // Escribimos el EOF que indica el final del archivo e imprimimos el fin del tour en el stdout
    archivoSalida.appendText("EOF\n")
    println("Fin del tour.")
}
