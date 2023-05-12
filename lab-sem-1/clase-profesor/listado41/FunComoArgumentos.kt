fun sumaEntero(a: Int, b: Int): Int {
    return a + b
}

fun aplicarMostrar(f :(Int, Int) -> Int, a: Int, b: Int){
    val r = f(a, b)
    println("Resultado: $r")
}

fun main() {
    var suma = ::sumaEntero
    val n = 5
    val m = 4
    println("Resultado: ${suma(n, m)}")
    aplicarMostrar(suma, n, m)
    aplicarMostrar(::sumaEntero, n, m)
}
