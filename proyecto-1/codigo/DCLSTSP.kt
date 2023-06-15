fun obtenerPuntoDeCorte(P: Array<Pair<Int, Int>>, eje: Int): Pair<Int, Int>{
    val n = P.size
    val pos = Math.ceil(n/2.0).toInt() - 1
    
}

fun obtenerPuntoDeCorteMitad(rectangulo: Array<Pair<Int, Int>>, eje: Int): Pair<Int, Int>{

}

fun aplicarCorte(eje: Int, puntoDeCorte: Pair<Int, Int>, rectangulo: Array<Pair<Int, Int>>): Pair<Int, Int>{
    
}

fun obtenerParticiones(P: Array<Pair<Int, Int>>): Pair<Array<Int>, Array<Int>>{
    
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