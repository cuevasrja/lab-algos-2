fun circuloAreaPerimetro(radio: Double): Pair<Double,Double> {
    return Pair(2*Math.PI*radio, 2*Math.PI*radio)
}

fun areaRectangulo(base: Int, altura:Int): Int  = base*altura

fun main() {
    val radio = 2.3
    val h = 2
    val b = 3
    val (area, perimetro) = circuloAreaPerimetro(radio)
    val rArea = areaRectangulo(b, h)
    println("Circunferencia area: $area")
    println("Circunferencia perimetro: $perimetro")
    println("Area rectangulo: $rArea")
}
