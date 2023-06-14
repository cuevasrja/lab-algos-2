fun obtenerPuntoDeCorte(P: Array<Pair<Int, Int>>, eje: Int): Pair<Int, Int>{

}

fun obtenerPuntoDeCorteMitad(rectangulo: Array<Pair<Int, Int>>, eje: Int): Pair<Int, Int>{

}

fun aplicarCorte(eje: Int, puntoDeCorte: Pair<Int, Int>, rectangulo: Array<Pair<Int, Int>>): Pair<Int, Int>{
    
}

fun obtenerParticiones(P: Array<Pair<Int, Int>>): Array<Array<Pair<Int, Int>>>{
    
}

fun obtenterPuntosRectangulo(P: Array<Pair<Int, Int>>, rectangulo: Array<Pair<Int, Int>>): Array<Pair<Int, Int>>{
    
}

fun distancia(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Int{
    val x = p1.first - p2.first
    val y = p1.second - p2.second
    return Math.sqrt((x*x + y*y).toDouble()).toInt()
}

fun distanciaGanada(n1: Int, n2: Int, o1: Int, o2: Int): Int{
    return (n1 + n2) - (o1 + o2)
}

fun divideAndConquerAndLocalSearchTSP(P: Array<Pair<Int, Int>>): Array<Pair<Pair<Int, Int>, Pair<Int, Int>>>{
    
}

fun divideAndConquerTSP (P: Array<Pair<Int, Int>>): Array<Pair<Pair<Int, Int>, Pair<Int, Int>>>{

}


fun main(args: Array<String>) {
    
}