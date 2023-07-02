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
    // Contamos cuántas veces aparece cada clave en el arreglo A.
    val contador = Array<Int>(n/3 + 1) { 0 }
    for (i in 0 until n) {
        contador[A[i].first]++
    }

    // Imprimimos las claves que aparezcan un número impar de veces.
    println("\u001b[34mSe imprimen las claves que aparezcan un número impar de veces.\u001b[0m")
    println("----------------------------------------------------------------")
    var j = 1
    for (i in 0 until n/3 + 1) {
        if (contador[i] % 2 == 1) {
            println("${j}. La clave $i aparece ${contador[i]} veces.")
            j++
        }
    }
    println()

    // Probamos el diccionario basado en una tabla de hash con encadenamiento
    println("\u001b[36mPrueba del diccionario basado en una tabla de hash con encadenamiento:\u001b[0m")
    println("----------------------------------------------------------------")
    // Se crea un diccionario vacío, basado en una tabla de hash de encadenamiento.
    val dictChaining: HashTableChaining = createDictionaryChaining()

    // Empezamos a medir el tiempo de ejecución de las inserciones y eliminaciones.
    val startTime = System.nanoTime()
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
    // Se termina de medir el tiempo de ejecución de las inserciones y eliminaciones.
    val endTime = System.nanoTime()
    // Se calcula el tiempo de ejecución de las inserciones y eliminaciones.
    val tiempoDictChaining = endTime - startTime

    println("\u001b[32mSe insertaron y eliminaron exitosamente los elementos del arreglo A en el diccionario.\u001b[0m")
    println("El tiempo de ejecución de las inserciones y eliminaciones fue de: \u001b[33m${tiempoDictChaining/1000000000.0} segundos.\u001b[0m")
    println("El diccionario tiene ${dictChaining.obtenerNumElementos()} elementos.")
    println("El número de claves conocidas por el diccionario es ${dictChaining.obtenerNumClavesConocidas()}.")
    println("El factor de carga del diccionario es ${dictChaining.obtenerFactorCarga()}.")
    println("\nEl diccionario luego de todas las inserciones y eliminaciones:")
    println("$dictChaining")
}
