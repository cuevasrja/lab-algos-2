// Laboratorio de la semana 8 de Algoritmos y Estructuras de Datos II (CI-2692).
// Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).

fun main(args: Array<String>) {
    // El primer argumento representa el tamaño del arreglo de prueba a crear
    val n = args[0].toInt()
    // Se crea un arreglo de tamaño n con pares (clave, valor) donde la clave es un entero aleatorio entre 0 y n/3 y el valor es la clave convertida a String.
    val A = Array<Pair<Int, String>>(n) { Pair(0, "") }
    for (i in 0 until n) {
        val clave = (0..n/3).random()
        A[i] = Pair(clave, clave.toString())
    }
    println("\u001b[34mSe creó un arreglo A de tamaño $n con pares (clave, valor).\u001b[0m")
    println("\u001b[34mLas claves son números aleatorios entre 0 y ${n}/3.\u001b[0m")
    println("----------------------------------------------------------------")
    println()

    // Probamos el diccionario basado en una tabla de hash con encadenamiento
    println("\u001b[36mPrueba del diccionario basado en una tabla de hash con encadenamiento:\u001b[0m")
    println("----------------------------------------------------------------")
    // Se crea un diccionario vacío, basado en una tabla de hash de encadenamiento.
    val dictChaining: DictionaryChaining = createDictionaryChaining()

    // Se insertan los elementos del arreglo A en el diccionario.
    for (i in 0 until n) {
        // Se busca si la clave ya existe en el diccionario.
        if (dictChaining.existe(A[i].first)) {
            // Si la clave ya existe, se elimina el par (clave, valor) del diccionario.
            dictChaining.eliminar(A[i].first)
        } else {
            // Si la clave no existe, se agrega el par (clave, valor) en el diccionario.
            dictChaining.agregar(A[i].first, A[i].second)
        }
    }
    println("Se insertaron y eliminaron los elementos del arreglo A en el diccionario.")
    println("El diccionario tiene ${dictChaining.obtenerNumElementos()} elementos.")
    println("El factor de carga del diccionario es ${dictChaining.obtenerFactorCarga()}.")
    println("\nEl diccionario luego de todas las inserciones y eliminaciones:")
    println("$dictChaining")
}