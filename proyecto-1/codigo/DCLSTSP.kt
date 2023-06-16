fun swap(P: Array<Pair<Int, Int>>, i: Int, j: Int): Unit {
    val temp = P[i]
    P[i] = P[j]
    P[j] = temp
}

fun partitionX(P: Array<Pair<Int, Int>>, l: Int, r: Int): Int {
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

fun quickSortX(P: Array<Pair<Int, Int>>, l: Int, r: Int): Unit {
    if (l < r) {
        val p = partitionX(P, l, r)
        quickSortX(P, l, p - 1)
        quickSortX(P, p + 1, r)
    }
}

fun partitionY(P: Array<Pair<Int, Int>>, l: Int, r: Int): Int {
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

fun quickSortY(P: Array<Pair<Int, Int>>, l: Int, r: Int): Unit {
    if (l < r) {
        val p = partitionY(P, l, r)
        quickSortY(P, l, p - 1)
        quickSortY(P, p + 1, r)
    }
}

fun ordenarCoordXIguales(P: Array<Pair<Int, Int>>): Unit {
    val n = P.size
    for (i in 0 until n - 1) {
        if (P[i].first == P[i + 1].first && obtenerCoordMayor(P[i], P[i + 1], 'X') == P[i]) {
            swap(P, i, i + 1)
        }
    }
}

fun ordenarCoordYIguales(P: Array<Pair<Int, Int>>): Unit {
    val n = P.size
    for (i in 0 until n - 1) {
        if (P[i].second == P[i + 1].second && obtenerCoordMayor(P[i], P[i + 1], 'Y') == P[i]) {
            swap(P, i, i + 1)
        }
    }
}

fun obtenerCoordMayor(p1: Pair<Int, Int>, p2: Pair<Int, Int>, ejeDeCorte: Char): Pair<Int, Int> {
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

fun obtenerPuntoDeCorte(P: Array<Pair<Int, Int>>, ejeDeCorte: Char): Pair<Int, Int> {
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

fun obtenerPuntoDeCorteMitad(rectangulo: Array<Pair<Int, Int>>, eje: Char): Pair<Int, Int> {
    val xMin = obtenerCoordMinX(rectangulo)
    val yMin = obtenerCoordMinY(rectangulo)
    if (eje == 'X') {
        return Pair(xMin + (obtenerCoordMaxX(rectangulo) - xMin)/2, yMin)
    }
    else {
        return Pair(xMin, yMin + (obtenerCoordMaxY(rectangulo) - yMin)/2)
    }
}

fun aplicarCorte(eje: Int, puntoDeCorte: Pair<Int, Int>, rectangulo: Array<Pair<Int, Int>>): Pair<Int, Int>{

}

fun obtenerParticiones(P: Array<Pair<Int, Int>>): Pair<Array<Int>, Array<Int>>{

}

fun obtenerCoordMaxX(P: Array<Pair<Int, Int>>): Int{
    return P.maxBy { it.first }!!.first
}

fun obtenerCoordMaxY(P: Array<Pair<Int, Int>>): Int{
    return P.maxBy { it.second }!!.second
}

fun obtenerCoordMinX(P: Array<Pair<Int, Int>>): Int{
    return P.minBy { it.first }!!.first
}

fun obtenerCoordMinY(P: Array<Pair<Int, Int>>): Int{
    return P.minBy { it.second }!!.second
}

fun crearRectangulo(P: Array<Pair<Int, Int>>): Array<Pair<Int, Int>>{
    val maxX = obtenerCoordMaxX(P)
    val maxY = obtenerCoordMaxY(P)
    val minX = obtenerCoordMinX(P)
    val minY = obtenerCoordMinY(P)
    return arrayOf(Pair(minX, minY), Pair(maxX, maxY), Pair(maxX, minY), Pair(minX, maxY))
}

fun obtenterPuntosRectangulo(P: Array<Pair<Int, Int>>, rectangulo: Array<Pair<Int, Int>>): Array<Pair<Int, Int>>{

}

fun distancia2D(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Int{
    val x = p1.first - p2.first
    val y = p1.second - p2.second
    return Math.sqrt((x*x + y*y).toDouble()).toInt()
}

fun distancia3D(p1: Triple<Int, Int, Int>, p2: Triple<Int, Int, Int>): Int{
    val x = p1.first - p2.first
    val y = p1.second - p2.second
    val z = p1.third - p2.third
    return Math.sqrt((x*x + y*y + z*z).toDouble()).toInt()
}

fun distanciaGanada(n1: Int, n2: Int, o1: Int, o2: Int): Int{
    return (n1 + n2) - (o1 + o2)
}

fun divideAndConquerAndLocalSearchTSP(P: Array<Pair<Int, Int>>): Array<Pair<Pair<Int, Int>, Pair<Int, Int>>>{
    return
}

fun combinarCiclos(c1: Array<Pair<Pair<Int, Int>, Pair<Int, Int>>>, c2: Array<Pair<Pair<Int, Int>, Pair<Int, Int>>>): Array<Pair<Pair<Int, Int>, Pair<Int, Int>>>{
    return
}

fun divideAndConquerTSP (P: Array<Pair<Int, Int>>): Array<Pair<Pair<Int, Int>, Pair<Int, Int>>>{
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
        return
    }
    else{
        val (pright, pleft) = obtenerParticiones(P)
        val c1 = divideAndConquerTSP(pright)
        val c2 = divideAndConquerTSP(pleft)
        return combinarCiclos(c1, c2)
    }
}

fun main(args: Array<String>) {

}
