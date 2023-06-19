/**
* Nombre del archivo: DCLSTSP.kt
* Descripcion: Implementacion del algoritmo "Divide, Conquer, Local Search" para el problema del agente viajero
* Autores: Luis Miguel Isea 19-10175, Juan Cuevas 19-10056
*/

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import kotlin.math.abs
import kotlin.math.sqrt


/**
* Funcion: swap(P: Array<Pair<Double, Double>>, i: Int, j: Int)
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
* Funcion: swapEnCiclos(ciclo:  Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>, i: Int, j: Int)
* Entradas: ciclo, un arreglo de ciudades que realizan un ciclo
*           i, un entero que representa la posicion de un elemento del arreglo
*           j, un entero que representa la posicion de un elemento del arreglo
* Salidas: Unit
* Descripcion: Intercambia los elementos en las posiciones i y j del arreglo ciclo
*/
fun swapEnCiclos(ciclo:  Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>, i: Int, j: Int): Unit {
    val temp = ciclo[i]
    ciclo[i] = ciclo[j]
    ciclo[j] = temp
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
        // Rectangulo izquierdo = [Punto inferior izquierdo, Punto superior derecho, Punto inferior derecho, Punto superior izquierdo]
        rectanguloIzq[0] = Pair(xMin, yMin)
        rectanguloIzq[1] = Pair(puntoDeCorte.first, yMin)
        rectanguloIzq[2] = Pair(puntoDeCorte.first, yMax)
        rectanguloIzq[3] = Pair(xMin, yMax)
        // Rectangulo derecho = [Punto inferior izquierdo, Punto superior derecho, Punto inferior derecho, Punto superior izquierdo]
        rectanguloDer[0] = Pair(puntoDeCorte.first, yMin)
        rectanguloDer[1] = Pair(xMax, yMin)
        rectanguloDer[2] = Pair(xMax, yMax)
        rectanguloDer[3] = Pair(puntoDeCorte.first, yMax)
    }
    else {
        // Rectangulo izquierdo = [Punto inferior izquierdo, Punto superior derecho, Punto inferior derecho, Punto superior izquierdo]
        rectanguloIzq[0] = Pair(xMin, yMin)
        rectanguloIzq[1] = Pair(xMax, yMin)
        rectanguloIzq[2] = Pair(xMax, puntoDeCorte.second)
        rectanguloIzq[3] = Pair(xMin, puntoDeCorte.second)
        // Rectangulo derecho = [Punto inferior izquierdo, Punto superior derecho, Punto inferior derecho, Punto superior izquierdo]
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
    // Obtenemos las coordenadas del rectangulo que contiene a todos los puntos en P
    // Buscamos maximas y minimas coordenadas en X y Y
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
fun obtenterPuntosRectangulo(P: Array<Pair<Double, Double>>, rectangulo: Array<Pair<Double, Double>>, puntoDeCorte: Pair<Double,Double>, ejeDeCorte: Char): Array<Pair<Double, Double>>{
    var numElementos = 0
    for (punto in P){
        if (punto.first >= rectangulo[0].first && punto.first <= rectangulo[1].first && punto.second >= rectangulo[0].second && punto.second <= rectangulo[3].second){
            if (numElementos == 0 && punto == puntoDeCorte) {
                continue
            }
            if (ejeDeCorte == 'X' && punto.first == puntoDeCorte.first && numElementos == 0) {
                continue
            }
            else if (ejeDeCorte == 'Y' && punto.second == puntoDeCorte.second && numElementos == 0) {
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
            if (ejeDeCorte == 'X' && punto.first == puntoDeCorte.first && i == 0) {
                continue
            }
            else if (ejeDeCorte == 'Y' && punto.second == puntoDeCorte.second && i == 0) {
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
    // Distancia euclidiana entre dos puntos
    val x = p1.first - p2.first
    val y = p1.second - p2.second
    // Se redondea al entero mas cercano
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
    var particionIzq = obtenterPuntosRectangulo(P, rectangulosInternos.first, puntoDeCorte, ejeDeCorte)
    var particionDer = obtenterPuntosRectangulo(P, rectangulosInternos.second, puntoDeCorte, ejeDeCorte)
    if ((particionIzq.size == 0 && particionDer.size > 3) || (particionDer.size == 0 && particionIzq.size > 3)){
        if (ejeDeCorte == 'X') {
            ejeDeCorte = 'Y'
        } else {
            ejeDeCorte = 'X'
        }
        puntoDeCorte = obtenerPuntoDeCorte(P, ejeDeCorte)
        rectangulosInternos = aplicarCorte(ejeDeCorte, puntoDeCorte, rectangulo)
        particionIzq = obtenterPuntosRectangulo(P, rectangulosInternos.first, puntoDeCorte, ejeDeCorte)
        particionDer = obtenterPuntosRectangulo(P, rectangulosInternos.second, puntoDeCorte, ejeDeCorte)
        if ((particionIzq.size == 0 && particionDer.size > 3) || (particionDer.size == 0 && particionIzq.size > 3)){
            if (ejeDeCorte == 'X') {
                ejeDeCorte = 'Y'
            } else {
                ejeDeCorte = 'X'
            }
            puntoDeCorte = obtenerPuntoDeCorteMitad(P, ejeDeCorte)
            rectangulosInternos = aplicarCorte(ejeDeCorte, puntoDeCorte, rectangulo)
            particionIzq = obtenterPuntosRectangulo(P, rectangulosInternos.first, puntoDeCorte, ejeDeCorte)
            particionDer = obtenterPuntosRectangulo(P, rectangulosInternos.second, puntoDeCorte, ejeDeCorte)
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
* Funcion: ordenarCiclo(ciclos Array<Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>>): Array<Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>>
* Entradas: ciclo: Arreglo de ciudades que forman un ciclo, pero que puede estar desordenado
* Salidas: Unit
* Descipcion: Ordena el ciclo de ciudades, de tal forma que la ciudad i + 1 sea la ciudad con la que la ciudad i tiene un extremo en común
*/
fun ordenarCiclo(ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>): Unit {
    // Iteramos sobre el ciclo, buscando que la ciudad extremo de i sea igual a la ciudad inicial de i + 1
    // En caso de que no tengan extremos iguales, buscamos a partir de i + 1 ambos extremos, hasta conseguir que el extremo de i sea igual al inicial de i + 1
    for (i in 0 until ciclo.size - 1) {
        var j = i + 1
        while (j < ciclo.size) {
            if (ciclo[i].second == ciclo[j].first) {
                // Conseguimos que esta ciudad está en la posición correcta
                break
            }
            if (ciclo[i].second == ciclo[j].second) {
                // Volteamos ciclo[j]
                ciclo[j] = Pair(ciclo[j].second, ciclo[j].first)
                break
            }
            j++
        }
        // Si el j = i + 1, no haria falta intercambiarlo, pues ya tendrian extremos iguales.
        // Si j > i + 1 los intercambiamos.
        // En caso de intercambiarlos, lo intercambiamos con i + 1.
        if (j > i + 1) {
            swapEnCiclos(ciclo, i + 1, j)
        }
    }
}

/**
* Funcion: combinarCiclos(c1: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>, c2: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>)
* Entradas: c1: Arreglo que representa un ciclo, c2: Arreglo que representa un ciclo
* Salidas: Arreglo que resulta de combinar los ciclos c1 y c2 en un solo ciclo
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
    ladosAgregarC1 = Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))
    ladosAgregarC2 = Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))
    ladosEliminarC1 = Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))
    ladosEliminarC2 = Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))
    val ladosC1 = c1.copyOf()
    val ladosC2 = c2.copyOf()

    // Ordenamos los ciclos
    for (i in 0 until ladosC1.size) {
        // Ordenamos el ciclo 1
        a = ladosC1[i].first
        b = ladosC1[i].second

        // Calculamos la distancia original
        dOLD1 = distancia2D(a, b)
        for (j in 0 until ladosC2.size) {
            c = ladosC2[j].first
            d = ladosC2[j].second

            // Calculamos la distancia original
            dOLD2 = distancia2D(c, d)
            // Calculamos las nuevas distancias
            dNEW1 = distancia2D(a, c)
            dNEW2 = distancia2D(b, d)
            dNEW3 = distancia2D(a, d)
            dNEW4 = distancia2D(b, c)

            // Calculamos si hay ganancia
            g1 = distanciaGanada(dOLD1, dOLD2, dNEW1, dNEW2)
            g2 = distanciaGanada(dOLD1, dOLD2, dNEW3, dNEW4)

            // Agarramos la menor distancia
            ganancia = Math.min(g1, g2)

            // Si hay ganancia, la guardamos
            if (ganancia < minG) {
                minG = ganancia
                // Guardamos los lados a agregar y eliminar
                if (g1 < g2) { // si g1 es menor, entonces el lado a agregar es (a, c) y (b, d)
                    ladosAgregarC1 = Pair(a, c)
                    ladosAgregarC2 = Pair(d, b)
                }
                else { // en otro caso, el lado a agregar es (a, d) y (b, c)
                    ladosAgregarC1 = Pair(a, d)
                    ladosAgregarC2 = Pair(c, b)
                }
                ladosEliminarC1 = Pair(a, b)
                ladosEliminarC2 = Pair(c, d)
            }
        }
    }

    // Eliminamos el lado a eliminar de c1
    for (i in 0 until ladosC1.size) {
            if (ladosC1[i] == ladosEliminarC1) {
                ladosC1[i] = ladosAgregarC1 // sustituimos el lado a eliminar por el lado a agregar
                break // ya eliminamos y agregamos el lado
            }
        }
    // analogamente, hacemos lo mismo con c2
    for (i in 0 until ladosC2.size) {
        if (ladosC2[i] == ladosEliminarC2) {
            ladosC2[i] = ladosAgregarC2
            break // ya eliminamos y agregamos el lado
        }
    }

    // Ahora, creamos un nuevo ciclo, que es la union de los dos ciclos
    var nuevoCiclo = Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>(ladosC1.size + ladosC2.size){Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))}
    var j = 0

    // llenamos nuevoCiclo
    for (i in 0 until nuevoCiclo.size) {
        if (i < ladosC1.size) {
            nuevoCiclo[i] = ladosC1[i]
        } else {
            nuevoCiclo[i] = ladosC2[j]
            j++
        }
    }

    // Por último, ordenamos el ciclo, para que cada ciudad extremo de un lado sea la ciudad inicial o final de otro lado
    ordenarCiclo(nuevoCiclo)
    return nuevoCiclo
}

/**
* Funcion: distanciaTotal(ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>)
* Entradas: ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>
* Salidas: Distancia total del ciclo (Double)
*/
fun distanciaTotal(ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>): Int{
    // Calculamos la distancia total del ciclo
    var acc: Double = 0.0
    for (i in 0 until ciclo.size){
        // Sumamos la distancia de cada lado
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
* busquedaLocalCon2Opt(ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>)
* Entradas: ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>
* Salidas: Ciclo de distancia minima (Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>)
*/
fun busquedaLocalCon2Opt(ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>): Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>{
    // Convertimos el ciclo a una ruta
    val ruta: Array<Pair<Double, Double>> = cicloARuta(ciclo)
    // Declaramos la ruta nueva
    val rutaNueva = Array<Pair<Double, Double>>(ruta.size){Pair(0.0, 0.0)}
    // Tomamos la primera ciudad de la ruta
    rutaNueva[0] = ruta[0]
    val n = ruta.size
    var index = 1
    // Agregamos las ciudades a la ruta nueva
    for (i in 0 until n-1){
        // Buscamos la ciudad mas cercana a la ciudad actual
        var minDist = Int.MAX_VALUE
        // Agregamos la ciudad mas cercana a la ruta nueva
        for (j in 0 until n){
            // Calculamos la distancia entre la ciudad actual y la ciudad j
            val dist = distancia2D(ruta[i], ruta[j])
            // Si la distancia es menor a la distancia minima y la ciudad j no esta en la ruta nueva y no es la ciudad actual
            // entonces la ciudad j es la mas cercana a la ciudad actual
            if (dist < minDist && !(rutaNueva.contains(ruta[j])) && (i != j)){
                // Actualizamos la distancia minima
                minDist = dist
                // Agregamos la ciudad j a la ruta nueva
                rutaNueva[index] = ruta[j]
            }
        }
        // Actualizamos el indice
        index++
    }
    // Convertimos la ruta nueva a un ciclo
    return rutaACiclo(ruta)
}

/**
* Funcion: rutaARuta(ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>)
* Entradas: ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>
* Salidas: Ruta (Array<Pair<Double, Double>>)
*/
fun rutaACiclo(ruta: Array<Pair<Double, Double>>): Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>{
    // Convertimos la ruta a un ciclo
    var ciclo = Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>(ruta.size){Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))}
    // Agregamos las ciudades a la ruta nueva
    for (i in 0 until ruta.size-1){
        // Agregamos la ciudad mas cercana a la ruta nueva
        ciclo[i] = Pair(ruta[i], ruta[i+1])
    }
    // Agregamos la ultima ciudad a la ruta nueva, esta ciudad esta conectada con la primera ciudad
    ciclo[ruta.size-1] = Pair(ruta[ruta.size-1], ruta[0])
    return ciclo
}

/**
* Funcion: cicloARuta(ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>)
* Entradas: ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>
* Salidas: Ruta (Array<Pair<Double, Double>>)
* Descripcion: Convierte un ciclo en una ruta
* Ejemplo: ciclo = [(1, 2), (2, 3), (3, 4), (4, 1)], ruta = [1, 2, 3, 4]
*/
fun cicloARuta(ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>): Array<Pair<Double, Double>>{
    val n = ciclo.size
    // Declaramos la ruta
    var ruta = Array<Pair<Double, Double>>(n){Pair(0.0, 0.0)}
    for (i in 0 until n){
        // Agregamos la ciudad i a la ruta
        // La ciudad i es la primera ciudad del par i
        ruta[i] = ciclo[i].first
    }
    return ruta
}

/**
* Funcion: divideAndConquerAndLocalSearchTSP(P: Array<Pair<Double, Double>>)
* Entradas: P: Array<Pair<Double, Double>>
* Salidas: Ciclo de distancia minima (Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>)
*/
fun divideAndConquerAndLocalSearchTSP(C: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>): Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>{
    return busquedaLocalCon2Opt(C)
}

/**
* Funcion: ordenarDesdeLaPrimeraCiudad(ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>)
* Entradas: ciclo: Representa un ciclo que contenga todas las ciudades
* Salidas: Unit
* Descripcion: Ordena el ciclo desde la primera ciudad
*/
fun ordenarDesdeLaPrimeraCiudad(ciclo: Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>, primerCiudad: Pair<Double, Double>) {
    val cicloOrdenado = Array<Pair<Pair<Double, Double>, Pair<Double, Double>>>(ciclo.size){Pair(Pair(0.0, 0.0), Pair(0.0, 0.0))}
    // Obtenemos el indice de la primera ciudad especificada al inicio del programa dentro del ciclo
    val indicePrimeraCiudad = ciclo.indexOfFirst { it.first == primerCiudad }
    // Agregamos la primera ciudad al ciclo ordenado
    cicloOrdenado[0] = ciclo[indicePrimeraCiudad]
    // Agregamos las ciudades restantes al ciclo ordenado
    var index = 1
    for (i in indicePrimeraCiudad+1 until ciclo.size){
        cicloOrdenado[index] = ciclo[i]
        index++
    }
    for (i in 0 until indicePrimeraCiudad){
        cicloOrdenado[index] = ciclo[i]
        index++
    }
    // Actualizamos el ciclo
    for (i in 0 until ciclo.size){
        ciclo[i] = cicloOrdenado[i]
    }
}

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

    // Obtenemos la primera ciudad
    val primerCiudad = ciudades[0]

    // Aplicamos el algoritmo de divide and conquer para obtener la solución
    val solucion = divideAndConquerTSP(ciudades)
    val distanciaRuta = distanciaTotal(solucion)

    // Ordenamos la solución desde la primera ciudad
    ordenarDesdeLaPrimeraCiudad(solucion, primerCiudad)

    // Aplicamos la busqueda local con 2-opt para mejorar la solución
    val solucionMejorada = busquedaLocalCon2Opt(solucion)
    val distanciaRutaMejorada = distanciaTotal(solucionMejorada)

    // Ordenamos la solución desde la primera ciudad
    ordenarDesdeLaPrimeraCiudad(solucionMejorada, primerCiudad)

    // Imprimimos la solución en el stdout
    println("NAME : ${nombre}")
    println("Solucion inicial: ${distanciaRuta}")
    println("Solucion mejorada: ${distanciaRutaMejorada}")

    // Escribimos la solución en un archivo de salida
    val archivoSalida = File(args[1])

    archivoSalida.writeText("NAME : ${nombre}\n")
    archivoSalida.appendText("COMMENT : Length ${distanciaRuta}\n")
    archivoSalida.appendText("DIMENSION : ${solucion.size}\n")
    archivoSalida.appendText("TOUR_SECTION\n")

    for (i in 0 until solucion.size) {
        val par = solucion[i].first
        val indice = ciudadesEntrada.indexOf(par)
        archivoSalida.appendText("${indice+1}\n")
    }
    archivoSalida.appendText("-1\n")

    // Escribimos el EOF que indica el final del archivo e imprimimos el fin del tour en el stdout
    archivoSalida.appendText("EOF\n")
    println("Fin del tour.")
}
